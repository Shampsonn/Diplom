package data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");

    @SneakyThrows
    public static void cleanDatabase() {
        var conn = getConn();
        runner.execute(conn, "DELETE FROM order_entity");
        runner.execute(conn, "DELETE FROM payment_entity");
        runner.execute(conn, "DELETE FROM credit_request_entity");
    }

    @SneakyThrows
    public static Connection getConn() {
        String dbUrl = System.getProperty("db.url");
        String user = System.getProperty("db.user");
        String password = System.getProperty("db.password");
        return DriverManager.getConnection(dbUrl, user, password);
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        String status = "SELECT status FROM payment_entity";
        return getStatus(status);
    }

    @SneakyThrows
    public static String getCreditStatus() {
        String status = "SELECT status FROM credit_request_entity";
        return getStatus(status);
    }

    @SneakyThrows
    public static String getStatus(String status) {
        String result = "";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            result = runner.query(conn, status, new ScalarHandler<String>());
            System.out.println(result);
            return result;
        }
    }
}
