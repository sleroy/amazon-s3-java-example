/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.byoskill.amazon.s3.demo.api.IBucketService;
import com.byoskill.amazon.s3.demo.model.BucketID;
import com.google.common.base.Optional;

/**
 * The Class BucketService is amanging the buckets
 */
public class BucketService implements IBucketService {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BucketService.class);

    /** The Constant SUFFIX. */
    private static final String SUFFIX = "/";

    /** The amazon client service. */
    private final AmazonClientService amazonClientService;

    /**
     * Instantiates a new bucket service.
     *
     * @param amazonClientService
     *            the amazon client service
     */
    public BucketService(final AmazonClientService amazonClientService) {
	super();
	this.amazonClientService = amazonClientService;
    }

    /* (non-Javadoc)
     * @see com.byoskill.amazon.s3.demo.api.IBucketService#createBucket(java.lang.String)
     */
    @Override
    public BucketID createBucket(final String bucketName) {
	final AmazonS3 amazonClient = getClient();
	final Bucket bucket = amazonClient.createBucket(bucketName);
	return BucketID.of(bucket, amazonClientService.getRegion());
    }

    /* (non-Javadoc)
     * @see com.byoskill.amazon.s3.demo.api.IBucketService#createFolder(com.byoskill.amazon.s3.demo.model.BucketID, java.nio.file.Path)
     */
    @Override
    public void createFolder(final BucketID bucketID, final Path path) {
	// create meta-data for your folder and set content-length to 0
	final ObjectMetadata metadata = new ObjectMetadata();
	metadata.setContentLength(0);
	// create empty content
	final InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
	final String bucketName = bucketID.getBucketName();
	final String pathName = path.toString();
	// create a PutObjectRequest passing the folder name suffixed by /
	final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, pathName + SUFFIX, emptyContent,
		metadata);
	// send request to S3 to create folder
	getClient().putObject(putObjectRequest);

    }

    /* (non-Javadoc)
     * @see com.byoskill.amazon.s3.demo.api.IBucketService#deleteBucket(com.byoskill.amazon.s3.demo.model.BucketID)
     */
    @Override
    public void deleteBucket(final BucketID bucketID) {
	Validate.isTrue(bucketID.getRegion().equals(amazonClientService.getRegion()));
	final AmazonS3 amazonClient = getClient();
	amazonClient.deleteBucket(bucketID.getBucketName());
    }

    /* (non-Javadoc)
     * @see com.byoskill.amazon.s3.demo.api.IBucketService#deleteFolder(com.byoskill.amazon.s3.demo.model.BucketID, java.nio.file.Path)
     */
    @Override
    public void deleteFolder(final BucketID bucketID, final Path path) {
	final AmazonS3 client = getClient();
	final String pathName = path.toString();
	final String bucketName = bucketID.getBucketName();
	final List<S3ObjectSummary> fileList = client.listObjects(bucketName, pathName).getObjectSummaries();
	for (final S3ObjectSummary file : fileList) {
	    deleteObject(bucketID, file.getKey());
	}
	deleteObject(bucketID, pathName);

    }

    /**
     * Delete an object from a bucket.
     *
     * @param bucketID
     *            the bucket ID
     * @param key
     *            the key
     */
    @Override
    public void deleteObject(final BucketID bucketID, final String key) {
	getClient().deleteObject(bucketID.getBucketName(), key);

    }

    /**
     * Exist bucket.
     *
     * @param bucketID
     *            the create bucket
     * @return true, if successful
     */
    @Override
    public boolean existBucket(final BucketID bucketID) {
	Validate.isTrue(bucketID.getRegion().equals(amazonClientService.getRegion()));
	return existBucket(bucketID.getBucketName());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.byoskill.amazon.s3.demo.api.IBucketService#existBucket(java.lang.
     * String)
     */
    @Override
    public boolean existBucket(final String bucketName) {
	return getClient().doesBucketExist(bucketName);
    }

    /* (non-Javadoc)
     * @see com.byoskill.amazon.s3.demo.api.IBucketService#existObject(com.byoskill.amazon.s3.demo.model.BucketID, java.lang.String)
     */
    @Override
    public boolean existObject(final BucketID bucketID, final String objectKey) {
	return getClient().doesObjectExist(bucketID.getBucketName(), objectKey);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.byoskill.amazon.s3.demo.api.IBucketService#findBucketByName(java.lang
     * .String)
     */
    @Override
    public Optional<BucketID> findBucketByName(final String bucketName) {
	if (existBucket(bucketName)) {
	    return Optional.of(BucketID.of(bucketName, amazonClientService.getRegion()));
	} else {
	    return Optional.absent();
	}
    }

    /* (non-Javadoc)
     * @see com.byoskill.amazon.s3.demo.api.IBucketService#getACL(com.byoskill.amazon.s3.demo.model.BucketID)
     */
    @Override
    public CannedAccessControlList getACL(final BucketID bucketID) {
	return CannedAccessControlList.PublicRead;
    }

    /* (non-Javadoc)
     * @see com.byoskill.amazon.s3.demo.api.IBucketService#getOrCreateBucket(java.lang.String)
     */
    @Override
    public BucketID getOrCreateBucket(final String bucketName) {
	if (existBucket(bucketName)) {
	    return BucketID.of(bucketName, amazonClientService.getRegion());
	} else {
	    return createBucket(bucketName);
	}
    }

    /**
     * List buckets.
     *
     * @return the list
     */
    @Override
    public final List<Bucket> listBuckets() {
	return getClient().listBuckets();
    }

    /**
     * Gets the client.
     *
     * @return the client
     */
    private AmazonS3 getClient() {
	return amazonClientService.initAmazonClient();
    }
}
