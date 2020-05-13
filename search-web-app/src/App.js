import React from 'react';
import './App.scss';
import { BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom";

// Import components
import Search from './Search';
import Home from './Home';


function App() {
  return (
    <Router>

      <Switch>
        <Route path="/search" exact component={Search} />
        <Route path="/" exact component={Home} />
      </Switch>

    </Router>
  );
}

export default App;
