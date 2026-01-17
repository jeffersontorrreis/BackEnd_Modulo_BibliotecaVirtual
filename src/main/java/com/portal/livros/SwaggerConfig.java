package com.portal.livros;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean //Veja que por padrão é escaneado automaticamente tudo que tem no pacote "controllers
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Biblioteca Virtual - Rest API")
                        .description("Testes Rotas ")
                        .version("1.0")
                        .termsOfService("Termo de uso: Open Source")
                        .contact(new Contact()
                                .name("Portal Livros ")
                                .url("http://www.seusite.com.br")
                                .email("voce@seusite.com.br"))
                        .license(new License()
                                .name("Licença - Sua Empresa")
                                .url("http://www.seusite.com.br")));
    }

    // Grupo 1: APIs de Livros
    // Obs: Veja que podemos criar "Bean" para cada controller.
    @Bean
    public GroupedOpenApi livrosApi() {
        return GroupedOpenApi.builder()
                .group("1-livros")
                .displayName("Livros")
                .pathsToMatch("/livros/**")
                .build();
    }

    // Grupo 2: APIs de Empréstimos
    @Bean
    public GroupedOpenApi emprestimosApi() {
        return GroupedOpenApi.builder()
                .group("2-emprestimos")
                .displayName("Empréstimos")
                .pathsToMatch("/emprestimos/**", "/historicoemprestimos/**")
                .build();
    }

    // Grupo 3: APIs de Usuários
    @Bean
    public GroupedOpenApi usuariosApi() {
        return GroupedOpenApi.builder()
                .group("3-usuarios")
                .displayName("Usuários")
                .pathsToMatch("/users/**")
                .build();
    }

    // Grupo 4: Todas as APIs (opcional - para visão geral)
    @Bean
    public GroupedOpenApi todasApis() {
        return GroupedOpenApi.builder()
                .group("0-todas")
                .displayName("Todas as APIs")
                .pathsToMatch("/**")
                .build();
    }
}