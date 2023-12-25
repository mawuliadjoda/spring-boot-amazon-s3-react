package com.esprit.springbootamazons3;

import com.esprit.springbootamazons3.s3.S3Buckets;
import com.esprit.springbootamazons3.s3.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAmazonS3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAmazonS3Application.class, args);
	}



	@Bean
	CommandLineRunner runner(
			S3Service s3Service,
			S3Buckets s3Buckets
	) {
		return args -> {
			testBucketUploadAndDownload(s3Service, s3Buckets);
		};
	}

	private static void testBucketUploadAndDownload(S3Service s3Service, S3Buckets s3Buckets) {
		s3Service.putObject(
				s3Buckets.getCustomer(),
				"test_s3",
				"Hello world test s3".getBytes()
		);
		byte[] obj = s3Service.getObject(s3Buckets.getCustomer(), "test_s3");
		System.out.printf("Hooray " + new String(obj));
	}

}
