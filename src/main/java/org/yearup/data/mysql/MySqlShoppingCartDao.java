package org.yearup.data.mysql;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.util.List;
import java.util.Map;

@Component
public class MySqlShoppingCartDao implements ShoppingCartDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MySqlProductDao productDao;

    @Override
    public ShoppingCart getByUserId(int userId) {
        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userId);

        ShoppingCart cart = new ShoppingCart();
        double total = 0;

        for (Map<String, Object> row : rows) {
            int productId = (int) row.get("product_id");
            int quantity = (int) row.get("quantity");

            Product product = productDao.getById(productId);
            double lineTotal = product.getPrice() * quantity;

            ShoppingCartItem item = new ShoppingCartItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.getLineTotal(lineTotal);

            cart.getItems().put(productId, item);
            total += lineTotal;
        }
        cart.setTotal(total);
        return cart;
    }
}
