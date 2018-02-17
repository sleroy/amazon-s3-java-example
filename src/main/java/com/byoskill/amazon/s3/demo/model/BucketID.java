/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.model;

import com.amazonaws.services.s3.model.Bucket;

/**
 * The Class BucketID represents a Bucket identifier.
 */
public class BucketID {

    /**
     * Factory to build a bucktID.
     *
     * @param bucket the bucket
     * @param region the region
     * @return the bucket ID
     */
    public static BucketID of(final Bucket bucket, final String region) {
	return new BucketID(bucket.getName(), region);
    }

    /**
     * Factory to build a Bucket ID.
     *
     * @param bucketName
     *            the bucket name
     * @param region
     *            the region
     * @return the bucket ID
     */
    public static BucketID of(final String bucketName, final String region) {
	return new BucketID(bucketName, region);
    }

    /** The bucket name. */
    private String bucketName;

    /** The region. */
    private final String region;

    /**
     * Instantiates a new bucket ID.
     *
     * @param bucketName
     *            the bucket name
     * @param region
     *            the region
     */
    public BucketID(final String bucketName, final String region) {
	this.bucketName = bucketName;
	this.region = region;
    }

    /**
     * Gets the bucket name.
     *
     * @return the bucket name
     */
    public String getBucketName() {
	return bucketName;
    }

    public String getRegion() {
	return region;
    }

    /**
     * Sets the bucket name.
     *
     * @param bucketName the new bucket name
     */
    public void setBucketName(final String bucketName) {
	this.bucketName = bucketName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "BucketID [bucketName=" + bucketName + ", region=" + region + "]";
    }
}
