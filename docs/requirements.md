# Requirements

## Functional Requirements

### Autorization and Authentication

1. There are three roles for users in the system: the _admin_, the _librarian_, and the _reader_.
2. The _admin_ has full permissions while the _reader_ can only access content without doing modifications. The _librarian_ can do some modifications in data.
3. All users must have:
    1. A __unique username__.
    2. A __secret password__.
    3. A __unique e-mail address__ for notification ans password recovery.
4. Only authenticated users may access system resources. Unauthenticated access is not permitted.
5. Authentication is based on an access token issued upon successful login.
6. The token have a configurable lifetime in which the _admin_ user can modify it. The default value is 7 days to expire.
7. If a token is expired the user will issue a new valid access token with a refresh token.
8. If a user is blocked, any active access or refresh tokens must be revoked immediately.

### User Registration and Account Management

1. The _admin_ user:
    1. Is the first account in the system.
    2. Must always exist.
    3. Cannot be deleted.
    4. Must be unique in the system.
2. Only _admin_ can create new user accounts. Users cannot self-register.
3. When creating a user, the _admin_ must assign a temporary password. Upon first login, the user is required to set a new permanent password.
4. _Admin_ can block or reactivate user accounts.
5. Blocking a user prevents system access but does not remove their data.

### Role Permissions

1. __Admin__:
    1. Full access to all system features, configuration, and data.
    2. Cannot be deleted.
2. __Librarian__:
    1. Can create and modify documents, upload files, and manage bundles.
    2. Cannot modify administrative configuration settings.
3. __Reader__:
    1. Can view all public documents and public bundles.
    2. Can only modify their own user-related data (profile, notes, personal lists).
4. Role hierarchy is cumulative:
    _admin_ > _librarian_ > _reader_ (each role inherits the permissions of the lower role).

### File Storage

1. The storage is a separated service which provides a interface for the available actions.
2. The maximum upload size by default is 50mb per file. It is configurable by _admin_.
3. The client must provide a hash (e.g., SHA-256) of the uploaded file so the server can verify integrity.
4. _Admin_ and _librarians_ may upload and download files; _readers_ can only download them.
5. The storage system does not enforce directory structures; files are identified by generated keys.

### Documents Management

1. A document represents any written content: books, whitepapers, manuals, etc.
2. Documents can be created and edited by _admin_ and _librarians_.
3. A document record may exist with or without associated files.
4. Many files can be uploaded for each _document_, representing different filetypes or versions.
5. For each document file uploaded, a __version__, and a __description__, and __publishing year__ can be registered together. The version is the only required field.
6. The version identifier is user-defined and not required to be unique.
7. Each document may have none or many topics that represents more specific subjects covered in the document.
8. Each document may have one or many _authors_ related to it;

### Books Registration

1. If different versions exists, for example, in another language, it is considered a different book;
2. The same applies if the book is reprinted in another publisher, it will be considered a different book;
3. Books are classified as:
    1. __Fiction__.
    2. __Non-Fiction__.
4. Each book may have one _publisher_ related to it;
5. Each book should have a __category__ related to it. In fiction books, subjects may be interpreted as __genre__;

### Whitepapers Registration

1. Each whitepaper may have one _organization_ related to it;
2. Organizations are classified in:
    1. Company;
    2. University;

### Research Papers Registration

1. Reasearch papers contains _keywords_. They differ from document's topis because it can contain very specific terms to the paper;
2. Each research paper may have one _organization_ related to it;

### Document Bundles

1. _Admin_ and _librarians_ can create document bundles giving a __title__ and a __description__ to it.
2. There are no limits of how many documents a bundle can have.
3. Duplicates of documents are not allowed in one bundle, but the same document can be present in more than one bundle.
4. Collection's title are unique in the system.
5. Bundles are visible to all users.

### Shelves and Memo

1. Shelves and Memo are private to each user;
2. All users can have their own favorites and document memos;
3. Documents can be in multiple shelves, but must be unique in a shelf;
4. The user can write multiple memos for a document;
5. No user can access another user's shelves and memos;