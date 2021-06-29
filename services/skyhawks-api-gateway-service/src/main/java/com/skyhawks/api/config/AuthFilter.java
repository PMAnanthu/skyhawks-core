package com.skyhawks.api.config;

import com.skyhawks.dtos.resonses.UserDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

   private final ApplicationConfiguration applicationConfiguration;
    
    private final RestTemplate restTemplate;

    public AuthFilter(RestTemplate restTemplate,ApplicationConfiguration applicationConfiguration) {
        super(Config.class);
        this.restTemplate = restTemplate;
        this.applicationConfiguration=applicationConfiguration;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing authorization information");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = authHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new RuntimeException("Incorrect authorization structure");
            }
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            UserDto userDto = restTemplate.getForObject(String.format("%s/authentication/validateToken?token=%s",
                    applicationConfiguration.getAuthenticationServiceUrl(), parts[1]), UserDto.class);
            if(userDto!=null) {
                builder.header("X-auth-user-id", String.valueOf(userDto.getId()));
            }else{
                throw new RuntimeException("Login failed");
            }
            return chain.filter(exchange.mutate().request(builder.build()).build());
        };
    }

    public static class Config {

    }
}