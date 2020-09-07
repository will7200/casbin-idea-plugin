Casbin support for IntelliJ-based IDEs
========================================
[![Version](https://img.shields.io/jetbrains/plugin/v/14809.svg)](https://plugins.jetbrains.com/plugin/14809)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/14809.svg)](https://plugins.jetbrains.com/plugin/14809)

Get it from plugin repository: https://plugins.jetbrains.com/plugin/14809-casbin

Plugin for editing and testing Casbin models in IntelliJ-based IDEs.

Provides:
 * syntax highlighting
 * auto-completion for policy effect definitions and object attributes
 * quick fixes to add missing attributes to models
 * test casbin model using the casbin executor tool window
    * model and policy definitions are watched for changes to automatically update the Casbin Model and execution results

Editor
![Editor](assets/screenshots/editor_with_structure.PNG?raw=true)

Casbin Executor
![Casbin Executor](assets/screenshots/casbin_executor.PNG?raw=true)

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "CASBIN"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/will7200/casbin/releases/latest) and install it manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>



### Development

The plugin is built using Gradle and uses [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin)
to integrate with IntelliJ Platform.
 
To build a plugin run

```
$ ./gradlew buildPlugin
```

Plugin zip file will be created in `build/distributions`

To test plugin in IDE run `./gradlew runIde`

#### Grammar modifications

The plugin uses [Grammar-Kit](https://github.com/jetbrains/grammar-kit) to generate parser and lexer. Please install [Grammar-Kit plugin](https://plugins.jetbrains.com/plugin/6606-grammar-kit) and refer to the documentation if you want to modify grammar.

Casbin Model Parser & Lexer  
- To regenerate the parser for casbin models, open [Casbin.bnf](grammars/Casbin.bnf) and press <kbd>Ctrl+Shift+G</kbd>  
- To regenerate the lexer, open [CasbinLexer.flex](grammars/CasbinLexer.flex) and press <kbd>Ctrl+Shift+G</kbd>

Casbin CSV Parser & Lexer  
- To regenerate the parser, open [CasbinCSV.bnf](grammars/CasbinCSV.bnf) and press <kbd>Ctrl+Shift+G</kbd>  
- To regenerate the lexer, open [CasbinCSVLexer.flex](grammars/CasbinCSVLexer.flex) and press <kbd>Ctrl+Shift+G</kbd>

 
### Contribution

Plugin is written in [Kotlin](http://kotlinlang.org/).

### Third Party Libraries

Look at [ThirdPartyNotices.txt](ThirdPartyNotices.txt) for more information.