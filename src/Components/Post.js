import React, { Component } from "react";
import HeaderComponent from "./services/headerFooter/HeaderComponent";
import axios from "axios";
class Post extends Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      password: "",
      respUsername: "",
      resPassword: "",
      listVisible: false,
    };
  }

  changeHandler = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  submitHndler = (e) => {
    e.preventDefault();
    console.log(this.state);

    axios
      .post("http://localhost:8080/test_react/users", this.state)
      .then((response) => {
        console.log(response);
        console.log(`DAta Object`);
        console.log(response.data);
        console.log(`Below field based`);
        this.setState({ respUsername: response.data.username, resPassword: response.data.password, listVisible: true });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  render() {
    const { username, password, respUsername, resPassword, listVisible } = this.state;
    return (
      <>
        <HeaderComponent />
        <div className="container">
          <div className="row">
            <h2 className={listVisible ? "" : "d-none"}>Data Saved successfully</h2>
            <div className="col-6">
              <form onSubmit={this.submitHndler}>
                <div className="form-group row">
                  <label className="col-sm-2 col-form-label">username</label>
                  <div className="col-sm-10">
                    <input type="text" name="username" className="form-control" value={username} onChange={this.changeHandler} />
                  </div>
                </div>
                <div className="form-group row">
                  <label className="col-sm-2 col-form-label">password</label>
                  <div className="col-sm-10">
                    <input type="password" name="password" className="form-control" value={password} onChange={this.changeHandler} />
                  </div>
                </div>
                <button type="submit" className="btn btn-primary float-right push-right mb-2">
                  Save User
                </button>
              </form>
          </div>

            <div className="col-6 d-flex justify-content-start ">
              <ul className={listVisible ? "" : "d-none"}>
                <li id="username">{respUsername}</li>
                <li id="password">{resPassword}</li>
              </ul>
            </div>
          </div>
        </div>
      </>
    );
  }
}

export default Post;
