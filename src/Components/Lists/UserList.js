import React from "react";
import Students from "./Students";
function UserList() {
  const users = ["SANGWA", "ROBERT"];
  const usersList = users.map((name) => <h2>{name}</h2>);

  const students = [
    { id: 0, name: "SANGWA", age: 33, skill: "PROGRAMMING JAVA" },
    { id: 1, name: "ROBERT", age: 29, skill: "PYTHON" },
    { id: 2, name: "ETIENNE", age: 30, skill: "JAVA PROGRAMMING" },
  ];

  const studentList = students.map((student) => (
    <Students key={student.id} student={student} />
  ));

  return <div>{studentList}</div>;
}

export default UserList;
