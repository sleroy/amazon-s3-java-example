/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.model.Bucket;
import com.beust.jcommander.JCommander;
import com.byoskill.amazon.s3.demo.api.IBucketService;
import com.byoskill.amazon.s3.demo.api.IDocumentManagementService;
import com.byoskill.amazon.s3.demo.model.BucketID;
import com.byoskill.amazon.s3.demo.model.DocumentID;
import com.byoskill.amazon.s3.demo.model.FileInputDocument;
import com.byoskill.amazon.s3.demo.model.FileInputDocumentBuilder;
import com.byoskill.amazon.s3.demo.service.AmazonClientService;
import com.byoskill.amazon.s3.demo.service.BucketService;
import com.byoskill.amazon.s3.demo.service.DocumentManagementService;

public class AmazonS3Example {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonS3Example.class);

    public static void main(final String[] args) {

	final AmazonArgs amazonArgs = new AmazonArgs();
	JCommander.newBuilder().addObject(amazonArgs).build().parse(args);

	amazonArgs.setAccessKey("N386RDH9QVMS0R6XFSJX");
	amazonArgs.setSecretKey("V17KJd7wr0tTxBzhut3ZRTgtRSNf15UjNy1oEcpK");
	amazonArgs.setEndpointURL("http://172.17.0.2:9000");
	amazonArgs.setRegion("us-east-2");

	final AmazonClientService amazonClientService = new AmazonClientService(amazonArgs);
	final IBucketService bucketService = new BucketService(amazonClientService);
	final IDocumentManagementService documentManagementService = new DocumentManagementService(bucketService,
		amazonClientService);

	final String bucketName = "customer-1-bucket";
	LOGGER.info("Attempt to create the bucket {}", bucketName);
	final BucketID currentBucket = bucketService.getOrCreateBucket(bucketName);
	Validate.isTrue(bucketService.existBucket(currentBucket), "Bucket created should exists");
	LOGGER.info("Bucket ID ---> {}", currentBucket);

	// list buckets

	LOGGER.info("Bucket list : ");
	for (final Bucket bucket : bucketService.listBuckets()) {
	    LOGGER.info(" - " + bucket.getName());
	}



	final Path path = Paths.get("reports", "financial");
	LOGGER.info("Creating the folder {}", path);
	bucketService.createFolder(currentBucket, path);

	final File resource = new File("src/main/resources/example1.txt");
	final FileInputDocument fileInputDocument = new FileInputDocumentBuilder().withBucketID(currentBucket)
		.withFileName(Paths.get("reports", "financial", resource.getName())).withResource(resource).build();

	LOGGER.info("Uploading a new resource in the bucket {}", fileInputDocument);
	final DocumentID documentID = documentManagementService.createDocumentFromFile(fileInputDocument);
	LOGGER.info("Document ID ---> {}", documentID);
	Validate.isTrue(documentManagementService.existsDocument(documentID), "The document should exists");

	final String content = documentManagementService.getDocumentContentAsString(documentID);
	LOGGER.info("Content {}", content);


	LOGGER.info("Deleting the folder");

	bucketService.deleteFolder(currentBucket, path);

	LOGGER.info("Deleting the bucket");
	// deletes bucket
	//bucketService.deleteBucket(currentBucket);
    }
}