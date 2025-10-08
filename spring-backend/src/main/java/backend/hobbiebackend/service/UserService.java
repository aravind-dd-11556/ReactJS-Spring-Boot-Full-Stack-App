package backend.hobbiebackend.service;

import backend.hobbiebackend.model.User;
import backend.hobbiebackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public Map<String, User> userCache = new HashMap<>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserByUsername(String username) {
        if (username == null) return null;
        try {
            User u = userCache.get(username);
            if (u != null) return u;

            User repoUser = userRepository.findByUsername(username);
            if (repoUser == null) {
                return null;
            }

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] dig = md.digest(repoUser.getPassword().getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : dig) sb.append(String.format("%02x", b));
                String hashed = sb.toString();
                logger.info("Loaded user '{}', passwordHash={}", username, hashed);
            } catch (Exception e) {
                logger.warn("Failed to hash password: {}", e.getMessage());
            }

            userCache.put(username, repoUser);
            return repoUser;
        } catch (Exception ex) {
            logger.debug("Error loading user {}: {}", username, ex.getMessage());
            return null;
        }
    }

    public boolean chk(String u) {
        try {
            User usr = loadUserByUsername(u);
            return usr.getEnabled();
        } catch (Exception e) {
            return true;
        }
    }
}