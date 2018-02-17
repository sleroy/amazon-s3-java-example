/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.model;

import java.io.File;
import java.nio.file.Path;

import javax.annotation.concurrent.Immutable;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class FileInputDocument represents a resource to store into S3. the
 * content is provided as a file path.
 */
@Immutable
public class FileInputDocument extends InputDocument {

    /** The resource. */
    private final File resource;

    /**
     * Instantiates a new file input document.
     *
     * @param resource
     *            the resource
     * @param fileName
     *            the file name
     * @param bucketID
     *            the bucket ID
     * @param path
     *            the path
     */
    @GeneratePojoBuilder
    public FileInputDocument(final File resource, final Path fileName, final BucketID bucketID) {
	super(bucketID, fileName);
	this.resource = resource;
    }

    /**
     * Gets the resource.
     *
     * @return the resource
     */
    public File getResource() {
	return resource;
    }

    @Override
    public String toString() {
	return "FileInputDocument [resource=" + resource + ", toString()=" + super.toString() + "]";
    }
}
