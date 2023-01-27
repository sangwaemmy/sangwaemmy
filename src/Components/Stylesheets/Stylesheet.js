import React from "react";
import "./MyStyles.css";

function Stylesheet(props) {
  let className = props.primary ? "primary" : "";
  return (
    <div>
      <h1 className={`${className} font-xl `}>StyleSheet</h1>
    </div>
  );
}

export default Stylesheet;
