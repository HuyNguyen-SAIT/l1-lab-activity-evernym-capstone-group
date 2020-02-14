DROP DATABASE IF EXISTS notes;

CREATE DATABASE notes;

USE notes;

CREATE TABLE user
(
    username VARCHAR(40),
    password VARCHAR(40) NOT NULL,
    firstname VARCHAR(40) NOT NULL,
    lastname VARCHAR(40) NOT NULL,
    email VARCHAR(120) NOT NULL,
    PRIMARY KEY (username),
    UNIQUE (email)
);

INSERT INTO user (username, password, firstname, lastname, email)
    VALUES ('admin', 'password', 'Admin', 'Istrator', 'admin@email.com');

INSERT INTO user (username, password, firstname, lastname, email)
    VALUES ('joeblow', 'password', 'Joe', 'Blow', 'joeblow@email.com');

INSERT INTO user (username, password, firstname, lastname, email)
    VALUES ('bobsmith', 'password', 'Bob', 'Smith', 'bobsmith@email.com');

INSERT INTO user (username, password, firstname, lastname, email)
    VALUES ('johndoe', 'password', 'John', 'Doe', 'johndoe@email.com');

INSERT INTO user (username, password, firstname, lastname, email)
    VALUES ('janedoe', 'password', 'Jane', 'Doe', 'janedoe@email.com');

COMMIT;