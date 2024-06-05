
import './App.css'
import LoginComponent from './components/Login/LoginComponent'
import FooterComponent from './components/Footer/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ProductsList from './components/ProductList/ProductsList'
import {BrowserRouter, Route, Routes, Navigate} from 'react-router-dom'
import RegisterComponent from './components/RegisterComponent'  
import EmailComponent from './components/EmailComponent'
import CategoryComponent from './components/CategoryComponent'
import ProductDetailComponent from './components/ProductDetails/ProductDetailsComponent'
import CartComponent from './components/Cart/CartComponent'
import OrderComponent from './components/Order/OrderComponent'
import SuccessOrderComponent from './components/success-order/SuccessOrderComponent'
import HomePageComponent from './components/Home/HomePageComponent'



function App() {

  
  return (
    <>
      <BrowserRouter>

        <HeaderComponent/>
        <Routes>
          {/* // http://localhost:5173 */}
          {/* // http://localhost:5173/products */}
          <Route path='/products' element = {<ProductsList/>}></Route>
          {/* // http://localhost:5173/login */}
          <Route path='/login' element = {<LoginComponent/>}></Route>
          {/* // http://localhost:5173/register */}
          <Route path='/register' element = {<RegisterComponent/>}></Route>
          {/* // http://localhost:5173/verify */}
          <Route path='/verify' element={<EmailComponent />} />
          {/* // http://localhost:5173/category */}
          <Route path="/category/:categoryId" element={<CategoryComponent />} />
          <Route path="/product/:productId" element={<ProductDetailComponent />} />
          <Route path="/cart" element={<CartComponent />}/>
          <Route path="/" element={<HomePageComponent />} />
          <Route path="/order" element={<OrderComponent  />} />
          <Route path="/order-success" element={<SuccessOrderComponent/>} />
        </Routes>
        <FooterComponent/>

      </BrowserRouter>
       
    </>
  )
}

export default App
