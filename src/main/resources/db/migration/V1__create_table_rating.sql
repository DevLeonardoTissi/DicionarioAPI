CREATE TABLE rating(
    id bigint NOT NULL auto_increment,
    user_email VARCHAR(100),
    rating FLOAT,
    comment VARCHAR(1000),
    PRIMARY KEY (id)
);

