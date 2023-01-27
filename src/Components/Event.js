import React from "react";

function Event(props) {
  return (
    <div>
      <button onClick={() => props.clicked_event()}>Call</button>
    </div>
  );
}

export default Event;
