import React, { Component } from 'react';
import {connect} from "react-redux";
import axios from 'axios';
import Flippy, { FrontSide, BackSide } from 'react-flippy';
import { Container, Col, Row, Table,Button, Alert } from 'react-bootstrap';

class CreateUser extends Component {
    constructor()
    {
        super();
        this.state={
            isFlipped:false,
            fields:{},
            errors:{}
        };
        this.handleClick=this.handleClick.bind(this);
        this.handleChange=this.handleChange.bind(this);
        this.handleAdd=this.handleAdd.bind(this);
    }
    handleAdd(e)
    {
        if(this.validateForm())
        {
            alert("sucess");
        }
        else
        {
            e.preventDefault();
        }
    }
    validateForm()
    {
        let fields=this.state.fields;
        let errors={};
        let formIsValid="true";

        if(!fields['username'])
        {
            formIsValid=false;
            errors['username']="*Please enter firstname";
        }
        if (!fields["password"]) {
            formIsValid = false;
            errors["password"] = "*Please enter your password.";
        }
        if (typeof fields["email"] !== "undefined") {
            //regular expression for email validation
            var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
            if (!pattern.test(fields["email"])) {
              formIsValid = false;
              errors["email"] = "*Please enter valid email-ID.";
            }
          }
        this.setState({errors:errors});
        return formIsValid;
    }
    handleChange(e)
    {
        let fields=this.state.fields;
        fields[e.target.name]=e.target.value;
        this.setState(
            {
              fields
            }
        )
        if(this.state.fields["type"]==="Student")
        {
            document.getElementById("class").disabled=false;
        }
        else
        {
            document.getElementById("class").disabled=true;
            fields["class"]=""
            this.setState({
                fields
            })
        }
    }
    handleClick(e)
    {
        e.preventDefault();
        this.setState(prevState=>({isFlipped:!prevState.isFlipped}));
    }
    render() { 
        return (
            <Flippy
                        isFlipped={this.state.isFlipped}
                        flipDirection="horizontal"
                        ref={(r) => this.flippy = r}
                        style={{ width: '400px', height: '500px' }}
                        >
                        <FrontSide
                            style={{
                                    backgroundColor: '#41669d',
                                }}
                        >
                        <Container>
                            <Row>
                                <Col>
                                    <center>
                                    <img src={ require('./newuser.png') } style={{height:"250px",width:"250px"}}/>
                                    </center>
                                </Col> 
                            </Row>
                            <br></br><br></br>
                            <Row>
                                <Col>
                                    <center>
                                    <Button variant="success" onClick={this.handleClick}>Click to Add User</Button>
                                    </center>
                                </Col>
                            </Row>
                        </Container>
                        </FrontSide>
                        <BackSide
                            style={{ backgroundColor: '#41669d'}}>
                       
                        <br></br>
                        <Table responsive borderless hover size="sm">
                            <tbody>
                                <tr>
                                    <td>
                                        Username: 
                                    </td>
                                    <td>
                                        <input type="text" name="username" value={this.state.fields.username} onChange={this.handleChange}></input>
                                        <p>{this.state.errors.username}</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        password: 
                                    </td>
                                    <td>
                                        <input type="password" name="password"  onChange={this.handleChange}></input>
                    
                                        <p>{this.state.errors.password}</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Email: 
                                    </td>
                                    <td>
                                        <input type="text" name="email" value={this.state.fields.email} onChange={this.handleChange}></input>
                                        
                                        <p>{this.state.errors.email}</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                            Type:
                                    </td>
                                    <td>
                                        <select name="type" onChange={this.handleChange}>
                                            <option>Teacher</option>
                                            <option>Student</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Class:
                                    </td>
                                    <td>
                                        <select id="class" name="class" disabled onChange={this.handleChange}>
                                            <option>FYBsc</option>
                                            <option>SYBsc</option>
                                            <option>TYBsc</option>
                                            <option>FYMsc</option>
                                            <option>SYMsc</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                            <p> {this.state.fields.class}</p>          
                                    </td>
                                    <td>
                                        <Button  variant="success" onClick={this.handleAdd}>Add</Button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <Button onClick={this.handleClick}>Back</Button>
                                    </td>
                                </tr>

                            </tbody>
                            
                        </Table>
                        </BackSide>
                        </Flippy>
                

          );
    }
}
 
export default CreateUser;