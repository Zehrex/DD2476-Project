import React from 'react';
import { Container } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch } from '@fortawesome/free-solid-svg-icons'

class Home extends React.Component {
  constructor(props) {
    super(props)
    this.search = this.search.bind(this);
  }

  search(event) {
    if (event.keyCode === undefined || event.keyCode === 13) {
      event.preventDefault();
      console.log(event);
      let searchString = document.getElementById("searchField").value
      this.props.history.push('/search/' + searchString);
      window.location.reload();
    }
  }

  render() {
    return (
      <Container>
        <div id="home">
          <div className="home-inner">
            <h1>Search all Java Github Projects!</h1>

            <form className="form-inline my-2 my-lg-0">
              <input className="form-control" id="searchField" type="search" placeholder="Type something..." aria-label="Type something..." onKeyDown={this.search}/>
              <button className="model-search-component button button-secondary" id="nav-search-icon" onClick={this.search}>Search!</button>
            </form>
          </div>
        </div>
      </Container>
    );
  }
}

export default Home;
