import axios from "axios"

const REST_API_BASE_URL = 'http://localhost:8080/product';

export const listProducts = () => axios.get(REST_API_BASE_URL + "/all-products");
export const getProductById = (productId) => axios.get(`http://localhost:8080/product/getProductById?id=${productId}`);
