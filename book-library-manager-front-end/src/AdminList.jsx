import { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import './Style/adminlist.css'
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

  let deletedata = (id) => {
    axios.delete(`http://localhost:8080/book/${id}`)
      .then((resp) => {
        console.log(resp.data);
        alert(`${id} deleted success`)
      }).catch((error) => {
        console.error(error);
        alert('failed to delete')
      })
  }
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
              <td className="table-cell"> <Link to={`/edit/${all.id}`}><button className="edit-button"> Edit book </button></Link></td>
              <td className="table-cell"><button onClick={() => { deletedata(all.id) }} className="delete-button" >delete book</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}