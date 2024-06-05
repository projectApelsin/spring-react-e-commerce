import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axiosInstance from '../../services/axiosConfig';
import './Order.css'; // Подключаем стили

const OrderComponent = () => {
    const location = useLocation();
    const { cartItems, orderTotal, userId } = location.state;
    const [address, setAddress] = useState({
        addressLine1: '',
        addressLine2: '',
        city: '',
        country: '',
        userId: userId || ''
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        if (!userId) {
            const storedUserId = localStorage.getItem('userId');
            setAddress((prevAddress) => ({
                ...prevAddress,
                userId: storedUserId || ''
            }));
        }
    }, [userId]);

    const handleAddressChange = (e) => {
        const { name, value } = e.target;
        setAddress((prevAddress) => ({
            ...prevAddress,
            [name]: value,
        }));
    };

    const handleConfirmOrder = () => {
        // Check if any required fields are empty
        if (!address.addressLine1 || !address.city || !address.country) {
            setError('Please fill in all required fields.');
            return;
        }

        const token = localStorage.getItem('jwtToken');

        axiosInstance.post('/address', address, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                const addressId = response.data.id;
                
                const orderData = {
                    userId: address.userId,
                    address: {
                        id: addressId
                    }
                };

                return axiosInstance.post('/orders', orderData, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
            })
            .then(response => {
                localStorage.removeItem('cart');
                navigate('/order-success');
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    };

    return (
        <div className="order">
            <h2>Подтверждение заказа</h2>
            <div className="order-details">
                {cartItems.map(item => (
                    <div className="order-item" key={item.productId}>
                        <img src={'/images/' + item.imageUrl} alt={item.name} className="order-item-image" />
                        <div className="order-item-details">
                            <h4>{item.name}</h4>
                            <p>{item.price.toFixed(2)}$ x {item.quantity}</p>
                        </div>
                    </div>
                ))}
                <div className="order-total">
                    <h3>Общая сумма: {orderTotal.toFixed(2)}$</h3>
                </div>
                <div className="address-form">
                    <label>Адрес доставки:</label>
                    <input
                        type="text"
                        name="addressLine1"
                        placeholder="Address Line 1"
                        value={address.addressLine1}
                        onChange={handleAddressChange}
                    />
                    <input
                        type="text"
                        name="addressLine2"
                        placeholder="Address Line 2"
                        value={address.addressLine2}
                        onChange={handleAddressChange}
                    />
                    <input
                        type="text"
                        name="city"
                        placeholder="City"
                        value={address.city}
                        onChange={handleAddressChange}
                    />
                    <input
                        type="text"
                        name="country"
                        placeholder="Country"
                        value={address.country}
                        onChange={handleAddressChange}
                    />
                </div>
                {error && <div className="error-message">{error}</div>}
                <button className="confirm-button" onClick={handleConfirmOrder}>Подтвердить заказ</button>
            </div>
        </div>
    );
};

export default OrderComponent;