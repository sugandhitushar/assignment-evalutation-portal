import React from 'react';
import Header from './Header';
import Login from './Login';
import Admin from './Admin.js';
import {Container,Row,Col } from 'react-bootstrap';
class Main extends React.Component {
    constructor(props)
    {
        super(props);
        this.state={
            islogin:"false",
            type:""
        }
        this.handleLogin=this.handleLogin.bind(this);
        this.myCallback=this.myCallback.bind(this);
    }
    myCallback=(data)=>{
        this.setState({type:data});
    }
    handleLogin(e)
    {
           if(this.state.islogin==='false')
           {
               this.setState({islogin:"true"});
           } 
           else if(this.state.islogin==='true')
           {
               this.setState({islogin:"false"});
           }
    }
    render() {
        if(this.state.islogin==='false')
        { 
        return ( 
            <Container fluid={true}>
  <Row>
  <Col xl={1} style={{backgroundColor: 'orange',border:"1px solid red"}}><h6 style={{width:"100%"}}>logo</h6></Col>
   <Col xs xl={11} lg md sm style={{border:"1px solid blue",backgroundColor:"aqua"}}><h2 style={{textAlign:"center"}}>PVG's College of Science</h2></Col>
  </Row>
  <Row>
    <Col style={{backgroundColor: '#f1f1f1',height:'570px'}}>Features</Col>
    <Col style={{border: '1px solid red',height:'570px'}}><Login islogin={this.handleLogin} call={this.myCallback}/></Col>
  </Row>
  <Row>
      <Col style={{backgroundColor: 'orange',height:'37px'}}>Footer</Col>
  </Row>
</Container>
         );
        }
        else if(this.state.type==="admin")
        {
            return(<Admin islogin={this.handleLogin}/>);
        }
        else{
            return(<h2>hiiii</h2>);
        }
    }
}
 
export default Main;