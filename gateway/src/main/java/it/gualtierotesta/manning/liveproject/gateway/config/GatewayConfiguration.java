package it.gualtierotesta.manning.liveproject.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Gateway configuration: routes, filters,...
 */
@Configuration
public class GatewayConfiguration {

    @Value("${app.businessserver.uri}")
    private String businessserverUri;

    @Bean
    public RouteLocator routes(final RouteLocatorBuilder pBuilder) {

        return pBuilder.routes()
            .route(ps -> ps
                .path("/metric/**")
                .uri(businessserverUri + "/metric"))

            .route(ps -> ps
                .path("/profile/**")
                .uri(businessserverUri + "/profile"))

            .route(ps -> ps
                .path("/advice/**")
                .uri(businessserverUri + "/advice"))

            .build();
    }
}
