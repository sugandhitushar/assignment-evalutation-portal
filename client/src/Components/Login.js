import React, { Component } from 'react';
import {connect} from 'react-redux';
import { Table, Button } from 'react-bootstrap';
import axios from 'axios';
let jwt = require('jsonwebtoken');
let token={};

class Login extends Component {
   constructor()
   {

       super();
       this.state=
        {
            username:"",
            password:"",
        
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
       
       const instance = axios.create({baseURL: 'http://localhost:8080'})
       instance.post("/authenticate",{username:this.state.username,password:this.state.password})
       .then((res)=>
       {
           console.log(res.data.token);
           token=res.data.token;
           console.log("token is :",token);
           let decodedToken=this.parseJwt(token)
           //console.log(a.sub)

           var isExpired = "true";
           var dateNow = new Date();
           decodedToken.exp*=1000;
           console.log("token",decodedToken.exp)
           console.log("current",dateNow.getTime())
           if(decodedToken.exp > dateNow.getTime())
           {
         //   console.log("Expired :",isExpired);
               isExpired = "false";
               console.log("token is before hello :",token);
               const instance1 = axios.create({baseURL: 'http://localhost:8080',headers:{'Authorization':' Bearer '+token}})
               instance1.post("/api/v1/hello")
               .then((res)=>
               {
                   console.log("hello",res.data);
        
               })                     
               
           }
           console.log("Expired :",isExpired);
           console.log("sub",decodedToken.sub);
           
           this.props.onAuthenticate();
           
       })
       .catch((error)=>{console.log(error)})
    
       
       if(this.state.username==="admin" && this.state.password==="admin")
       {
        this.props.credentials();
        this.props.login();
       }
        
   }
    render() { 
        return ( 
            <Table responsive borderless hover  style={{marginTop:"15vh"}}>
                <tbody>
                <tr>
                    <th style={{textAlign:"right"}}><i><h3>Username :</h3></i></th>
                    <td ><input type="text" name="username" onChange={this.handleChange}></input></td>
                </tr>
                <tr>
                    <th style={{textAlign:"right"}}><i><h3>Password :</h3></i></th>
                    <td><input type="password" name="password" onChange={this.handleChange} ></input></td>
                </tr>
                <tr>
                    
                    <td colSpan="2" style={{textAlign:"center"}}><Button variant="success" size="lg" onClick={this.handleLogin}>Login</Button></td>
                </tr>
                </tbody>
            </Table>
         );
    }
}

const mapDispatchToProps=(dispatch)=>{
    return{
        login:()=>dispatch({type:'Login',val:"true"}), //Action Creators
        onAuthenticate:()=>dispatch({type:'AuthenticationAction',val:token}),
       credentials:()=>dispatch({type:'details',username:"admin",utype:"admin"})
    }
}


const mapStateToProps=(state)=>{
    return{
        token:state.token

    }
}
 
export default connect(mapStateToProps,mapDispatchToProps) (Login);