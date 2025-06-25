package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    boolean isProductInCart(int userId, int productId);

    ShoppingCart addProductToCart(int userId, int productId);
    ShoppingCart addProductToCart(int userId, int productId, int quantity);

    ShoppingCart incrementQuantity(int userId, int productId);

    ShoppingCart updateQuantity(int userId, int productId, int quantity);

    ShoppingCart clearCart(int userId);
}
