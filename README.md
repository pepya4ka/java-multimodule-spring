# Multi-Module Java Spring Project

This is a **test / practice project** created to explore and practice building a **multi-module Java
application**.

## Tech Stack

The project demonstrates usage of the following technologies:

- Java
- Spring Framework 4.x
- Gradle (multi-module setup)
- jOOQ for type-safe SQL and database access
- Liquibase for database migrations
- custom jooq generate plugin for jOOQ code generation

> ⚠ note: the jooq code generation uses a **custom plugin** (`com.soup.plugins.jooq-generate`)  
> which is **not publicly available**. to build this project successfully, you will need access  
> to this plugin or replace it with your own jOOQ code generation setup.

## Purpose

The main goal of this project is to practice:

- Designing a multi-module architecture
- Integrating jOOQ with Spring
- Managing database schema with Liquibase
- Writing and testing jOOQ-based repositories

This project is intended for learning and experimentation rather than production use.
