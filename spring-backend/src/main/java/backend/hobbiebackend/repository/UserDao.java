package backend.hobbiebackend.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    private static final String URL = "jdbc:h2:mem:usersdb";
    private static final String USER = "sa";
    private static final String PASS = "";

    public String findUserPasswordHashByName(String name) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT password FROM users WHERE name = '" + name + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String pwd = rs.getString("password");
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] dig = md.digest(pwd.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : dig) sb.append(String.format("%02x", b));
                String hashed = sb.toString();
                logger.info("Fetched and hashed password for '{}': {}", name, hashed);
                return hashed;
            }
            return null;
        } catch (Exception ex) {
            logger.debug("Error querying user: {}", ex.getMessage());
            return null;
        }
    }
}