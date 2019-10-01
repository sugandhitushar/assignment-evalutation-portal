import {Row} from 'react-bootstrap'
import Header from './Components/Header/Index'
import Features from './Components/Features/Index'
import Login from './Components/Login/Index'
import Footer from './Components/Footer/Index'
import Forget from './Components/ForgetPassword/Index'
import SignUp from './Components/Signup/Index'
import Admin from './Components/Admin/Index'
import Teacher from './Components/Teacher/Index.jsx'
//import Bread from "./Components/Bread/Index"
import {BrowserRouter as Router,Switch,Route} from "react-router-dom"
import React, { Component } from 'react';
import './App.css';


class App extends Component {

  render() { 
    return (
      <Router>
      <Header/>
      <Row id="row2">
        <Switch>
          <Route path="/" exact>
            <Features/>
            <Login/>
          </Route>
    
          <Route path="/login" exact>
            <Features/>
            <Login/>
          </Route>
    
          <Route path="/forget" exact>
            <Features/>
            <Forget/>
          </Route>
    
          <Route path="/new" exact>
            <SignUp/>
          </Route>

          <Route path="/admin">
          <Admin/>
          </Route>

          <Route path="/as">
          <Teacher/>
          </Route>
    
        </Switch>
      </Row>
      <Footer/>
      </Router>
    );
  }
}
 
export default App;
