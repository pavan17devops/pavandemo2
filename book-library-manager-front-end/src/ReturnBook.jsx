import axios from 'axios';
import React, { useContext, useRef } from 'react';
import { UserContext } from './UserContext';
import './Style/returnbook.css'
export default function ReturnBook() {
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
            returned: true
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
        <div className="return-container">
            <form className="return-form">
                <div className="form-group">
                    <label className="form-label">Borrow Date:</label>
                    <input type="date" className="form-input" ref={borrowDateref} />
                </div>
                <div className="form-group">
                    <label className="form-label">Return Date:</label>
                    <input type="date" className="form-input" ref={returnDateref} />
                </div>
                <div className="form-group">
                    <label className="form-label">Book ID:</label>
                    <input type="number" className="form-input" placeholder="Enter Book ID" ref={bookidref} />
                </div>
                <button className="form-button" onClick={submitid}>Submit Book</button>
            </form>
        </div>
    );
}
