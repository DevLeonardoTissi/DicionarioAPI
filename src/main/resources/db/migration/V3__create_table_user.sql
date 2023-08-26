CREATE TABLE USER(
    id bigint NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password text,
    PRIMARY KEY (id)
);