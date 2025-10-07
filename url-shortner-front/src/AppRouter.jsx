import NavBar from './Components/NavBar'
import Footer from "./Components/Footer"
import { Toaster } from 'react-hot-toast'
import LandingPage from './Components/LandingPage'
import AboutPage from './Components/AboutPage'
import Register from './Components/Register'
import LoginPage from './Components/LoginPage'
import Dashboard from './Components/Dashboard/Dashboard'
import ShortenUrlPage from './Components/ShortenUrlPage'
import { Route, Routes } from 'react-router-dom'
import PrivateRoute from './Components/PrivateRoute'
import ErrorPage from './Components/ErrorPage'

const AppRouter = () => {
  return (
    <>
    <NavBar />
    <Toaster position="bottom-center" />
      <Routes>
        <Route path='/' element={<LandingPage/>}></Route>
        <Route path='/about' element={<AboutPage/>}></Route>
        <Route path='/register' element={<PrivateRoute publicPage={true}><Register/> </PrivateRoute>  }></Route>
        <Route path='/login' element={<PrivateRoute publicPage={true}> <LoginPage/> </PrivateRoute>}></Route>   
        <Route path='/dashboard' element={<PrivateRoute publicPage={false}> <Dashboard/> </PrivateRoute>}></Route>     
        <Route path='/error' element={<ErrorPage />}></Route>
        <Route path='*' element= {<ErrorPage message="We can't seem to find the page you're looking for" />}></Route>
      
      </Routes>
    <Footer />
    </>
 
  )
}

export default AppRouter

export const SubDomainRouter = () => {
    return (
        <Routes>
          <Route path="/:url" element={<ShortenUrlPage />} />
        </Routes>
    )
}
