branch structure

- main	: it is master branch, to keep the stable release version.
- qa	: It is a branch for integration/acceptable test.
- develop	:working branch.
- feature/001-create-prototype
- feature/002-design-map
- doc/019-create-design-doc




branch naming convention
Start a new future, we should create new branch from the develop branch, using following naming convention:

[feature type]/[issue-no]-[simplified-issue-title]

e.g. to create a branch for a document, whose issue name is :025-read map into game, then its branch name will be 
feature/025-read-map

commit and push your change to this branch
create Pull Request to merge the new branch to develop branch, make sure it is compilable.
after the new branch was reviewed, then the branch can be merged into develop branch.

