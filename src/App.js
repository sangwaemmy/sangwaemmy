import "./App.css";

import SayName from "./Components/Displays";
import YouWelcome from "./Components/Welcome";
import Mesg from "./Components/Message";
import Count from "./Components/Counter";
import Event from "./Components/Event";
import Parent from "./Components/Parent";
import UserList from "./Components/Lists/UserList";
import Stylesheet from "./Components/Stylesheets/Stylesheet";
import Form from "./Components/Form";
import "bootstrap/dist/css/bootstrap.min.css";
import Bootstrap_test from "./Components/services/Bootstrap_test";
import HeaderComponent from "./Components/services/headerFooter/HeaderComponent";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Add_user from "./Components/services/Add_user";
import Post from "./Components/Post";
import MyProps from "./Components/Navigation/TestProps";
import ContentPage from "./Components/Navigation/ContentPage";
import Navbar from "./Components/Navigation/Navbar";


function App() {
  const redColor = {
    color: "red"
  }
  return (


    <div className="App">
      {/* <SayName name="SANGWA" sangwaName="CODEGURU">
        <p>Developer</p>
      </SayName> */}
      {/* <SayName name="Etienne" sangwaName="Dev" friend="Vive" /> */}
      {/* <YouWelcome name="SANGWA" />
      <YouWelcome name="Etienne" /> */}

      {/* <Mesg /> */}
      {/* <Count /> */}

      {/* <Parent /> */}

      {/* <UserList /> */}

      {/* <Stylesheet primary={true} /> */}
      {/* <Form /> */}

      
      
      {/* <Router>
        <Routes>
          <Route path="/"  element={<Bootstrap_test />} />
          <Route path="/users" element={<Bootstrap_test />} />
          <Route path="/add_user" element={<Add_user />} />
          <Route path="/post" element={<Post />} />
        </Routes>
      </Router> */}

      <Navbar />
       
        <Routes>
          <Route path="/" element={<Bootstrap_test/>} />
          <Route path="/Test" element={<ContentPage/>} />
        </Routes>
    



    </div>
  );
}

export default App;
