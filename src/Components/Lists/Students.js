import React from "react";

function Students({ student }) {
  return (
    <div>
      <h2>
        I am {student.name} I am {student.age} old and I have skills in
        {student.skill}
      </h2>
    </div>
  );
}

export default Students;
