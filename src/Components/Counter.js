import React, { Component } from "react";

class Count extends Component {
  constructor() {
    super();
    this.state = {
      count: 0,
    };
  }

  increment() {
    this.setState((prev_val) => ({
      count: prev_val.count + 1,
    }));
  }

  render() {
    return (
      <div>
        <div>count - {this.state.count}</div>;
        <button onClick={() => this.increment()}>Increment</button>
      </div>
    );
  }
}

export default Count;
