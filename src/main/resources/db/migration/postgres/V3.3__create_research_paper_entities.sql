CREATE TABLE application.whitepapers (
    id UUID PRIMARY KEY REFERENCES application.document(id),
    organization_id UUID REFERENCES application.organizations(id),
    keywords VARCHAR(255),
);
