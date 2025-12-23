# Source Version Control

[GIT](https://git-scm.com/) is being used in this project to manage versioning.

## Writing commit messages

The structure of commit should be structured in the following pattern:

```git-commit
<type>: <description>
```

As suggested in the article "_How to Write Better Git Commit Messages_", the _types_ can be the following:

* __feat__: a new feature is introduced with the changes;
* __fix__: a bug fix has occurred;
* __chore__: changes that do not relate to a fix or feature and don't modify src or test files (for example updating dependencies);
* __refactor__: refactored code that neither fixes a bug nor adds a feature;
* __docs__: updates to documentation such as a the README or other markdown files;
* __style__: changes that do not affect the meaning of the code, likely related to code formatting such as white-space, missing semi-colons, and so on.;
* __test__: including new or correcting previous tests;
* __perf__: performance improvements;
* __ci__: continuous integration related;
* __build__: changes that affect the build system or external dependencies;
* __revert__: reverts a previous commit;

### References

* [How to Write Better Git Commit Messages – A Step-By-Step Guide](https://www.freecodecamp.org/news/how-to-write-better-git-commit-messages/), accessed on 2025-12-20;
