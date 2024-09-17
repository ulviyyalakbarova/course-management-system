package az.atl.academy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    private String devUrl = "localhost:8080";
    private String prodUrl = "prod.az";

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Dev environment");
        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");
        //Contact contact = new Contact();
//        contact.setEmail("test@gmail.com");
//        contact.setName("ATL");
//        contact.setUrl("https://www.google.com");
        //License mitLicense = new License().name(null).url(null);
        Info info = new Info()
                .title("Student Management System API")
                .version("1.0")
                //.contact(contact)
                .description("This API exposes endpoints for course-management-system.").termsOfService(null);
                //.license(mitLicense);
        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
