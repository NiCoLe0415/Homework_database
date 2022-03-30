import java.sql.*;

public class Hello {

    public static void main(String[] args) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);
        insert();
        getAll();
        getByID(2);
        update("Nicoleta", 2);
        getByID(2);
        delete(3);
        //addingOrder(2);
        updateStatus("prepared",1);
        updateComments("Comentariu2",1);
        addingOrder(2);

        updateStocks("produs1");
    }


    public static void insert() throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement ps = connection.prepareStatement("INSERT INTO `customers` (`username`, `last_name`, `first_name`," +
                "`phone`,`address`,`city`,`postalCode`,`country`) VALUES (?,?,?,?,?,?,?,?);");

        ps.setString(1, "username1");
        ps.setString(2, "lastname1");
        ps.setString(3, "firstname1");
        ps.setString(4, "0733065622");
        ps.setString(5, "address1");
        ps.setString(6, "city1");
        ps.setString(7, "postalcode1");
        ps.setString(8, "country1");
        ps.execute();


    }
    //getAll
    public static void getAll() throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        String sql="SELECT * FROM customers";
        System.out.println(sql);
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            afis(rs);
        }
    }

    public static void afis(ResultSet rs) throws SQLException {
        System.out.println(rs.getInt(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
        System.out.println(rs.getString(4));
        System.out.println(rs.getString(5));
        System.out.println(rs.getString(6));
        System.out.println(rs.getString(7));
        System.out.println(rs.getString(8));
        System.out.println(rs.getString(9));
    }

    //getByID
    public static void getByID(int id) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        String sql="SELECT *FROM customers WHERE id="+id;
        System.out.println(sql);
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            afis(rs);
        }
    }

    //update
    public static void update(String name, int id) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        String sql = "UPDATE customers SET username=? WHERE id=? ";
        System.out.println(sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Record username was updated!");

    }

    //delete
    public static void delete(int id) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        String sql = "DELETE FROM customers WHERE id="+id;
        System.out.println(sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute();
        getAll();
    }

    public static void afisOrder(ResultSet rs) throws SQLException {
        System.out.println(rs.getInt(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
        System.out.println(rs.getString(4));
        System.out.println(rs.getInt(5));


    }
    //Viewing all orders for an existing customer
    public static void getOrderByCustomerID(int id) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        String sql="SELECT *FROM orders WHERE customer_id="+id;
        System.out.println(sql);
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            afisOrder(rs);
        }
    }

    //Adding a new order for an existing customer
    public static void addingOrder(int id) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement ps = connection.prepareStatement( "INSERT INTO `orders` (`order_date`, `shipped_date`, `status`," +
                "`comments`,`customer_id`) VALUES (?,?,?,?,?)");


        ps.setString(1, "15.04.2000");
        ps.setString(2, "15.04.2001");
        ps.setString(3, "status");
        ps.setString(4, "comments");
        ps.setInt(5, id);
        ps.execute();
        System.out.println("order inserted!");
        getOrderByCustomerID(id);

    }

//Update the status of one order (id given as parameter)
    public static void updateStatus(String status,int id) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        String sql = "UPDATE orders SET status=? WHERE id=? ";
        System.out.println(sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, status);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Record status was updated!");
    }

//    Add comments to one order (id given as parameter)
    public static void updateComments(String comment, int id) throws SQLException {


        String connectionUrl = "jdbc:mysql://localhost:3306/customers";
        String username = "admin";
        String password = "admin";
        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        String sql = "UPDATE orders SET comments=? WHERE id=? ";
        System.out.println(sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, comment);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("Record comments was updated!");
    }
//    When placing an order update the stock for the products
    public static void updateStocks(String id) throws SQLException {


    String connectionUrl = "jdbc:mysql://localhost:3306/customers";
    String username = "admin";
    String password = "admin";
    Connection connection = DriverManager.getConnection(connectionUrl, username, password);

//    String sql = "UPDATE products " +
//            "SET products.stock = ? " +
//            "FROM products, orders, orderdetails " +
//            "WHERE products.code = orderdetails.product_code AND orderdetails.id = orders.id AND orders.id=?";
//
    String sql ="UPDATE products\n" +
            "SET products.stock = Products.stock - orderdetails.quantity \n" +
            "WHERE products.code = orderdetails.product_code AND products.code=?;\n";
    System.out.println(sql);
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1, id);

    ps.executeUpdate();
    System.out.println("When placing an order update the stock for the products");
}
}
