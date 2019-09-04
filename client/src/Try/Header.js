import React from 'react';
import { Container, Col, Row, ButtonGroup,DropdownButton,Dropdown } from 'react-bootstrap';
class Header extends React.Component {
    constructor(props)
    {
        super(props);
        this.handle=this.handle.bind(this);
        this.handleLogout=this.handleLogout.bind(this);
    }
    handle()
    {
        console.log('The link was clicked.');
    }
    handleLogout()
    {
        this.props.islogin();
    }
    render() { 
        return ( 
            <Container fluid="true">
            <Row>
            <Col xl={1} style={{backgroundColor: 'orange',border:"1px solid red"}}><h6 style={{width:"100%"}}>logo</h6></Col>
            <Col xs xl={10} lg md sm style={{border:"1px solid blue",backgroundColor:"aqua"}}><h2 style={{textAlign:"center"}}>PVG's College of Science</h2></Col>
            <Col xl={0} style={{border:"1px solid green"}}>
            <DropdownButton  variant="danger"  alignRight title="Admin" size="lg" style={{width:"100%",marginLeft:"0px",padding:"0px"}} >
              <Dropdown.Item as="button"  onClick={this.handle}>Change Password</Dropdown.Item>
              <Dropdown.Item as="button" onClick={this.handleLogout} >Logout</Dropdown.Item>
            </DropdownButton>
            </Col>
            </Row>
            </Container> 
        );
    }
}
 
export default Header;