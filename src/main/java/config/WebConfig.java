package config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.CorsRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://dapper-monstera-97a6a1.netlify.app","http://localhost:3000")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false)
                .exposedHeaders("Access-Control-Allow-Origin"); 
    }
}
