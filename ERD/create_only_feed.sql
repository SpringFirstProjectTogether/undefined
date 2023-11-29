SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS feed_comments;
DROP TABLE IF EXISTS feed_likes;
DROP TABLE IF EXISTS feed_photos;
DROP TABLE IF EXISTS feed_tag_table;
DROP TABLE IF EXISTS feeds;
DROP TABLE IF EXISTS feed_tags;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE feeds
(
    feed_id int NOT NULL AUTO_INCREMENT,
    feed_title varchar(200) NOT NULL,
    feed_content text NOT NULL,
    feed_state varchar(50) NOT NULL check (feed_state in ('comp', 'temp', 'del')),
    feed_regdate date now(),
    PRIMARY KEY (feed_id)
);


CREATE TABLE feed_comments
(
    comment_id int NOT NULL AUTO_INCREMENT,
    feed_id int NOT NULL,
    user_id int NOT NULL,
    parent_id int,
    content varchar(300) NOT NULL,
    regdate date now(),
    PRIMARY KEY (comment_id)
);


CREATE TABLE feed_likes
(
    user_id int NOT NULL,
    feed_id int NOT NULL,
    PRIMARY KEY (user_id, feed_id)
);


CREATE TABLE feed_photos
(
    photo_id int NOT NULL AUTO_INCREMENT,
    feed_id int NOT NULL,
    photo_url varchar(1000) NOT NULL,
    PRIMARY KEY (photo_id)
);


CREATE TABLE feed_tags
(
    tag_id int NOT NULL AUTO_INCREMENT,
    tag_name varchar(200) NOT NULL,
    PRIMARY KEY (tag_id)
);


CREATE TABLE feed_tag_table
(
    tag_id int NOT NULL,
    feed_id int NOT NULL,
    PRIMARY KEY (tag_id, feed_id)
);


CREATE TABLE users
(
    user_id int NOT NULL AUTO_INCREMENT,
    user_login_id varchar(100) NOT NULL,
    user_password varchar(100) NOT NULL,
    nickname varchar(100) NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (user_login_id)
);



/* Create Foreign Keys */

ALTER TABLE feed_comments
    ADD FOREIGN KEY (feed_id)
        REFERENCES feeds (feed_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE feed_likes
    ADD FOREIGN KEY (feed_id)
        REFERENCES feeds (feed_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE feed_photos
    ADD FOREIGN KEY (feed_id)
        REFERENCES feeds (feed_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE feed_tag_table
    ADD FOREIGN KEY (feed_id)
        REFERENCES feeds (feed_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE feed_comments
    ADD FOREIGN KEY (parent_id)
        REFERENCES feed_comments (comment_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE feed_tag_table
    ADD FOREIGN KEY (tag_id)
        REFERENCES feed_tags (tag_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE feed_comments
    ADD FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE feed_likes
    ADD FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


