<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Casbin Idea Plugin Changelog

## [Unreleased]
### Added
- Support for 2021.1 IDEs 

## [0.1.4]
### Added

### Changed

### Deprecated

### Removed
- Unused PsiChangeListeners
### Fixed
- Sub object attribute accessors in matching function
### Security


## [0.1.3]
### Added
- Use JDK 11
- Support for 2020.3 IDEs
### Fixed
- Intellij Warning using Default Project
- Failed Test Cases

## [0.1.2]
### Added
- Use Jetbrains changelog to patch xml
- Add Github actions to build and deploy
- Add error message for bad requests
- Split pane between options and editor in tool window

### Fixed
- Exclude org.slf4j dependency from jcasbin
- Remove old casbin enforcers from listening to requests

## [0.1.1]
### Added
- Change pluginUntilBuild to 201

## [0.1.0]
### Added
- Support for parsing Casbin Files
- Support for parsing Casbin CSV Policy Definitions
- Syntax highlighting for Casbin Files
- Support to evaluate policies given a model and policy definition
- Tool Window for interactive evaluation of policies
