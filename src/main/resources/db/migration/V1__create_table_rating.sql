CREATE TABLE rating(
    id bigint NOT NULL auto_increment,
    userEmail VARCHAR(100),
    rating FLOAT,
    comment VARCHAR(1000),
    PRIMARY KEY (id)
);

