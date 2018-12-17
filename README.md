# Liquibase Plugin 'commitsql'

This is plugin for [Liquibase](https://www.liquibase.org/).

It acts only on commands generating an SQL output and not when directly used 

This plugin adds a SQL `commit` statement after each transaction generated by [Liquibase](https://www.liquibase.org/) when used to only generate SQL files. This way the generated file is suitable for a DBA review and it can also be directly executed by the DBA all in once, trying to maintain the same transaction behaviour as 

## How to use

In order for this plugin to work, it has to be in the classpath of Liquibase.

### Example with Maven


