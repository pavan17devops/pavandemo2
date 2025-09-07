// UserNavbar.js
import { Link } from 'react-router-dom';
import './Style/UserNavbar.css'
export default function UserNavbar() {
  return (
    <nav className="navbar">
      <ul className="nav-list">
        <li><Link to="/books" className="nav-link">View Books</Link></li>
        <li><Link to="/borrow" className="nav-link">Borrow Book</Link></li>
        <li><Link to="/return" className="nav-link">Return Book</Link></li>
      </ul>
    </nav>
  );
}