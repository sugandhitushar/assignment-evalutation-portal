import React, { Component } from 'react';
import { Row } from 'react-bootstrap';
import Bread from "../Bread/Index"
class CreateClass extends Component {
    render() { 
        return ( 
            <Row  style={{padding:0,margin:0,width:"109%",height:"68vh"}}>
                <Bread/>
            </Row>
         );
    }
}


 
export default CreateClass;