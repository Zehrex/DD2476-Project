import React from 'react';

class Search extends React.Component {
  constructor(props) {
    super(props);
    const { match } = this.props;
    const searchString = match.params.searchString;

    this.state = {
      searchString: searchString,
      results: [],
      loaded: false
    };
  }

  search(searchString) {
    // TODO: Fetch and return the results
    this.setState({loaded: true});
  }

  componentDidMount() {
    this.search(this.state.searchString);
  }

  render() {
    // Page loaded
    if (this.state.loaded){
      return (
        <React.Fragment>
          <div className="container">
          <br/><br/>
            <h1 className="text-center"><span>Results</span></h1>

            <h4>Query is: {this.state.searchString}</h4>

            {this.state.results.length === 0 ? (
              <h4>No results match this query</h4>
            ) : (
              <React.Fragment>
                {this.state.results.map((result, index) => {
                  return (
                    <p>Result</p>
                  );
                })}
              </React.Fragment>
            )}
          </div>
        </React.Fragment>
      );
    }

    // invalid park name
    return (
      <p>Loading...</p>
    );
  }
}

export default Search;
