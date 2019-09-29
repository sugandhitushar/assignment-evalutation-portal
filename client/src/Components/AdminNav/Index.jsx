import React, { Component } from 'react';
import { Navbar, NavItem, Nav,NavDropdown, Dropdown,Tab,Tabs, Row, Col } from 'react-bootstrap';
import {Link,NavLink} from "react-router-dom"
import "./AdminNav.css"
import CreateClass from "../CreateClass/Index"
class AdminNav extends Component {
   
  render() { 
        return ( 
          <Row style={{padding:0,margin:0,height:"75vh"}}>
            <Col xl="11"  style={{padding:0,margin:0}}>
          <Tabs justify className="myClass" defaultActiveKey="profile"  >
          <Tab eventKey="home" title="View User" style={{flex: 1,
  textAlign:"center"}}>
            hiii
          </Tab>
          <Tab eventKey="profile" title="Create Class" >
            <CreateClass/>
          </Tab>
        </Tabs>
        </Col>
        <Col xl="1" style={{padding:0,margin:0}}>
        <Dropdown drop="left" >
  <Dropdown.Toggle variant="success" id="dropdown-basic">
    Dropdown
  </Dropdown.Toggle>

  <Dropdown.Menu>
    <Dropdown.Item href="#/action-1">Change Password</Dropdown.Item>
    <Dropdown.Item href="#/action-2">Logout</Dropdown.Item>
  </Dropdown.Menu>
</Dropdown>
        </Col>
        </Row>
             );
    }
}
 
export default AdminNav;