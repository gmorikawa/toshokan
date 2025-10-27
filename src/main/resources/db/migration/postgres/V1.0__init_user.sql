-- CREATE TYPE application.USER_ROLE AS ENUM ('ADMIN', 'LIBRARIAN', 'READER');

CREATE TABLE application.users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(127) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(127) UNIQUE NOT NULL,
    role VARCHAR(31) NOT NULL DEFAULT 'READER',
    fullname VARCHAR(127)
);

INSERT INTO application.users (username, password, email, role, fullname)
    VALUES ('gmorikawa', '$2a$10$D2d9ikFdcpnQSr3xTQTAhuzvAIxsOcn1f9hFJpnVsBn84YI.j5GWG', 'gabriel.morikawa@live.com', 'ADMIN', 'Gabriel Morikawa');