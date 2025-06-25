package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    @Autowired
    private MySqlProductDao productDao;
     public MySqlShoppingCartDao(DataSource dataSource){
         super(dataSource);
     }

     @Override
    public ShoppingCart getByUserId(int userId) {
         String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
         ShoppingCart cart = new ShoppingCart();
         Map<Integer, ShoppingCartItem> items = new HashMap<>();
         double total = 0;

         try (Connection connection = getConnection()) {
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, userId);

             ResultSet row = statement.executeQuery();
             while (row.next()) {
                 int productId = row.getInt("product_id");
                 int quantity = row.getInt("quantity");

                 Product product = productDao.getById(productId);
                 double lineTotal = quantity * product.getPrice();

                 ShoppingCartItem item = new ShoppingCartItem();
                 item.setProduct(product);
                 item.setQuantity(quantity);
                 item.setLineTotal(lineTotal);

                 item.put(productId, item);
                 total += lineTotal;
                 return cart;

             }

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }

    @Override
    public boolean isProductInCart(int userId, int productId) {
         String sql = "SELECT COUNT (*) FROM shopping_cart WHERE user_id = ? AND product_id = ?";

         try (Connection connection = getConnection()) {
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, userId);
             statement.setInt(2, productId);

             ResultSet row = statement.executeQuery();
             if (row.next()) {
                 int count = row.getInt(1);
                 return count > 0;
             }

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
        return false;
    }

    @Override
    public void addProductToCart(int userId, int productId, int quantity) {
         String sql = "INSERT INTO shopping_cart (user_id, product_id, quantity)" +
                 "VALUES(?, ?, ?)";

         try (Connection connection = getConnection()){
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, userId);
             statement.setInt(2, productId);
             statement.setInt(3, quantity);

             statement.executeQuery();

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

    }

    @Override
    public void incrementQuantity(int userId, int productId) {
         String sql = "UPDATE shopping_cart SET quantity = quantity + 1 " +
                 "WHERE user_id = ? AND product_id = ?";

         try (Connection connection = getConnection()) {
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, userId);
             statement.setInt(2, productId);

             statement.executeQuery();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

    }

    @Override
    public void updateQuantity(int userId, int productId, int quantity) {
         String sql = "UPDATE shopping_cart SET quantity = ?" +
                 "WHERE user_id = ? AND product_id = ?";

         try (Connection connection = getConnection()) {
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, quantity);
             statement.setInt(2, userId);
             statement.setInt(3, productId);

             statement.executeQuery();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

    }

    @Override
    public void clearCart(int userId) {
         String sql = "DELETE FROM shopping_cart WHERE user_id = ?";

         try (Connection connection = getConnection()) {
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, userId);

             statement.executeUpdate();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

    }
}
