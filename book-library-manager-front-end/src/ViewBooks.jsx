import { useState, useEffect } from "react";
import axios from "axios";
import './Style/viewbooks.css'
export default function ViewBooks() {
    const [book, setBook] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/book")
            .then(response => {
                setBook(response.data.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error("Error fetching books:", error);
                setBook([]);
            });
    }, []);

   return (
  <div className="books-container">
    <table className="books-table">
      <thead>
        <tr>
          <th className="table-header">Id</th>
          <th className="table-header">Title</th>
          <th className="table-header">Author</th>
          <th className="table-header">Isbn</th>
          <th className="table-header">Genre</th>
          <th className="table-header">Total Copies</th>
          <th className="table-header">Available Copies</th>
        </tr>
      </thead>
      <tbody>
        {book.map((all) => (
          <tr key={all.id}>
            <td className="table-cell">{all.id}</td>
            <td className="table-cell">{all.title}</td>
            <td className="table-cell">{all.author}</td>
            <td className="table-cell">{all.isbn}</td>
            <td className="table-cell">{all.genre}</td>
            <td className="table-cell">{all.totalCopies}</td>
            <td className="table-cell">{all.availableCopies}</td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);
}