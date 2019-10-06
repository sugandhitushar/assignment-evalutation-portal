import React, { Component } from 'react';
import {connect} from "react-redux"
import { Button,Row,Form,Col } from 'react-bootstrap';
class SelectDivision extends Component {
    constructor()
    {
        super()
        this.handleNext=this.handleNext.bind(this)
    }
    componentDidMount()
    {
        this.props.onbchange();
        
    }
    handleNext(e)
    {
        this.props.onnext();
        this.props.history.push("/SelectStudents")
    }
    render() { 
        return ( 
            <Row style={{margin:0,padding:0,width:"100%",backgroundColor:"#B9D3FF",height:"58vh"}}>
            <Form style={{backgroundColor:"white",padding:30,width:"80%",border:"1px solid  #9FC3FD",marginLeft:100,marginTop:20,marginBottom:10}}>
            <Form.Group as={Col}>
                Select Division
                            <br></br><Button onClick={this.handleNext}>Next>>></Button>
                    </Form.Group>
            </Form>
            </Row> );
    }
}
const mapDispatchToProps=(dispatch)=>{
    return{
        //Action Creators
        onbchange:()=>dispatch({type:'tbreadchange',val:"d"}),
        onnext:()=>dispatch({type:'tbreadchange',val:"s"}),
        
    }
  }
  const mapStateToProps=(state)=>
  {
      return{
            token:state.token
      }
  }
  
 
export default connect(mapStateToProps,mapDispatchToProps)(SelectDivision);