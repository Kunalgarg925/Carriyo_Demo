package com.carriyo.carriyodemo.config;

import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
    @Value("${elasticsearch.endpoint}")
    private String elasticSearchEndPoint;

    @Value("${elasticsearch.authorization.api}")
    private String authorizationAPIKey;

    @Bean
    public RestHighLevelClient elasticSearchClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                                new HttpHost(elasticSearchEndPoint, 443, "https"))
                        .setDefaultHeaders(new BasicHeader[]{new BasicHeader("Authorization", "ApiKey " + authorizationAPIKey)})
        );
    }

}
