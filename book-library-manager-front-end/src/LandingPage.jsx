import React from 'react';
import Button from 'react-bootstrap/Button';
import 'bootstrap-icons/font/bootstrap-icons.css'; 
import { Link } from 'react-router-dom';
import './Style/landing.css';
export default function LandingPage() {
  return (
    <div className="landing-container">
      <h1 className="welcome-title">Welcome to Public Library</h1>
      <Link to={'/log'}><Button className="login-button">
        <i className="bi bi-box-arrow-in-right"></i> LogIn
      </Button></Link>
      
    </div>
  );
}