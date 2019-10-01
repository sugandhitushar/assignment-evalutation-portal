const initialStatte={
    logged_in:'false',
    username:"",
    utype:"",
    token:{},
    login:"true",
    forget:"",
    new_user:"",
    bname:"c",
    cname:"",
    division:"",
    capacity:"",
    start_year:"",
    end_year:"",
    class_teacher:""
};

const reducer=(state=initialStatte,action)=>{

    const newState={...state};
    if(action.type==='Login')
    {
        newState.logged_in=action.val;
    }
    if(action.type==="details")
    {
        newState.username=action.username;
        newState.utype=action.utype;
    }
    if(action.type==='AuthenticationAction')
    {
        newState.token=action.val;
        console.log("in reducer token is :",newState.token);
    }
    if(action.type==='bchange')
    {
        newState.bname=action.val;
    }
    if(action.type==='store_class')
    {
        newState.cname=action.val;
    }
    if(action.type==='store_div')
    {
        newState.division=action.division;
        newState.capacity=action.capacity;
        newState.start_year=action.start_year;
        newState.end_year=action.end_year;
        newState.class_teacher=action.class_teacher;
    }
    return newState;
};
export default reducer;