/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.service;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.byoskill.amazon.s3.demo.AmazonArgs;

public class AmazonClientService {
    private static final Logger	LOGGER = LoggerFactory.getLogger(AmazonClientService.class);
    private final AmazonArgs	amazonArgs;

    /**
     * Instantiates a new amazon client service.
     *
     * @param amazonArgs
     *            the amazon args
     */
    public AmazonClientService(final AmazonArgs amazonArgs) {
	this.amazonArgs = amazonArgs;
	Validate.notNull(amazonArgs);
    }

    /**
     * Gets the region.
     *
     * @return the region
     */
    public String getEndpoint() {
	return amazonArgs.getEndpointURL();
    }

    /**
     * Gets the region.
     *
     * @return the region
     */
    public String getRegion() {
	return amazonArgs.getRegion();
    }

    /**
     * Inits the amazon client.
     *
     * @return the amazon S3 client
     */
    public AmazonS3 initAmazonClient() {

	// credentials object identifying user for authentication
	// user must have AWSConnector and AmazonS3FullAccess for
	// this example to work
	final AWSCredentials credentials = new BasicAWSCredentials(amazonArgs.getAccessKey(),
		amazonArgs.getSecretKey());

	// create a client connection based on credentials
	return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
		.withEndpointConfiguration(
			new EndpointConfiguration(amazonArgs.getEndpointURL(), amazonArgs.getRegion()))
		.build();

    }
}
