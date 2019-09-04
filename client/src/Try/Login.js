import React from 'react';
import {Container,Row,Col,Table, Button } from 'react-bootstrap';
import axios from "axios";
class Login extends React.Component {
    constructor()
    {
        super();
        this.state=
        {
            username:"",
            password:"",
        
        }
        this.handleChange=this.handleChange.bind(this);
        
    }

    handleChange(e)
    {
        this.setState(
            {
                [e.target.name]:e.target.value
            }
        )
    }
    handleClick(e)
    {
        e.preventDefault();
        if((this.state.username==='admin')&&(this.state.password==='admin'))
        {
            this.props.call("admin");
            this.props.islogin();
            axios.post("/demo",{username:"abc"})
        }
    }

    render() { 
        return (  
           <Table responsive hover borderless striped>
               <tbody><br></br><br></br>
               <tr>
                   <td>
                       Username :
                   </td>
                   <td>
                       <input type="text" name="username" onChange={this.handleChange}></input>
                   </td>
               </tr>
               <tr>
                   <td>
                       Password : 
                   </td>
                   <td>
                       <input type="password" name="password" onChange={this.handleChange}></input>
                   </td>
               </tr>
               <tr>
                   <td>

                   </td>
                   <td>
                       
                       <Button variant="success" onClick={this.handleClick.bind(this)}>Login</Button>
                       
                   </td>
               </tr>
               </tbody>
           </Table>
        );
    }
}
 
export default Login;