# Environment Variables

Before starting the application, or even running first migrations, there are some environment variables that should be set in a `.env`. The following is an example of the variables:

```conf
SERVER_PORT=

DATABASE_URI=
DATABASE_USERNAME=
DATABASE_PASSWORD=

JWT_SECRET=
JWT_EXPIRES_IN=
JWT_ISSUER=
JWT_SUBJECT=

STORAGE_TYPE=

LOCAL_STORAGE_ROOT_DIRECTORY=

MINIO_ENDPOINT=
MINIO_USER=
MINIO_PASSWORD=
MINIO_ACCESS_KEY=
MINIO_SECRET_KEY=
MINIO_BUCKET=
```

## SERVER_PORT

__Optional__.

Defines the port in which the application will be served. Default value is `8080`.

## DATABASE_URI

__Required__.

A _connection string_ to access the database. Usually prefixed with `jdbc:`.

## DATABASE_USERNAME

__Required__.

## DATABASE_PASSWORD

__Required__.

## JWT_SECRET

__Required__.

## JWT_EXPIRES_IN

__Required__.

## JWT_ISSUER

__Required__.

## JWT_SUBJECT

__Required__.

## STORAGE_TYPE

__Required__.

The possible values are: local and minio.

## LOCAL_STORAGE_ROOT_DIRECTORY

__Optional/Required__.

If `STORAGE_TYPE` is local this property is required; Otherwise optional.

The root directory is the main directory in the OS where the files will be stored. The application will manage file IO from this directory.

## MINIO_ENDPOINT

__Optional/Required__.

If `STORAGE_TYPE` is minio this property is required; Otherwise optional.

## MINIO_USER

__Optional/Required__.

If `STORAGE_TYPE` is minio this property is required; Otherwise optional.

## MINIO_PASSWORD

__Optional/Required__.

If `STORAGE_TYPE` is minio this property is required; Otherwise optional.

## MINIO_ACCESS_KEY

__Optional/Required__.

If `STORAGE_TYPE` is minio this property is required; Otherwise optional.

## MINIO_SECRET_KEY

__Optional/Required__.

If `STORAGE_TYPE` is minio this property is required; Otherwise optional.

## MINIO_BUCKET

__Optional/Required__.

If `STORAGE_TYPE` is minio this property is required; Otherwise optional.
