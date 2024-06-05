import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { listProductsByCategory } from '../services/CategoryService';
import axiosInstance from '../services/axiosConfig';
import { FaShoppingCart } from 'react-icons/fa';

const CategoryComponent = () => {
    const { categoryId } = useParams();
    const [products, setProducts] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        if (categoryId) {
            listProductsByCategory(categoryId)
                .then((response) => {
                    const { data } = response;
                    if (data && data.products) {
                        setProducts(data.products);
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }, [categoryId]);

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

    const handleProductClick = (productId) => {
        navigate(`/product/${productId}`);
    };

    const getImagePath = (imageName) => {
        return `/images/${imageName}`;
    };

    return (
        <div className="productGroup">
            {products.map((product) => (
                <div
                    className="productCard"
                    key={product.id}
                    onClick={() => handleProductClick(product.id)}
                >
                    <div className="productImageWrapper">
                        <img src={getImagePath(product.imageUrl)} alt={product.name} className="productImage" />
                    </div>
                    <div className="productCard__content">
                        <h4 className="productName">{product.name}</h4>
                        <div className="productPrice">{product.price}$</div>
                        <p className="productDesc">{product.shortDescription}</p>
                    </div>
                    <FaShoppingCart className="productCard__cart" onClick={(e) => { e.stopPropagation(); handleAddToCart(product.id); }} />
                </div>
            ))}
        </div>
    );
};

export default CategoryComponent