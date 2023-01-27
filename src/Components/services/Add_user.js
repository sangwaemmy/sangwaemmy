import React, { Component } from "react";
import HeaderComponent from "./headerFooter/HeaderComponent";

class Add_user extends Component {
  constructor(props) {
    super(props);

    this.state = {
      account_id: "",
      username: "",
      password: "",
    };
  }

  changedImage = (event) => {
    // var files = e.target.files; // FileList object
    console.log(`Below is the event`);
    console.log(event);
    // var output = [];
    // fileCount = files.length;
    // totalFileLength = 0;
    // for (var i = 0; i < fileCount; i++) {
    //     var file = files[i];
    //     output.push(file.name, " (", file.size, " bytes, ", file.lastModifiedDate.toLocaleDateString(), ")");
    //     output.push("<br/>");
    //     debug("add " + file.size);
    //     totalFileLength += file.size;
    // }
    //   document.getElementById("selectedFiles").innerHTML = output.join("");
    //   debug("totalFileLength:" + totalFileLength);
  };

  render() {
    return (
      <>
        <HeaderComponent />
        <div className="container border border-light">
          <div className="row d-flex justify-content-center ">
            <div className="col-6">
              <form>
                <fieldset>
                  <div className="form-group row">
                    <label htmlFor="staticEmail" className="col-sm-2 col-form-label">
                      Email
                    </label>
                    <div className="col-sm-10">
                      <input type="text" className="form-control" id="staticEmail" />
                    </div>
                  </div>
                  <div className="form-group row">
                    <label htmlFor="inputPassword" className="col-sm-2 col-form-label">
                      Password
                    </label>
                    <div className="col-sm-10">
                      <input type="password" className="form-control" id="inputPassword" />
                    </div>
                  </div>
                </fieldset>
              </form>
            </div>
            <button className="btn btn-primary float-right ">Save</button>
          </div>

          <div className="row">
            <input type="file" onChange={(event) => this.changedImage()} />
          </div>
        </div>
      </>
    );
  }
}

export default Add_user;
