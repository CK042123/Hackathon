import React from 'react';
import PropTypes from 'prop-types';
import Heading from 'terra-heading';
import Fieldset from 'terra-form/lib/Fieldset';
import Field from 'terra-form-field';
import Input from 'terra-form/lib/Input';
import Select from 'terra-form-select';
import Button from 'terra-button';
import './Frontend.css';

class Frontend extends React.Component
{

  constructor(props){
     super(props);
     this.state={weights:{weight:"", weightunit:""}};
   }


/*getInitialState(){
  weights:{weight:"", weightunit:"",};
}*/

// Update Weight Information
updateWeight(wt,wtunit){
  this.setState({weights:{weight:wt,weightunit:wtunit}});
}

// Save Weight Information
saveWeight(){
  let weights = this.state.weights.slice();
  weights.push({weight:this.state.weights.weight,weightunit:this.state.weights.weightunit});
  this.setState({weights});
}

// Reset Weight Information
resetWeight(){
  this.setState({weights:{weight:"",weightunit:""}})
}

  render()
  {
    const {weights} = this.state;
    return(
      <div className="App">
        <Heading id ="App-Header" level={1} size="medium" weight={700}>
          ClinicalWeightValidation
        </Heading>

        <Fieldset className="App-Controls">
          <Field label="Weight">
          <Input type="text"
          value={weights.weight}
          placeholder="Enter the Weight Value"
          // event.target.value -> gives the text entered by user
          onChange = {(event)=>this.updateWeight(event.target.value, weights.weightunit)}/>
          </Field>
          <Field label="Weight unit">
            <Select defaultValue="" onChange={(event)=>this.updateWeight(weights.weight, event.target.value)} value={weights.weightunit}>
              <Select.Option value="kg" display="kg" />
              <Select.Option value="g" display="g" />
              <Select.Option value="lb" display="lb" />
            </Select>
          </Field>
            <div className="App-Button">
            <Button onClick={(event)=>this.saveWeight()} id="Savebutton" text="Save" />
            <Button onClick={(event)=>this.resetWeight()} id="Resetbutton" text="Reset" />
            </div>
          </Fieldset>
      </div>
    )
  }
}

export default Frontend;