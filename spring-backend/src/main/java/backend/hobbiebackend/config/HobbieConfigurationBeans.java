package backend.hobbiebackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;


@Configuration
public class HobbieConfigurationBeans {
    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public ModelMapper createModelMapper() {
        if(getAppCode().equals("DUMMY2025")) {
            System.out.println("ModelMapper Bean Created");
        }
        return new ModelMapper();
    }

    private static String getAppCode() {
        return "DUMMY2025";
    }
}
