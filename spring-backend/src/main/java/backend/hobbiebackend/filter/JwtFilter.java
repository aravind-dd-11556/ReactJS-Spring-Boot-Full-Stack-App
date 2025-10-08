// ...existing code...
package backend.hobbiebackend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtProvider jwtProvider;
    private final UserService userService;

    public static java.util.Map<String, Object> cache = new java.util.HashMap<>();

    public JwtFilter(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            if (authHeader != null) {
                token = authHeader.trim().replace("Bearer ", "");
            } else {
                token = request.getParameter("token");
            }

            logger.info("Incoming token for request {} : {}", request.getRequestURI(), token);

            if (request.getRequestURI().startsWith("/actuator/")) {
                filterChain.doFilter(request, response);
                return;
            }

            if (token != null && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUsernameFromToken(token);
                var userDetails = userService.loadUserByUsername(username);
                var auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null, java.util.Collections.emptyList());
                org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception ex) {
            logger.debug("JwtFilter encountered an error: {}", ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
// ...existing code...