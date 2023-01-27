import React from 'react'
import { BrowserRouter, Link } from 'react-router-dom'
function Navbar() {
    let my_class = "background-color: #1c6156;  ";
    const my_styles = {
        color: "#fff",
    };
    const nav_styles = {
        backgroundColor: "#2d0733",
        fontStyle: "italic",
    };
    return (

        <div>
             
            <header>
                <nav style={nav_styles} className={`${my_class} navbar navbar-expand-sm navbar-dark p-3  `}>
                    <a className="navbar-brand" style={my_styles} href="/">
                        Users Management
                    </a>
                    <div className="collapse navbar-collapse" id="navbarsExample03">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item">
                                <a className="nav-link" style={my_styles} href="/users">
                                    Home
                                </a>
                            </li>
                            <li className="nav_link">
                                <a className="nav-link" href="/add_user" style={my_styles}>
                                    Add user
                                </a>
                            </li>
                            <li className="nav_link">
                                <a className="nav-link" href="/Post" style={my_styles}>
                                    Post
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>











        </div>
    )
}

export default Navbar