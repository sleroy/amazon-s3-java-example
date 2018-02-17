/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.service;

import java.io.File;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.byoskill.amazon.s3.demo.api.IBucketService;
import com.byoskill.amazon.s3.demo.api.IDocumentManagementService;
import com.byoskill.amazon.s3.demo.model.BucketID;
import com.byoskill.amazon.s3.demo.model.DocumentID;
import com.byoskill.amazon.s3.demo.model.FileInputDocument;
import com.byoskill.amazon.s3.demo.model.InputstreamInputDocument;
import com.byoskill.amazon.s3.demo.model.StringInputDocument;

/**
 * The Class DocumentManagementService is an example service to manipulate
 * documents.
 */
public class DocumentManagementService implements IDocumentManagementService {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentManagementService.class);

    /** The amazon client service. */
    private final AmazonClientService amazonClientService;

    /** The bucket service. */
    private final IBucketService bucketService;

    /**
     * Instantiates a new document management service.
     *
     * @param bucketServiceRef
     *            the bucket service
     * @param amazonClientService
     *            the amazon client service
     */
    public DocumentManagementService(final IBucketService bucketServiceRef,
	    final AmazonClientService amazonClientService) {
	bucketService = bucketServiceRef;
	this.amazonClientService = amazonClientService;
    }

    @Override
    public DocumentID createDocumentFromFile(final FileInputDocument inputDocument) {

	// upload file to folder and set it to public final String fileName =
	final AmazonS3 client = getClient();

	final BucketID bucketID = inputDocument.getBucketID();
	Validate.isTrue(bucketService.existBucket(bucketID));

	final String bucketName = bucketID.getBucketName();
	final String objectKey = inputDocument.getPath().toString();
	final File resource = inputDocument.getResource();
	final PutObjectResult putObject = client.putObject(
		new PutObjectRequest(bucketName, objectKey, resource).withCannedAcl(bucketService.getACL(bucketID)));

	return DocumentID.of(bucketID, putObject, objectKey);
    }

    @Override
    public DocumentID createDocumentFromStream(final InputstreamInputDocument inputDocument) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public DocumentID createDocumentFromString(final StringInputDocument inputDocument) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void deleteDocument(final DocumentID documentID) {
	bucketService.deleteObject(documentID.getBucketID(), documentID.getObjectKey());
    }

    @Override
    public boolean existsDocument(final DocumentID documentID) {
	return bucketService.existObject(documentID.getBucketID(), documentID.getObjectKey());
    }

    @Override
    public S3ObjectInputStream getDocumentContentAsStream(final DocumentID documentID) {
	final AmazonS3 client = getClient();
	final S3Object object = client.getObject(documentID.getBucketID().getBucketName(), documentID.getObjectKey());
	Validate.notNull(object);
	return object.getObjectContent();
    }

    @Override
    public String getDocumentContentAsString(final DocumentID documentID) {
	final AmazonS3 client = getClient();
	return client.getObjectAsString(documentID.getBucketID().getBucketName(), documentID.getObjectKey());
    }

    private AmazonS3 getClient() {
	return amazonClientService.initAmazonClient();
    }
}
