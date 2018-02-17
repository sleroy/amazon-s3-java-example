/**
 * Copyright (C) 2017-2018 Sylvain Leroy
 */
package com.byoskill.amazon.s3.demo.api;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.byoskill.amazon.s3.demo.model.DocumentID;
import com.byoskill.amazon.s3.demo.model.FileInputDocument;
import com.byoskill.amazon.s3.demo.model.InputstreamInputDocument;
import com.byoskill.amazon.s3.demo.model.StringInputDocument;

public interface IDocumentManagementService {

    /**
     * Write a document into the object storage.
     *
     * @param inputDocument
     *            the input document
     * @return the document ID
     */
    DocumentID createDocumentFromFile(FileInputDocument inputDocument);

    /**
     * Write a document into the object storage.
     *
     * @param inputDocument
     *            the input document
     * @return the document ID
     */
    DocumentID createDocumentFromStream(InputstreamInputDocument inputDocument);

    /**
     * Write a document into the object storage.
     *
     * @param inputDocument
     *            the input document
     * @return the document ID
     */
    DocumentID createDocumentFromString(StringInputDocument inputDocument);

    /**
     * Delete a document.
     *
     * @param documentID
     *            the document ID
     */
    void deleteDocument(DocumentID documentID);

    /**
     * Tests if the document exists.
     *
     * @param documentID
     *            the document ID
     * @return true, if successful
     */
    boolean existsDocument(DocumentID documentID);

    /**
     * Gets the document content as a stream.
     *
     * @param documentID the document ID
     * @return the document content as a stream
     */
    S3ObjectInputStream getDocumentContentAsStream(DocumentID documentID);

    /**
     * Gets the document content as string.
     *
     * @param documentID the document ID
     * @return the document content as string
     */
    String getDocumentContentAsString(DocumentID documentID);

}
