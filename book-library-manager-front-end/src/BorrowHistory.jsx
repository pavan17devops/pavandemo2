import axios from 'axios';
import React, { useEffect, useState } from 'react'
import './Style/borrowhistory.css'
export default function BorrowHistory() {
    const [book, setBook] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/BorrowRecord')
            .then((resp) => {
                console.log(resp.data);
                setBook(resp.data.data)
            }).catch((error) => {
                console.error(error);
            })
    }, [])

    return (
        <div>
            <table className="books-table">
                <thead>
                    <tr>
                        <th className="table-header">Id</th>
                        <th className="table-header">Borrow Date</th>
                        <th className="table-header">Return_Date</th>
                        <th className="table-header">Returned</th>
                        <th className="table-header">Book Id</th>
                        <th className="table-header">User Id</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        book.map((allbook) => (
                            <tr key={allbook.id} className="table-row">
                                <td className="table-cell">{allbook.borrowDate}</td>
                                <td className="table-cell">{allbook.id}</td>
                                <td className="table-cell">{allbook.returnDate}</td>
                                <td className="table-cell">{allbook.returned ? "Yes" : "No"}</td>
                                <td className="table-cell">{allbook.book?.id}</td>
                                <td className="table-cell">{allbook.user?.id}</td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}