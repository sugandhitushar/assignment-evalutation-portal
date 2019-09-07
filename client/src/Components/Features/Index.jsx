import React, { Component } from 'react';
import { Accordion,Card,Button } from 'react-bootstrap';
import FeaturesCss from '../Features/Features.css'
class Features extends Component {
    
    render() { 
        return ( 
            <div>
            
               <Accordion defaultActiveKey="0" style={{marginTop:"15vh"}}>
                <Card>
                <Card.Header>
                    <Accordion.Toggle as={Button} variant="link" eventKey="0">
                        General Instructions
                    </Accordion.Toggle>
                </Card.Header>
                    <Accordion.Collapse eventKey="0">
                        <Card.Body><ul>
                            <li>
                                Please change your password through confirmation link sent via email to log in for the first time.
                            </li>
                            
                            <li>
                                Before you begin submitting you should prepare your submission as a single file.
                            </li>

                            </ul></Card.Body>
                    </Accordion.Collapse>
                </Card>
                <Card>
                <Card.Header>
                    <Accordion.Toggle as={Button} variant="link" eventKey="1">
                        Instructions for Students
                    </Accordion.Toggle>
                </Card.Header>
                    <Accordion.Collapse eventKey="1">
                        <Card.Body><ul>
                            <li>
                                When you log in , you will get all the assignments on your dashboard with a reply button.
                            </li>
                            
                            <li>
                                To upload your submission click on reply button and add the submission.
                            </li>
                            <li>
                                Once you submit your assignment , you cannot change it.
                            </li>
                            <li>
                                After last submission date , you cannot submit the assignment.
                            </li>
                            
                            <li>
                                You have to confirm that you agree with the marks you scored or report the issues. 
                            </li>
                            </ul></Card.Body>
                    </Accordion.Collapse>
                </Card>
                   </Accordion> 
            </div>
         );
    }
}
 
export default Features ;