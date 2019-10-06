import React, { Component } from 'react';
import {connect} from "react-redux"
import { Button,Row,Form,Col } from 'react-bootstrap';
import DatePicker from 'react-date-picker'
class CreateAssignment extends Component {
    constructor()
    {
        super()
        this.handleNext=this.handleNext.bind(this)
    }
    componentDidMount()
    {
        this.props.onbchange();
        
    }
    handleNext(e)
    {
        this.props.onnext();
        this.props.history.push("/SelectDivision")
    }
    render() { 
        return ( 
            <Row style={{margin:0,padding:0,width:"100%",backgroundColor:"#B9D3FF",height:"58vh"}}>
            <Form style={{backgroundColor:"white",padding:30,width:"90%",border:"1px solid  #9FC3FD",marginLeft:50,marginTop:20,marginBottom:10}}>
            <Form.Row>
                <Form.Group as={Col}  >
                    <Form.Label >Title</Form.Label>
                    <Form.Control 
                        type="text" 
                        name="title"
                        //isValid={this.state.errors.division===null?(null):(!this.state.errors.division)}
                        //isInvalid={this.state.errors.division}
                        onChange={this.handleChange}
                    >
                                
                    </Form.Control>
                </Form.Group>
                <Form.Group as={Col}  >
                    <Form.Label >Content</Form.Label>
                    <Form.Control 
                        type="text" 
                        name="content"
                        //isValid={this.state.errors.division===null?(null):(!this.state.errors.division)}
                        //isInvalid={this.state.errors.division}
                        onChange={this.handleChange}
                    >
                                
                    </Form.Control>
                </Form.Group>
                <Form.Group as={Col}  >
                    <Form.Label >Attachments</Form.Label>
                    <Form.Control 
                        type="file" 
                        name="title"
                        multiple
                        //isValid={this.state.errors.division===null?(null):(!this.state.errors.division)}
                        //isInvalid={this.state.errors.division}
                        onChange={this.handleChange}
                    >
                                
                    </Form.Control>
                </Form.Group>
            </Form.Row>
            <Form.Row>
                <Form.Group as={Col}  >
                    <Form.Label >Remarks</Form.Label>
                    <Form.Control as="textarea" rows="2" 
                        name="remarks"
                        //isValid={this.state.errors.division===null?(null):(!this.state.errors.division)}
                        //isInvalid={this.state.errors.division}
                        onChange={this.handleChange}
                    >
                                
                    </Form.Control>
                </Form.Group>
                <Form.Group as={Col}  >
                    <Form.Label >Total Marks</Form.Label>
                    <Form.Control 
                        type="number" 
                        name="totalMarks"
                        //isValid={this.state.errors.division===null?(null):(!this.state.errors.division)}
                        //isInvalid={this.state.errors.division}
                        onChange={this.handleChange}
                    >
                                
                    </Form.Control>
                </Form.Group>
                <Form.Group as={Col}  >
                    <Form.Label >Submission Due Date</Form.Label>
                    <br></br>
                    <DatePicker
                        required
                        popperModifiers={{
                            preventOverflow: {
                              enabled: true,
                            },
                          }}
                            dateFormat="dd/MM/yyyy"
                            //value={this.state.submissionDate}
                            onChange={this.handleChange1}
                        />
                                
                </Form.Group>
            </Form.Row>
            <Form.Row>
                <Form.Group as={Col}>
                    <Form.Label >Subjects</Form.Label>
                    <Form.Control as="select"
                        name="subjects"
                        //isValid={this.state.errors.division===null?(null):(!this.state.errors.division)}
                        //isInvalid={this.state.errors.division}
                        onChange={this.handleChange}
                    >
                        <option selected disabled>--Select--</option>
                                
                    </Form.Control>
                </Form.Group>
                <Form.Group as={Col}>
                                <br></br><Button onClick={this.handleNext}>Next>>></Button>
                </Form.Group>
            </Form.Row>
            </Form>
            </Row> );
    }
}
const mapDispatchToProps=(dispatch)=>{
    return{
        //Action Creators
        onbchange:()=>dispatch({type:'tbreadchange',val:"c"}),
        onnext:()=>dispatch({type:'tbreadchange',val:"d"}),
        
    }
  }
  const mapStateToProps=(state)=>
  {
      return{
            token:state.token
      }
  }
  
 
export default connect(mapStateToProps,mapDispatchToProps)(CreateAssignment);