/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.model;

import java.nio.file.Path;

import javax.annotation.concurrent.Immutable;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class FileInputDocument represents a resource to store into S3. the
 * content is provided as a string.
 */
@Immutable
public class StringInputDocument extends InputDocument {

    /** The resource. */
    private String resource;

    /**
     * Instantiates a new string input document.
     *
     * @param bucketID
     *            the bucket ID
     * @param fileName
     *            the file name
     * @param path
     *            the path
     * @param resource
     *            the resource
     */
    @GeneratePojoBuilder
    public StringInputDocument(final BucketID bucketID, final Path path, final String resource) {
	super(bucketID, path);
	this.resource = resource;
    }

    /**
     * Gets the resource.
     *
     * @return the resource
     */
    public String getResource() {
	return resource;
    }

    /**
     * Sets the resource.
     *
     * @param resource
     *            the new resource
     */
    public void setResource(final String resource) {
	this.resource = resource;
    }

    @Override
    public String toString() {
	return "StringInputDocument [resource=" + resource + ", toString()=" + super.toString() + "]";
    }
}
