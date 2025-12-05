CREATE TABLE application.bundles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(127) UNIQUE NOT NULL,
    description VARCHAR(4095)
);
