# Entities

## Classes

### User

* _id_: __primary key, UUID, not null__;
* _username_: __unique, not null, varchar(63)__;
* _password_: __crypt, not null, varchar(255)__;
* _email_: __unique, not null, varchar(127)__;
* _role_: __UserRole, not null__;
* _status_: __UserStatus, not null__;
* _fullname_: __varchar(127)__;

### User Configuration

* _user_: __User, foreign key, UUID, not null__
* _firstAccess_: __not null, boolean__

### FileType

* _id_: __primary key, UUID, not null__;
* _name_: __not null, varchar(127)__;
* _extension_: __unique, not null, varchar(15)__;
* _mime\_type_: __not null, varchar(127)__;

### File

* _id_: __primary key, UUID, not null__;
* _path_: __not null, varchar(1023)__;
* _filename_: __not null, varchar(255)__;
* _type_: __FileType, not null__;
* _state_: __FileState, not null__;

### Language

* _id_: __primary key, UUID, not null__;
* _name_: __varchar(127), not null, unique__;

### Document

* _id_: __primary key, UUID, not null__;
* _title_: __not null, varchar(127)__;
* _language_: __Language, foreign key, UUID, not null__;
* _summary_: __varchar(4095)__;

### Topic

* _id_: __primary key, UUID, not null__;
* _name_: __not null, varchar(127)__;

### DocumentTopic

* _document_: __Document, foreign key, UUID, not null__;
* _topic_: __Topic, foreign key, UUID, not null__;

### Author

* _id_: __primary key, UUID, not null__;
* _fullname_: __varchar(127), not null__;
* _biography_: __varchar(4095)__;

### DocumentAuthor

* _document_: __Document, foreign key, UUID, not null__;
* _author_: __Author, foreign key, UUID, not null__;

### DocumentFile

* _document_: __Document, foreign key, UUID, not null__;
* _file_: __File, foreign key, UUID, not null__;
* _version_: __not null, varchar(127)__;
* _description_: __varchar(2047)__;
* _publishing\_year_: __integer__;

### Collection

* _id_: __primary key, UUID, not null__;
* _title_: __not null, varchar(127), unique__;
* _description_: __varchar(4095)__;

### CollectionDocument

* _collection_: __Collection, foreign key, UUID, not null__;
* _document_: __Document, foreign key, UUID, not null__;

### Category

* _id_: __primary key, UUID, not null__;
* _name_: __not null, unique, varchar(127)__;

### Publisher

* _id_: __primary key, UUID, not null__;
* _name_: __varchar(63)__;
* _description_: __varchar(4095)__;

### Book

* _document_: __Document, foreign key, UUID, not null__;
* _category_: __Category, foreign key, UUID, not null__;
* _publisher_: __Publisher, foreign key, UUID__;
* _type_: __BookType, not null__;

### Organization

* _id_: __primary key, UUID, not null__;
* _name_: __varchar(127), not null__;
* _description_: __varchar(4095)__;
* _type_: __OrganizationType, not null__;

### Whitepaper

* _document_: __Document, foreign key, UUID, not null__;
* _organization_: __Organization, foreign key, UUID__;

### ResearchPaper

* _document_: __Document, foreign key, UUID, not null__;
* _organization_: __Organization, foreign key, UUID__;
* _keywords_: __varchar(4095)__;

## Enums

### UserStatus

* _ACTIVE_
* _BLOCKED_

### UserRole

* _ADMIN_
* _LIBRARIAN_
* _READER_

### FileState

* _UPLOADING_
* _AVAILABLE_
* _CORRUPTED_

### BookType

* _FICTION_
* _NON\_FICTION_

### OrganizationType

* _UNIVERSITY_
* _COMPANY_