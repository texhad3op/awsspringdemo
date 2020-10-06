'use strict';

import axios from 'axios';

const React = require('react');
const ReactDOM = require('react-dom');

class TemperatureComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { messages:[], targetTemperature:"", currentTemperature: ""};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleSubmit(event) {
        axios.post('/api/temperature/target', {
                                        targetTemperature:this.state.targetTemperature,
                                    });
        event.preventDefault();
    }

    handleChange(event) {
        this.setState({targetTemperature: event.target.value});
    }

    refresh() {
        axios.get('/api/temperature/current')
            .then((result) => {
            console.log(result.data);
                this.setState({currentTemperature:result.data});
        });
    }

    componentDidMount() {
        this.refresh();
        this.interval = setInterval(() => this.refresh(), 1000);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    render() {
        return (
                <div>
                    <div className="container">
                        <div className="row">
                            <div className="col">
                                <form onSubmit={this.handleSubmit.bind(this)}>
                                <div className="form-group">
                                    <input type="text" value={this.state.targetTemperature} onChange={this.handleChange} className="form-control" aria-describedby="emailHelp" placeholder="Фрагмент имени"></input>
                                </div>
                                <button type="submit" className="btn btn-primary">Set target temperature</button>
                                </form>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col">
                                <div className="list-group">Current temperature: {this.state.currentTemperature}</div>
                            </div>
                        </div>
                    </div>
                </div>
        );
    }
}

export default TemperatureComponent;