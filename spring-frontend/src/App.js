javascript
import React from 'react';
import { fetchData } from './api';

function App() {
  const data = fetchData();
  return (
    <div>
      <h1>Changes Nocomments App</h1>
      <p>{data.message}</p>
    </div>
  );
}

export default App;