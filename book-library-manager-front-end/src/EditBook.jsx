import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react';
import { useParams } from 'react-router-dom';
import './Style/editbook.css'
export default function EditBook() {
  let param = useParams();
  let [book, setBook] = useState({
    title: "",
    author: "",
    isbn: "",
    genre: "",
    totalCopies: "",
    availableCopies: ""
  });

  useEffect(() => {
    axios.get(`http://localhost:8080/book/${param.id}`)
      .then((resp) => {
        console.log(resp.data);
        setBook({
          title: resp.data.title,
          author: resp.data.author,
          isbn: resp.data.isbn,
          genre: resp.data.genre,
          totalCopies: resp.data.totalCopies,
          availableCopies: resp.data.availableCopies
        });
      })
      .catch((error) => {
        console.error(error);
        alert('Failed to fetch book data');
      });
  }, [param.id]);

  let titleref = useRef();
  let authorref = useRef();
  let isbnref = useRef();
  let genreref = useRef();
  let totalCopiesref = useRef();
  let availableCopiesref = useRef();

  let editbook = (e) => {
    e.preventDefault();
    const book = {
      title: titleref.current.value,
      author: authorref.current.value,
      isbn: isbnref.current.value,
      genre: genreref.current.value,
      totalCopies: totalCopiesref.current.value,
      availableCopies: availableCopiesref.current.value
    };
    console.log(book);
    axios.put(`http://localhost:8080/book/${param.id}`, book)
      .then((resp) => {
        console.log(resp.data);
        alert('Book updated successfully');
        titleref.current.value = '';
        authorref.current.value = '';
        isbnref.current.value = '';
        genreref.current.value = '';
        totalCopiesref.current.value = '';
        availableCopiesref.current.value = '';
      })
      .catch((error) => {
        console.error(error);
        alert('Failed to update book. Check console for details.');
      });
  };

  return (
    <div className="edit-book-container">
      <form className="add-book-form">
        <div className="form-group">
          <label className="form-label">Title:</label>
          <input
            type="text"
            placeholder="Enter title"
            ref={titleref}
            className="add-book-input"
            required
            defaultValue={book.title}
          />
        </div>
        <div className="form-group">
          <label className="form-label">Author:</label>
          <input
            type="text"
            placeholder="Enter author"
            ref={authorref}
            className="add-book-input"
            required
            defaultValue={book.author}
          />
        </div>
        <div className="form-group">
          <label className="form-label">ISBN:</label>
          <input
            type="text"
            placeholder="Enter isbn"
            ref={isbnref}
            className="add-book-input"
            required
            defaultValue={book.isbn}
          />
        </div>
        <div className="form-group">
          <label className="form-label">Genre:</label>
          <input
            type="text"
            placeholder="Enter genre"
            ref={genreref}
            className="add-book-input"
            required
            defaultValue={book.genre}
          />
        </div>
        <div className="form-group">
          <label className="form-label">Total Copies:</label>
          <input
            type="number"
            placeholder="Enter total copies"
            ref={totalCopiesref}
            className="add-book-input"
            required
            min="0"
            defaultValue={book.totalCopies}
          />
        </div>
        <div className="form-group">
          <label className="form-label">Available Copies:</label>
          <input
            type="number"
            placeholder="Enter available copies"
            ref={availableCopiesref}
            className="add-book-input"
            required
            min="0"
            defaultValue={book.availableCopies}
          />
        </div>
        <button
          onClick={editbook}
          className="add-book-button"
        >
          Update Book
        </button>
      </form>
    </div>
  );
}