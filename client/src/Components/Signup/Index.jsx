import React, { Component } from 'react';
import {Button, Form,Col}from 'react-bootstrap';
import axios from "axios";
import { withRouter } from 'react-router-dom';
import DatePicker from 'react-date-picker'
import moment from 'moment'
import {connect} from 'react-redux'
import "./Signup.css"



class SignUp extends Component {
    constructor ()
    {
        super();
        this.state={
            fields:{type:"Teacher",class:"FyBsc",division:"A", designation:"Assistant Professor"},
            errors:{firstname:null,lastname:null,email:null,mobile:null,empid:null,password:null,cpassword:null,rollno:null,
                prn:null,admission_no:null},
            startDate:new Date(),
            admissionDate:new Date()
           
            
        };

        this.handleChange=this.handleChange.bind(this)
        this.handleClick=this.handleClick.bind(this)
        this.handleChange1=this.handleChange1.bind(this)
        this.handleChange2=this.handleChange2.bind(this)

    }
    handleChange1 = date => {
        this.setState({
          startDate: date
        });
      };
    handleChange2 = date => {
        this.setState({
          admissionDate: date
        });
      };
    handleChange(e)
    {
     
            let fields=this.state.fields;
            fields[e.target.name]=e.target.value;
            this.setState(
                {
                fields
                
                }
            );
        
        
    }
    handleClick(e)
    {
        if(this.validateForm()==="true")
        {
            alert("sucess");
            console.log(this.state.startDate)
            this.props.history.push('/login');
            if(this.state.fields.type==="Student")
            {
                const instance = axios.create({baseURL: 'http://localhost:8080'})
                instance.post("/api/v1/students/STUDENT_SIGNUP",{firstName:this.state.fields.firstname,lastName:this.state.fields.lastname})
                .then((res)=>
                {
                    console.log("student signup response",res);
                })
                .catch((error)=>{console.log(error)});
            }
        }
        else
        {
            e.preventDefault();
        }

    }
    validateForm()
    {
        let fields=this.state.fields;
        let formIsValid="true";
        let errors={}
        if(!fields['firstname'])
        {
                        
           formIsValid="false";
           errors['firstname']=true 
        }
        if (typeof fields["firstname"] !== "undefined")
        {
            var letters = /^[A-Za-z]+$/;
            if(!fields['firstname'].match(letters))
            {
                formIsValid="false";
                errors['firstname']=true
            }
        }
        if(!fields['lastname'])
        {
            formIsValid="false";
            errors['lastname']=true
        }
        if (typeof fields["lastname"] !== "undefined")
        {
            var letters = /^[A-Za-z]+$/;
            if(!fields['lastname'].match(letters))
            {
                formIsValid="false";
                errors['lastname']=true
            }
        }
        if(!fields['email'])
        {
            formIsValid="false";
            errors['email']=true
        }
        if (typeof fields["email"] !== "undefined") 
        {
            //regular expression for email validation
            var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
            if (!pattern.test(fields["email"]))
            {
              formIsValid = "false";
              errors['email']=true
            }
        }
        if(!fields['mobile'])
        {
            formIsValid="false";
            errors['mobile']=true
        }
        if (typeof fields["mobile"] !== "undefined")
        {
            var phoneno = /^\d{10}$/;
            if(!fields['mobile'].match(phoneno))
            {
                formIsValid="false";
                errors['mobile']=true
            }
        }
        if(!fields['password'])
        {
            formIsValid="false";
            errors['password']=true
        }
        if (typeof fields["password"] !== "undefined")
        {
            var decimal=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
            if(!fields['password'].match(decimal))
            {
                formIsValid="false";
                errors['password']=true
            }
        }
        if(!fields['cpassword'])
        {
            formIsValid="false";
            errors['cpassword']=true
        }
        if (typeof fields["cpassword"] !== "undefined")
        {
            if(fields['password']!==fields['cpassword'])
            {
                formIsValid="false";
                errors['cpassword']=true
            }
        }
        if(fields['type']==="Teacher")
        {
            if(!fields['empid'])
            {
                formIsValid="false";
                errors['empid']=true
            }
            if(!fields['rollno'])
            {
                errors['rollno']=true
            }
            if(!fields['admission_no'])
            {
                errors['admission_no']=true
            }
        
        }
        if(fields['type']==="Student")
        {
            if(!fields['rollno'])
            {
                formIsValid="false";
                errors['rollno']=true
            }
            if (typeof fields["rollno"] !== "undefined")
            {
                var rollno = /^\d+$/;
                if(!fields['rollno'].match(rollno))
                {
                    formIsValid="false";
                    errors['rollno']=true
                }
            }
            if (typeof fields["prn"] !== "undefined")
            {
                var prn = /^\d+$/;
                if(!fields['prn'].match(prn))
                {
                    formIsValid="false";
                    errors['prn']=true
                }
            }
            if(!fields['admission_no'])
            {
                formIsValid="false";
                errors['admission_no']=true
            }
            if (typeof fields["admission_no"] !== "undefined")
            {
                var admission_no = /^\d+$/;
                if(!fields['admission_no'].match(admission_no))
                {
                    formIsValid="false";
                    errors['admission_no']=true
                }
            }
            if(!fields['empid'])
            {
                errors['empid']=true
            }
        }   
        
        if(this.state.admissionDate===null)
        {
            formIsValid="false";
        }
        this.setState({errors:errors})
        return formIsValid;
    }

    

   
    render() { 
        return (
            <Col id="signup_col" x1="12">
                <Form id="signup_form">
                <Form.Row>
                    <Form.Group as={Col} controlId="validateFirst" style={{width:"100%"}}>
                        <Form.Label >First Name </Form.Label>
                        <Form.Label id="mandatory">&nbsp;*</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            name="firstname"
                            placeholder="Firstname"
                            onChange={this.handleChange}
                            isValid={this.state.errors.firstname===null?(null):(!this.state.errors.firstname)}
                            isInvalid={this.state.errors.firstname}
                         />
                    </Form.Group>
                    <Form.Group as={Col} controlId="validateLast" style={{width:"100%"}}>
                        <Form.Label >Last Name</Form.Label>
                        <Form.Label id="mandatory">&nbsp;*</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            name="lastname"
                            placeholder="Lastname"
                            onChange={this.handleChange}
                            isValid={this.state.errors.lastname===null?(null):(!this.state.errors.lastname)}
                            isInvalid={this.state.errors.lastname}
                         />
                    </Form.Group>
                    <Form.Group as={Col} controlId="validateEmail" style={{width:"100%"}}>
                        <Form.Label >Email</Form.Label>
                        <Form.Label id="mandatory">&nbsp;*</Form.Label>
                        <Form.Control 
                            required
                            type="text"
                            name="email"
                            placeholder="Email"
                            onChange={this.handleChange}
                            isValid={this.state.errors.email===null?(null):(!this.state.errors.email)}
                            isInvalid={this.state.errors.email}
                         />
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="validateMobile" style={{width:"100%"}}>
                        <Form.Label >Mobile Number</Form.Label>
                        <Form.Label id="mandatory">&nbsp;*</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            name="mobile"
                            placeholder="mobile"
                            onChange={this.handleChange}
                            isValid={this.state.errors.mobile===null?(null):(!this.state.errors.mobile)}
                            isInvalid={this.state.errors.mobile}
                         />
                    </Form.Group>
                    <Form.Group as={Col} controlId="validateGender" style={{width:"100%"}}>
                        <Form.Label >Gender</Form.Label><br></br>
                        <Form.Check
                            defaultChecked 
                            inline
                            type="radio"
                            name="gender"
                            label="Male"
                            id="Male"
                            onChange={this.handleChange}
                         />
                         <Form.Check 
                            inline
                            type="radio"
                            name="gender"
                            label="Female"
                            id="Female"
                            onChange={this.handleChange}
                         />
                    </Form.Group>
                    <Form.Group as={Col} controlId="validateType" style={{width:"100%"}}>
                        <Form.Label >Type</Form.Label>
                        <Form.Control as="select"
                            name="type"
                            onChange={this.handleChange}
                        >
                            <option>Teacher</option>
                            <option>Student</option>
                        </Form.Control>
                    </Form.Group>
                    </Form.Row>
                                        {
                    (this.state.fields.type==='Teacher')?
                    (
                        <div>
                        <Form.Row>
                        <Form.Group as={Col} controlId="validateEmpid" style={{width:"100%"}}>
                        <Form.Label >Employee Id</Form.Label>
                        <Form.Label id="mandatory">&nbsp;*</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            name="empid"
                            placeholder="empid"
                            onChange={this.handleChange}
                            isValid={this.state.errors.empid===null?(null):(!this.state.errors.empid)}
                            isInvalid={this.state.errors.empid}
                         />
                    </Form.Group>
                    <Form.Group as={Col} controlId="validateDesignation" style={{width:"100%"}}>
                        <Form.Label >Designation</Form.Label>
                        <Form.Control as="select"
                            name="designation"
                            onChange={this.handleChange}
                        >
                            <option>Assistant Professor</option>
                            <option>Lab Incharge</option>
                            <option>Visitor</option>
                        </Form.Control>
                    </Form.Group>
                    <Form.Group as={Col} controlId="validateJdate" style={{width:"100%"}}>
                        <Form.Label >Joining Date</Form.Label>
                        <br></br>
                    
                         <DatePicker
                        required
                        popperModifiers={{
                            preventOverflow: {
                              enabled: true,
                            },
                          }}
                            dateFormat="dd/MM/yyyy"
                            value={this.state.startDate}
                            onChange={this.handleChange1}
                        />
                        
                                    
                        
                    </Form.Group>
                    </Form.Row>
                        </div>
                    ):(<div>
                        <Form.Row>
                            <Form.Group as={Col} controlId="validateClass" style={{width:"100%"}}>
                            <Form.Label >Class</Form.Label>
                            <Form.Control as="select"
                                name="class"
                                onChange={this.handleChange}
                            >
                                <option>FyBsc</option>
                                <option>SyBsc</option>
                                <option>TyBsc</option>
                                <option>FyMsc</option>
                                <option>SyMsc</option>
                            </Form.Control>
                            </Form.Group>
                            <Form.Group as={Col} controlId="validateDivision" style={{width:"100%"}}>
                            <Form.Label >Division</Form.Label>
                            <Form.Control as="select"
                                name="division"
                                onChange={this.handleChange}
                            >
                                <option>A</option>
                            </Form.Control>
                            </Form.Group>
                            <Form.Group as={Col} controlId="validateRoll" style={{width:"100%"}}>
                                <Form.Label >Roll Number</Form.Label>
                                <Form.Label id="mandatory">&nbsp;*</Form.Label>
                                <Form.Control 
                                    required
                                    type="text"
                                    name="rollno"
                                    onChange={this.handleChange}
                                    isValid={this.state.errors.rollno===null?(null):(!this.state.errors.rollno)}
                                    isInvalid={this.state.errors.rollno}
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                        <Form.Group as={Col} controlId="validatePRN" style={{width:"100%"}}>
                                <Form.Label >PRN</Form.Label>
                                <Form.Control 
                                    type="text"
                                    name="prn"
                                    onChange={this.handleChange}
                                    isValid={this.state.errors.prn===null?(null):(!this.state.errors.prn)}
                                    isInvalid={this.state.errors.prn}
                                />
                            </Form.Group>
                            <Form.Group as={Col} controlId="validateno" style={{width:"100%"}}>
                                <Form.Label >Admission Number</Form.Label>
                                <Form.Label id="mandatory">&nbsp;*</Form.Label>
                                <Form.Control
                                    required
                                    type="text"
                                    name="admission_no"
                                    onChange={this.handleChange}
                                    isValid={this.state.errors.admission_no===null?(null):(!this.state.errors.admission_no)}
                                    isInvalid={this.state.errors.admission_no}
                                />
                            </Form.Group>
                            <Form.Group as={Col} controlId="validateAdate" style={{width:"100%"}}>
                                <Form.Label >Admission Date</Form.Label>
                                <Form.Label id="mandatory">&nbsp;*</Form.Label>
                                <br></br>
                            
                                <DatePicker
                                required
                                popperModifiers={{
                                    preventOverflow: {
                                    enabled: true,
                                    },
                                }}
                                    dateFormat="dd/MM/yyyy"
                                    value={this.state.admissionDate}
                                    onChange={this.handleChange2}
                            />
                                        
                        
                    </Form.Group>
                        </Form.Row>
                        
                    </div>)
                    }
                    <Form.Row>
                    <Form.Group as={Col} controlId="validatepass" style={{width:"100%"}}>
                                <Form.Label >Password</Form.Label>
                                <Form.Label id="mandatory">&nbsp;*</Form.Label>
                                <Form.Control 
                                    type="password"
                                    name="password"
                                    onChange={this.handleChange}
                                    isValid={this.state.errors.password===null?(null):(!this.state.errors.password)}
                                    isInvalid={this.state.errors.password}
                                />
                               <Form.Control.Feedback type="invalid">Atleast 1 lowecase,upercase,digit,special character</Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group as={Col} controlId="validatecpass" style={{width:"100%"}}>
                                <Form.Label >Confirm Password</Form.Label>
                                <Form.Label id="mandatory">&nbsp;*</Form.Label>
                                <Form.Control 
                                    type="password"
                                    name="cpassword"
                                    onChange={this.handleChange}
                                    isValid={this.state.errors.cpassword===null?(null):(!this.state.errors.cpassword)}
                                    isInvalid={this.state.errors.cpassword}
                                />
                    </Form.Group>
                    <Form.Group as={Col} controlId="register" style={{width:"100%"}}>
                                <Form.Label ></Form.Label>
                                <br></br>
                                    <Button onClick={this.handleClick}>Signup</Button>
                    </Form.Group>
                    </Form.Row>
                   
                </Form>    
            </Col>                
          );
    }
}

const mapStateToProps=(state)=>
{
    return{
    token:state.token,
    
    }
}
export default withRouter(connect(mapStateToProps)(SignUp));