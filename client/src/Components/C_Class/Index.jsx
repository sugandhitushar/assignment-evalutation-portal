import React, { Component } from 'react';
import { Row, Col, Button } from 'react-bootstrap';
import {connect} from "react-redux"
import {Form} from 'react-bootstrap';
import axios from "axios"
let cname=""
let decodedToken=""
let arr=[]

class Demo extends Component {
   constructor()
   {
       super()
       this.state={
           cname:"FyBsc"
       }
       this.handleClick=this.handleClick.bind(this)
       this.handleChange=this.handleChange.bind(this)
   }
   handleChange(e)
   {
       this.setState({
           cname:e.target.value
       })
   }
   parseJwt(token) {
    if (!token) { return; }
    const base64Url = token.toString().split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
}
    componentDidMount()
    {
        this.props.onbchange();  
    }
    componentWillUnmount()
    {
        console.log("willl mount token is=",this.props.token,"hiii")
        decodedToken=this.parseJwt(this.props.token)
           var dateNow = new Date();
            decodedToken.exp*=1000;
            if(decodedToken.exp > dateNow.getTime())
           {console.log("before api")
            const instance1 = axios.create({baseURL: 'http://localhost:8080',headers:{'Authorization':'Bearer '+this.props.token}}) 
            instance1.get("/api/v1/teachers")
            .then((res)=>{
                for(var i in res.data["data"])
                {
                  arr.push({id:res.data["data"][i]["id"],firstname:res.data["data"][i]["firstName"],lastname:res.data["data"][i]["lastName"]})
                     
                     
                }
                for(var j in arr)
                {
                    console.log("array of teacher is :",arr[j])
                }
                this.props.ongetTeacher()
                
            });
    
            
           }
    }
    handleClick(e)
    {
        cname=this.state.cname
        this.props.onstore()
        this.props.onnext();
        this.props.history.push("/CreateDivision")
    }
    render() { 
        return ( 
            <Row style={{margin:0,padding:0,width:"100%",height:"58vh",backgroundColor:"#B9D3FF"}}>
                <Form style={{backgroundColor:"white",width:"30%",padding:30,border:"1px solid #9FC3FD",marginLeft:"70vh",marginTop:20,marginBottom:10}}>
               <Form.Row>
                <Form.Group as={Col} controlId="validateClass" >
                            <Form.Label >Class Name</Form.Label>
                            <Form.Control as="select" style={{width:"40%"}}
                                name="cname"
                                onChange={this.handleChange}
                            >
                                <option>FyBsc</option>
                                <option>SyBsc</option>
                                <option>TyBsc</option>
                                <option>FyMsc</option>
                                <option>SyMsc</option>
                            </Form.Control>
                           
                            </Form.Group>
                            </Form.Row> 
                <Form.Row>
                <Form.Group as={Col}>
                    <br></br>
                <Button style={{marginLeft:"40vh"}} onClick={this.handleClick}>Next>>></Button>
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
        onbchange:()=>dispatch({type:'bchange',val:"c"}),
        onnext:()=>dispatch({type:'bchange',val:"d"}),
        onstore:()=>dispatch({type:'store_class',val:cname}),
        ongetTeacher:()=>dispatch({type:"get_teacher",val:arr})
    }
  }
const mapStateToProps=(state)=>{
    return{
        token:state.token

    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Demo)