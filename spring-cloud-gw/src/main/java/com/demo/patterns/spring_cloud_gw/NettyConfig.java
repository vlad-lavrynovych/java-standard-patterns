package com.demo.patterns.spring_cloud_gw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.server.logging.AccessLog;

@Slf4j
@Configuration
public class NettyConfig implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

    @Override
    public void customize(NettyReactiveWebServerFactory container) {
        container.addServerCustomizers(httpServer ->
            httpServer.accessLog(true, (accessLog) -> {
                var origin = accessLog.requestHeader("Origin");
                if (origin == null) origin = "N/A";

                return AccessLog.create(
                    "method=\"{}\", uri=\"{}\", status=\"{}\", duration=\"{}\", origin=\"{}\"",
                    accessLog.method(),
                    accessLog.uri(),
                    accessLog.status(),
                    accessLog.duration(),
                    origin
                );
            })
        );
    }
}
