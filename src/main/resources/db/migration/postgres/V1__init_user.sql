CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR(127) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(127) UNIQUE NOT NULL,
    role VARCHAR(32) NOT NULL DEFAULT 'READER',
    fullname VARCHAR(127)
);

INSERT INTO users (
    id,
    username,
    password,
    email,
    role,
    fullname
)
VALUES (
    gen_random_uuid(),
    'gmorikawa',
    '$2a$10$D2d9ikFdcpnQSr3xTQTAhuzvAIxsOcn1f9hFJpnVsBn84YI.j5GWG',
    'gabriel.morikawa@live.com',
    'ADMIN',
    'Gabriel Morikawa'
);