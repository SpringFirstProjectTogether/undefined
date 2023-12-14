DELETE
FROM users ;
ALTER TABLE users
    AUTO_INCREMENT = 1;

INSERT INTO users (user_login_id, user_password, nickname)
VALUES ('id1', 'password_1', 'User1'),
       ('id2', 'password_2', 'User2'),
       ('id3', 'password_3', 'User3'),
       ('id4', 'password_4', 'User4');


DELETE FROM feeds ;
ALTER TABLE feeds
    AUTO_INCREMENT = 1;
INSERT INTO feeds (user_id, feed_title, feed_content, feed_state)
VALUES (1, 'title1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'temp'),
       (1, 'title3', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (2, 'title4', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (3, 'title5', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'del'),
       (1, 'title1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'temp'),
       (1, 'title3', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (2, 'title4', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (3, 'title5', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'del'),
       (1, 'title1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'temp'),
       (1, 'title3', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (2, 'title4', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (3, 'title5', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'del'),
       (1, 'title1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'temp'),
       (1, 'title3', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (2, 'title4', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (3, 'title5', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'del'),
       (1, 'title1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'temp'),
       (1, 'title3', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (2, 'title4', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (3, 'title5', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'del'),
       (1, 'title1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'temp'),
       (1, 'title3', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (2, 'title4', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (3, 'title5', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'del'),
       (1, 'title1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'temp'),
       (1, 'title3', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (2, 'title4', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title5', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'del'),
       (1, 'title1', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title2', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'temp'),
       (1, 'title3', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (2, 'title4', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'comp'),
       (1, 'title5', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi beatae corporis dignissimos dolores dolorum id incidunt ipsa ipsam iure maiores, minima, nobis perferendis perspiciatis provident sint veniam vitae! Eum, voluptatibus.', 'del');



DELETE FROM feed_tags;
ALTER TABLE feed_tags
    AUTO_INCREMENT = 1;
INSERT INTO feed_tags (tag_name)
VALUES ('spring'), ('java'), ('security');


DELETE FROM feed_tag_table;
INSERT INTO feed_tag_table (tag_id, feed_id)
VALUES (1, 1), (2, 1), (1, 3), (3, 3), (1, 4), (2, 4), (3, 4);


DELETE FROM feed_comments;
ALTER TABLE feed_comments
    AUTO_INCREMENT = 1;
INSERT INTO feed_comments (feed_id, user_id, parent_id, content)
VALUES (1, 1, null, 'comment1'),
       (1, 2, 1, 'comment1-1'),
       (1, 3, 1, 'comment1-2'),
       (1, 2, null, 'comment2'),
       (3, 1, null, 'comment1'),
       (3, 2, null, 'comment2'),
       (3, 3, null, 'comment3'),
       (3, 1, 7, 'comment3-1');


DELETE FROM feed_likes;
INSERT INTO feed_likes (user_id, feed_id)
VALUES (1, 1),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 3),
       (4, 1);


SELECT * FROM feed_comments;
SELECT * FROM feed_comments WHERE feed_id = 3 AND parent_id IS null;


SELECT * FROM feed_photos;