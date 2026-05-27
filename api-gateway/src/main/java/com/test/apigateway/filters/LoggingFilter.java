package com.test.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String method = request.getMethod().name();
        String uri = request.getURI().toString();
        LocalDateTime timestamp = LocalDateTime.now();

        log.info("[Gateway Request] Timestamp: {} | Method: {} | URL: {}",
                timestamp, method, uri);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("[Gateway Response] Trả về response thành công cho URL: {}", uri);
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
