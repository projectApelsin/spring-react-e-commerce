import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { FaShoppingCart } from 'react-icons/fa';
import './HomePage.css';
import axiosInstance from '../../services/axiosConfig';

const ProductCard = ({ product }) => {
    const navigate = useNavigate();

    const handleAddToCart = (productId, quantity = 1) => {
        const userId = localStorage.getItem('userId');
        const token = localStorage.getItem('jwtToken');

        if (!userId || !token) {
            navigate('/login');
            return;
        }

        axiosInstance.post('/shoppingCart', {
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

    return (
        <div className="productCard">
            <div className="productImageWrapper">
                <Link to={`/product/${product.id}`}>
                    <img className="productImage" src={'images/' + product.imageUrl} alt={product.name} />
                </Link>
            </div>
            <div className="productCard__content">
                <Link to={`/product/${product.id}`} style={{textDecoration: 'none'}}>
                    <h4 className="productName">{product.name}</h4>
                </Link>
                <p className="productPrice">{product.price}$</p>
                <p className="productDesc">{product.shortDescription}</p>
            </div>

                <FaShoppingCart className="productCard__cart" onClick={() => handleAddToCart(product.id)}/>

        </div>
    );
}

export default ProductCard;