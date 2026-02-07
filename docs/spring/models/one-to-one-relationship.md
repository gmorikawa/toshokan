# Model - One-to-One Relationship

## Case: Shared Primary Key

A _shared primary key_ case is when one of the sides generates a _primary key_, and the other reuses the same key as their _primary key_. Thus, the _primary key_ is also a _foreign key_.

Taking as example an implementation in this application, each _user_ has one and only one _profile_ and _configuration_. Thus, creating a one-to-one relationship between them. The _users_ table is the main one, while _user\_profiles_ and _user\_configurations_ are secondary tables that reuse the same _primary key_ value as _users_.

To model this case, first, the user should have a reference to the other entities. The decorators `@OneToOne` to create the relationship between entities, and the `@PrimaryKeyJoinColumn` to indicate that the referenced entity uses their `User` _foreign key_ as _primary key_.

UserModel.java
```java
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserProfile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserConfiguration configuration;

    ...
}
```

On `UserProfile` and `UserConfiguration` the id mapping should exist normally, without a `@GeneratedValue` annotation, since it is coming from `User`. But a `@Column` is required to indicate the field that is the _primary key_, since in my case I have not used `id`, but, instead, `user_id`.

Then, is necessary to define a property representing the `User` entity decorated with `@OneToOne`, that represents a one to one relationship, and `@MapsId`, that indicates that the primary key should come from `User`.

UserProfileModel.java
```java
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserProfileModel {

    @Id
    @Column(name = "user_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    ...
}
```

UserConfigurationModel.java
```java
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserConfigurationModel {

    @Id
    @Column(name = "user_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    ...
}
```

## References

* [One-to-One Relationship in JPA | Baeldung](https://www.baeldung.com/jpa-one-to-one), accessed on 2026-02-07;
