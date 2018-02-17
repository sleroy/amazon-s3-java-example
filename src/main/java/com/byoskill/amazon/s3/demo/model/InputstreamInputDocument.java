/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.model;

import java.io.InputStream;
import java.nio.file.Path;

import javax.annotation.concurrent.Immutable;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class FileInputDocument represents a resource to store into S3. the
 * content is provided as a file path.
 */
@Immutable
public class InputstreamInputDocument extends InputDocument {

    /** The resource. */
    private InputStream resource;

    /**
     * Instantiates a new inputstream input document.
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
    public InputstreamInputDocument(final BucketID bucketID, final Path path, final InputStream resource) {
	super(bucketID, path);
	this.resource = resource;
    }

    /**
     * Gets the resource.
     *
     * @return the resource
     */
    public InputStream getResource() {
	return resource;
    }

    /**
     * Sets the resource.
     *
     * @param resource
     *            the new resource
     */
    public void setResource(final InputStream resource) {
	this.resource = resource;
    }

    @Override
    public String toString() {
	return "InputstreamInputDocument [resource=" + resource + ", toString()=" + super.toString() + "]";
    }
}
