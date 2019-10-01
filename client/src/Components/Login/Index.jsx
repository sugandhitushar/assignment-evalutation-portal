import React, { Component } from 'react';
import {connect} from 'react-redux';
import {Button,Col, Form,Row } from 'react-bootstrap';
import { withRouter } from 'react-router-dom';
import axios from 'axios';
import './Login.css'
import {Link} from 'react-router-dom'

let token={};

class Login extends Component {
   constructor()
   {

       super();
       this.state=
        {
            username:"",
            password:"",
            ValidatedState:false,
            WrongState:false
        
        }
        this.handleChange=this.handleChange.bind(this);
        this.handleLogin=this.handleLogin.bind(this);
   }
   handleChange(e)
   {
    this.setState(
        {
            [e.target.name]:e.target.value
        }
    )
   }
   parseJwt(token) {
    if (!token) { return; }
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
}
   handleLogin(e)
   {
       e.preventDefault();
       
       /*const instance = axios.create({baseURL: 'http://localhost:8080'})
       instance.post("/login",{username:this.state.username,password:this.state.password})
       .then((res)=>
       {
           token=res.data.token;
           console.log("token is :",token);
           let decodedToken=this.parseJwt(token)
           var isExpired = "true";
           var dateNow = new Date();
           decodedToken.exp*=1000;
           if(decodedToken.exp > dateNow.getTime())
           {
               isExpired = "false";
               console.log("token is before hello :",token);
               const instance1 = axios.create({baseURL: 'http://localhost:8080',headers:{'Authorization':' Bearer '+token}})
               instance1.post("/api/v1/hello")
               .then((res)=>
               {
                   console.log("hello",res.data);
        
               })                     
               
           }
           
           this.props.onAuthenticate(); //save token in store for further use
           
       })
       .catch((error)=>{console.log(error)})*/
       if(this.state.username==="admin" && this.state.password==="admin")
       {
        //this.props.credentials(); //save username and type fro further use
            this.props.history.push('\admin')
       
       }
       else if(this.state.username==="teacher" && this.state.password==="teacher")
       {
        //this.props.credentials(); //save username and type fro further use
            this.props.history.push('\as')
       
       }
        
   }
    render() { 
        return (
            <Col id="log_col" x1="4">
                <Form id="log_form" >
                    <Form.Group as={Col} controlId="validateusername" style={{width:"100%"}}>
                       
                        <Form.Label >Your Email</Form.Label>
                        
                            <Form.Control style={{width:"80%"}}
                            required
                            type="text"
                            name="username"
                            placeholder="email"
                            onChange={this.handleChange}
                            isValid={this.state.ValidatedState}
                            isInvalid={this.state.WrongState}
                            />
                            <Form.Control.Feedback type="valid">Right!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">Wrong!</Form.Control.Feedback>
                                           
                    </Form.Group>
                    <Form.Group as={Col} controlId="validatepassword" style={{width:"100%"}}>
                        <Form.Label >Password</Form.Label>
                        
                            <Form.Control style={{width:"80%"}}
                            required
                            type="password"
                            name="password"
                            placeholder="password"
                            onChange={this.handleChange}
                            isValid={this.state.ValidatedState}
                            isInvalid={this.state.WrongState}
                            />
                            <Form.Control.Feedback type="valid">Right!</Form.Control.Feedback>
                            <Form.Control.Feedback type="invalid">Wrong!</Form.Control.Feedback>
                                           
                    </Form.Group>
                    <Form.Group as={Row} controlId="submitdetails" style={{paddingLeft: 0,marginLeft:0,width:"100%"}}>
                        <Col style={{padding:0}}>
                           <center>
                            <Button  onClick={this.handleLogin}>Sign in</Button>
                            </center>
                            </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="links" style={{paddingLeft: 0,marginLeft:0,width:"100%"}}>
                    <Col style={{padding:0}}>
                    <center>
                        <Form.Label >
                            <ul type="none" style={{paddingLeft:0}}>
                        
                            <Link to="/forget">
                                <li>Forget password?</li>
                            </Link>
                            <Link to="/new">
                                <li>
                                    Signup?
                                </li>
                            </Link>
                            
                            </ul>
                           
                            
                        </Form.Label>
                        </center>
                        </Col>
                    </Form.Group>
                </Form>
            </Col>
         );
    }
}

const mapDispatchToProps=(dispatch)=>{
    return{
        //Action Creators
        onAuthenticate:()=>dispatch({type:'AuthenticationAction',val:token}),
       // credentials:()=>dispatch({type:'details',username:"admin",utype:"admin"}
       credentials:()=>dispatch({type:'details',username:"teacher",utype:"teacher"})
    }
}


const mapStateToProps=(state)=>{
    return{
        token:state.token

    }
}
 
export default withRouter(connect(mapStateToProps,mapDispatchToProps)(Login));