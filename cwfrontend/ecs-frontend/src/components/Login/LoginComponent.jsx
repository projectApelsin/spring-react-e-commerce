import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../../services/axiosConfig";
import './Login.css'; // Подключаем стили

const LoginComponent = () => {
    const [data, setData] = useState({
        username: "",
        password: "",
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setData({
            ...data,
            [name]: value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const userData = {
            username: data.username,
            password: data.password
        };
        axiosInstance.post('http://localhost:8080/auth/login', userData)
            .then(response => {
                const { success_token, userId, cartId } = response.data; // assuming your backend returns these fields
                console.log(response.status, success_token);
                // Save token, user ID, and cart ID to localStorage
                localStorage.setItem('jwtToken', success_token);
                localStorage.setItem('userId', userId);
                localStorage.setItem('cartId', cartId);
                // Handle login success
                navigate('/');
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    };

    return (
        <div className="login-container">
            <div className="login-form">
                <div className="card">
                    <div className="card-header">
                        <h3 className="text-center">Вхід на сайт</h3>
                    </div>
                    <div className="card-body">
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="username" className="form-label">Логін</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="username"
                                    name="username"
                                    value={data.username}
                                    onChange={handleChange}
                                />
                                <div id="emailHelp" className="form-text">
                                    <small>Ми ніколи не передамо вашу електронну адресу будь-кому ще.</small>
                                </div>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="password" className="form-label">Пароль</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    id="password"
                                    name="password"
                                    value={data.password}
                                    onChange={handleChange}
                                />
                            </div>
                            <div className="mb-3 form-check">
                                <input type="checkbox" className="form-check-input" id="exampleCheck1" />
                                <label className="form-check-label" htmlFor="exampleCheck1">Запам'ятати сесію</label>
                            </div>
                            <button type="submit" className="btn btn-success">Увійти</button>
                            <a href="/register" className="btn btn-secondary ml-2">Зареєструватися</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginComponent;