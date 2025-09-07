// AdminNavbar.js
import { Link } from 'react-router-dom';
import './Style/adminnavbar.css'
export default function AdminNavbar() {
  return (
    <nav className="admin-navbar">
      <ul className="nav-list">
        <li><Link to="/add-book"className="nav-link">Add Book</Link></li>
        <li><Link to="/borrow-history"className="nav-link">View Borrow History</Link></li>
        <li><Link to='/list-of-books'className="nav-link">View Books</Link></li>
      </ul>
    </nav>
  );
}