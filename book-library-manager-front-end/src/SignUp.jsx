import axios from 'axios';
import React, { useState } from 'react';

export default function SignUp() {
    const [userData, setUserData] = useState({
        username: '',
        email: '',
        password: '',
        role: ''
    });

    const handleChange = (e) => {
        setUserData({...userData, [e.target.name]: e.target.value});
    };

    const signup = (e) => {
        e.preventDefault();

        if (!userData.role) {
            alert("Please select a role");
            return;
        }

        axios.post('http://localhost:8080/api/userent', userData)
        .then((resp) => {
            console.log(resp.data);
            alert("Successfully signed up");
        })
        .catch((error) => {
            console.error(error);
            alert("Signup failed: " + (error.response?.data?.message || "Unknown error"));
        });
    };

    return (
        <div>
            <form onSubmit={signup}>
                <label>Username:</label>
                <input type="text" name="username" value={userData.username} onChange={handleChange} />
                <br /><br />

                <label>Email:</label>
                <input type="email" name="email" value={userData.email} onChange={handleChange} />
                <br /><br />

                <label>Password:</label>
                <input type="password" name="password" value={userData.password} onChange={handleChange} />
                <br /><br />

                <label>Role:</label>
                <select name="role" value={userData.role} onChange={handleChange}>
                    <option value="">Select role</option>
                    <option value="USER">USER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>
                <br /><br />

                <button type="submit">Signup</button>
            </form>
        </div>
    );
}