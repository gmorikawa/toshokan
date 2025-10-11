# Entities

## Operational Module

### User

* _id_: __primary key, UUID, not null__;
* _username_: __unique, not null, varchar(63)__;
* _password_: __crypt, not null, varchar(255)__;
* _email_: __unique, not null, varchar(127)__;
* _role_: __UserRole, not null__;
* _fullname_: __varchar(127)__;

### FileType

* _id_: __primary key, UUID, not null__;
* _name_: __not null, varchar(127)__;
* _extension_: __unique, not null, varchar(15)__;

### File

* _id_: __primary key, UUID, not null__;
* _path_: __not null, varchar(1023)__;
* _filename_: __not null, varchar(255)__;
* _type_: __FileType, not null__;
* _state_: __UserRole, not null__;

### FileState

* _UPLOADING_
* _AVAILABLE_
* _CORRUPTED_

## Library Module

### Author

* _id_: __primary key, UUID, not null__;
* _fullname_: __varchar(127), not null__;
* _biography_: __varchar(4095)__;

### Document

* _id_: __primary key, UUID, not null__;
* _title_: __not null, varchar(127)__;
* _category_: __Category, foreign key, UUID, not null__;
* _description_: __varchar(4095)__;

### DocumentTopic

* _document_: __Document, foreign key, UUID, not null__;
* _topic_: __Topic, foreign key, UUID, not null__;

### DocumentAuthor

* _document_: __Document, foreign key, UUID, not null__;
* _author_: __Author, foreign key, UUID, not null__;

### Book

* _document_: __Document, foreign key, UUID, not null__;
* _isbn_: __varchar(31), unique__;
* _publisher_: __Publisher, foreign key, UUID__;
* _published\_at_: __datetime__;
* _type_: __BookType, not null__;

### Whitepaper

* _document_: __Document, foreign key, UUID, not null__;
* _published\_at_: __datetime__;

### Category

* _id_: __primary key, UUID, not null__;
* _name_: __not null, unique, varchar(127)__;

### Topic

* _id_: __primary key, UUID, not null__;
* _name_: __not null, varchar(127)__;

### Publisher

* _id_: __primary key, UUID, not null__;
* _name_: __varchar(63)__;
* _description_: __varchar(4095)__;

### UserRole

* _ADMIN_
* _LIBRARIAN_
* _READER_

### BookType

* _FICTION_
* _NON\_FICTION_
