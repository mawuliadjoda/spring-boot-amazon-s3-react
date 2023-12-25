package com.esprit.springbootamazons3.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.internal.LazyAwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {


    @Value("${aws.region}")
    private String awsRegion;


    /*
     To configure credentials the right way is :
     @see https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-temporary.html
     */
    @Value("${aws.credentials.access-key}")
    private String accessKey;
    @Value("${aws.credentials.secret-key}")
    private String secretKey;

    @Bean
    public S3Client s3Client() {
        AwsCredentials awsCredentials = AwsBasicCredentials.create(
                accessKey,
                secretKey
        );

        S3Client client = S3Client.builder()
                .region(Region.of(awsRegion))

                // when set this
                // https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-temporary.html
                // we don't need to add  .credentialsProvider( ()-> awsCredentials  )
                .credentialsProvider( ()-> awsCredentials  )
                .build();
        return client;
    }
}
