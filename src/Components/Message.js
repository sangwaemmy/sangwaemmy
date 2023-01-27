import React, { Component } from "react";

class Mesg extends Component {
  constructor() {
    super();
    this.state = {
      message: "Welcome Visitor...",
    };
  }
  changeMessage() {
    this.setState({
      message: "Thank you for subscribing",
    });
  }
  sendEmail() {
    this.setState({
      message: "The email has been sent",
    });
  }
  render() {
    return (
      <div>
        <h1>{this.state.message}</h1>
        <button onClick={() => this.changeMessage()}>Subscribe</button>
        <button onClick={() => this.sendEmail()}>Send Emainl</button>
      </div>
    );
  }
}
export default Mesg;
