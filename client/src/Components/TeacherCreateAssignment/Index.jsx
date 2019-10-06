import React, { Component } from 'react';
import { Row } from 'react-bootstrap';
import TeacherBread from "../TeacherBread/Index"
class TeacherCreateAssignment extends Component {
    render() { 
        return ( 
            <Row  style={{padding:0,margin:0,width:"109%",height:"68vh"}}>
                <TeacherBread/>
            </Row>
        
        );
    }
}
 
export default TeacherCreateAssignment;