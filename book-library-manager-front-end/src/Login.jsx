import React, { useRef, useContext } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { UserContext } from './UserContext';
import './Style/login.css'
export default function Login() {
  const { setUser } = useContext(UserContext);
  const usernameref = useRef();
  const passwordref = useRef();
  const nav = useNavigate();

  const loginuser = (e) => {
    e.preventDefault();
    const data = {
      username: usernameref.current.value,
      password: passwordref.current.value,
    };
    axios
      .post('http://localhost:8080/api/userent/validation', data)
      .then((resp) => {
        alert('login success');
        console.log(resp.data);
        setUser(resp.data.data);
        nav('/home');
      })
      .catch((error) => {
        console.log(error);
        alert('login fail');
      });
  }

  return (
    <div>
      <form id='logform'>
        <label htmlFor="">UserName:</label>
        <input type="text" name="username" ref={usernameref} id='uname'/>
        <br /><br />
        <label htmlFor="">Password:</label>
        <input type="password" name="password" ref={passwordref} id='pws'/>
        <br /><br />
        <button onClick={loginuser}>Login</button>
        <br /><br />
        <Link to={'/sign'}>Click here to signUp</Link>
      </form>
    </div>
  );
}
