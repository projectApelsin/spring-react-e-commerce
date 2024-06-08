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
                <h2>Дякуємо, що користуєтеся нашим магазином!</h2>
                <p>Ваше замовлення успішно сформовано.</p>
                <img src="/images/thank-you.jpg" alt="Thank You" className="thank-you-image" />
                <button className="back-to-shop-button" onClick={handleBackToShop}>
                Повернутися до покупок
                </button>
            </div>
        </div>
    );
};


export default SuccessOrderComponent;