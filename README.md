# Java Spring JDBC
The target of this exercise is to practice Spring JDBC with Java 17.

## Features
- Store and retrieve file from Database through Stored procedures.
- Store and retrieve files up to 200MB of size.

## Requirements
- Install Docker
- Download Postgres Docker image
- Install psql client

## Run the project
1. Run postgres through Docker with following command: `docker run --rm --name lil-postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres`
2. Run psql command to create DB: `psql -h localhost -U postgres -f database.sql` and enter the password: `password`
3. Run psql command to create stored procedures: `psql -h localhost -U postgres -d tu_spring_db -f stored_procedures.sql` and enter the password: `password`
4. Sample files are in `/files` folder. Due to size restrictions, heavy file is not uploaded to the repository. So, to run this project locally, please move a heavy file inside `/files` folder with its proper extension.
5. Run Main program.
6. Run command to stop Docker execution: `docker stop lil-postgres`
