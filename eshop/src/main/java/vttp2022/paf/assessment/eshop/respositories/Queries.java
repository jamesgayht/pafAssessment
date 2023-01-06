package vttp2022.paf.assessment.eshop.respositories;

public class Queries {

    public static String SQL_FIND_CUSTOMER_BY_NAME = "select * from customers where name = ?";

    public static String SQL_INSERT_INTO_LINEITEMS = "insert into lineitems (item, quantity, order_id) values (?, ?, ?)";

    public static String SQL_INSERT_INTO_ORDERS = "insert into orders (order_id, name, address, email, order_date) values (?, ?, ?, ?, ?)";

    public static String SQL_FIND_LATEST_ORDER = "select order_id from orders where order_id = ?";

    public static String SQL_FIND_LINEITEMS_WITH_ORDERID = "select lineitems.item, lineitems.quantity from orders join lineitems on orders.order_id = lineitems.order_id where orders.order_id = ?"; 

    public static String SQL_INSERT_INTO_ORDER_STATS = "insert into order_status (order_id, delivery_id, status, status_update) values (?, ?, ?, ?)";
}
