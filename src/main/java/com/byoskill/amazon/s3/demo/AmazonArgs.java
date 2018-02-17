/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo;

import com.beust.jcommander.Parameter;

/**
 * The Class AmazonArgs.
 */
public class AmazonArgs {

    /** The access key. */
    @Parameter(names = { "--accessKey", "--access", "-key" }, description = "Amazon Access key")
    private String accessKey = "";

    /** The endpoint URL. */
    @Parameter(names = { "--s3Endpoint", "-s3" }, description = "Amazon Secret Access key")
    private String endpointURL = "";

    /** The region. */
    @Parameter(names = { "--region", "-r" }, description = "Amazon region")
    private String region = "us-east-2";

    /** The secret key. */
    @Parameter(names = { "--secretKey", "--secret", "-sc" }, description = "Amazon Secret Access key")
    private String secretKey = "";

    /**
     * Gets the access key.
     *
     * @return the access key
     */
    public String getAccessKey() {
	return accessKey;
    }

    /**
     * Gets the endpoint URL.
     *
     * @return the endpoint URL
     */
    public String getEndpointURL() {
	return endpointURL;
    }

    /**
     * Gets the region.
     *
     * @return the region
     */
    public String getRegion() {
	return region;
    }

    /**
     * Gets the secret key.
     *
     * @return the secret key
     */
    public String getSecretKey() {
	return secretKey;
    }

    /**
     * Sets the access key.
     *
     * @param accessKey the new access key
     */
    public void setAccessKey(final String accessKey) {
	this.accessKey = accessKey;
    }

    /**
     * Sets the endpoint URL.
     *
     * @param endpointURL the new endpoint URL
     */
    public void setEndpointURL(final String endpointURL) {
	this.endpointURL = endpointURL;
    }

    /**
     * Sets the region.
     *
     * @param region the new region
     */
    public void setRegion(final String region) {
	this.region = region;
    }

    /**
     * Sets the secret key.
     *
     * @param secretKey the new secret key
     */
    public void setSecretKey(final String secretKey) {
	this.secretKey = secretKey;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AmazonArgs [accessKey=" + accessKey + ", endpointURL=" + endpointURL + ", region=" + region + ", secretKey="
		+ secretKey + "]";
    }

}