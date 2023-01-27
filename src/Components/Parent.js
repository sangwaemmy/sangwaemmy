import React, { Component } from "react";
import Event from "./Event";

class Parent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "SANGWA",
    };
    this.greet_me = this.greet_me.bind(this);
  }
  greet_me() {
    alert(`Hello ${this.state.name} `);
  }

  render() {
    return (
      <div>
        <Event clicked_event={this.greet_me} />
      </div>
    );
  }
}

export default Parent;
