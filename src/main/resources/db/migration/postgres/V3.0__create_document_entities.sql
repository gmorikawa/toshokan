-- CREATE TYPE application.BOOK_TYPE AS ENUM ('FICTION', 'NON_FICTION');

CREATE TABLE application.categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(127) UNIQUE NOT NULL
);

CREATE TABLE application.authors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    fullname VARCHAR(127) UNIQUE NOT NULL,
    biography VARCHAR(4095)
);

CREATE TABLE application.publishers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(63) UNIQUE NOT NULL,
    description VARCHAR(4095)
);

CREATE TABLE application.topics (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(127) UNIQUE NOT NULL
);

CREATE TABLE application.document (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(225) NOT NULL,
    description VARCHAR(1024),
    category_id UUID REFERENCES application.categories(id)
);

CREATE TABLE application.books (
    id UUID PRIMARY KEY REFERENCES application.document(id),
    isbn VARCHAR(31) UNIQUE NOT NULL,
    publisher_id UUID REFERENCES application.publishers(id),
    published_at DATE,
    type VARCHAR(31) NOT NULL DEFAULT 'NON_FICTION'
);

CREATE TABLE application.whitepapers (
    id UUID PRIMARY KEY REFERENCES application.document(id),
    published_at DATE
);

CREATE TABLE application.document_file (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    document_id UUID REFERENCES application.document(id),
    file_id UUID REFERENCES application.files(id),
    label VARCHAR(4095)
);

CREATE TABLE application.document_authors (
    document_id UUID REFERENCES application.document(id) ON DELETE CASCADE,
    author_id UUID REFERENCES application.authors(id) ON DELETE CASCADE,
    PRIMARY KEY (document_id, author_id)
);

CREATE TABLE application.document_topics (
    document_id UUID REFERENCES application.document(id) ON DELETE CASCADE,
    topic_id UUID REFERENCES application.topics(id) ON DELETE CASCADE,
    PRIMARY KEY (document_id, topic_id)
);