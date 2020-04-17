import React from 'react';
import { Container, Navbar } from 'react-bootstrap';

class Header extends React.Component {
  render() {
    return (
      <Navbar bg="light">
        <Navbar.Brand href="/"><h3>Github Search Project</h3></Navbar.Brand>
      </Navbar>
    );
  }
}

export default Header;
