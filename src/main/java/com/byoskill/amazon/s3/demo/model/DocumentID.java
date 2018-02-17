/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.model;

import com.amazonaws.services.s3.model.PutObjectResult;

/**
 * The Class DocumentID is a reference to a document.
 */
public class DocumentID {

    /**
     * Builds the DocumentID.
     *
     * @param bucketID
     *            the bucket ID
     * @param putObject
     *            the put object
     * @param objectKey
     *            the object key
     * @return the document ID
     */
    public static DocumentID of(final BucketID bucketID, final PutObjectResult putObject, final String objectKey) {
	return new DocumentID(bucketID, objectKey, putObject.getContentMd5());
    }

    /** The bucket ID. */
    private BucketID bucketID;

    /** The content md 5. */
    private String contentMd5;

    /** The object key. */
    private String objectKey;

    /**
     * Instantiates a new document ID.
     *
     * @param bucketID
     *            the bucket ID
     * @param objectKey
     *            the object key
     * @param contentMd5
     *            the content md 5
     */
    public DocumentID(final BucketID bucketID, final String objectKey, final String contentMd5) {
	this.bucketID = bucketID;
	this.objectKey = objectKey;
	this.contentMd5 = contentMd5;

    }

    /**
     * Gets the bucket ID.
     *
     * @return the bucket ID
     */
    public BucketID getBucketID() {
	return bucketID;
    }

    /**
     * Gets the content md 5.
     *
     * @return the content md 5
     */
    public String getContentMd5() {
	return contentMd5;
    }

    /**
     * Gets the object key.
     *
     * @return the object key
     */
    public String getObjectKey() {
	return objectKey;
    }

    /**
     * Sets the bucket ID.
     *
     * @param bucketID
     *            the new bucket ID
     */
    public void setBucketID(final BucketID bucketID) {
	this.bucketID = bucketID;
    }

    /**
     * Sets the content md 5.
     *
     * @param contentMd5
     *            the new content md 5
     */
    public void setContentMd5(final String contentMd5) {
	this.contentMd5 = contentMd5;
    }

    /**
     * Sets the object key.
     *
     * @param objectKey
     *            the new object key
     */
    public void setObjectKey(final String objectKey) {
	this.objectKey = objectKey;
    }

    @Override
    public String toString() {
	return "DocumentID [bucketID=" + bucketID + ", contentMd5=" + contentMd5 + ", objectKey=" + objectKey + "]";
    }

}
