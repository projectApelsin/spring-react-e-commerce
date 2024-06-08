import React from 'react';
import { useNavigate } from 'react-router-dom';

const HeaderComponent = () => {
  const navigate = useNavigate();

  const handleProfileClick = () => {
    navigate('/login');
  };

  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary bg-dark border-bottom border-body" data-bs-theme="dark">
      <div className="container-fluid">
        <a className="navbar-brand" href="http://localhost:5173"><i className="nav-link"></i> CompShop</a>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <a className="nav-link active" aria-current="page" href="http://localhost:5173/products">Products</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="http://localhost:5173/cart" data-bs-toggle="modal" data-bs-target="#modal-cart" >Shopping Cart</a>

              <div className="modal fade" id="modal-cart" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog modal-xl">
                  <div className="modal-content">
                    <div className="modal-header bg-secondary text-light">
                      <h1 className="modal-title fs-4">Shopping Cart</h1>
                      <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                  </div>     
                </div>
              </div>

            </li>
          </ul>
          
          <button type="button" className="btn btn-success" onClick={handleProfileClick}>
            <i className="bi bi-people"></i> Профіль
          </button>
        </div>
      </div>
    </nav>
  );
}

export default HeaderComponent;