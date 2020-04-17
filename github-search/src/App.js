import React from 'react';
import './App.scss';
import { BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom";

// Import components
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './components/Home';
import NotFound from './components/NotFound';
import Search from './components/Search';

function App() {
  return (
    <Router>

      <Header></Header>

      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/search/:searchString" component={Search} />
        <Route path="*" component={NotFound} />
      </Switch>

      <Footer></Footer>
    </Router>
  );
}

export default App;
