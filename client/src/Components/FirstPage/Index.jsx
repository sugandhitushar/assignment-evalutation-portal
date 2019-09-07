import React, { Component } from 'react';
import {connect} from 'react-redux';
import {Container,Row,Col } from 'react-bootstrap';
import Login from '../Login/Index.jsx'
import Admin from '../Admin.js'
import Features from '../Features/Index.jsx'
import FirstPage from './FirstPage.css'
class First extends Component {
    render() {
        if(this.props.logged_in==='false')
        { 
            return ( 
                <Container  fluid="true">
                    <Row id="row1" >
                        <Col  xs="1"><h1><img id="image" src={ require('../logo1.JPG') }/></h1></Col>
                        <Col xs="10"><h6>Pune Vidhyarthi Griha's</h6>
                        <h3 style={{color:"#0770DF"}}>College Of Science</h3>
                        <h6 style={{color:"gray"}}>Affiliated to Savitribai Phule Pune University | Recognized by Govt. of Maharashtra</h6></Col>
                    </Row>
                    <Row id="row2">
                        <Col id="r2c1" xl="8" ><Features/></Col>
                        <Col  id="r2c2" xl="4"><Login/></Col>
                    </Row>
                    <Row id="row3">
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