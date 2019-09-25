import React, { Component } from 'react';
import {Breadcrumb, Row} from "react-bootstrap";
import {BrowserRouter as Router,Switch,Route} from "react-router-dom"
import C_Class from "../C_Class/Index"
import C_Division from "../C_Division/Index"
import {connect} from 'react-redux';
import { LinkContainer } from 'react-router-bootstrap'
let bname="d"
class Bread extends Component {

    render() { 
        return ( 
        <Router>
          <Row style={{margin:0,padding:0,width:"100%",height:"10vh"}}>
        
        {(() => {
        switch (this.props.bname) {
          case "c":   return(
            <Breadcrumb style={{width:"100%",padding:0,margin:0}}>
            
          <Breadcrumb.Item active >
          Create Class
          </Breadcrumb.Item>
          
          </Breadcrumb>);
          case "d": return( 
            <Breadcrumb style={{width:"100%",padding:0,margin:0}}>
          <LinkContainer to="/CreateClass">
          <Breadcrumb.Item >
          Create Class
          </Breadcrumb.Item>
          </LinkContainer>
          <LinkContainer to="/CreateDivision">
          <Breadcrumb.Item  active>
          Create Division
          </Breadcrumb.Item>
          </LinkContainer>
          </Breadcrumb>
          );
          case "s":  return(
            <Breadcrumb style={{width:"100%",padding:0,margin:0}}>
            <LinkContainer to="/CreateClass">
            <Breadcrumb.Item >
            Create Class
            </Breadcrumb.Item>
            </LinkContainer>
            <LinkContainer to="/CreateDivision">
            <Breadcrumb.Item >
            Create Division
            </Breadcrumb.Item>
            </LinkContainer>
          <LinkContainer to="/CreateSubjects">
          <Breadcrumb.Item active>
          Create Subjects
          </Breadcrumb.Item>
          </LinkContainer>
          </Breadcrumb>) ;
          default:      return "#FFFFFF";
        }
      })()}
        
        
        
     
      </Row>
      <Row style={{margin:0,padding:0,width:"100%",height:"58vh"}}>
        <Switch>
      <Route exact path="/admin" component={C_Class}/>
      <Route exact path="/CreateClass" component={C_Class} />
      <Route exact path="/CreateDivision" component={C_Division} />
      </Switch>
      </Row>
      </Router> );
    }
}
 



const mapStateToProps=(state)=>{
  return{
      bname:state.bname

  }
}

export default connect(mapStateToProps)(Bread);