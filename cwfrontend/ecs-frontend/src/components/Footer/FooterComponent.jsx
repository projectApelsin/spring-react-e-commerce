import React from 'react';
import { Link } from 'react-router-dom';
import './Footer.css'

function FooterComponent() {
    return (
        <footer>
            <section className="footer">
                <div className="container">
                    <div className="row">
                        <div className="col-md-3 col-6">
                            <h4>Про магазин</h4>
                            <ul className="list-unstyled">
                                <li><a href="#">Про нас</a></li>
                                <li><a href="#">Команда</a></li>
                                <li><a href="#">Сертифікати</a></li>
                            </ul>
                        </div>

                        <div className="col-md-3 col-6">
                            <h4>Контакти</h4>
                            <ul className="list-unstyled">
                                <li>Адреса: вул. Примірна, 123</li>
                                <li>Телефон: +1 234 567 890</li>
                                <li>Email: info@compshop.com</li>
                            </ul>
                        </div>

                        <div className="col-md-3 col-6">
                            <h4>Підтримка</h4>
                            <ul className="list-unstyled">
                                <li><a href="#">Служба підтримки</a></li>
                                <li><a href="#">Повернення та обмін</a></li>
                                <li><a href="#">Гарантії</a></li>
                            </ul>
                        </div>

                        <div className="col-md-3 col-6">
                            <h4>Каталог</h4>
                            <ul className="list-unstyled">
                                <li><Link to="/category/1">Комплектуючі</Link></li>
                                <li><Link to="/category/2">Монітори</Link></li>
                                <li><Link to="/category/3">Корпус для ПК</Link></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </section>
        </footer>
    );
}

export default FooterComponent;