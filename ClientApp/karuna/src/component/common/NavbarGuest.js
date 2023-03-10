import React from 'react';
import { Link } from 'react-router-dom';

const NavbarGuest = () => {
  return (
    <nav>
      <ul>
      <li>
          <Link to="/home">Home</Link>
        </li>
        <li>
          <Link to="/login">Login</Link>
        </li>
        <li>
          <Link to="/signup">Signup</Link>
        </li>
      </ul>
    </nav>
  );
};

export default NavbarGuest;
