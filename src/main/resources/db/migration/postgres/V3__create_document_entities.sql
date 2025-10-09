-- Table: categories
CREATE TABLE categories (
    id UUID PRIMARY KEY,
    name VARCHAR(127) UNIQUE NOT NULL
);

-- Table: authors
CREATE TABLE authors (
    id UUID PRIMARY KEY,
    fullname VARCHAR(127) UNIQUE NOT NULL,
    biography VARCHAR(4095)
);

-- Table: publishers
CREATE TABLE publishers (
    id UUID PRIMARY KEY,
    name VARCHAR(63) UNIQUE NOT NULL,
    description VARCHAR(4095)
);

-- Table: topics
CREATE TABLE topics (
    id UUID PRIMARY KEY,
    name VARCHAR(127) UNIQUE NOT NULL
);

-- Table: documents
CREATE TABLE document (
    id UUID PRIMARY KEY,
    title VARCHAR(225) NOT NULL,
    description VARCHAR(1024),
    category_id UUID REFERENCES categories(id)
);

-- Table: books
CREATE TABLE books (
    id UUID PRIMARY KEY REFERENCES document(id),
    isbn VARCHAR(31) UNIQUE NOT NULL,
    publisher_id UUID REFERENCES publishers(id),
    published_at DATE,
    type VARCHAR(32)
);

-- Table: whitepapers
CREATE TABLE whitepapers (
    id UUID PRIMARY KEY REFERENCES document(id),
    published_at DATE
);

-- Table: document_files
CREATE TABLE document_file (
    id UUID PRIMARY KEY,
    document_id UUID REFERENCES document(id),
    file_id UUID REFERENCES files(id),
    label VARCHAR(4095)
);

-- Table: document_authors (many-to-many)
CREATE TABLE document_authors (
    document_id UUID REFERENCES document(id) ON DELETE CASCADE,
    author_id UUID REFERENCES authors(id) ON DELETE CASCADE,
    PRIMARY KEY (document_id, author_id)
);

-- Table: document_topics (many-to-many)
CREATE TABLE document_topics (
    document_id UUID REFERENCES document(id) ON DELETE CASCADE,
    topic_id UUID REFERENCES topics(id) ON DELETE CASCADE,
    PRIMARY KEY (document_id, topic_id)
);