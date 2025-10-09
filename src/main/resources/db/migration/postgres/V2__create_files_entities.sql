-- Table: file_types
CREATE TABLE file_types (
    id UUID PRIMARY KEY,
    name VARCHAR(127) NOT NULL,
    extension VARCHAR(15) UNIQUE NOT NULL
);

-- Table: files
CREATE TABLE files (
    id UUID PRIMARY KEY,
    path VARCHAR(255) NOT NULL,
    filename VARCHAR(255) NOT NULL,
    type_id UUID REFERENCES file_types(id),
    state VARCHAR(32) NOT NULL
);
