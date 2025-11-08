CREATE TABLE application.categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(127) UNIQUE NOT NULL
);

CREATE TABLE application.publishers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(63) UNIQUE NOT NULL,
    description VARCHAR(4095)
);

CREATE TABLE application.books (
    id UUID PRIMARY KEY REFERENCES application.document(id),
    category_id UUID REFERENCES application.categories(id),
    publisher_id UUID REFERENCES application.publishers(id),
    type VARCHAR(31) NOT NULL DEFAULT 'NON_FICTION'
);