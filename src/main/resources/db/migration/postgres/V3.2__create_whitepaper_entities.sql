CREATE TABLE application.whitepapers (
    id UUID PRIMARY KEY REFERENCES application.document(id),
    published_at DATE
);
