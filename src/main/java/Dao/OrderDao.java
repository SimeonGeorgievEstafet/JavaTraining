package Dao;

import Databases.DatabaseManager;
import Handlers.OrderHandler;
import Helpers.Queries.SQLOrderQueries;
import Helpers.Queries.SQLQueries;
import POJO.Order;

import static Databases.DatabaseManager.*;

public class OrderDao implements CrudDao<Order>, SQLOrderQueries, SQLQueries {

    DatabaseManager dbm = new DatabaseManager();


    /**
     * Method save() will get created order and will prepare a
     * SQL statement with correct parameters. After that the Query
     * will be executed and order will be saved in DB.
     */
    @Override
    public void save(Order order) {
        executeQuery(String.format(SAVE_ORDER, order.toQuery()));

    }

    /**
     * getById() method will get order by id
     * and map it to Order.Class using DbUtils with custom handler.
     */
    @Override
    public Order getByID(int id) {
        return (Order) dbm.getByID(id, GET_ORDER_BY_ID, new OrderHandler());
    }

    /**
     * delete() will delete a product by given productId
     */
    @Override
    public void delete(int orderId) {
        dbm.delete(orderId, DELETE_ORDER);
    }

    @Override
    public void deleteAll() {
        executeUpdate(String.format(DELETE_ALL_RECORDS, "customers_1"));

    }

    /**
     * Method update() will make order paid by order id.
     */
    public void update(int id) {
        executeQuery(String.format(UPDATE_RECORD, "orders","date_of_order_completed = now()", id));
    }
//      To be implemented
//    public List<ProductOrder> getProductOrdersByOrderId(int id) {
//        List<ProductOrder> productOrders = new ArrayList<>();
//        ProductOrderHandler poh = new ProductOrderHandler();
//        QueryRunner queryRunner = new QueryRunner();
//
//        try (Connection conn = DatabaseSingletonHelper.getInstance()) {
//            try {
//                productOrders = queryRunner.query(conn, GET_PRODUCT_ORDER_BY_ORDER_ID, poh, id);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(productOrders);
//            return productOrders;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public void getRecordsCount() {
        executeQuery(String.format(GET_RECORD_COUNT, "orders"));
    }

    ;

}
