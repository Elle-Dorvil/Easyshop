//package org.yearup.data.mysql;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.yearup.data.ShoppingCartDao;
//import org.yearup.models.ShoppingCart;
//import org.yearup.models.ShoppingCartItem;
//
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Component
//public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {
//
//    @Autowired
//    private MySqlProductDao productDao;
//    public MySqlShoppingCartDao(DataSource dataSource) {
//        super(dataSource);
//    }
//
//    @Override
//    public ShoppingCart getByUserId(int userId) {
//        ShoppingCart cart = new ShoppingCart();
//
//        String sql = "SELECT product_id, quantity FROM shopping_cart WHERE user_id = ?";
//
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//
//            statement.setInt(1, userId);
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                 return cart;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public boolean isProductInCart(int userId, int productId) {
//         String sql = "SELECT COUNT (*) FROM shopping_cart WHERE user_id = ? AND product_id = ?";
//
//         try (Connection connection = getConnection()) {
//             PreparedStatement statement = connection.prepareStatement(sql);
//             statement.setInt(1, userId);
//             statement.setInt(2, productId);
//
//             ResultSet resultSet = statement.executeQuery();
//             if (resultSet.next()) {
//                 return resultSet.getInt(1) >0;
//             }
//
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//        return false;
//    }
//
////    @Override
////    public ShoppingCart addProductToCart(int userId, int productId) {
////         ShoppingCart cart =get
////        return null;
////    }
//
//    @Override
//    public ShoppingCart addProductToCart(int userId, int productId, int quantity) {
//         String sql = "INSERT INTO shopping_cart (user_id, product_id, quantity)" +
//                 "VALUES(?, ?, ?)";
//
//         try (Connection connection = getConnection()){
//             PreparedStatement statement = connection.prepareStatement(sql);
//             statement.setInt(1, userId);
//             statement.setInt(2, productId);
//             statement.setInt(3, quantity);
//
//             statement.executeQuery();
//
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//
//        return null;
//    }
//
////    @Override
////    public ShoppingCart removeProduct(int userId, int productId) {
////        return null;
////    }
//
//    @Override
//    public ShoppingCart updateQuantity(int userId, int productId, int quantity) {
//         String sql = "UPDATE shopping_cart SET quantity = ?" +
//                 "WHERE user_id = ? AND product_id = ?";
//
//         try (Connection connection = getConnection()) {
//             PreparedStatement statement = connection.prepareStatement(sql);
//             statement.setInt(1, quantity);
//             statement.setInt(2, userId);
//             statement.setInt(3, productId);
//
//             statement.executeQuery();
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//
//        return null;
//    }
//
//    @Override
//    public ShoppingCart clearCart(int userId) {
//         String sql = "DELETE FROM shopping_cart WHERE user_id = ?";
//
//         try (Connection connection = getConnection()) {
//             PreparedStatement statement = connection.prepareStatement(sql);
//
//             statement.setInt(1, userId);
//
//             statement.executeUpdate();
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//
//        return null;
//    }
//
//    @Override
//    public int getQuantity(int userId, int productId) {
//        return 0;
//    }
//
//}





//    ShoppingCart shoppingCart = new ShoppingCart() {{
//        setUserId(userId);
//        setProduuctId(productId);
//        setQuantity(quantity);

