-- CREATE TYPE application.FILE_STATE AS ENUM ('UPLOADING', 'AVAILABLE', 'CORRUPTED');

CREATE TABLE application.file_types (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(127) NOT NULL,
    extension VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE application.files (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    path VARCHAR(255) NOT NULL,
    filename VARCHAR(255) NOT NULL,
    type_id UUID NOT NULL REFERENCES application.file_types(id),
    state VARCHAR(31) NOT NULL DEFAULT 'UPLOADING'
);
