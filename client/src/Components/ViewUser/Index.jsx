import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import 'react-bootstrap-table2-filter/dist/react-bootstrap-table2-filter.min.css';
import 'react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css';
import paginationFactory from 'react-bootstrap-table2-paginator';
import React, { Component } from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import filterFactory, { selectFilter } from 'react-bootstrap-table2-filter';
let products=[{id:1,name:"a",quality:"good"},{id:2,name:"b",quality:"bad"},{id:3,name:"c",quality:"unknown"}]
let n=[]
const selectOptions = {
    'good': 'good',
    'bad': 'bad',
    'unknown': 'unknown'
  };

  const options = {
    // pageStartIndex: 0,
    sizePerPage: 2,
    hideSizePerPage: true,
    hidePageListOnlyOnePage: true
  };
  
  const columns = [{
    dataField: 'id',
    text: 'Product ID'
  }, {
    dataField: 'name',
    text: 'Product Name'
  }, {
    dataField: 'quality',
    text: 'Product Quailty',
    formatter: cell => selectOptions[cell],
  filter: selectFilter({
    options: selectOptions,
    defaultValue: 'good'
  })
  }];
  
class ViewUser extends Component {
  constructor()
  {
    super()
    //this.handleOnSelect=this.handleOnSelect.bind(this)
  }

  componentDidMount()
  {
    
      for(var i in products)
      {
        if(products[i].name==='a')
        {
          <Integer className="parseInt">
n.push(i)
          </Integer>
        }
      }
      
      
  }

  handleOnSelect = (row, isSelect) => {
    console.log(n,"array")
console.log(row.id,"====")
    if (isSelect && row.name === "a") {
      alert('Oops, You can not select Product ID which less than 3');
      return false; // return false to deny current select action
    }
    return true; // return true or dont return to approve current select action
  }
  

    render() { 
      

      const selectRow = {
        mode: 'checkbox',
        clickToSelect: true,
        onSelect: this.handleOnSelect,
        nonSelectable: n
        //onSelectAll: this.handleOnSelectAll
      };     
        return ( <BootstrapTable keyField='id' data={ products } selectRow={ selectRow } columns={ columns } filter={ filterFactory() } pagination={ paginationFactory(options) }/> );
    }
}
 
export default ViewUser;