CREATE TABLE application.users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(127) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(127) UNIQUE NOT NULL,
    role VARCHAR(31) NOT NULL DEFAULT 'READER',
    status VARCHAR(31) NOT NULL DEFAULT 'ACTIVE',
    fullname VARCHAR(127)
);
