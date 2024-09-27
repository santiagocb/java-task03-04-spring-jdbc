# Java Spring JDBC
The target of this exercise is to practice Spring JDBC with Java 17.

## Features
- Store and retrieve files from Database through Stored procedures.
- Store and retrieve files up to 200MB of size.
- Store and retrieve light and heavy files.
- Heavy file is provided by the user in the `/files` folder and its name is also provided in runtime.

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

## Output
![Screenshot 2024-09-26 at 10 16 25 PM](https://github.com/user-attachments/assets/8df92d5d-6f0e-4a38-bc84-a5888871fa2e)
