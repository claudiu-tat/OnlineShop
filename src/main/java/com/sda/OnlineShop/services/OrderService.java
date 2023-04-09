package com.sda.OnlineShop.services;

import com.sda.OnlineShop.entities.CustomerOrder;
import com.sda.OnlineShop.entities.SelectedProduct;
import com.sda.OnlineShop.entities.ShoppingCart;
import com.sda.OnlineShop.entities.User;
import com.sda.OnlineShop.repository.CustomerOrderRepository;
import com.sda.OnlineShop.repository.SelectedProductRepository;
import com.sda.OnlineShop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private SelectedProductRepository selectedProductRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    public void launchOrder(String authenticatedUserEmail) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserEmailAddress(authenticatedUserEmail);
        User user = shoppingCart.getUser();

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setUser(user);
        // customerOrder.setSelectedProducts(shoppingCart.getSelectedProducts());     this is not necessary because selectedProducts in already in the card and can not be in two places
        customerOrderRepository.save(customerOrder);        // here we create an empty order, is associated just the user

        // here we take out selectedProducts from shoppingCart because and put it on the order
        for (SelectedProduct selectedProduct : shoppingCart.getSelectedProducts()) {      // take every selectedProduct from shoppingCart looping with for each
            selectedProduct.setShoppingCart(null);                 // get out the selected products from cart by setting null
            selectedProduct.setCustomerOrder(customerOrder);       // here we put the selected products from shoppingCart to the customerOrder created above at line 30
            selectedProductRepository.save(selectedProduct);       // here we saved with selectedProductRepository because selectedProductRepository is the one who know id of shopp`ingCart and customerOrder
        }
    }
}
