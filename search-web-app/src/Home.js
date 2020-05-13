import React from 'react';
import { Link } from 'react-router-dom';
import Splash from './assets/splash.png';

class Home extends React.Component {
  render() {
    return (
      <React.Fragment>
        <section id="splash">
          <div className="row">
            <div className="col-md-7 splash-image">
              <img src={Splash} alt="Illustration of a woman searching with a flashlight"/>
            </div>
            <div className="col-md-5">
              <div className="splash-title">
                <h1><span>Java Github Search</span></h1>
                <p>Search through a multitude of Java Github Repositories in an instant!</p>
                <Link to="/search/"><button className="button button-primary">Start Searching Now!</button></Link>
              </div>
            </div>
          </div>
        </section>

        <section id="models-splash">
          <div className="container">
            <h1 className="text-center">Search with specific Java properties</h1>
            <div className="row">
              <div className="col-md-4 model-splash">
                <h3>Method Name</h3>
                <p>The easiest way to search through the index is by the name of the method. Enter in a desired method name and the search engine will find any methods matching the name, or methods with a similar spelling.</p>
              </div>
              <div className="col-md-4 model-splash">
                <h3>Return Type</h3>
                <p>Return type is one of the few properties that can filter by in the search engine. Simply browse the list of available return types in the right hand sidebar to choose your desired return type.</p>
              </div>
              <div className="col-md-4 model-splash">
                <h3>Repo Popularity</h3>
                <p>One measure of the quality and popularity of code is the number of stars its parent repository has. Search for code that has your desired star rating, or sort code from highest rated to lowest. </p>
              </div>
            </div>
          </div>
        </section>

        <section id="splash-call-to">
          <div className="splash-call-to-content">
            <h2>Find code for your project today!</h2>
          </div>
        </section>
      </React.Fragment>
    );
  }
}

export default Home;
