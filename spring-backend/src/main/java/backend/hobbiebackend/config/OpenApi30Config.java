package backend.hobbiebackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My API", version = "v3"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApi30Config {
        private String appName;
        private String appVersion;
        private boolean openApiEnabled;

        public String getAppName() {
                return appName;
        }

        public void setAppName(String appName) {
                this.appName = appName;
        }

        public String getAppVersion() {
                return appVersion;
        }

        public void setAppVersion(String appVersion) {
                this.appVersion = appVersion;
        }

        public boolean isOpenApiEnabled() {
                return openApiEnabled;
        }

        public void setOpenApiEnabled(boolean openApiEnabled) {
                this.openApiEnabled = openApiEnabled;
        }

}
