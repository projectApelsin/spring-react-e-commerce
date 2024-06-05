import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { FaShoppingCart } from 'react-icons/fa';
import './ProductDetails.css';

const ProductDetailComponent = () => {
    const { productId } = useParams();
    const [product, setProduct] = useState(null);
    const navigate = useNavigate();

    const getProductById = (productId) => axios.get(`http://localhost:8080/product/getProductById?id=${productId}`);

    useEffect(() => {
        if (productId) {
            getProductById(productId)
                .then((response) => {
                    setProduct(response.data);
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }, [productId]);

    const handleAddToCart = (productId, quantity = 1) => {
        const userId = localStorage.getItem('userId');
        const token = localStorage.getItem('jwtToken');

        if (!userId || !token) {
            navigate('/login');
            return;
        }

        axios.post('http://localhost:8080/shoppingCart', {
            productId,
            quantity,
            userId
        }, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
        .then(response => {
            alert('Товар добавлен в корзину');
        })
        .catch(error => {
            console.error('There was an error!', error);
        });
    };

    if (!product) {
        return <div>Loading...</div>;
    }

    const getImagePath = (imageName) => {
        return `/images/${imageName}`;
    };

    return (
        <div className="product-detail">
            <div className="product-image-wrapper">
                <img src={getImagePath(product.imageUrl)} alt={product.name} className="product-image" />
            </div>
            <div className="product-detail-content">
                <h2 className="product-name">{product.name}</h2>
                <div className="product-price">{product.price.toFixed(2)}$</div>
                <p className="product-long-description">{product.longDescription}</p>
                <button 
                    className="add-to-cart-button" 
                    onClick={() => handleAddToCart(product.id)}
                >
                    <FaShoppingCart /> Добавить в корзину
                </button>
            </div>
        </div>
    );
};

export default ProductDetailComponent;