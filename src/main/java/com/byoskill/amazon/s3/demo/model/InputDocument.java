/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.model;

import java.nio.file.Path;

/**
 * The Class InputDocument.
 */
public class InputDocument {

    /** The bucket ID. */
    private BucketID bucketID;

    private final Path path;

    /**
     * Instantiates a new input document.
     *
     * @param bucketID
     *            the bucket ID
     * @param fileName
     *            the file name
     * @param path
     *            the path
     */
    public InputDocument(final BucketID bucketID, final Path path) {
	super();
	this.bucketID = bucketID;
	this.path = path;
    }

    /**
     * Gets the bucket ID.
     *
     * @return the bucket ID
     */
    public BucketID getBucketID() {
	return bucketID;
    }

    public Path getPath() {
	return path;
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

    @Override
    public String toString() {
	return "InputDocument [bucketID=" + bucketID + ", path=" + path + "]";
    }

}
