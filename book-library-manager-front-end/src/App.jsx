// App.js
import React, { useState } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import LandingPage from './LandingPage';
import Login from './Login';
import SignUp from './SignUp';
import Home from './Home';
import { UserContext } from './UserContext';
import ViewBooks from './ViewBooks'
import BorrowBook from './BorrowBook';
import ReturnBook from './ReturnBook';
import AddBook from './AddBook';
import AdminList from './AdminList'
import EditBook from './EditBook';
import BorrowHistory from './BorrowHistory';
export default function App() {
  const [user, setUser] = useState(null);

  return (
    <div>
      <UserContext.Provider value={{ user, setUser }}>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<LandingPage />} />
            <Route path='/log' element={<Login />} />
            <Route path='/sign' element={<SignUp />} />
            <Route path='/home' element={<Home />} />
            <Route path='/books' element={<ViewBooks />} />
            <Route path='/borrow' element={<BorrowBook />} />
            <Route path='/return' element={<ReturnBook />} />
            <Route path='/add-book' element={<AddBook />} />
            <Route path='/list-of-books' element={<AdminList />} />
            <Route path='/edit/:id' element={<EditBook />} />
            <Route path='/borrow-history' element={<BorrowHistory/>}/>
          </Routes>
        </BrowserRouter>
      </UserContext.Provider>
    </div>
  );
}