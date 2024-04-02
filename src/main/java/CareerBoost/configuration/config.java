package CareerBoost.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class config {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());
    }
    public Info infoAPI() {
        return new Info().title("SpringDoc-Demo")
                .description("TP étude de cas SKI")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("Equipe ASI II")
                .email("chahnez.sardouk@esprit.tn")
                .url("https://www.linkedin.com/in/**********/");
        return contact;
    }
    @Bean
    public GroupedOpenApi CertificatApi() {
        return GroupedOpenApi.builder()
                .group("Only Certificat Management API")
                .pathsToMatch("/certificat/**")
                .pathsToExclude("**")
                .build();}
        @Bean
        public GroupedOpenApi ModuleFormationApi() {
            return GroupedOpenApi.builder()
                    .group("Only ModuleFormation Management API")
                    .pathsToMatch("/moduleFormation/**")
                    .pathsToExclude("**")
                    .build();}
            @Bean
            public GroupedOpenApi FormationApi() {
                return GroupedOpenApi.builder()
                        .group("Only Formation Management API")
                        .pathsToMatch("/formation/**")
                        .pathsToExclude("**")
                        .build();}
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
