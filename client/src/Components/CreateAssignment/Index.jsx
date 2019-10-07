import React, { Component } from 'react';
import {connect} from "react-redux"
import { Button,Row,Form,Col, Alert } from 'react-bootstrap';
import DatePicker from 'react-date-picker'
class CreateAssignment extends Component {
    constructor()
    {
        super()
        this.state={
            fields:{},
            errors:{title:null,content:null,attachments:null,totalMarks:null,submissionDate:null,subjects:null,type:null},
            submissionDate:new Date(),
            selectedFiles:null
        }
        this.handleNext=this.handleNext.bind(this)
        this.handleChange=this.handleChange.bind(this)
        this.handleChange1=this.handleChange1.bind(this)
        this.handleFiles=this.handleFiles.bind(this)
    }
    handleChange1 = date => {
        this.setState({
          submissionDate: date
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
    handleFiles(e)
    {
        this.setState({
            selectedFiles: e.target.files,
           })
    }
    componentDidMount()
    {
        this.props.onbchange();
        
    }
    handleNext(e)
    {
        if(this.validateForm())
        {
            alert("success")
            this.props.onnext();
            this.props.history.push("/SelectDivision")
        }
        else
        {
            e.preventDefault()
        }
    }
    validateForm()
    {
        let formIsValid=true
        let fields=this.state.fields
        let errors={}
        if(!fields['title'])
        {
            formIsValid=false;
           errors['title']=true 
        }
        if(!fields['content'])
        {
            formIsValid=false;
           errors['content']=true 
        }
        if(this.state.selectedFiles===null)
        {
            formIsValid=false;
           errors['attachments']=true 
        }
        if(!fields['totalMarks'])
        {
            formIsValid=false;
           errors['totalMarks']=true 
        }
        if (typeof fields["totalMarks"] !== "undefined")
        {
            if(!(fields['totalMarks']>0))
            {
                formIsValid=false
                errors['totalMarks']=true
            }
        }
        if(this.state.submissionDate===null)
        {
            formIsValid=false;
           errors['submissionDate']=true 
        }
        if(!fields['subjects'])
        {
            formIsValid=false;
           errors['subjects']=true 
        }
        if(!fields['type'])
        {
            formIsValid=false;
           errors['type']=true 
        }
        
        this.setState({errors:errors})
        return formIsValid;
        
    }
    render() { 
        return ( 
            <Row style={{margin:0,padding:0,width:"100%",backgroundColor:"#B9D3FF",height:"58vh"}}>
            <Form style={{backgroundColor:"white",padding:30,width:"90%",border:"1px solid  #9FC3FD",marginLeft:50,marginTop:20,marginBottom:10}}>
            <Form.Row>
                <Form.Group as={Col}  >
                    <Form.Label >Title</Form.Label>
                    <Form.Label id="mandatory">&nbsp;*</Form.Label>
                    <Form.Control 
                        type="text" 
                        name="title"
                        isValid={this.state.errors.title===null?(null):(!this.state.errors.title)}
                        isInvalid={this.state.errors.title}
                        onChange={this.handleChange}
                    >
                                
                    </Form.Control>
                </Form.Group>
                <Form.Group as={Col}  >
                    <Form.Label >Content</Form.Label>
                    <Form.Label id="mandatory">&nbsp;*</Form.Label>
                    <Form.Control 
                        type="text" 
                        name="content"
                        isValid={this.state.errors.content===null?(null):(!this.state.errors.content)}
                        isInvalid={this.state.errors.content}
                        onChange={this.handleChange}
                    >
                                
                    </Form.Control>
                </Form.Group>
                <Form.Group as={Col}  >
                    <Form.Label >Attachments</Form.Label>
                    <Form.Label id="mandatory">&nbsp;*</Form.Label>
                    <Form.Control 
                        type="file" 
                        name="attachments"
                        multiple
                        isValid={this.state.errors.attachments===null?(null):(!this.state.errors.attachments)}
                        isInvalid={this.state.errors.attachments}
                        onChange={this.handleFiles}
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
                    <Form.Label id="mandatory">&nbsp;*</Form.Label>
                    <Form.Control 
                        type="number" 
                        name="totalMarks"
                        isValid={this.state.errors.totalMarks===null?(null):(!this.state.errors.totalMarks)}
                        isInvalid={this.state.errors.totalMarks}
                        onChange={this.handleChange}
                    >
                                
                    </Form.Control>
                </Form.Group>
                <Form.Group as={Col}  >
                    <Form.Label >Submission Due Date</Form.Label>
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
                            value={this.state.submissionDate}
                            onChange={this.handleChange1}
                            isValid={this.state.errors.submissionDate===null?(null):(!this.state.errors.submissionDate)}
                            isInvalid={this.state.errors.submissionDate}
                        />
                                
                </Form.Group>
            </Form.Row>
            <Form.Row>
                <Form.Group as={Col}>
                    <Form.Label >Subjects</Form.Label>
                    <Form.Label id="mandatory">&nbsp;*</Form.Label>
                    <Form.Control as="select"
                        name="subjects"
                        isValid={this.state.errors.subjects===null?(null):(!this.state.errors.subjects)}
                        isInvalid={this.state.errors.subjects}
                        onChange={this.handleChange}
                    >
                        <option selected disabled>--Select--</option>
                        <option>S1</option>
                                
                    </Form.Control>
                </Form.Group>
                <Form.Group as={Col}>
                    <Form.Label >Type</Form.Label>
                    <Form.Label id="mandatory">&nbsp;*</Form.Label>
                    <Form.Control as="select"
                        name="type"
                        isValid={this.state.errors.type===null?(null):(!this.state.errors.type)}
                        isInvalid={this.state.errors.type}
                        onChange={this.handleChange}
                    >
                        <option selected disabled>--Select--</option>
                        <option>Theory Assignment</option>
                        <option>Practical Assignment</option>
                                
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