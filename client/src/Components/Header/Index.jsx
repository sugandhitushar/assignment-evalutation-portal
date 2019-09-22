import React, { Component } from 'react'
import "../Header/Header.css"
import logo from '../../Resources/logo.png';
import {Navbar} from 'react-bootstrap'
class Header extends Component {
    render() { 
        return (        
                <Navbar id="nav" variant="light">
                  <Navbar.Brand>
                    <img
                      alt=""
                      src={logo}
                      width="100%"
                      className="d-inline-block align-top"
                    />
                  </Navbar.Brand>
                </Navbar>
         );
    }
}
 
export default Header;