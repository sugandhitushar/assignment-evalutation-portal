import React, { Component } from 'react';
import {connect} from 'react-redux';
import { Container, Row,Col,DropdownButton,Dropdown } from 'react-bootstrap';
class Header extends Component {
    state = {  }
    render() { 
        return ( 
            <Container fluid="true">
                <Row>
                    <Col xs="1" style={{border:"1px solid red"}}>Logo</Col>
                    <Col xs="10" style={{border:"1px solid blue"}}><center><h1><i>PVG's College Of Science</i></h1></center></Col>
                    <Col xl="1" style={{border:"1px solid red"}}>
                        <DropdownButton  variant="danger" size="lg" alignRight title={this.props.username}>
                        <Dropdown.Item as="button">Change Password</Dropdown.Item>
                        <Dropdown.Item as="button" onClick={this.props.login}>Logout</Dropdown.Item>
                        </DropdownButton>
                    </Col>
                </Row>
            </Container>
         );
    }
}

const mapDispatchToProps=(dispatch)=>{
    return{
        login:()=>dispatch({type:'Login',val:"false"}), //Action Creators
    }
}


const mapStateToProps=(state)=>{
    return{
        username:state.username,
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Header);