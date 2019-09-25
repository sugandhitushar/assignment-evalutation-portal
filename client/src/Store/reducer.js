const initialStatte={
    logged_in:'false',
    username:"",
    utype:"",
    token:{},
    login:"true",
    forget:"",
    new_user:"",
    bname:"c"
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
    return newState;
};
export default reducer;