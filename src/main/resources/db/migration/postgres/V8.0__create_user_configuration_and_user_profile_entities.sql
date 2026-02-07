CREATE TABLE application.user_configurations (
    user_id UUID PRIMARY KEY REFERENCES application.users(id) ON DELETE CASCADE,
    email_confirmed BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE application.user_profiles (
    user_id UUID PRIMARY KEY REFERENCES application.users(id) ON DELETE CASCADE,
    fullname VARCHAR(127),
    biography TEXT,
    avatar_id UUID REFERENCES application.files(id) ON DELETE SET NULL
);

INSERT INTO application.user_configurations (user_id)
    SELECT u.id
    FROM application.users u
    ON CONFLICT (user_id) DO NOTHING;

INSERT INTO application.user_profiles (user_id, fullname)
    SELECT u.id, u.fullname
    FROM application.users u
    ON CONFLICT (user_id) DO NOTHING;

ALTER TABLE application.users
    DROP COLUMN fullname;