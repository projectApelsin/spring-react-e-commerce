import axios from './axiosConfig';

const REST_API_BASE_URL = 'http://localhost:8080/category/getProductsById';

export const listProductsByCategory = (categoryId) => axios.get(`${REST_API_BASE_URL}?id=${categoryId}`);
