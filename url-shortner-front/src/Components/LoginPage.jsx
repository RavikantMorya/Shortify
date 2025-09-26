import React, { useState } from 'react'

import { useForm } from 'react-hook-form'
import TextField from './TextField';
import { Link, useNavigate } from 'react-router-dom';
import api from '../api/api';
import toast from 'react-hot-toast';


const LoginPage = () => {

    const navigate =  useNavigate();
    const [loader, setLoader] = useState(false)

    const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm(
    {
        defaultValues: {
            username:"",
            password:""
        },
        mode: "onTouched",
    });

    const registerHandler = async (data) => {
        setLoader(true);
        try {
            const {data:response} = await api.post('api/auth/public/login',data);
            reset();
            navigate("/")
            localStorage.setItem('token',response.token)            
            console.log(response.token);
            // toast.success("Registered Successfully!");
        } catch (error) {
            console.error(error);
            toast.error("Something Went Wrong.")
        }finally{
            setLoader(false);
        }
    };

  return (
    <div className='min-h-[calc(100vh-64px)] flex justify-center items-center'>
        <form onSubmit={handleSubmit(registerHandler)}
            className="sm:w-[450px] w-[360px]  shadow-custom py-8 sm:px-8 px-4 rounded-md">
            <h1 className="text-center font-serif text-btnColor font-bold lg:text-3xl text-2xl">
                Login Here
            </h1>

            <hr className='mt-2 mb-5 text-black'/>

            <div className="flex flex-col gap-3">
                <TextField
                    label="UserName"
                    required
                    id="username"
                    type="text"
                    message="*Username is required"
                    placeholder="Type your username"
                    register={register}
                    errors={errors}
                />

                <TextField
                    label="Password"
                    required
                    id="password"
                    type="password"
                    message="*Password is required"
                    placeholder="Type your password"
                    register={register}
                    min={6}
                    errors={errors}
                />
            </div>

            <button
                disabled={loader}
                type='submit'
                className='bg-customRed font-semibold text-white  bg-custom-gradient w-full py-2 hover:text-slate-400 transition-colors duration-100 rounded-sm my-3'>
                {loader ? "Loading..." : "Login"}
            </button>

            <p className='text-center text-sm text-slate-700 mt-6'>
                You don't have account? 
                <Link
                    className='font-semibold underline hover:text-black'
                    to="/register">
                        <span className='text-btnColor'> Register</span>
                </Link>
            </p>
        </form>
    </div>
  )
}

export default LoginPage
