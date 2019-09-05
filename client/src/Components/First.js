import React, { Component } from 'react';
import {connect} from 'react-redux';
import {Container,Row,Col } from 'react-bootstrap';
import Login from './Login.js'
import Admin from './Admin.js'
import Features from './Features.js'
class First extends Component {
    render() {
        if(this.props.logged_in==='false')
        { 
            return ( 
                <Container fluid="true">
                    <Row style={{height:"14vh"}}>
                        <Col xs="1" style={{}}><h1><img src={ require('./logo1.JPG') } style={{height:"90px"}}/></h1></Col>
                        <Col xs="10"><h6>Pune Vidhyarthi Griha's</h6>
                        <h3 style={{color:"#0770DF"}}>College Of Science</h3>
                        <h6 style={{color:"gray"}}>Affiliated to Savitribai Phule Pune University | Recognized by Govt. of Maharashtra</h6></Col>
                    </Row>
                    <Row style={{height:"81vh"}}>
                        <Col xl="8" style={{backgroundColor:"#9BC4F0"}}><Features/></Col>
                        <Col xl="4" style={{backgroundColor:"#9BC4F0"}}><Login/></Col>
                    </Row>
                    <Row style={{backgroundColor:"",height:"5vh"}}>
                        Footer
                    </Row>
                </Container>
            );
        }
        else if(this.props.utype==="admin")
        {
            return(
                <div>
                    <Admin/>
                </div> 
            );
        }
    }
}
 
const mapStateToProps=(state)=>{
    return{
        logged_in:state.logged_in,
        username:state.username,
        utype:state.utype
    }
}

export default connect(mapStateToProps)(First);