import React, { Component } from "react";
import Files_uploads from "../files_upload/Files_uploads";
import FooterComponent from "./headerFooter/FooterComponent";
import HeaderComponent from "./headerFooter/HeaderComponent";
import UserService from "./UserService";

class Bootstrap_test extends Component {
  constructor(props) {
    super(props);

    this.state = {
      users: [],
    };
    this.go_add_page = this.go_add_page.bind(this);
  }

  componentDidMount() {
    UserService.getUsers().then((res) => {
      this.setState({ users: res.data });
    });
  }

  go_add_page() {
    this.props.history.push("/add_user");
  }
  render() {
    return (
      <>
        {/* <HeaderComponent /> */}
        <h3>This is From BootstrapTest</h3>

        <div className="container mt-2 ">
          <div className="row">
            <div className="col-12">
              <div className="row">
                <div className="col-3">
                  <button className="btn btn-primary" onClick={this.go_add_page}>
                    Add more
                  </button>
                  <a className="btn btn-success float-left" href="/add_user">
                    Add user
                  </a>
                </div>
                <div className="col-9">
                  <Files_uploads />

                  <h2 className="text-center">Users List </h2>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="container border border-dark">
          <div className="row">
            <table className="table table-stripped table-bodered">
              <thead>
                <tr>
                  <td>Name</td>
                  <td>Last Name</td>
                  <td>Account</td>
                </tr>
              </thead>

              <tbody>
                {this.state.users.map((account) => (
                  <tr key={account.account_id}>
                    <td>{account.username}</td>
                    <td>{account.password}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

        <FooterComponent />
      </>
    );
  }
}

export default Bootstrap_test;
