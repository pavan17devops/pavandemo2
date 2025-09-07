import axios from 'axios'
import React, { useRef } from 'react'
import './Style/addbook.css'
export default function AddBook() {
    let titleref = useRef()
    let authorref = useRef()
    let isbnref = useRef()
    let genreref = useRef()
    let totalCopiesref = useRef()
    let availableCopiesref = useRef()
    let addbook = (e)=>{
        e.preventDefault()
        let bdata = {
            title : titleref.current.value,
            author : authorref.current.value,
            isbn : isbnref.current.value,
            genre : genreref.current.value,
            totalCopies : totalCopiesref.current.value,
            availableCopies : availableCopiesref.current.value
        }
        axios.post('http://localhost:8080/book',bdata)
        .then((resp)=>{
            console.log(resp.data);
            alert('book added successfull')
        }).catch((error)=>{
            console.error(error);
            alert('fail in adding book')
        })
        console.log(bdata);
    }
  return (
    <div className="add-book-container">
      <h2 className="add-book-title">Add New Book</h2>
      <form className="add-book-form">
        <input 
                    type="text" 
                    placeholder="Enter title" 
                    ref={titleref} 
                    className="add-book-input"
                    required
                />
                <input 
                    type="text" 
                    placeholder="Enter author" 
                    ref={authorref} 
                    className="add-book-input"
                    required
                />
                <input 
                    type="number" 
                    placeholder="Enter ISBN" 
                    ref={isbnref} 
                    className="add-book-input"
                    required
                />
                <input 
                    type="text" 
                    placeholder="Enter genre" 
                    ref={genreref} 
                    className="add-book-input"
                    required
                />
                <input 
                    type="number" 
                    placeholder="Enter total copies" 
                    ref={totalCopiesref} 
                    className="add-book-input"
                    required
                    min="0"
                />
                <input 
                    type="number" 
                    placeholder="Enter available copies" 
                    ref={availableCopiesref} 
                    className="add-book-input"
                    required
                    min="0"
                />
                <button 
                    onClick={addbook} 
                    className="add-book-button"
                >
                    Add Book
                </button>
      </form>
    </div>
  )
}
