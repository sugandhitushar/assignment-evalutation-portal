import React, { Component } from 'react';
import {connect} from 'react-redux';
import {Button,Col, Form,Row } from 'react-bootstrap';
import { withRouter } from 'react-router-dom';
import axios from 'axios';
import './Login.css'
import {Link} from 'react-router-dom'

let token={};
let arr=[];

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
  
   componentWillUnmount()
   {
           const instance1 = axios.create({baseURL: 'http://localhost:8080'}) 
           instance1.get("/api/v1/courses")
           .then((res)=>{
               for(var i in res.data["data"])
               {
                 arr.push({id:res.data["data"][i]["id"],name:res.data["data"][i]["name"]})
               }
               for(var j in arr)
               {
                   console.log("array of course is :",arr[j])
               }
               this.props.ongetCourse()
               
           });

   }
  
   handleLogin(e)
   {
       e.preventDefault();
       
       const instance = axios.create({baseURL: 'http://localhost:8080'})
       instance.post("/login",{keepMeSignedIn:false,password:this.state.password,username:this.state.username})
       .then((res)=>
       {
           console.log("In login",res.data);
          token=res.data.accessToken;
           console.log("token is :",token);
           this.props.onAuthenticate(); //save token in store for further use
         
         
         /*  console.log("After handle click of login token is :",token)
           decodedToken=this.parseJwt(token)
          var dateNow = new Date();
           decodedToken.exp*=1000;
           if(decodedToken.exp > dateNow.getTime())
          {console.log("before api")
           const instance1 = axios.create({baseURL: 'http://localhost:8080',headers:{'Authorization':'Bearer '+token}}) 
           instance1.get("/api/v1/courses")
           .then((res)=>{
               for(var i in res.data["data"])
               {
                 arr.push({id:res.data["data"][i]["id"],name:res.data["data"][i]["name"]})
                    
                    
               }
               for(var j in arr)
               {
                   console.log("array of course is :",arr[j])
               }
               //this.props.ongetTeacher()
               
           });
   
           
          }*/
       })
       .catch((error)=>{console.log(error)})

       if(this.state.username==="admin@admin.com" && this.state.password==="Admin@123")
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
        ongetCourse:()=>dispatch({type:'getcourses',val:arr}),
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