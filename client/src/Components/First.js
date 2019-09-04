import React, { Component } from 'react';
import {connect} from 'react-redux';
import {Container,Row,Col } from 'react-bootstrap';
import Login from './Login.js'
import Admin from './Admin.js'
class First extends Component {
    render() {
        if(this.props.logged_in==='false')
        { 
            return ( 
                <Container fluid="true">
                    <Row style={{backgroundColor:"pink",height:"10vh"}}>
                        <Col xs="1" style={{backgroundColor:"aqua"}}><h1>logo</h1></Col>
                        <Col xs="10"><center><h1><i>PVG's College Of Science</i></h1></center></Col>
                    </Row>
                    <Row style={{height:"80vh"}}>
                        <Col style={{backgroundColor:"yellow"}}>Features</Col>
                        <Col style={{backgroundColor:"orange"}}><Login/></Col>
                    </Row>
                    <Row style={{backgroundColor:"aqua",height:"10vh"}}>
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