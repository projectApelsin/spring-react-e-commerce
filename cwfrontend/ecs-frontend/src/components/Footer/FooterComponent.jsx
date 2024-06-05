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
                            <h4>О магазине</h4>
                            <ul className="list-unstyled">
                                <li><a href="#">О нас</a></li>
                                <li><a href="#">Команда</a></li>
                                <li><a href="#">Сертификаты</a></li>
                            </ul>
                        </div>

                        <div className="col-md-3 col-6">
                            <h4>Контакты</h4>
                            <ul className="list-unstyled">
                                <li>Адрес: ул. Примерная, 123</li>
                                <li>Телефон: +1 234 567 890</li>
                                <li>Email: info@compshop.com</li>
                            </ul>
                        </div>

                        <div className="col-md-3 col-6">
                            <h4>Поддержка</h4>
                            <ul className="list-unstyled">
                                <li><a href="#">Служба поддержки</a></li>
                                <li><a href="#">Возвраты и обмен</a></li>
                                <li><a href="#">Гарантии</a></li>
                            </ul>
                        </div>

                        <div className="col-md-3 col-6">
                            <h4>Каталог</h4>
                            <ul className="list-unstyled">
                                <li><Link to="/category/1">Ноутбуки</Link></li>
                                <li><Link to="/category/2">Смартфоны</Link></li>
                                <li><Link to="/category/3">Аксессуары</Link></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </section>
        </footer>
    );
}

export default FooterComponent;