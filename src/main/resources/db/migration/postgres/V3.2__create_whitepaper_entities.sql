CREATE TABLE application.organizations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(127) NOT NULL,
    description VARCHAR(4095),
    type VARCHAR(31) NOT NULL DEFAULT 'UNIVERSITY'
);

CREATE TABLE application.whitepapers (
    id UUID PRIMARY KEY REFERENCES application.document(id),
    organization_id UUID REFERENCES application.organizations(id),
);
