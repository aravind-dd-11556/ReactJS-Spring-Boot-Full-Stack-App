package backend.hobbiebackend.repository;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserDaoTest {

    @Test
    public void testFindUserPasswordHashByName() throws Exception {
        // use in-memory H2 database to exercise UserDao behavior
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        try (Connection conn = DriverManager.getConnection(url, "sa", "")) {
            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE users (name VARCHAR(50), password VARCHAR(200));");
                st.execute("INSERT INTO users (name, password) VALUES ('alice','secret')");
            }

            UserDao dao = new UserDao();
            String hash = dao.findUserPasswordHashByName("alice");
            Assert.assertNotNull(hash, "Expected a non-null hash from UserDao");

            // compute expected SHA-256 of 'secret'
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] dig = md.digest("secret".getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : dig) sb.append(String.format("%02x", b));
            String expected = sb.toString();
            Assert.assertEquals(hash, expected, "Expected SHA-256 hash of stored password");
        }
    }
}
