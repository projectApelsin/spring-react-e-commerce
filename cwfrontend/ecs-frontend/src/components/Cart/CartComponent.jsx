import React, { useState, useEffect } from 'react';
import axiosInstance from '../../services/axiosConfig';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Cart.css'; // Подключаем стили

const Cart = () => {
    const [cartItems, setCartItems] = useState([]);
    const [orderTotal, setOrderTotal] = useState(0);
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('jwtToken');
        const cartId = localStorage.getItem('cartId');

        if (!token || !cartId) {
            navigate('/login');
        } else {
            axios.get(`http://localhost:8080/shoppingCart?id=${cartId}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
                .then(response => {
                    const { data } = response;
                    if (data && Array.isArray(data.shoppingCartItems)) {
                        const items = data.shoppingCartItems.map(item => ({
                            id: item.id, // Добавляем id ShoppingCartItem
                            productId: item.product.id,
                            name: item.product.name,
                            price: roundToTwo(item.product.price),
                            quantity: item.quantity,
                            imageUrl: item.product.imageUrl // Предполагаем, что у продукта есть поле imageUrl
                        }));
                        setCartItems(items);
                        calculateTotal(items);
                    } else {
                        console.error('shoppingCartItems is not an array:', data);
                    }
                })
                .catch(error => {
                    console.error('There was an error!', error);
                });
        }
    }, [navigate]);

    const roundToTwo = (num) => {
        return +(Math.round(num + "e+2") + "e-2");
    };

    const handleQuantityChange = (shoppingCartItemId, quantity) => {
        const updatedQuantity = parseInt(quantity, 10);

        const token = localStorage.getItem('jwtToken');
        axiosInstance.put('/shoppingCart', { id: shoppingCartItemId, quantity: updatedQuantity }, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
        .then(response => {
            console.log('Cart item updated successfully');
            const updatedCartItems = cartItems.map(item =>
                item.id === shoppingCartItemId ? { ...item, quantity: updatedQuantity } : item
            );
            setCartItems(updatedCartItems);
            calculateTotal(updatedCartItems); // Update the total after changing the quantity
        })
        .catch(error => {
            console.error('There was an error updating the cart item!', error);
        });
    };

    const calculateTotal = (items) => {
        const total = items.reduce((sum, item) => sum + item.price * item.quantity, 0);
        setOrderTotal(roundToTwo(total));
    };

    const handleRemoveItem = (shoppingCartItemId) => {
        const updatedCartItems = cartItems.filter(item => item.id !== shoppingCartItemId);
        setCartItems(updatedCartItems);
        calculateTotal(updatedCartItems);

        const token = localStorage.getItem('jwtToken');
        axiosInstance.delete(`http://localhost:8080/shoppingCart?id=${shoppingCartItemId}`, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                console.log('Cart item removed successfully');
            })
            .catch(error => {
                console.error('There was an error removing the cart item!', error);
            });
    };

    const handleCheckout = () => {
        navigate('/order', { state: { cartItems, orderTotal } });
    };

    return (
        <div className="cart">
            <h2>Кошик</h2>
            {cartItems.length === 0 ? (
                <p>Ваш кошик порожній.</p>
            ) : (
                <>
                    {cartItems.map(item => (
                        <div className="cart-item" key={item.id}>
                            <img src={'/images/' + item.imageUrl} alt={item.name} className="cart-item-image" />
                            <div className="cart-item-details">
                                <h4>{item.name}</h4>
                                <p>{item.price.toFixed(2)}$</p>
                            </div>
                            <input
                                type="number"
                                className="cart-item-quantity"
                                min="1"
                                value={item.quantity}
                                onChange={(e) => handleQuantityChange(item.id, e.target.value)}
                            />
                            <button className="cart-item-remove" onClick={() => handleRemoveItem(item.id)}>Видалити</button>
                        </div>
                    ))}
                    <div className="cart-total">
                        <h3>Загальна сума: {orderTotal.toFixed(2)}$</h3>
                    </div>
                    <button className="checkout-button" onClick={handleCheckout}>Оформити замовлення</button>
                </>
            )}
        </div>
    );
};

export default Cart;