import React, { Component } from 'react';
import { Row, Col, Button } from 'react-bootstrap';
import {connect} from "react-redux"
import {Form} from 'react-bootstrap';
let division,capacity,start_year,end_year,class_teacher
class Demo extends Component {
   constructor()
   {
       super()
       this.state={
           fields:{division:"A"},
            errors:{capacity:null,start_year:null,end_year:null,class_teacher:null},
       }
       this.handleClick=this.handleClick.bind(this)
       this.handleChange=this.handleChange.bind(this)
    }
   
   handleChange(e)
   {
    let fields=this.state.fields;
    fields[e.target.name]=e.target.value;
    this.setState(
        {
        fields
        
        }
    );
   }
    componentDidMount()
    {
        this.props.onbchange();
    }
    handleClick(e)
    {
        console.log("fields",this.state.fields)
        if(this.validateForm())
        {
            division=this.state.fields.division
            capacity=this.state.fields.capacity
            start_year=this.state.fields.start_year
            end_year=this.state.fields.end_year
            class_teacher=this.state.fields.class_teacher
            this.props.onstore();
            this.props.onnext();
            this.props.history.push("/CreateSubjects")
        }
        else
        {
            e.preventDefault()
        }
    }
    validateForm()
    {
       let valid=true
        let fields=this.state.fields
        let errors={}
        if(!fields['capacity'])
        {
            valid=false
            errors['capacity']=true
        }
        if (typeof fields["capacity"] !== "undefined")
        {
            if(!(fields['capacity']>0))
            {
                valid=false
                errors['capacity']=true
            }
        }
        if(!fields['start_year'])
        {
            valid=false
            errors['start_year']=true
        }
        if (typeof fields["start_year"] !== "undefined")
            {
                var syear = /^\d+$/;
                if(!fields['start_year'].match(syear))
                {
                    valid=false
                    errors['start_year']=true
                }
            }
            if(!fields['end_year'])
            {
                valid=false
                errors['end_year']=true
            }
            if (typeof fields["end_year"] !== "undefined")
                {
                    var eyear = /^\d+$/;
                    if(!fields['end_year'].match(eyear))
                    {
                        valid=false
                        errors['end_year']=true
                    }
                }
            if(!fields['class_teacher'])
            {
                valid=false
                errors['class_teacher']=true
            }
        this.setState({errors:errors})
        console.log("errors",this.state.errors)
        return valid;

    }
    render() { 
        return ( 
            <Row style={{margin:0,padding:0,width:"100%",backgroundColor:"#B9D3FF",height:"58vh"}}>
                <Form style={{backgroundColor:"white",padding:30,width:"80%",border:"1px solid  #9FC3FD",marginLeft:100,marginTop:20,marginBottom:10}}>
               <Form.Row>
                <Form.Group as={Col}  >
                            <Form.Label >Division</Form.Label>
                            <Form.Control as="select" 
                                name="division"
                                onChange={this.handleChange}
                            >
                                <option>A</option>
                            </Form.Control>
                </Form.Group>
                <Form.Group as={Col}>
                    <Form.Label>Capacity</Form.Label>
                    <Form.Control 
                    name="capacity"
                    onChange={this.handleChange}
                    type="number"
                    isValid={this.state.errors.capacity===null?(null):(!this.state.errors.capacity)}
                    isInvalid={this.state.errors.capacity}>
                    </Form.Control>
                </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col}>
                        <Form.Label>Start Year</Form.Label>
                        <Form.Control 
                    name="start_year"
                    onChange={this.handleChange}
                    type="text"
                    isValid={this.state.errors.start_year===null?(null):(!this.state.errors.start_year)}
                    isInvalid={this.state.errors.start_year}
                    >

                    </Form.Control>
                    </Form.Group>
                    <Form.Group as={Col}>
                        <Form.Label>End Year</Form.Label>
                        <Form.Control
                        name="end_year"
                        onChange={this.handleChange}
                        type="text"
                        isValid={this.state.errors.end_year===null?(null):(!this.state.errors.end_year)}
                        isInvalid={this.state.errors.end_year}
                        >
                            
                        </Form.Control>
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col}>
                    <Form.Label>Class Teacher</Form.Label>
                    <Form.Control as="select" 
                                name="class_teacher"
                                onChange={this.handleChange}
                                isValid={this.state.errors.class_teacher===null?(null):(!this.state.errors.class_teacher)}
                            isInvalid={this.state.errors.class_teacher}
                            >
                                <option selected disabled>---Select---</option>
                                <option>Teacher2</option>
                                <option>Teacher3</option>
                            </Form.Control>    
                    </Form.Group> 
                    <Form.Group as={Col}>
                            <br></br><Button onClick={this.handleClick}>Next>>></Button>
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
        onbchange:()=>dispatch({type:'bchange',val:"d"}),
        onnext:()=>dispatch({type:'bchange',val:"s"}),
        onstore:()=>dispatch({type:'store_div',division:division,start_year:start_year,end_year:end_year,capacity:capacity,class_teacher:class_teacher})
    }
  }
 
export default connect(null,mapDispatchToProps)(Demo)