import React from 'react';
import './App.scss';
import { BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom";

// Import components
import Search from './Search';
import Home from './Home';

// Firebase App (the core Firebase SDK) is always required and
// must be listed before other Firebase SDKs
var firebase = require("firebase/app");

// Add the Firebase products that you want to use
require("firebase/auth");
require("firebase/firestore");

// TODO: Replace the following with your app's Firebase project configuration
var firebaseConfig = {
    apiKey: "AIzaSyCuZ4Nv4JLtA82oAu5zPJt19icKWtxy1n0",
    authDomain: "github-search-f1cfc.firebaseapp.com",
    databaseURL: "https://github-search-f1cfc.firebaseio.com",
    projectId: "github-search-f1cfc",
    storageBucket: "github-search-f1cfc.appspot.com",
    messagingSenderId: "608617866",
    appId: "1:608617866:web:2dce4d9324dc17168f3473"
  };
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

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
