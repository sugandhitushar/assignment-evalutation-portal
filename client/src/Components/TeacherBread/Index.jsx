import React, { Component } from 'react';
import CreateAssignment from "../CreateAssignment/Index"
import SelectDivision from "../SelectDivision/Index"
import SelectStudents from "../SelectStudents/Index"
import {connect} from "react-redux"
import {Breadcrumb, Row} from "react-bootstrap";
import {BrowserRouter as Router,Switch,Route} from "react-router-dom"
import { LinkContainer } from 'react-router-bootstrap'

class TeacherBread extends Component {
    
    render() { 
        return ( 
        <Router>
          <Row style={{margin:0,padding:0,width:"100%",height:"10vh"}}>
        
        {(() => {
        switch (this.props.tbread) {
          case "c":   return(
            <Breadcrumb style={{width:"100%",padding:0,margin:0}}>
            
          <Breadcrumb.Item active >
          Create Assignment
          </Breadcrumb.Item>
          
          </Breadcrumb>);
          case "d": return( 
            <Breadcrumb style={{width:"100%",padding:0,margin:0}}>
          <LinkContainer to="/CreateAssignment">
          <Breadcrumb.Item >
          Create Assignment
          </Breadcrumb.Item>
          </LinkContainer>
          <LinkContainer to="/SelectDivision">
          <Breadcrumb.Item  active>
          Select Division
          </Breadcrumb.Item>
          </LinkContainer>
          </Breadcrumb>
          );
          case "s":  return(
            <Breadcrumb style={{width:"100%",padding:0,margin:0}}>
            <LinkContainer to="/CreateAssignment">
            <Breadcrumb.Item >
            Create Assignment
            </Breadcrumb.Item>
            </LinkContainer>
            <LinkContainer to="/SelectDivision">
            <Breadcrumb.Item >
            Select Division
            </Breadcrumb.Item>
            </LinkContainer>
          <LinkContainer to="/SelectStudents">
          <Breadcrumb.Item active>
          Select Students
          </Breadcrumb.Item>
          </LinkContainer>
          </Breadcrumb>) ;
          default:      return "#FFFFFF";
        }
      })()}
        
        
        
     
      </Row>
      <Row style={{margin:0,padding:0,width:"100%",height:"58vh"}}>
        <Switch>
      <Route exact path="/as" component={CreateAssignment}/>
      <Route exact path="/CreateAssignment" component={CreateAssignment} />
      <Route exact path="/SelectDivision" component={SelectDivision} />
      <Route exact path="/SelectStudents" component={SelectStudents}/>
      </Switch>
      </Row>
      </Router> );
    }
}
const mapStateToProps=(state)=>{
    return{
        tbread:state.tbread
  
    }
  }
  
  export default connect(mapStateToProps)(TeacherBread);