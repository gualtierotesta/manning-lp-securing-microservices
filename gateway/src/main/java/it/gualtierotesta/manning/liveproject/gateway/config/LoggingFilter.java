package it.gualtierotesta.manning.liveproject.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(
        final ServerWebExchange exchange,
        final GatewayFilterChain chain) {

        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        log.info("Incoming request {} {} is routed to {}: {}",
            exchange.getRequest().getMethod(),
            exchange.getRequest().getURI(),
            route.getUri(), route);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
