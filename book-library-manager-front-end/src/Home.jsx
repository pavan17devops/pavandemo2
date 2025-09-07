import { useContext } from 'react';
import { UserContext } from './UserContext';
import AdminNavbar from './AdminNavbar';
import UserNavbar from './UserNavbar';
import './Style/home.css'
export default function Home() {
  const { user } = useContext(UserContext);

  if (!user) {
    return <div className="no-user-message">Please log in.</div>;
  }

  console.log('User object:', user);
  console.log('User role:', user.role);

  return (
    <div className="home-container">
      <h1 className="welcome-message">Welcome, {user.username}!</h1>
      {user.role === 'USER' ? (
        <div className="navbar-container">
          <UserNavbar />
        </div>
      ) : user.role === 'ADMIN' ? (
        <div className="navbar-container">
          <AdminNavbar />
        </div>
      ) : (
        <p className="error-message">No role-based options available for role: {user.role}</p>
      )}
    </div>
  );
}