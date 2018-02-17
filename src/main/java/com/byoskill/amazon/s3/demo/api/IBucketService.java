/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.api;

import java.nio.file.Path;
import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.byoskill.amazon.s3.demo.model.BucketID;
import com.google.common.base.Optional;

// TODO: Auto-generated Javadoc
/**
 * The Interface IBucketService returns the.
 */
public interface IBucketService {

    /**
     * Creates the bucket.
     *
     * @param bucketName the name
     * @return the bucket ID
     */
    BucketID createBucket(String bucketName);

    /**
     * Creates a folder inside a bucket.
     *
     * @param bucketID the bucket ID
     * @param path the path
     */
    void createFolder(BucketID bucketID, Path path);

    /**
     * Delete a bucket.
     *
     * @param bucketID the bucket ID
     */
    void deleteBucket(BucketID bucketID);

    /**
     * Delete folder.
     *
     * @param bucketID the bucket ID
     * @param path the path
     */
    void deleteFolder(BucketID bucketID, Path path);

    /**
     * Delete object.
     *
     * @param bucketID the bucket ID
     * @param objectKey the object key
     * @return the object
     */
    void deleteObject(BucketID bucketID, String objectKey);

    /**
     * Tests if the bucketID referes to an existing bucket.
     *
     * @param createBucket the create bucket
     * @return true, if successful
     */
    boolean existBucket(BucketID createBucket);

    /**
     * Tests if the bucket exists.
     *
     * @param bucketName the name
     * @return true, if successful
     */
    boolean existBucket(String bucketName);

    /**
     * Tests if the object exists.
     *
     * @param bucketID the bucket ID
     * @param objectKey the object key
     * @return true, if successful
     */
    boolean existObject(BucketID bucketID, String objectKey);

    /**
     * Find bucket by its name.
     *
     * @param bucketName the bucket name
     * @return the bucket ID
     */
    Optional<BucketID> findBucketByName(String bucketName);

    /**
     * Gets the acl.
     *
     * @param bucketID the bucket ID
     * @return the acl
     */
    CannedAccessControlList getACL(BucketID bucketID);

    /**
     * Gets or create the bucket.
     *
     * @param bucketName the bucket name
     * @return the or create bucket
     */
    BucketID getOrCreateBucket(String bucketName);

    /**
     * List the buckets.
     *
     * @return the lit of buckets
     */
    List<Bucket> listBuckets();

}
