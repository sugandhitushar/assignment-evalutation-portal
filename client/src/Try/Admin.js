import React, { Component } from 'react';
import Header from './Header';
import CreateUser from "./CreateUser";
import { Container, Row, Col,Table, Button } from 'react-bootstrap';
class Admin extends Component {
    constructor(props)
    {
        super(props);
        this.handleLogout=this.handleLogout.bind(this);
    }
    handleLogout(e)
    {
        this.props.islogin();
    }
    render() { 
        return (
            <Container fluid="true">
                <Row>
                    <Header islogin={this.handleLogout}/>
                </Row>
                <Row style={{backgroundColor:"pink",height:"607px"}}>
                    <Col style={{border:"1px solid black"}}>
                    <CreateUser/>
                    </Col>
                    <Col>
                    <Table responsive>
                            <thead>
                                <th>username</th>
                                <th>Class</th>
                                <th>Type</th>
                                <th>Email</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>mayuri</td>
                                    <td>msc</td>
                                    <td>student</td>
                                    <td>monu</td>
                                    <td><Button variant="success"> edit</Button></td>
                                    <td><Button variant="danger"> Delete</Button></td>
                                </tr>
                                <tr>
                                    <td>udayati</td>
                                    <td>msc</td>
                                    <td>student</td>
                                    <td>udu</td>
                                    <td><Button variant="success"> edit</Button></td>
                                    <td><Button variant="danger"> Delete</Button></td>
                                </tr>
                            </tbody>
                        </Table>

                    </Col>
                </Row>
            </Container>
          );
    }
}
 
export default Admin;