CREATE TABLE application.authors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    fullname VARCHAR(127) UNIQUE NOT NULL,
    biography VARCHAR(4095)
);

CREATE TABLE application.topics (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(127) UNIQUE NOT NULL
);

CREATE TABLE application.languages (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(127) UNIQUE NOT NULL
);

CREATE TABLE application.document (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(225) NOT NULL,
    summary VARCHAR(4095),
    language_id UUID REFERENCES application.languages(id)
);

CREATE TABLE application.document_file (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    document_id UUID REFERENCES application.document(id),
    file_id UUID REFERENCES application.files(id),
    version VARCHAR(127) NOT NULL,
    description VARCHAR(4095),
    publishing_year SMALLINT
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