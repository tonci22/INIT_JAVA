# INIT_JAVA

technical task for junior developer 


JAVA zadatak

Prerequisites:
    - Java
    - Spring Boot
    - DB (PostgreSQL/MySQL)
  
Create application where admin user will manage movie categories, regular users can add
new movies and bind it to existing categories and unauthenticated users can get movies.

DB:
    User:
        - id
        - name
        - role_id
    Role:
        - id
        - name
    Movie:
        - id
        - name
    Category:
        - id
        - name
    Movie_Category:
        - id
        - movie_id
        - category_id
        
Steps:
    - Connect DB to application
    - Create tables: User, Role, Movie, Category
    - Populate database with 1 admin user and 1 regular user, with 1 category on starting application.

    - Create controllers:
        Movie:
            - GET/POST/PUT/DELETE
        Category:
            - GET/POST/PUT
        Admin:
            - can access all endpoints
        Regular user:
            - can GET/POST/PUT movies and GET categories
        Unauthenticated user:
            - can GET movies
      - add search filters (movie name) and pagination
    
Optional: add unit/integration tests
