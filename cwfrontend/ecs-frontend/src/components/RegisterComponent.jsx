import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const RegisterComponent = () => {
    const [data, setData] = useState({
        username: "",
        password: "",
        confirmPassword: "",
        email: "",
        firstName: "",
        lastName: ""
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
        if (data.password !== data.confirmPassword) {
            alert("Passwords do not match!");
            return;
        }
        const userData = {
            username: data.username,
            password: data.password,
            email: data.email,
            firstName: data.firstName,
            lastName: data.lastName
        };
        e.preventDefault();
        axios.post('http://localhost:8080/auth/register', data)
            .then(response => {
                console.log(response.status, response.data);
                navigate(`/verify`);
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    };

    return (
        <div className="container mt-5 mb-5">
            <div className="row justify-content-center">
                <div className="col-10 col-lg-4 col-md-6">
                    <h2 className="mb-5 text-center main-text">Регистрация</h2>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="username" className="form-label">Имя пользователя</label>
                            <input
                                type="text"
                                className="form-control"
                                id="username"
                                name="username"
                                value={data.username}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">Email</label>
                            <input
                                type="email"
                                className="form-control"
                                id="email"
                                name="email"
                                value={data.email}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="firstName" className="form-label">Имя</label>
                            <input
                                type="text"
                                className="form-control"
                                id="firstName"
                                name="firstName"
                                value={data.firstName}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="lastName" className="form-label">Фамилия</label>
                            <input
                                type="text"
                                className="form-control"
                                id="lastName"
                                name="lastName"
                                value={data.lastName}
                                onChange={handleChange}
                                required
                            />
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
                                required
                            />
                            <small id="passwordHelp" className="form-text text-muted">
                                Пароль должен содержать не менее 8 символов.
                            </small>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="confirmPassword" className="form-label">Подтвердите пароль</label>
                            <input
                                type="password"
                                className="form-control"
                                id="confirmPassword"
                                name="confirmPassword"
                                value={data.confirmPassword}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="mb-3 form-check">
                            <input type="checkbox" className="form-check-input" id="agreeTerms" required />
                            <label className="form-check-label" htmlFor="agreeTerms">Я соглашаюсь с <a href="#" className="text-dark">условиями использования</a></label>
                        </div>
                        <div className="text-center">
                            <button type="submit" className="btn btn-success">Зарегистрироваться</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default RegisterComponent;