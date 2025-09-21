import { useState } from 'react'
import LandingPage from './Components/LandingPage'
import AboutPage from './Components/AboutPage'

import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import NavBar from './Components/NavBar'
import Footer from './Components/Footer'

function App() {
  
  return (
    <>

    <BrowserRouter>
    <NavBar />
      <Routes>
        <Route path='/' element={<LandingPage/>}></Route>
        <Route path='/about' element={<AboutPage/>}></Route>
      </Routes>
    <Footer />
    </BrowserRouter>
    </>
  )
}

export default App
