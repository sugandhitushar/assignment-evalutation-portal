import React, { Component } from 'react';
import {Breadcrumb, Row, Container, Col} from 'react-bootstrap'
import {BrowserRouter as Router,Switch,Route} from "react-router-dom"
import Login from '../Login/Index'

import AdminNav from "../AdminNav/Index"
class Admin extends Component {
    render() { 
        return ( 
         <Col xl="12" style={{padding:0,marginLeft:0,marginRight:0}}>
         <AdminNav/>  
         </Col>

         );
    }
}
 
export default Admin;