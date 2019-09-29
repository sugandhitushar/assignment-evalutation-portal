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
                                name="class"
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
        onnext:()=>dispatch({type:'bchange',val:"d"})
    }
  }
 
export default connect(null,mapDispatchToProps)(Demo)