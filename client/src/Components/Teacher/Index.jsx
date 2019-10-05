import React, { Component} from 'react';
import {connect} from 'react-redux';
import DatePicker from 'react-date-picker'
import edit from '../../Resources/edit.jpg';
import evaluation from '../../Resources/evaluation.png';
import TeacherCreateAssignment from "../TeacherCreateAssignment/Index"
import TeacherPreviousAssignment from "../TeacherPreviousAssignment/Index"
import TeacherReceivedAssignment from "../TeacherReceivedAssignment/Index"

import {Row,Col, Container,Tab,Tabs, Button,Form,Table,Dropdown} from 'react-bootstrap';
//import Teacher from './Teacher.css'
var bgColors = { "Default": "#81b71a",
                    "Blue": "#00B1E1",
                    "Cyan": "#37BC9B",
                    "Green": "#8CC152",
                    "Red": "#E9573F",
                    "Yellow": "#F6BB42",
};

class Teacher extends Component {
    render() { 
        return ( 
            <Col xl="12" style={{padding:0,marginLeft:0,marginRight:0}}>
            <Row style={{padding:0,margin:0,height:"75vh"}}>
            <Col xl="11"  style={{padding:0,margin:0}}>
            <Tabs justify className="myClass" defaultActiveKey="CreateAssignment">
                <Tab eventKey="CreateAssignment" title="Create New Assignment">
                    <TeacherCreateAssignment/>
                </Tab>
                <Tab eventKey="PreviousAssignment" title="Previous Assignment">
                    <TeacherPreviousAssignment/>
                </Tab>
                <Tab eventKey="ReceivedAssignment" title="Received Assignment">
                    <TeacherReceivedAssignment/>
                </Tab>
            </Tabs>
            </Col>
            <Col xl="1" style={{padding:0,margin:0}}>
            <Dropdown drop="left" >
                <Dropdown.Toggle variant="primary" id="dropdown-basic">
                    Dropdown
                </Dropdown.Toggle>
                <Dropdown.Menu>
                    <Dropdown.Item href="#/action-1">Change Password</Dropdown.Item>
                    <Dropdown.Item href="#/action-2">Logout</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
            </Col>
            </Row>
            </Col>
            
               /*         <Container fluid="true">
                           
                            
                <Tabs defaultActiveKey="upload" id="uncontrolled-tab-example">
                     <Tab eventKey="upload" title="Create New Assignment">
                     <Row className="justify-content-md-center">
                     <Col xs lg="4">
                     <Form style={{padding:0,margin:0}}>
                     <div id="form">
                     <div class="form-group">
                     <Form.Group controlId="exampleForm.ControlSelect1">
                         <Form.Label>Class</Form.Label>
                            <Form.Control as="select">
                            <option>Fy.B.Sc.</option>
                            <option>Sy.B.Sc.</option>
                            <option>Ty.B.Sc.</option>
                            <option>Fy.M.Sc.</option>
                            <option>Fy.M.Sc.</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect2">
                         <Form.Label>Division</Form.Label>
                            <Form.Control as="select" multiple>
                            <option>A</option>
                            <option>B</option>
                            <option>C</option>
                            <option>D</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect3">
                         <Form.Label>Subject</Form.Label>
                            <Form.Control as="select" >
                            <option>sub1</option>
                            <option>sub2</option>
                            <option>sub3</option>
                            <option>sub4</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.Title">
                         <Form.Label>Title</Form.Label>
                         <Form.Control type="text" placeholder="Title" />
                         </Form.Group>
                         <Form.Group controlId="exampleForm.date">
                        <Form.Label>Last date of submission : </Form.Label>
                        <DatePicker
                        required
                            dateFormat="dd/MM/yyyy"
                        />
                        
                        </Form.Group>
                        <Form.Group controlId="exampleForm.upload">
                    <div className="input-group">
                    <div className="input-group-prepend">
                      <span className="input-group-text" id="inputGroupFileAddon01">
                        Upload
                        </span>
                    </div>
                    <div className="custom-file">
                    <input
                      type="file"
                      className="custom-file-input"
                      id="inputGroupFile01"
                     aria-describedby="inputGroupFileAddon01"
                    />
                    <label className="custom-file-label" htmlFor="inputGroupFile01">
                        Choose file
                    </label>
                    </div>
                    </div>
                    </Form.Group>
                    <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Remark(if any) </Form.Label>
                        <Form.Control as="textarea" rows="3" />
                    </Form.Group>
                    <button class="btn btn-primary" type="submit">Create</button>
                    </div>
                    
                    </div>
                    </Form>
                    </Col>
                </Row>
                
                     </Tab>



                     <Tab eventKey="previous" title="Previous Assignments">
                     <Row >
                         
                     <Col xs lg="2">
                         <div>
                         <Form style={{backgroundColor:'#EDF5FF'}}>
                             <Form.Group controlId="exampleForm.ControlSelect1">
                             <Form.Label>Class</Form.Label>
                                
                            <Form.Control as="select">
                            <option>Fy.B.Sc.</option>
                            <option>Sy.B.Sc.</option>
                            <option>Ty.B.Sc.</option>
                            <option>Fy.M.Sc.</option>
                            <option>Fy.M.Sc.</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect2">
                             <Form.Label>Division</Form.Label>
                                
                            <Form.Control as="select">
                            <option>A</option>
                            <option>B</option>
                            <option>C</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect3">
                             <Form.Label>Subject</Form.Label>
                                
                            <Form.Control as="select">
                            <option>Subject1</option>
                            <option>Subject2</option>
                            <option>Subject3</option>
                            </Form.Control>
                        </Form.Group>
                        <button class="btn btn-primary" type="submit">View</button>
                        </Form>
                         </div>                             
                        </Col>
                        
                        <Col xs lg="6">
                        <Table responsive="lg">
                        <caption>Previous Assignments</caption> 
                        <thead>
                            <tr>
                            <th>Sr.no</th>
                            <th>Title</th>
                            <th>Start date</th>
                            <th>Last Date of submission</th>
                            <th>File</th>
                            <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                            <td>1</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td> <Button variant="outline-light" ><img width="30px" height="30px" src={edit} /></Button></td>
                            </tr>
                            <tr>
                            <td>2</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td><Button variant="outline-light" ><img width="30px" height="30px" src={edit} /></Button></td>
                            </tr>
                            <tr>
                            <td>3</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td> <Button variant="outline-light" ><img width="30px" height="30px" src={edit} /></Button></td>
                            </tr>
                        </tbody>
                        </Table>
                        </Col>
                        <Col xs lg="3"> 
                        <Table responsive="lg" style={{backgroundColor:'#FFEDED'}}>
                        <caption>Draft</caption>
                        <thead style={{backgroundColor:'#FFCCCC'}}>
                            <tr>
                            <th>Sr.no</th>
                            <th>Title</th>
                            <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                            <td>1</td>
                            <td>Table cell</td>
                            <td> <Button variant="outline-light" ><img width="30px" height="30px" src={edit} /></Button></td>
                            </tr>
                            <tr>
                            <td>2</td>
                            <td>Table cell</td>
                            <td><Button variant="outline-light" ><img width="30px" height="30px" src={edit} /></Button></td>
                            </tr>
                            <tr>
                            <td>3</td>
                            <td>Table cell</td>
                            <td> <Button variant="outline-light" ><img width="30px" height="30px" src={edit} /></Button></td>
                            </tr>
                        </tbody>
                        </Table>
                        </Col>
                        </Row>
                    </Tab>
                    <Tab eventKey="received" title=" Received Assignments">
                    <Table responsive="lg">
                        <thead>
                            <tr>
                            <th>Sr.no</th>
                            <th>From</th>
                            <th>class</th>
                            <th>Division</th>
                            <th>Subject</th>
                            <th>File</th>
                            <th>Evaluate</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                            <td>1</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td> <Button variant="outline-light" ><img width="30px" height="30px" src={evaluation} /></Button></td>
                            </tr>
                            <tr>
                            <td>2</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td> <Button variant="outline-light" ><img width="30px" height="30px" src={evaluation} /></Button></td>
                            </tr>
                            <tr>
                            <td>3</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td> <Button variant="outline-light" ><img width="30px" height="30px" src={evaluation} /></Button></td>
                            </tr>
                        </tbody>
                        </Table>
                    </Tab>
                    <Tab eventKey="marks" title="Marks and Reports">
                        <div>
                            <Row>
                            <Col xs lg="4">
                            <Form>
                             <Form.Group controlId="exampleForm.ControlSelect1">
                             <Form.Label>Class</Form.Label>
                                
                            <Form.Control as="select">
                            <option>Fy.B.Sc.</option>
                            <option>Sy.B.Sc.</option>
                            <option>Ty.B.Sc.</option>
                            <option>Fy.M.Sc.</option>
                            <option>Fy.M.Sc.</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect2">
                             <Form.Label>Division</Form.Label>
                                
                            <Form.Control as="select">
                            <option>A</option>
                            <option>B</option>
                            <option>C</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="exampleForm.ControlSelect3">
                             <Form.Label>Subject</Form.Label>
                                
                            <Form.Control as="select">
                            <option>Subject1</option>
                            <option>Subject2</option>
                            <option>Subject3</option>
                            </Form.Control>
                        </Form.Group>
                        
                        <Form.Group controlId="exampleForm.ControlSelect4">
                             <Form.Label>Title</Form.Label>
                                
                            <Form.Control as="select">
                            <option>Title1</option>
                            <option>Title2</option>
                            <option>Title3</option>
                            </Form.Control>
                        </Form.Group>
                        <button class="btn btn-primary" type="submit">View</button>
                                </Form>
                                </Col>
                            </Row>

                        </div>
                     </Tab>
                     
                </Tabs>
                
            </Container>
            */
           
            
           );
        }
    }
     

const mapStateToProps=(state)=>{
    return{
        username:state.username,
    }
}

export default connect(mapStateToProps)(Teacher);