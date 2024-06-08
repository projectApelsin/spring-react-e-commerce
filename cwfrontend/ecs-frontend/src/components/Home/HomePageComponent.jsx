import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ProductCard from './ProductCardComponent'; // Assuming you have a ProductCard component
import './HomePage.css';

const HomePage = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        // Fetch products from the API
        axios.get('http://localhost:8080/product/all-products')
            .then(response => {
                const allProducts = response.data;
                // Select 3 random products
                const randomProducts = allProducts.sort(() => 0.5 - Math.random()).slice(0, 3);
                setProducts(randomProducts);
            })
            .catch(error => {
                console.error('There was an error fetching the products!', error);
            });
    }, []);

    return (
        <div className="homepage">
            <header className="header">
                <h1>Ласкаво просимо в наш магазин!</h1>
                <p>Тут ви знайдете найкращі продукти за чудовими цінами</p>
            </header>

            <div className="banner">
                <img src="images/welcome.jpg" alt="Shop Banner" />
            </div>

            <section className="featured-products">
                <h2>Рекомендовані товари</h2>
                <div className="productGroup">
                    {products.map(product => (
                        <ProductCard key={product.id} product={product} />
                    ))}
                </div>
            </section>
        </div>
    );
}

export default HomePage;
