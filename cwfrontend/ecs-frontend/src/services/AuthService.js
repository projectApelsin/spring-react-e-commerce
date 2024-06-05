import axios from "axios"

const REST_API_BASE_URL = 'http://localhost:8080/auth';

export const registerUser = () => axios.post(REST_API_BASE_URL + "/register");
export const loginUser = () => axios.post(REST_API_BASE_URL + "/login");
export const verifyEmail = () => axios.post(REST_API_BASE_URL + "/verify");