ALTER TABLE application.document_file
    DROP COLUMN publishing_year,
    DROP COLUMN version;

ALTER TABLE application.document
    ADD COLUMN publishing_year SMALLINT;

ALTER TABLE application.books
    ADD COLUMN edition VARCHAR(127) NOT NULL DEFAULT 'First Edition';
