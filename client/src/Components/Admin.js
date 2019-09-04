import React, { Component } from 'react';
import {connect} from 'react-redux';
import Header from './Header.js';
import CreateUser from './CreateUser.js'
import { Container, Row, Col } from 'react-bootstrap';
class Admin extends Component {
    state = {  }
    render() { 
        return (
            <Container fluid="true">
                <Row style={{height:"10vh"}}>
                    <Header/>
                </Row>
                <Row style={{height:"80vh"}}>
                    <Col><CreateUser/></Col>
                    <Col></Col>
                </Row>
            </Container>
          );
    }
}

const mapStateToProps=(state)=>{
    return{
        username:state.username,
    }
}

export default connect(mapStateToProps)(Admin);