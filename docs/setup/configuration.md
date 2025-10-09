# Setup: Configuration

## Environment Variables

Before starting the application, or even running first migrations, there are some environment variables that should be set in a `.env`. The following is an example of the variables:

```conf
DATABASE_URI=
DATABASE_USERNAME=
DATABASE_PASSWORD=

JWT_SECRET=
JWT_EXPIRES_IN=
JWT_ISSUER=
JWT_SUBJECT=

LOCAL_STORAGE_ROOT_DIRECTORY=

MINIO_ENDPOINT=
MINIO_USER=
MINIO_PASSWORD=
MINIO_ACCESS_KEY=
MINIO_SECRET_KEY=
MINIO_BUCKET=
```

### DATABASE_URI

__Required__.

A _connection string_ to access the database. Usually prefixed with `jdbc:`.

### DATABASE_USERNAME

__Required__.

### DATABASE_PASSWORD

__Required__.

### JWT_SECRET

__Required__.

### JWT_EXPIRES_IN

__Required__.

### JWT_ISSUER

__Required__.

### JWT_SUBJECT

__Required__.
