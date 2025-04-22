# Requirements

## Autorization and Authentication

* There are three roles for users in the system: the _admin_, the _librarian_, and the _reader_;
* The _admin_ has full permissions while the _reader_ can only access content without doing modifications. The _librarian_ can do some modifications in data;
* All users need to have a unique username and a secret password to login in the system;
* All the users should have a unique e-mail address registered for the case of notifications and password recovery purposes;
* Not logged users cannot access any of the contents of the system;
* The _admin_ is the only user that can add new users to the system. Therefore, no user can create their own account without noticing a _admin_;
* The access of each user is identified by a token. A request should have the access token that the user receives after doing a successful login in the system;
* The token have a configurable lifetime in which the _admin_ user can modify it. The default value is 7 days to expire;
* If a token is expired the user will issue a new valid access token with a refresh token;

## User Management and Permissions

* By default, the _admin_ does not have any restrictions in data access or modification;
* The _admin_ is the first user to be created by the system and may be unique in the system;
* The _admin_ cannot be deleted, since it is the user with permissions of making major configurations;
* The _librarian_ has some permissions of mutating data in the system of some resources;
* The _reader_ can only modify information specfic to their own environment, like profile, notes on books, collections, etc.
* The three roles are an hierarch: a _admin_ is a _librarian_, a _librarian_ is a _reader_, and consequently, the _admin_ is also a _reader_.

## Documents Management

* A document is anything that is composed of words: books, whitepapers, handout, etc.;
* The _document_ represents the information that classifies a file, but it does not require the file to be existant;
* Many files can be uploaded for each _document_, representing different filetypes;
* As for books, specifically, a new version represents a new book, because it should have a different ISBN;
* Each document have a major category or genre and multiple tags that represents more specific topics covered in the book.

## File Storage

* The storage is a separated service which provides a interface for the available actions;
* The maximum upload size by default is 50mb per file, but it is configurable by _admin_;
* To ensure that the file is not corrupted the server must receive a hash to be verified after upload finishes;
* The _admin_ user is the only user that can manage files directly;
* The file storage is supposed to be a object storage (like S3), thus, the concept of directories do not exist.

## Data Lifecycle

* To prevent mistakes and allow recovery, it will be adopted the strategy of not directly deleting data. Thus, soft delete;
* The information that is delete can be recovered by the _admin_ before the system wipe it out;
* After deletion request, the information will remain in the system for a period configured by the _admin_ user. The default period is 7 days.