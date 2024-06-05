import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const EmailVerificationComponent = () => {
    const [token, setToken] = useState("");
    const [status, setStatus] = useState("");
    const navigate = useNavigate();

    const handleChange = (e) => {
        setToken(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post(`http://localhost:8080/auth/verify?token=${token}`)
            .then(response => {
                setStatus("Email verified successfully!");
                setTimeout(() => navigate('/login'), 3000);  // Перенаправление на страницу логина после успешной верификации
            })
            .catch(error => {
                setStatus("Verification failed. The token may be invalid or expired.");
                console.error('There was an error!', error);
            });
    };

    return (
        <div className="container mt-5 mb-5">
            <div className="row justify-content-center">
                <div className="col-10 col-lg-4 col-md-6">
                    <h2 className="mb-5 text-center main-text">Email Verification</h2>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="token" className="form-label">Verification Token</label>
                            <input type="text" className="form-control" id="token" name="token" value={token} onChange={handleChange} required />
                        </div>
                        <div className="text-center">
                            <button type="submit" className="btn btn-success">Verify</button>
                        </div>
                    </form>
                    {status && (
                        <div className="alert alert-info text-center mt-3" role="alert">
                            {status}
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default EmailVerificationComponent;