package com.exadel.awsspringdemo.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.iot.AWSIot;
import com.amazonaws.services.iot.AWSIotClient;
import com.amazonaws.services.iotdata.AWSIotData;
import com.amazonaws.services.iotdata.AWSIotDataClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSIotDataConfiguration {

    @Value("${aws.iot.endpoint}")
    private String endpoint;

    @Value("${aws.iot.cognito.region}")
    private String region;

    private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(endpoint, region);
    }

    @Bean("awsIotData")
    public AWSIotData getIotDataClient() {
        //Overwrite insecure https://data.iot.us-east-1.amazonaws.com
        return AWSIotDataClient.builder().withEndpointConfiguration(getEndpointConfiguration()).build();
    }

    @Bean("awsIot")
    public AWSIot getIotClient() {
        return AWSIotClient.builder().withRegion(Regions.fromName(region)).build();
    }

}