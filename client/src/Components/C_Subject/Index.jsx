import React, { Component } from 'react';
import { Row, Col, Button } from 'react-bootstrap';
import {connect} from "react-redux"
import {Form} from 'react-bootstrap';
import axios from 'axios'

class Demo extends Component {
   constructor()
   {
       super()
       this.state=({
           subjects:[{sub:"",teacher:""}],
           errors:[{sub:null,teacher:null}]
       })
       this.handleClick=this.handleClick.bind(this)
       this.handleNew=this.handleNew.bind(this)
       this.handleChange=this.handleChange.bind(this)
       //this.handleRemove=this.handleRemove.bind(this)
   }
   
   handleChange(e)
   {
        if(["sub form-control","teacher form-control","sub form-control is-invalid","sub form-control is-valid","teacher form-control is-invalid","teacher form-control is-valid"].includes(e.target.className))
        {
            let subjects=[...this.state.subjects]
            if(e.target.className==="teacher form-control"||e.target.className==="teacher form-control is-invalid"||e.target.className==="teacher form-control is-valid")
            {
                subjects[e.target.dataset.id]["teacher"]=e.target.value
                this.setState({
                    subjects
                })    
            }
            if(e.target.className==="sub form-control"||e.target.className==="sub form-control is-invalid"||e.target.className==="sub form-control is-valid")
            {
                subjects[e.target.dataset.id]["sub"]=e.target.value
                this.setState({
                    subjects
                })
            }
        }
        else
        {
            this.setState({
                 [e.target.name]:e.target.value
            })
        }
       
   }
    componentDidMount()
    {
        this.props.onbchange();
    }
    handleNew(e)
    {
           e.preventDefault()
           this.setState((prevState)=>({subjects:[...prevState.subjects,{sub:"",teacher:""}]}));
           this.setState((prevState)=>({errors:[...prevState.errors,{sub:null,teacher:null}]}));
           
    }
    handleRemove()
    {
       // e.preventDefault()
        
        let s= this.state.subjects
        s.splice(-1,1)
        for(var i in s)
        {
            console.log("---",s[i])
        }
        this.setState({
            subjects:s
        })
        for(var i in this.state.subjects)
        {
            console.log("****",this.state.subjects[i])
        }
       

    }
    validateform11()
    {
        let valid=true
        let errors=[...this.state.errors]
        for(var i in this.state.subjects)
        {
            errors[i]["sub"]=false
            errors[i]["teacher"]=false
            if(!this.state.subjects[i].sub)
            {
                valid=false
                errors[i]["sub"]=true
            }
            if(!this.state.subjects[i].teacher)
            {
                valid=false
                errors[i]["teacher"]=true
            }
        }        
         this.setState({
                errors
            })
            return valid
    }
    handleClick(e)
    {
        e.preventDefault()
        
       if(this.validateform11())
       {
           alert("Success")
            /*
                const instance = axios.create({baseURL: 'http://localhost:8080',headers:{'Authorization':' Bearer '+this.props.token}})
                instance.post("/createClass",{cname:this.props.cname,division:this.props.division,capacity:this.props.capacity
                start_year:this.props.start_year,end_year:this.props.end_year,class_teacher:this.props.class_teacher,subjects:this.state.subjects})
                .then((res)=>
                {
                    console.log("create class response",res);
                })
                .catch((error)=>{console.log(error)});
            */

           this.props.onnext();
           this.props.history.push("/CreateClass")
       }
        
    }
    render() { 
        return ( 
            <Row style={{margin:0,padding:0,width:"100%",height:"58vh",backgroundColor:"#B9D3FF",overflow:"auto"}}>
                <Form id="f" style={{backgroundColor:"white",width:"100%",padding:30,border:"1px solid #9FC3FD",marginTop:20,marginBottom:10}}>             
                {
                    this.state.subjects.map((val,idx)=>{
                        let subjectsId='sub'+idx,teacherId='teacher'+idx
                        return(
                            <Form.Row>
                               <Form.Group as={Col}  >
                            <Form.Label >Subject Name</Form.Label>
                            <Form.Control
                                required
                                type="text"
                                name={subjectsId}
                                data-id={idx}
                                className="sub"  
                                onChange={this.handleChange}
                                isValid={this.state.errors[idx].sub===null?(null):(!this.state.errors[idx].sub)}
                                isInvalid={this.state.errors[idx].sub}
                            >
                            </Form.Control>
                           
                </Form.Group>
                <Form.Group as={Col} >
                            <Form.Label >Teacher Name</Form.Label>
                            <Form.Control as="select"
                                name={teacherId}
                                data-id={idx}
                                className="teacher"
                                onChange={this.handleChange}
                                isValid={this.state.errors[idx].teacher===null?(null):(!this.state.errors[idx].teacher)}
                                isInvalid={this.state.errors[idx].teacher}
                            >
                                <option disabled selected>--select--</option>
                                <option>T2</option>
                                <option>T3</option>
                                
                            </Form.Control>
                           
                </Form.Group>
                
                            </Form.Row>
                        )
                    })
                }
                 <Form.Row>
                    <Form.Group as={Col}>
                        <br></br>
                        <Button onClick={this.handleNew}>
                            Add+
                        </Button>
                    </Form.Group>
                    <Form.Group as={Col}>
                    <br></br>
                    <Button  onClick={() => this.handleRemove()}>Remove</Button>
                </Form.Group> 
                    <Form.Group as={Col}>
                    <br></br>
                <Button  onClick={this.handleClick}>Save>>></Button>
                </Form.Group>
                </Form.Row>
     
                </Form>
            </Row>
         );
    }
}
const mapDispatchToProps=(dispatch)=>{
    return{
        //Action Creators
        onbchange:()=>dispatch({type:'bchange',val:"s"}),
        onnext:()=>dispatch({type:'bchange',val:"c"}),
        //onnext:()=>dispatch({type:'bchange',val:"d"})
    }
  }

const mapStateToProps=(state)=>
{
    return{
    cname:state.cname,
    division:state.division,
    capacity:state.capacity,
    start_year:state.start_year,
    end_year:state.end_year,
    class_teacher:state.class_teacher,
    token:state.token
    }
}
 
export default connect(mapStateToProps,mapDispatchToProps)(Demo)