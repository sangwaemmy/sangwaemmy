import React, { Component } from "react";

class Form extends Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      comment: "",
      topic: "",
    };
  }

  changeUsername = (event) => {
    this.setState({
      username: event.target.value,
    });
  };

  changeComment = (ev) => {
    this.setState({
      comment: ev.target.value,
    });
  };
  changeTopic = (event) => {
    this.setState({
      topic: event.target.value,
    });
  };
  formSubmitted = (event) => {
    alert(` ${this.state.username} ${this.state.comment} ${this.state.topic} `);
    event.preventDefault();
  };

  render() {
    const { username, comment, topic } = this.state;
    return (
      <>
        <form onSubmit={this.formSubmitted}>
          <div>
            <label>Username</label>
            <input
              type="text"
              value={username}
              onChange={this.changeUsername}
            />
          </div>
          <div>
            <label>Comment:</label>
            <textarea value={comment} onChange={this.changeComment}></textarea>
          </div>
          <div>
            <label>Topic</label>
            <select value={topic} onChange={this.changeTopic}>
              <option></option>
              <option>React</option>
              <option>Angular</option>
              <option>Vue</option>
            </select>
          </div>
          <button type="submit">Save</button>
        </form>
      </>
    );
  }
}

export default Form;
