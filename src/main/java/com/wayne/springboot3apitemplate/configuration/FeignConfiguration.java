package com.wayne.springboot3apitemplate.configuration;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Configuration
@RequiredArgsConstructor
public class FeignConfiguration {


    private final ObjectFactory<HttpMessageConverters> messageConverters;

    private final ObjectProvider<HttpMessageConverterCustomizer> customizers;


    private static final long RETRY_PERIOD = 1000; // 初始重试间隔
    private static final long RETRY_PERIOD_MAX = RETRY_PERIOD * 3; // 最大重试间隔
    private static final int RETRY_ATTE = 3; // 最大重试次数，初始调用算一次

    /**
     * 服务调用客户端
     */
    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();

        builder.setReadTimeout(Duration.ofSeconds(30));
        builder.setConnectTimeout(Duration.ofSeconds(3));

        return builder.build();
    }

    /**
     * 重试机制
     */
    @Bean
    public Retryer retryer() {

        return new Retryer.Default(
                RETRY_PERIOD,
                RETRY_PERIOD_MAX,
                RETRY_ATTE
        );
    }


    /**
     * 表单编码器
     */
    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    /**
     * 消息解码器
     */
    @Bean
    public Decoder feignDecoder(){
        return new SpringDecoder(messageConverters, customizers);
    }
    // 开启Feign的日志
    @Bean
    public Logger.Level logger() {
        return Logger.Level.FULL;
    }

    /**
     * 配置 内部调用认证
     * @return
     */
    @Bean
    public InternalRequestInterceptor internalRequestInterceptor() {
        return new InternalRequestInterceptor();
    }

    protected static class InternalRequestInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate requestTemplate) {
            requestTemplate.header("Authentication", "internal");
        }
    }
}
