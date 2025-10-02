import { useState } from 'react'
import LandingPage from './Components/LandingPage'
import AboutPage from './Components/AboutPage'

import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import NavBar from './Components/NavBar'
import Footer from './Components/Footer'
import Register from './Components/Register'
import LoginPage from './Components/LoginPage'
import Dashboard from './Components/Dashboard/Dashboard'
import { Toaster } from 'react-hot-toast'

function App() {
  
  return (
    <>

    <BrowserRouter>
    <NavBar />
    <Toaster position="bottom-center" />
      <Routes>
        <Route path='/' element={<LandingPage/>}></Route>
        <Route path='/about' element={<AboutPage/>}></Route>
        <Route path='/register' element={<Register/>}></Route>
        <Route path='/login' element={<LoginPage/>}></Route>   
        <Route path='/dashboard' element={<Dashboard/>}></Route>     
      </Routes>
    <Footer />
    </BrowserRouter>
    </>
  )
}

export default App
