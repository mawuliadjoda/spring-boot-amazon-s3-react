package com.esprit.springbootamazons3.aws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// https://www.youtube.com/watch?v=i-hoSg8iRG0&list=PLwvrYc43l1MzeA2bBYQhCWr2gvWLs9A7S&index=6
@Configuration
public class AmazonConfig {
    private final AmazonKeyProperties amazonKeyProperties;

    public AmazonConfig(AmazonKeyProperties amazonKeyProperties) {
        this.amazonKeyProperties = amazonKeyProperties;
    }

    @Bean
    public AmazonS3 s3 () {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                amazonKeyProperties.getAccessKey(),
                amazonKeyProperties.getSecretKey()
        );
        return AmazonS3ClientBuilder
                .standard()
                // https://stackoverflow.com/questions/44151982/aws-java-sdk-unable-to-find-a-region-via-the-region-provider-chain
                .withRegion("eu-west-3")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
