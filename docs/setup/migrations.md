# Migrations

This projects uses [_Flyway_](https://documentation.red-gate.com/flyway) to manage migrations.

All the migration files are locates in `/src/main/resources/db/migration`. For each DBMS vendor there is a separated folder with the migrations scripts.

## Migration file naming convention

### Created At: October 26, 2025

_Flyway_ recognizes which files should be considered, and in which order by the name of files. The name should follow a pattern: __V\<version\>\_\_\<migration_name\>.sql__. The version can be a single integer, or it can follow the pattern _major and minor_ releases, for example, 2.0.

So, for example, the first migration could be named: __V1\_\_initial_migration.sql__ or __V1.0\_\_initial_migration.sql__.

### References

* [Flyway: Naming Patterns Matter | Redgate](https://www.red-gate.com/blog/database-devops/flyway-naming-patterns-matter);

## Running migrations

### Created At: October 26, 2025

_Flyway_ can be executed prior or before the to start of the application. In this project I had decided to run migrations before.

To execute a migration run the command:

```sh
./mvnw flyway:migrate
```

_Flyway_ will create a table `flyway_scheme_history` to manage which migrations were executed.

## Rollback migrations

### Created At: October 26, 2025

Rolling back migrations are not available in free usage of _Flyway_.

To run rollback, scripts that executes the action of undoing migration actions should exists in the same folder. The name convention for rolling back migrations follows a similar pattern of the migration: __U\<version\>\_\_\<migration_name\>.sql__. The requirements to run rollback are: the migration file should have a undo migration file, this is achieved by naming same version number for both. Example: for __V1\_\_initial_migration.sql__ should have a __U1\_\_reset_initial_migration.sql__. See that only the version number must be equal. The name _migration name_ can have another label.

After writing the necessary files run the command:

```sh
./mvnw flyway:undo
```

## Reset migrations

### Created At: October 26, 2025

To clean all migrations run the following command:

```sh
./mvnw flyway:clean
```

This command will drop all tables, views, triggers, and other objects in the specified schemas.
