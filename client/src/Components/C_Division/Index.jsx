import React, { Component } from 'react';
import { Row, Col, Button } from 'react-bootstrap';
import {connect} from "react-redux"
import {Form} from 'react-bootstrap';
class Demo extends Component {
   constructor()
   {
       super()
       this.handleClick=this.handleClick.bind(this)
   }
    componentDidMount()
    {
        this.props.onbchange();
    }
    handleClick(e)
    {
        this.props.onnext();
        this.props.history.push("/CreateSubject")
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
                    type="text">

                    </Form.Control>
                </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col}>
                        <Form.Label>Start Year</Form.Label>
                        <Form.Control
                        name="start_year"
                        onChange={this.handleChange}
                        type="text">

                        </Form.Control>
                    </Form.Group>
                    <Form.Group as={Col}>
                        <Form.Label>End Year</Form.Label>
                        <Form.Control
                        name="end_year"
                        onChange={this.handleChange}
                        type="text">
                            
                        </Form.Control>
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col}>
                    <Form.Label>Class Teacher</Form.Label>
                    <Form.Control as="select" 
                                name="class_teacher"
                                onChange={this.handleChange}
                            >
                                <option>Teacher1</option>
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
        onnext:()=>dispatch({type:'bchange',val:"s"})
    }
  }
 
export default connect(null,mapDispatchToProps)(Demo)