# REST API - Endpoints

## Table of Contents

* [Authentication](#authentication)
  * [POST /api/auth/login](#post-apiauthlogin)
* [Users](#users)
  * [GET /api/users](#get-apiusers)
  * [GET /api/users/count](#get-apiuserscount)
  * [GET /api/users/{id}](#get-apiusersid)
  * [POST /api/users](#post-apiusers)
  * [PATCH /api/users/{id}](#patch-apiusersid)
  * [DELETE /api/users/{id}](#delete-apiusersid)
* [Configuration](#configuration)
  * [POST /api/configuration/admin](#post-apiconfigurationadmin)
* [File Types](#file-types)
  * [GET /api/file-types](#get-apifile-types)
  * [GET /api/file-types/{id}](#get-apifile-typesid)
  * [POST /api/file-types](#post-apifile-types)
  * [PATCH /api/file-types/{id}](#patch-apifile-typesid)
  * [DELETE /api/file-types/{id}](#delete-apifile-typesid)
* [Authors](#authors)
  * [GET /api/authors](#get-apiauthors)
  * [GET /api/authors/count](#get-apiauthorscount)
  * [GET /api/authors/{id}](#get-apiauthorsid)
  * [POST /api/authors](#post-apiauthors)
  * [PATCH /api/authors/{id}](#patch-apiauthorsid)
  * [DELETE /api/authors/{id}](#delete-apiauthorsid)
* [Categories](#categories)
  * [GET /api/categories](#get-apicategories)
  * [GET /api/categories/count](#get-apicategoriescount)
  * [GET /api/categories/{id}](#get-apicategoriesid)
  * [GET /api/categories/name/{name}](#get-apicategoriesnamename)
  * [POST /api/categories](#post-apicategories)
  * [PATCH /api/categories/{id}](#patch-apicategoriesid)
  * [DELETE /api/categories/{id}](#delete-apicategoriesid)
* [Topics](#topics)
  * [GET /api/topics](#get-apitopics)
  * [GET /api/topics/count](#get-apitopicscount)
  * [GET /api/topics/{id}](#get-apitopicsid)
  * [GET /api/topics/name/{name}](#get-apitopicsnamename)
  * [POST /api/topics](#post-apitopics)
  * [PATCH /api/topics/{id}](#patch-apitopicsid)
  * [DELETE /api/topics/{id}](#delete-apitopicsid)
* [Languages](#languages)
  * [GET /api/languages](#get-apilanguages)
  * [GET /api/languages/count](#get-apilanguagescount)
  * [GET /api/languages/{id}](#get-apilanguagesid)
  * [GET /api/languages/name/{name}](#get-apilanguagesnamename)
  * [POST /api/languages](#post-apilanguages)
  * [PATCH /api/languages/{id}](#patch-apilanguagesid)
  * [DELETE /api/languages/{id}](#delete-apilanguagesid)
* [Organizations](#organizations)
  * [GET /api/organizations](#get-apiorganizations)
  * [GET /api/organizations/count](#get-apiorganizationscount)
  * [GET /api/organizations/{id}](#get-apiorganizationsid)
  * [POST /api/organizations](#post-apiorganizations)
  * [PATCH /api/organizations/{id}](#patch-apiorganizationsid)
  * [DELETE /api/organizations/{id}](#delete-apiorganizationsid)
* [Publishers](#publishers)
  * [GET /api/publishers](#get-apipublishers)
  * [GET /api/publishers/count](#get-apipublisherscount)
  * [GET /api/publishers/{id}](#get-apipublishersid)
  * [GET /api/publishers/name/{name}](#get-apipublishersnamename)
  * [POST /api/publishers](#post-apipublishers)
  * [PATCH /api/publishers/{id}](#patch-apipublishersid)
  * [DELETE /api/publishers/{id}](#delete-apipublishersid)
* [Books](#books)
  * [GET /api/books](#get-apibooks)
  * [GET /api/books/count](#get-apibookscount)
  * [GET /api/books/{id}](#get-apibooksid)
  * [GET /api/books/{id}/files](#get-apibooksidfiles)
  * [GET /api/books/{id}/files/{documentFileId}/download](#get-apibooksidfilesdocumentfileiddownload)
  * [POST /api/books](#post-apibooks)
  * [POST /api/books/{id}/files/upload](#post-apibooksidfilesupload)
  * [PATCH /api/books/{id}](#patch-apibooksid)
  * [DELETE /api/books/{id}](#delete-apibooksid)
  * [DELETE /api/books/{id}/files/{documentFileId}](#delete-apibooksidfilesdocumentfileid)
* [Whitepapers](#whitepapers)
  * [GET /api/whitepapers](#get-apiwhitepapers)
  * [GET /api/whitepapers/count](#get-apiwhitepaperscount)
  * [GET /api/whitepapers/{id}](#get-apiwhitepapersid)
  * [GET /api/whitepapers/{id}/files](#get-apiwhitepapersidfiles)
  * [GET /api/whitepapers/{id}/files/{documentFileId}/download](#get-apiwhitepapersidfilesdocumentfileiddownload)
  * [POST /api/whitepapers](#post-apiwhitepapers)
  * [POST /api/whitepapers/{id}/files/upload](#post-apiwhitepapersidfilesupload)
  * [PATCH /api/whitepapers/{id}](#patch-apiwhitepapersid)
  * [DELETE /api/whitepapers/{id}](#delete-apiwhitepapersid)
  * [DELETE /api/whitepapers/{id}/files/{documentFileId}](#delete-apiwhitepapersidfilesdocumentfileid)
* [Research Papers](#research-papers)
  * [GET /api/research-papers](#get-apiresearch-papers)
  * [GET /api/research-papers/count](#get-apiresearch-paperscount)
  * [GET /api/research-papers/{id}](#get-apiresearch-papersid)
  * [GET /api/research-papers/{id}/files](#get-apiresearch-papersidfiles)
  * [GET /api/research-papers/{id}/files/{documentFileId}/download](#get-apiresearch-papersidfilesdocumentfileiddownload)
  * [POST /api/research-papers](#post-apiresearch-papers)
  * [POST /api/research-papers/{id}/files/upload](#post-apiresearch-papersidfilesupload)
  * [PATCH /api/research-papers/{id}](#patch-apiresearch-papersid)
  * [DELETE /api/research-papers/{id}](#delete-apiresearch-papersid)
  * [DELETE /api/research-papers/{id}/files/{documentFileId}](#delete-apiresearch-papersidfilesdocumentfileid)
* [Bundles](#bundles)
  * [GET /api/bundles](#get-apibundles)
  * [GET /api/bundles/count](#get-apibundlescount)
  * [GET /api/bundles/{id}](#get-apibundlesid)
  * [POST /api/bundles](#post-apibundles)
  * [PATCH /api/bundles/{id}](#patch-apibundlesid)
  * [DELETE /api/bundles/{id}](#delete-apibundlesid)

## Authentication

### POST /api/auth/login

Request for a _authorization token_ given a valid `username` and `password`.

#### Request

##### Payload

```json
{
    "username": "username",
    "password": "password123"
}
```

#### Response

##### Status 200 OK

If the `username` corresponds to a existing user and the `password` matches the _hash value_ saved in the database, then it returns logged user data with authorization token:

```json
{
    "loggedUser": {
        "id": "UUID",
        "username": "username",
        "email": "user@email.com",
        "role": "ADMIN",
        "status": "ACTIVE",
        "fullname": "John Doe",
    },
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

##### Status 401 Unauthorized

If a user with given `username` is not found, or if the `password` does not match the _hash_ on database, then `InvalidCredentialsException` is thrown:

```json
{
    "message": "Username and/or password are incorrect."
}
```

## Users

### GET /api/users

Get a list of users.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "username": "username",
        "email": "user@email.com",
        "role": "ADMIN",
        "status": "ACTIVE",
        "fullname": "John Doe",
    }
]
```

If users do not exist then returns a empty array:

```json
[]
```

##### Status 403 Forbidden

If the _authorization token_ is not present in the headers, then it throws `InvalidCredentialsException`.

```json
{
    "message": "User not authorized to execute action."
}
```

### GET /api/users/count

Get the total count of users in the system.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

#### Response

##### Status 200 OK

Returns the total number of users:

```json
42
```

##### Status 403 Forbidden

If the _authorization token_ is not present in the headers, then it throws `InvalidCredentialsException`.

```json
{
    "message": "User not authorized to execute action."
}
```

### GET /api/users/{id}

Get a specific user by ID.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the user

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "username": "username",
    "email": "user@email.com",
    "role": "ADMIN",
    "status": "ACTIVE",
    "fullname": "John Doe"
}
```

##### Status 403 Forbidden

If the _authorization token_ is not present or invalid.

```json
{
    "message": "User not authorized to execute action."
}
```

##### Status 404 Not Found

If the user with the specified ID is not found.

### POST /api/users

Create a new user.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "username": "newuser",
    "email": "newuser@email.com",
    "password": "password123",
    "role": "READER",
    "status": "ACTIVE",
    "fullname": "Jane Smith"
}
```

#### Response

##### Status 200 OK

Returns the created user (without password):

```json
{
    "id": "UUID",
    "username": "newuser",
    "email": "newuser@email.com",
    "role": "READER",
    "status": "ACTIVE",
    "fullname": "Jane Smith"
}
```

##### Status 403 Forbidden

If the user does not have permission to create users.

### PATCH /api/users/{id}

Update an existing user.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the user to update

##### Payload

```json
{
    "email": "updated@email.com",
    "fullname": "Updated Name"
}
```

#### Response

##### Status 200 OK

Returns the updated user (without password):

```json
{
    "id": "UUID",
    "username": "username",
    "email": "updated@email.com",
    "role": "READER",
    "status": "ACTIVE",
    "fullname": "Updated Name"
}
```

##### Status 403 Forbidden

If the user does not have permission to update users.

##### Status 404 Not Found

If the user with the specified ID is not found.

### DELETE /api/users/{id}

Delete a user.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the user to delete

#### Response

##### Status 200 OK

Returns `true` if the user was successfully deleted:

```json
true
```

##### Status 403 Forbidden

If the user does not have permission to delete users.

##### Status 404 Not Found

If the user with the specified ID is not found.

## Configuration

### POST /api/configuration/admin

Configure the first admin user in the system. This endpoint is typically used during initial system setup.

#### Request

##### Payload

```json
{
    "username": "admin",
    "email": "admin@example.com",
    "password": "securePassword123",
    "fullname": "System Administrator"
}
```

#### Response

##### Status 200 OK

Returns the created admin user:

```json
{
    "id": "UUID",
    "username": "admin",
    "email": "admin@example.com",
    "role": "ADMIN",
    "status": "ACTIVE",
    "fullname": "System Administrator"
}
```

## File Types

### GET /api/file-types

Get a list of all supported file types.

#### Request

No authentication required.

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "name": "PDF",
        "extension": ".pdf",
        "mimeType": "application/pdf"
    },
    {
        "id": "UUID",
        "name": "EPUB",
        "extension": ".epub",
        "mimeType": "application/epub+zip"
    }
]
```

If no file types exist:

```json
[]
```

### GET /api/file-types/{id}

Get a specific file type by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the file type

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "PDF",
    "extension": ".pdf",
    "mimeType": "application/pdf"
}
```

##### Status 404 Not Found

If the file type with the specified ID is not found.

### POST /api/file-types

Create a new file type.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "name": "MOBI",
    "extension": ".mobi",
    "mimeType": "application/x-mobipocket-ebook"
}
```

#### Response

##### Status 200 OK

Returns the created file type:

```json
{
    "id": "UUID",
    "name": "MOBI",
    "extension": ".mobi",
    "mimeType": "application/x-mobipocket-ebook"
}
```

### PATCH /api/file-types/{id}

Update an existing file type.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the file type to update

##### Payload

```json
{
    "name": "Updated Name",
    "extension": ".updated"
}
```

#### Response

##### Status 200 OK

Returns the updated file type:

```json
{
    "id": "UUID",
    "name": "Updated Name",
    "extension": ".updated",
    "mimeType": "application/x-mobipocket-ebook"
}
```

### DELETE /api/file-types/{id}

Delete a file type.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the file type to delete

#### Response

##### Status 200 OK

Returns the deleted file type:

```json
{
    "id": "UUID",
    "name": "MOBI",
    "extension": ".mobi",
    "mimeType": "application/x-mobipocket-ebook"
}
```

## Authors

### GET /api/authors

Get a list of authors with optional search and pagination.

#### Request

##### Query Parameters

- `query` (string, optional) - Search term to filter authors by fullname
- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "fullname": "John Smith",
        "biography": "An accomplished author with multiple publications..."
    },
    {
        "id": "UUID",
        "fullname": "Jane Doe",
        "biography": "Award-winning writer specializing in..."
    }
]
```

If no authors exist:

```json
[]
```

### GET /api/authors/count

Get the total count of authors.

#### Response

##### Status 200 OK

Returns the total number of authors:

```json
42
```

### GET /api/authors/{id}

Get a specific author by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the author

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "fullname": "John Smith",
    "biography": "An accomplished author with multiple publications..."
}
```

##### Status 404 Not Found

If the author with the specified ID is not found.

### POST /api/authors

Create a new author.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "fullname": "New Author",
    "biography": "Biography of the new author..."
}
```

#### Response

##### Status 200 OK

Returns the created author:

```json
{
    "id": "UUID",
    "fullname": "New Author",
    "biography": "Biography of the new author..."
}
```

##### Status 403 Forbidden

If the user does not have permission to create authors.

### PATCH /api/authors/{id}

Update an existing author.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the author to update

##### Payload

```json
{
    "fullname": "Updated Name",
    "biography": "Updated biography..."
}
```

#### Response

##### Status 200 OK

Returns the updated author:

```json
{
    "id": "UUID",
    "fullname": "Updated Name",
    "biography": "Updated biography..."
}
```

##### Status 403 Forbidden

If the user does not have permission to update authors.

### DELETE /api/authors/{id}

Delete an author.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the author to delete

#### Response

##### Status 200 OK

No content returned on successful deletion.

##### Status 403 Forbidden

If the user does not have permission to delete authors.

## Categories

### GET /api/categories

Get a list of categories with optional pagination.

#### Request

##### Query Parameters

- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "name": "Fiction"
    },
    {
        "id": "UUID",
        "name": "Science"
    }
]
```

### GET /api/categories/count

Get the total count of categories.

#### Response

##### Status 200 OK

```json
15
```

### GET /api/categories/{id}

Get a specific category by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the category

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Fiction"
}
```

### GET /api/categories/name/{name}

Get a specific category by name.

#### Request

##### Path Parameters

- `name` (string) - The name of the category

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Fiction"
}
```

### POST /api/categories

Create a new category.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "name": "New Category"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "New Category"
}
```

##### Status 400 Bad Request

If the category name is already taken:

```json
{
    "message": "Category name not available."
}
```

### PATCH /api/categories/{id}

Update an existing category.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the category to update

##### Payload

```json
{
    "name": "Updated Category Name"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Updated Category Name"
}
```

### DELETE /api/categories/{id}

Delete a category.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the category to delete

#### Response

##### Status 200 OK

No content returned on successful deletion.

## Topics

### GET /api/topics

Get a list of topics with optional search and pagination.

#### Request

##### Query Parameters

- `query` (string, optional) - Search term to filter topics by name
- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "name": "Artificial Intelligence"
    },
    {
        "id": "UUID",
        "name": "Machine Learning"
    }
]
```

### GET /api/topics/count

Get the total count of topics.

#### Response

##### Status 200 OK

```json
28
```

### GET /api/topics/{id}

Get a specific topic by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the topic

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Artificial Intelligence"
}
```

### GET /api/topics/name/{name}

Get a specific topic by name.

#### Request

##### Path Parameters

- `name` (string) - The name of the topic

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Artificial Intelligence"
}
```

### POST /api/topics

Create a new topic.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "name": "Quantum Computing"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Quantum Computing"
}
```

### PATCH /api/topics/{id}

Update an existing topic.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the topic to update

##### Payload

```json
{
    "name": "Updated Topic Name"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Updated Topic Name"
}
```

### DELETE /api/topics/{id}

Delete a topic.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the topic to delete

#### Response

##### Status 200 OK

Returns the deleted topic:

```json
{
    "id": "UUID",
    "name": "Quantum Computing"
}
```

## Languages

### GET /api/languages

Get a list of languages with optional pagination.

#### Request

##### Query Parameters

- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "name": "English"
    },
    {
        "id": "UUID",
        "name": "Spanish"
    }
]
```

### GET /api/languages/count

Get the total count of languages.

#### Response

##### Status 200 OK

```json
12
```

### GET /api/languages/{id}

Get a specific language by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the language

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "English"
}
```

### GET /api/languages/name/{name}

Get a specific language by name.

#### Request

##### Path Parameters

- `name` (string) - The name of the language

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "English"
}
```

### POST /api/languages

Create a new language.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "name": "French"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "French"
}
```

### PATCH /api/languages/{id}

Update an existing language.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the language to update

##### Payload

```json
{
    "name": "Updated Language Name"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Updated Language Name"
}
```

### DELETE /api/languages/{id}

Delete a language.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the language to delete

#### Response

##### Status 200 OK

Returns the deleted language:

```json
{
    "id": "UUID",
    "name": "French"
}
```

## Organizations

### GET /api/organizations

Get a list of organizations with optional pagination.

#### Request

##### Query Parameters

- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "name": "IEEE",
        "description": "Institute of Electrical and Electronics Engineers"
    },
    {
        "id": "UUID",
        "name": "ACM",
        "description": "Association for Computing Machinery"
    }
]
```

### GET /api/organizations/count

Get the total count of organizations.

#### Response

##### Status 200 OK

```json
8
```

### GET /api/organizations/{id}

Get a specific organization by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the organization

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "IEEE",
    "description": "Institute of Electrical and Electronics Engineers"
}
```

### POST /api/organizations

Create a new organization.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "name": "New Organization",
    "description": "Description of the organization"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "New Organization",
    "description": "Description of the organization"
}
```

### PATCH /api/organizations/{id}

Update an existing organization.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the organization to update

##### Payload

```json
{
    "name": "Updated Organization",
    "description": "Updated description"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Updated Organization",
    "description": "Updated description"
}
```

### DELETE /api/organizations/{id}

Delete an organization.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the organization to delete

#### Response

##### Status 200 OK

Returns the deleted organization:

```json
{
    "id": "UUID",
    "name": "IEEE",
    "description": "Institute of Electrical and Electronics Engineers"
}
```

## Publishers

### GET /api/publishers

Get a list of publishers with optional pagination.

#### Request

##### Query Parameters

- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "name": "O'Reilly Media"
    },
    {
        "id": "UUID",
        "name": "Packt Publishing"
    }
]
```

### GET /api/publishers/count

Get the total count of publishers.

#### Response

##### Status 200 OK

```json
25
```

### GET /api/publishers/{id}

Get a specific publisher by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the publisher

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "O'Reilly Media"
}
```

### GET /api/publishers/name/{name}

Get a specific publisher by name.

#### Request

##### Path Parameters

- `name` (string) - The name of the publisher

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "O'Reilly Media"
}
```

### POST /api/publishers

Create a new publisher.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "name": "New Publisher"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "New Publisher"
}
```

### PATCH /api/publishers/{id}

Update an existing publisher.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the publisher to update

##### Payload

```json
{
    "name": "Updated Publisher Name"
}
```

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Updated Publisher Name"
}
```

### DELETE /api/publishers/{id}

Delete a publisher.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the publisher to delete

#### Response

##### Status 200 OK

Returns the deleted publisher:

```json
{
    "id": "UUID",
    "name": "O'Reilly Media"
}
```

## Books

### GET /api/books

Get a list of books with optional search and pagination.

#### Request

##### Query Parameters

- `query` (string, optional) - Search term to filter books by title
- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "title": "Clean Code",
        "subtitle": "A Handbook of Agile Software Craftsmanship",
        "category": {
            "id": "UUID",
            "name": "Programming"
        },
        "publisher": {
            "id": "UUID",
            "name": "Prentice Hall"
        },
        "type": "TECHNICAL",
        "description": "Even bad code can function...",
        "authors": [
            {
                "id": "UUID",
                "fullname": "Robert C. Martin"
            }
        ],
        "topics": [],
        "language": {
            "id": "UUID",
            "name": "English"
        }
    }
]
```

### GET /api/books/count

Get the total count of books.

#### Response

##### Status 200 OK

```json
156
```

### GET /api/books/{id}

Get a specific book by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the book

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "title": "Clean Code",
    "subtitle": "A Handbook of Agile Software Craftsmanship",
    "category": {
        "id": "UUID",
        "name": "Programming"
    },
    "publisher": {
        "id": "UUID",
        "name": "Prentice Hall"
    },
    "type": "TECHNICAL",
    "description": "Even bad code can function...",
    "authors": [
        {
            "id": "UUID",
            "fullname": "Robert C. Martin"
        }
    ],
    "topics": [],
    "language": {
        "id": "UUID",
        "name": "English"
    }
}
```

### GET /api/books/{id}/files

Get all files associated with a book.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the book

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "version": "1.0",
        "description": "First edition",
        "publishingYear": 2008,
        "file": {
            "id": "UUID",
            "filename": "clean-code.pdf",
            "fileType": {
                "id": "UUID",
                "name": "PDF",
                "extension": ".pdf"
            }
        }
    }
]
```

### GET /api/books/{id}/files/{documentFileId}/download

Download a specific file of a book.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the book
- `documentFileId` (UUID) - The unique identifier of the document file

#### Response

##### Status 200 OK

Returns the file as a binary stream with appropriate headers:

```
Content-Type: application/octet-stream
Content-Disposition: attachment; filename=clean-code.pdf
```

### POST /api/books

Create a new book.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "title": "New Book Title",
    "subtitle": "Optional subtitle",
    "category": {
        "id": "UUID"
    },
    "publisher": {
        "id": "UUID"
    },
    "type": "TECHNICAL",
    "description": "Book description",
    "authors": [
        {
            "id": "UUID"
        }
    ],
    "topics": [
        {
            "id": "UUID"
        }
    ],
    "language": {
        "id": "UUID"
    }
}
```

#### Response

##### Status 200 OK

Returns the created book with all details.

### POST /api/books/{id}/files/upload

Upload a file for a book.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
Content-Type: multipart/form-data
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the book

##### Form Parameters

- `binary` (file) - The file to upload
- `version` (string) - Version of the document
- `description` (string) - Description of this file version
- `publishingYear` (integer) - Year of publication

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "version": "1.0",
    "description": "First edition",
    "publishingYear": 2008,
    "file": {
        "id": "UUID",
        "filename": "clean-code.pdf",
        "fileType": {
            "id": "UUID",
            "name": "PDF",
            "extension": ".pdf"
        }
    }
}
```

### PATCH /api/books/{id}

Update an existing book.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the book to update

##### Payload

```json
{
    "title": "Updated Title",
    "description": "Updated description"
}
```

#### Response

##### Status 200 OK

Returns the updated book with all details.

### DELETE /api/books/{id}

Delete a book.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the book to delete

#### Response

##### Status 200 OK

```json
true
```

### DELETE /api/books/{id}/files/{documentFileId}

Delete a specific file of a book.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the book
- `documentFileId` (UUID) - The unique identifier of the document file to delete

#### Response

##### Status 200 OK

```json
true
```

## Whitepapers

### GET /api/whitepapers

Get a list of whitepapers with optional search and pagination.

#### Request

##### Query Parameters

- `query` (string, optional) - Search term to filter whitepapers by title
- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "title": "Blockchain Technology Overview",
        "organization": {
            "id": "UUID",
            "name": "IEEE"
        },
        "description": "Comprehensive overview of blockchain technology...",
        "authors": [
            {
                "id": "UUID",
                "fullname": "John Smith"
            }
        ],
        "topics": [],
        "language": {
            "id": "UUID",
            "name": "English"
        }
    }
]
```

### GET /api/whitepapers/count

Get the total count of whitepapers.

#### Response

##### Status 200 OK

```json
42
```

### GET /api/whitepapers/{id}

Get a specific whitepaper by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the whitepaper

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "title": "Blockchain Technology Overview",
    "organization": {
        "id": "UUID",
        "name": "IEEE"
    },
    "description": "Comprehensive overview of blockchain technology...",
    "authors": [
        {
            "id": "UUID",
            "fullname": "John Smith"
        }
    ],
    "topics": [],
    "language": {
        "id": "UUID",
        "name": "English"
    }
}
```

### GET /api/whitepapers/{id}/files

Get all files associated with a whitepaper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the whitepaper

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "version": "1.0",
        "description": "Original version",
        "publishingYear": 2023,
        "file": {
            "id": "UUID",
            "filename": "blockchain-overview.pdf",
            "fileType": {
                "id": "UUID",
                "name": "PDF",
                "extension": ".pdf"
            }
        }
    }
]
```

### GET /api/whitepapers/{id}/files/{documentFileId}/download

Download a specific file of a whitepaper.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the whitepaper
- `documentFileId` (UUID) - The unique identifier of the document file

#### Response

##### Status 200 OK

Returns the file as a binary stream with appropriate headers:

```
Content-Type: application/octet-stream
Content-Disposition: attachment; filename=blockchain-overview.pdf
```

### POST /api/whitepapers

Create a new whitepaper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "title": "New Whitepaper Title",
    "organization": {
        "id": "UUID"
    },
    "description": "Whitepaper description",
    "authors": [
        {
            "id": "UUID"
        }
    ],
    "topics": [
        {
            "id": "UUID"
        }
    ],
    "language": {
        "id": "UUID"
    }
}
```

#### Response

##### Status 200 OK

Returns the created whitepaper with all details.

### POST /api/whitepapers/{id}/files/upload

Upload a file for a whitepaper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
Content-Type: multipart/form-data
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the whitepaper

##### Form Parameters

- `binary` (file) - The file to upload
- `version` (string) - Version of the document
- `description` (string) - Description of this file version
- `publishingYear` (integer) - Year of publication

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "version": "1.0",
    "description": "Original version",
    "publishingYear": 2023,
    "file": {
        "id": "UUID",
        "filename": "blockchain-overview.pdf",
        "fileType": {
            "id": "UUID",
            "name": "PDF",
            "extension": ".pdf"
        }
    }
}
```

### PATCH /api/whitepapers/{id}

Update an existing whitepaper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the whitepaper to update

##### Payload

```json
{
    "title": "Updated Title",
    "description": "Updated description"
}
```

#### Response

##### Status 200 OK

Returns the updated whitepaper with all details.

### DELETE /api/whitepapers/{id}

Delete a whitepaper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the whitepaper to delete

#### Response

##### Status 200 OK

```json
true
```

### DELETE /api/whitepapers/{id}/files/{documentFileId}

Delete a specific file of a whitepaper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the whitepaper
- `documentFileId` (UUID) - The unique identifier of the document file to delete

#### Response

##### Status 200 OK

```json
true
```

## Research Papers

### GET /api/research-papers

Get a list of research papers with optional search and pagination.

#### Request

##### Query Parameters

- `query` (string, optional) - Search term to filter research papers by title
- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "title": "Machine Learning in Healthcare",
        "organization": {
            "id": "UUID",
            "name": "ACM"
        },
        "description": "Analysis of machine learning applications in healthcare...",
        "authors": [
            {
                "id": "UUID",
                "fullname": "Dr. Jane Doe"
            }
        ],
        "topics": [],
        "language": {
            "id": "UUID",
            "name": "English"
        }
    }
]
```

### GET /api/research-papers/count

Get the total count of research papers.

#### Response

##### Status 200 OK

```json
87
```

### GET /api/research-papers/{id}

Get a specific research paper by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the research paper

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "title": "Machine Learning in Healthcare",
    "organization": {
        "id": "UUID",
        "name": "ACM"
    },
    "description": "Analysis of machine learning applications in healthcare...",
    "authors": [
        {
            "id": "UUID",
            "fullname": "Dr. Jane Doe"
        }
    ],
    "topics": [],
    "language": {
        "id": "UUID",
        "name": "English"
    }
}
```

### GET /api/research-papers/{id}/files

Get all files associated with a research paper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the research paper

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "version": "1.0",
        "description": "Final version",
        "publishingYear": 2024,
        "file": {
            "id": "UUID",
            "filename": "ml-healthcare.pdf",
            "fileType": {
                "id": "UUID",
                "name": "PDF",
                "extension": ".pdf"
            }
        }
    }
]
```

### GET /api/research-papers/{id}/files/{documentFileId}/download

Download a specific file of a research paper.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the research paper
- `documentFileId` (UUID) - The unique identifier of the document file

#### Response

##### Status 200 OK

Returns the file as a binary stream with appropriate headers:

```
Content-Type: application/octet-stream
Content-Disposition: attachment; filename=ml-healthcare.pdf
```

### POST /api/research-papers

Create a new research paper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "title": "New Research Paper Title",
    "organization": {
        "id": "UUID"
    },
    "description": "Research paper description",
    "authors": [
        {
            "id": "UUID"
        }
    ],
    "topics": [
        {
            "id": "UUID"
        }
    ],
    "language": {
        "id": "UUID"
    }
}
```

#### Response

##### Status 200 OK

Returns the created research paper with all details.

### POST /api/research-papers/{id}/files/upload

Upload a file for a research paper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
Content-Type: multipart/form-data
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the research paper

##### Form Parameters

- `binary` (file) - The file to upload
- `version` (string) - Version of the document
- `description` (string) - Description of this file version
- `publishingYear` (integer) - Year of publication

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "version": "1.0",
    "description": "Final version",
    "publishingYear": 2024,
    "file": {
        "id": "UUID",
        "filename": "ml-healthcare.pdf",
        "fileType": {
            "id": "UUID",
            "name": "PDF",
            "extension": ".pdf"
        }
    }
}
```

### PATCH /api/research-papers/{id}

Update an existing research paper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the research paper to update

##### Payload

```json
{
    "title": "Updated Title",
    "description": "Updated description"
}
```

#### Response

##### Status 200 OK

Returns the updated research paper with all details.

### DELETE /api/research-papers/{id}

Delete a research paper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the research paper to delete

#### Response

##### Status 200 OK

```json
true
```

### DELETE /api/research-papers/{id}/files/{documentFileId}

Delete a specific file of a research paper.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the research paper
- `documentFileId` (UUID) - The unique identifier of the document file to delete

#### Response

##### Status 200 OK

```json
true
```

## Bundles

### GET /api/bundles

Get a list of bundles with optional pagination.

#### Request

##### Query Parameters

- `page` (integer, optional, default: 0) - Page number for pagination
- `size` (integer, optional, default: 0) - Number of items per page (0 returns all)

#### Response

##### Status 200 OK

```json
[
    {
        "id": "UUID",
        "name": "Software Engineering Essentials",
        "description": "A curated collection of essential software engineering books",
        "documents": [
            {
                "id": "UUID",
                "title": "Clean Code"
            },
            {
                "id": "UUID",
                "title": "Design Patterns"
            }
        ]
    }
]
```

If no bundles exist:

```json
[]
```

### GET /api/bundles/count

Get the total count of bundles.

#### Response

##### Status 200 OK

Returns the total number of bundles:

```json
12
```

### GET /api/bundles/{id}

Get a specific bundle by ID.

#### Request

##### Path Parameters

- `id` (UUID) - The unique identifier of the bundle

#### Response

##### Status 200 OK

```json
{
    "id": "UUID",
    "name": "Software Engineering Essentials",
    "description": "A curated collection of essential software engineering books",
    "documents": [
        {
            "id": "UUID",
            "title": "Clean Code"
        },
        {
            "id": "UUID",
            "title": "Design Patterns"
        }
    ]
}
```

##### Status 404 Not Found

If the bundle with the specified ID is not found.

### POST /api/bundles

Create a new bundle.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Payload

```json
{
    "name": "New Bundle",
    "description": "Bundle description",
    "documents": [
        {
            "id": "UUID"
        },
        {
            "id": "UUID"
        }
    ]
}
```

#### Response

##### Status 200 OK

Returns the created bundle:

```json
{
    "id": "UUID",
    "name": "New Bundle",
    "description": "Bundle description",
    "documents": [
        {
            "id": "UUID",
            "title": "Document Title 1"
        },
        {
            "id": "UUID",
            "title": "Document Title 2"
        }
    ]
}
```

##### Status 403 Forbidden

If the user does not have permission to create bundles.

### PATCH /api/bundles/{id}

Update an existing bundle.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the bundle to update

##### Payload

```json
{
    "name": "Updated Bundle Name",
    "description": "Updated description",
    "documents": [
        {
            "id": "UUID"
        }
    ]
}
```

#### Response

##### Status 200 OK

Returns the updated bundle:

```json
{
    "id": "UUID",
    "name": "Updated Bundle Name",
    "description": "Updated description",
    "documents": [
        {
            "id": "UUID",
            "title": "Document Title"
        }
    ]
}
```

##### Status 403 Forbidden

If the user does not have permission to update bundles.

##### Status 404 Not Found

If the bundle with the specified ID is not found.

### DELETE /api/bundles/{id}

Delete a bundle.

#### Request

##### Headers

```
Authorization: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

##### Path Parameters

- `id` (UUID) - The unique identifier of the bundle to delete

#### Response

##### Status 200 OK

No content returned on successful deletion.

##### Status 403 Forbidden

If the user does not have permission to delete bundles.

##### Status 404 Not Found

If the bundle with the specified ID is not found.
