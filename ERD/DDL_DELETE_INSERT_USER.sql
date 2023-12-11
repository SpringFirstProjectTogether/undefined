DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id int NOT NULL AUTO_INCREMENT,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    name varchar(80) NOT NULL,
    email varchar(80),
    regdate datetime DEFAULT now(),
    provider varchar(40),
    providerId varchar(200),
    PRIMARY KEY (id),
    UNIQUE (username)
);
DELETE FROM user;
ALTER TABLE user AUTO_INCREMENT = 1;


INSERT INTO user (username, password, name, email)
VALUES ('USER1', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', '회원1', 'user1@mail.com'),
       ('USER2', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa', '회원2', 'user2@mail.com'),
       ('ADMIN1', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '관리자1', 'admin1@mail.com')
;

SELECT * FROM user;


DROP TABLE IF EXISTS chatbot;
CREATE TABLE chatbot
(
    chatbot_id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    context text NOT NULL,
    isFromChat bit NOT NULL,
    reg_date datetime DEFAULT now(),
    PRIMARY KEY (chatbot_id)
);

DELETE FROM chatbot;
ALTER TABLE chatbot AUTO_INCREMENT = 1;

SELECT * FROM chatbot;

