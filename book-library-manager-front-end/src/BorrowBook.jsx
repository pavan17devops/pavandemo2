import axios from 'axios';
import React, { useContext, useRef } from 'react';
import { UserContext } from './UserContext';
import './Style/borrowbook.css'
export default function BorrowBook() {
    const { user } = useContext(UserContext);
    const bookidref = useRef();
    const borrowDateref = useRef();
    const returnDateref = useRef();

    const submitid = (e) => {
        e.preventDefault();
        const data = {
            book: { id: bookidref.current.value },
            user: { id: user.id },
            borrowDate: borrowDateref.current.value,
            returnDate: returnDateref.current.value,
            returned: false
        };

        axios.post('http://localhost:8080/BorrowRecord', data)
            .then((resp) => {
                console.log(resp.data);
                alert("Successfully borrowed the book");
            }).catch((error) => {
                console.error(error);
                alert("Failed to borrow book");
            });
    };

    return (
        <div className="borrow-container">
            <form className="borrow-form">
                <div className="form-group">
                    <label>Borrow Date: </label>
                    <input type="date" ref={borrowDateref} />
                </div>
                <div className="form-group">
                    <label>Return Date: </label>
                    <input type="date" ref={returnDateref} />
                </div>
                <div className="form-group">
                    <label className="form-label">Book ID:</label>
                    <input type="number" className="form-input" placeholder="Enter Book ID" ref={bookidref} />
                </div>
                <button onClick={submitid} className="form-button">Get book</button>
            </form>
        </div>
    );
}
