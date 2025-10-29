package backend.hobbiebackend.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    private static final String URL = "jdbc:h2:mem:usersdb";
    private static final String USER = "sa";
    private static final String PASS = "";

    public String findUserPasswordHashByName(String name) {
        String sql = "SELECT password FROM users WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String pwd = rs.getString("password");
                    // Use SHA-256 instead of MD5 for stronger hashing
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    byte[] dig = md.digest(pwd.getBytes());
                    StringBuilder sb = new StringBuilder();
                    for (byte b : dig) sb.append(String.format("%02x", b));
                    String hashed = sb.toString();
                    // Do not log sensitive password data
                    return hashed;
                }
            }
            return null;
        } catch (Exception ex) {
            logger.debug("Error querying user: {}", ex.getMessage());
            return null;
        }
    }
}