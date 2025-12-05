CREATE TABLE application.bundle_documents (
    document_id UUID REFERENCES application.document(id) ON DELETE CASCADE,
    bundle_id UUID REFERENCES application.bundles(id) ON DELETE CASCADE,
    PRIMARY KEY (document_id, bundle_id)
);
