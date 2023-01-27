import React from "react";

const SayName = (props) => (
  <div>
    <h1>
      Hello {props.name} a.k.a {props.sangwaName}
    </h1>
    <h4>
      I want to add more name, my other friend is called {props.friend}
    </h4>
    {props.children}
  </div>
);

export default SayName;
