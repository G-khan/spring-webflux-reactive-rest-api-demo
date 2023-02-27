version: '3'

services:

  database:
    image: 'postgres:latest'

    ports:
      - 5432:5432
    environment:
      POSTGRES_USER: postgres # The PostgreSQL user (useful to connect to the database)
      POSTGRES_PASSWORD: postgres # The PostgreSQL password (useful to connect to the database)
      POSTGRES_DB: mydatabase
    volumes:
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql