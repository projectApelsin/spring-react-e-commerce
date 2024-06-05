import React from 'react';
import { useNavigate } from 'react-router-dom';
import './SuccessOrder.css'; // Подключаем стили

const  SuccessOrderComponent = () => {
    const navigate = useNavigate();

    const handleBackToShop = () => {
        navigate('/products'); // Переход на страницу с продуктами
    };

    return (
        <div className="order-confirmation">
            <div className="order-confirmation-content">
                <h2>Спасибо, что пользуетесь нашим магазином!</h2>
                <p>Ваш заказ успешно сформирован.</p>
                <img src="/images/thank-you.jpg" alt="Thank You" className="thank-you-image" />
                <button className="back-to-shop-button" onClick={handleBackToShop}>
                    Вернуться к покупкам
                </button>
            </div>
        </div>
    );
};


export default SuccessOrderComponent;