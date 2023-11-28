DELETE
FROM users ;
ALTER TABLE users
    AUTO_INCREMENT = 1;

INSERT INTO users (user_login_id, user_password, nickname, email, tel, address, isCompany)
VALUES ('id1', 'password_1', 'User1', 'user1@example.com', '010-456-7890', 'Address 1', 0),
       ('id2', 'password_2', 'User2', 'user2@example.com', '010-567-8901', 'Address 2', 0),
       ('id3', 'password_3', 'User3', 'user3@example.com', '010-678-9012', 'Address 3', 1),
       ('id4', 'password_4', 'User4', 'user4@example.com', '010-789-0123', 'Address 4', 1);

-- SELECT * FROM users;


DELETE
FROM skills ;
ALTER TABLE skills
    AUTO_INCREMENT = 1;

INSERT INTO skills (skill_name, skill_img_url)
values ('Java', 'http'),
       ('Spring', 'http'),
       ('Python', 'http'),
       ('C', 'http'),
       ('C++', 'http'),
       ('Flutter', 'http'),
       ('React', 'http'),
       ('Node.JS', 'http');

-- SELECT * FROM skills;

DELETE
FROM urltype ;
ALTER TABLE urltype
    AUTO_INCREMENT = 1;

INSERT INTO urltype (type_name)
VALUES ('Github'),
       ('Notion'),
       ('Tistory'),
       ('Velog'),
       ('Figma');

DELETE
FROM project_files ;
ALTER TABLE project_files
    AUTO_INCREMENT = 1;
INSERT INTO project_files (file_url)
VALUES ('http'),
       ('http'),
       ('http'),
       ('http'),
       ('http'),
       ('http'),
       ('http'),
       ('http');
SELECT * FROM project_files;




DELETE
FROM positions ;
ALTER TABLE positions
    AUTO_INCREMENT = 1;

INSERT INTO positions (position_name)
VALUES ('백엔드'),
       ('프론트엔드'),
       ('디자이너'),
       ('기획자'),
       ('모바일');

-- SELECT * FROM positions;


DELETE
FROM project_gallery ;
ALTER TABLE project_gallery
    AUTO_INCREMENT = 1;

INSERT INTO project_gallery (user_id, pg_title, pg_content, pg_startdate, pg_duration)
VALUES (1, '프로젝트1', '아 이런거 개발했음 ㅎㅋㅎㅋ','2023-09-07'  ,1),
       (2, '프로젝트2', '아 이런거 개발했음 ㅎㅋㅎㅋ','2023-09-07',2),
       (3, '프로젝트3', '아 이런거 개발했음 ㅎㅋㅎㅋ','2023-09-07',3),
       (4, '프로젝트4', '아 이런거 개발했음 ㅎㅋㅎㅋ','2023-09-07',4);

SELECT * FROM project_gallery;


DELETE
FROM pg_likes;
INSERT INTO pg_likes (user_id, pg_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 3),
       (2, 4);
-- SELECT * FROM pg_likes;


DELETE
FROM pg_comments;
ALTER TABLE pg_comments
    AUTO_INCREMENT = 1;

INSERT INTO pg_comments (pg_id, user_id, pg_c_comment)
VALUES (1,1,'야호'),
       (1,2,'야호'),
       (1,3,'야호'),
       (1,4,'야호'),
       (2,1,'야호'),
       (2,2,'야호'),
       (2,3,'야호'),
       (2,4,'야호');

-- SELECT * FROM pg_comments;

DELETE
FROM pg_types_url ;
INSERT INTO pg_types_url (type_id, pg_id, url)
VALUES (1, 1, 'http'),
       (2, 1, 'http'),
       (3, 1, 'http'),
       (1, 2, 'http'),
       (2, 2, 'http'),
       (3, 2, 'http');
SELECT * FROM pg_types_url;


DELETE
FROM pg_skills ;
INSERT INTO pg_skills (skill_id, pg_id)
VALUES (1,1),
       (2,1),
       (3,1),
       (2,2),
       (2,3);
SELECT * FROM pg_skills;

DELETE
FROM pg_positions ;
INSERT INTO pg_positions (position_id, pg_id)
VALUES (1,2),
       (1,1),
       (1,3),
       (2,3),
       (2,4);
SELECT * FROM pg_positions;


DELETE
FROM pg_files ;
INSERT INTO pg_files (pg_id, file_id)
VALUES (1,1),
       (1,2),
       (2,3);
SELECT * FROM pg_files;


DELETE
FROM project_recruits ;
ALTER TABLE project_recruits
    AUTO_INCREMENT = 1;

INSERT INTO project_recruits (pr_title, pr_content, pr_method, pr_predict)
VALUES ('백엔드 모집', '우리 이런 프로젝트 할껀데 올사람111','온라인', 3),
       ('프론트엔드 모집', '우리 이런 프로젝트 할껀데 올사람222','온오프라인', 2),
       ('다자이너 모집' ,'우리 이런 프로젝트 할껀데 올사람333','오프라인', 4),
       ('기획자 모집' ,'우리 이런 프로젝트 할껀데 올사람444','온오프라인', 3),
       ('자바개발자 모집' ,'우리 이런 프로젝트 할껀데 올사람555','오프라인', 1);
SELECT * FROM project_recruits;



DELETE
FROM pr_comments ;
ALTER TABLE pr_comments
    AUTO_INCREMENT = 1;

INSERT INTO pr_comments (pr_id, user_id, pr_c_comment)
VALUES (1,1,'지원 각1'),
       (1,2,'지원 각2'),
       (1,3,'지원 각3'),
       (2,1,'지원 각4'),
       (1,1,'지원 각5');

SELECT * FROM pr_comments;


-- pr 좋아요
DELETE
FROM pr_likes ;
INSERT INTO pr_likes (user_id, pr_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2,1);

SELECT * FROM pr_likes;

-- pr skill


DELETE
FROM pr_skills ;
INSERT INTO pr_skills (skill_id, pr_id)
VALUES (1,1),
       (2,1),
       (3,1),
       (2,2);
SELECT * FROM pr_skills;



DELETE
FROM pr_positions ;
INSERT INTO pr_positions (position_id, pr_id)
VALUES (1,2),
       (1,1),
       (1,3),
       (2,3),
       (2,4);
SELECT * FROM pr_positions;


DELETE
FROM pr_types_urls ;
INSERT INTO pr_types_urls (type_id, pr_id, url)
VALUES (1, 1, 'http'),
       (2, 1, 'http'),
       (3, 1, 'http'),
       (1, 2, 'http'),
       (2, 2, 'http'),
       (3, 2, 'http');
SELECT * FROM pg_types_url;

DELETE
FROM pr_files ;
INSERT INTO pr_files (pr_id, file_id)
VALUES (1,1),
       (1,2),
       (2,3);
SELECT * FROM pr_files;





-- QnA

DELETE
FROM qna_posts ;
ALTER TABLE qna_posts
    AUTO_INCREMENT = 1;

INSERT INTO qna_posts (user_id, post_title, post_content)
VALUES (1, '어려워요', '~~~~한게 어렵다'),
       (2, '어려워요2', '~~~~~한게 어렵다2'),
       (3, '어려워요3', '~~~~~한게 어렵다3'),
       (4, '어려워요4', '~~~~~한게 어렵다4'),
       (3, '어려워요5', '~~~~~한게 어렵다5');

SELECT * FROM qna_posts;

DELETE
FROM qna_comments ;
ALTER TABLE qna_comments
    AUTO_INCREMENT = 1;

INSERT INTO qna_comments (user_id, post_id, comment)
VALUES (1, 1, '~~~하면 됨!'),
       (1, 2, '~~~하면 됨!'),
       (1, 3, '~~~하면 됨!'),
       (1, 4, '~~~하면 됨!');

SELECT * FROM qna_comments;


DELETE
FROM qna_comment_likes;
INSERT INTO qna_comment_likes (user_id, comment_id)
VALUES (1,2),
       (1,1),
       (2, 3),
       (1,4);

SELECT * FROM qna_comment_likes;


-- Community
DELETE
FROM feeds ;
ALTER TABLE feeds
    AUTO_INCREMENT = 1;

INSERT INTO feeds (user_id, feed_title, feed_content, feed_state, feed_likeCnt)
VALUES (1, 'title1', 'content1', 'comp', 3),
       (1, 'title2', 'content2', 'temp', 0),
       (1, 'title3', 'content3', 'comp', 2),
       (2, 'title4', 'content4', 'comp', 1),
       (3, 'title5', 'content5', 'del', 0);

SELECT * FROM feeds;

DELETE
FROM feed_tags ;
INSERT INTO feed_tags (tag_id, feed_id)
VALUES ('spring', 1),
       ('spring', 3),
       ('security', 1),
       ('security', 4),
       ('java', 5),
       ('java', 3);

SELECT * FROM feed_tags;

DELETE
FROM feed_likes;
INSERT INTO feed_likes (user_id, feed_id)
VALUES (1, 1),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 3),
       (4, 1);
SELECT * FROM feed_likes;


DELETE
FROM feed_comments;
ALTER TABLE feed_comments
    AUTO_INCREMENT = 1;

INSERT INTO feed_comments (feed_id, user_id, parent_id, content)
VALUES (1, 2, null, 'comment1'),
       (1, 1, 1, 'comment1-1'),
       (1, 3, 1, 'comment1-2'),
       (1, 4, null, 'comment2'),
       (1, 2, null, 'comment3');

SELECT * FROM feed_comments;

DELETE
FROM feed_photos;
ALTER TABLE feed_photos
    AUTO_INCREMENT = 1;

INSERT INTO feed_photos (feed_id, photo_url, photo_order)
VALUES (1, 'image1.png', 1),
       (1, 'image5.png', 5),
       (1, 'image3.png', 3),
       (1, 'image4.png', 4),
       (1, 'image2.png', 2),
       (3, '3image1.png', 1),
       (3, '3image2.png', 2),
       (3, '3image3.png', 3);
SELECT * FROM feed_photos;


-- Study

DELETE FROM study_posts;
ALTER TABLE study_posts AUTO_INCREMENT = 1;
INSERT INTO study_posts (user_id, post_title, post_content, post_veiwCnt, post_enddate, post_member)
VALUES
    (1, '자바 스터디 구해영!', '자바 스터디 구합니다!', 27, '2023-12-12', 4),
    (2, '스프링 스터디 구해영!', '스프링 스터디 구합니다!', 109, '2023-12-10', 3),
    (3, 'css 스터디 구해영!', 'css 스터디 구합니다!', 105, '2023-12-05', 4),
    (4, 'html 스터디 구해영!', 'html 스터디 구합니다!', 105,'2023-12-07', 3);
SELECT * FROM study_posts;




DELETE FROM study_comments;
ALTER TABLE study_comments AUTO_INCREMENT = 1;
INSERT INTO study_comments( user_id, post_id, comment)
VALUES
    (1,1, '저도 구합니다!'),
    (2,2, '저두용'),
    (3,3, '이러쿵 저러쿵'),
    (4,4, '오늘 팟 구함!');
SELECT * FROM study_comments;




DELETE FROM study_files;
ALTER TABLE study_files AUTO_INCREMENT = 1;
INSERT INTO study_files(file_url)
VALUES
    ('나는/파일주소/다1'),
    ('나는/파일주소/다2'),
    ('나는/파일주소/다3'),
    ('나는/파일주소/다4');
SELECT * FROM study_files;




DELETE FROM posts_files;
INSERT INTO posts_files(post_id, file_id)
VALUES
    (1,1),
    (2,2),
    (3,3),
    (4,4);
SELECT * FROM posts_files;



DELETE FROM study_likes;
INSERT INTO study_likes(post_id, user_id)
VALUES
    (1,1),
    (2,2),
    (3,3),
    (4,4);
SELECT * FROM study_likes;



DELETE FROM study_skills;
INSERT INTO study_skills(post_id, skill_id)
VALUES
    (1,1),
    (2,2),
    (3,3),
    (4,4);
SELECT * FROM study_skills;


-- info

# CREATE TABLE info_jobs
# (
#     job_id int NOT NULL AUTO_INCREMENT,
#     user_id int NOT NULL,
#     company_name varchar(200) NOT NULL,
#     company_intro text NOT NULL,
#     main_job text NOT NULL,
#     required text NOT NULL,
#     job_location varchar(300) NOT NULL,
#     job_title varchar(300) NOT NULL,
#     job_startdate date DEFAULT NOW(), SYSDATE(),
# 	job_enddate date,
# 	PRIMARY KEY (job_id)
# );
#
DELETE
FROM info_jobs;
ALTER TABLE info_jobs
    AUTO_INCREMENT = 1;
-- 샘플 글
INSERT INTO info_jobs (user_id, company_name, company_intro, main_job, required, job_location, job_title, job_startdate, job_enddate)
VALUES (3, '코리아 it학원', '회사소개 어쩌구저꺼구', 'back', '3년 이상 경력자', '서울시 강남구 역삼역', '[sw개발팀] 모바일 어플리케이션 개발자 모집(경력)', '2023.11.26', null),   -- 상시 = null
       (4, '리테일앤인사이트', 'ri 회사소개', 'ui/ux 디자이너 모집', '경력 무관', '서울시 강남구 테헤란로 305 한국기술센터', '[cb팀] ui/ux 디자이너 모집(경력 무관)', '2023.11.10', '2023.12.20'),
       (3, '네이버', '네이버 회사소개', 'data 분석가', '5년이상 경력자', '경기도 성남시 판교 네이버 본사', '[분석/기획팀] 자사 앱 데이터 분석 및 타사 레퍼런스 분석', '2023.11.03', null),
       (4, '스타벅스', '스타벅스 회사소개', 'front', '신입/경력 무관', '서울시 강남구 스타벅스', '[모바일 개발팀] 자사 앱 리모델링 및 디벨롭 전문가 모집(경력 무관)', '2023.10.01', '2023.11.30')
;
SELECT * FROM info_jobs;

# CREATE TABLE info_resume
# (
#     user_id int NOT NULL,
#     job_id int NOT NULL, -> 공고 번호
#     resume_title varchar(200) NOT NULL,
#     resume_content text NOT NULL,
#     graduate_school varchar(100) NOT NULL,
#     graduate_year date NOT NULL,
#     career int DEFAULT 0 NOT NULL, -> experience
#     applydate date DEFAULT NOW(), SYSDATE() NOT NULL,
# 	update_date date DEFAULT NOW(), SYSDATE(),
# 	isWritten bit(1) DEFAULT '0' NOT NULL,
# 	PRIMARY KEY (user_id, job_id)
# );
#
DELETE
FROM info_resume;
-- 샘플 글
INSERT INTO info_resume (user_id, job_id,resume_title, resume_content, graduate_school, graduate_year, career, isWritten)
values (1,1,'제목1', '저는 서울대학교를 졸업했숨당', '서울대학교', '2022.02.28', 0, 1),
       (1,2,'제목2','저는 대졸입니다', 'Coventry University', '2020.11.15', 3, 0),
       (2,3,'제목3','네이버 지원합니다.', '홍익대학교', '2023.02.28', 0, 1),
       (2,4,'제목4','저를 뽑아주세요..!', '가좌고등학교', '2019.02.28', 4, 1);
SELECT * FROM info_resume;


# CREATE TABLE info_careerdetails
# (
#     user_id int NOT NULL,
#     job_id int NOT NULL,
#     career_id int NOT NULL AUTO_INCREMENT,
#     company_name varchar(100) NOT NULL,
#     working_start date NOT NULL,
#     working_end date DEFAULT NOW(), SYSDATE(),
# 	PRIMARY KEY (user_id, job_id, career_id)
# );
DELETE
FROM info_careerdetails;
ALTER TABLE info_careerdetails
    AUTO_INCREMENT = 1;
-- 샘플 글
INSERT INTO info_careerdetails (user_id, job_id, company_name, working_start, working_end)
values (1, 1, '코리아 it학원', '2018.03.09', '2020.04.01'),
       (1, 2, '리테일앤인사이트', '2020.04.27', '2021.04.30'),
       (2, 3, '네이버', '2021.05.17', '2023.09.18'),
       (2, 4, '스타벅스', '2023.10.08', null);  -- null = 재직중
SELECT * FROM info_careerdetails;


DELETE
FROM info_files;
ALTER TABLE info_files
    AUTO_INCREMENT = 1;

INSERT INTO info_files (file_image_url)
VALUES ('image1.png'),
       ('image2.jpg'),
       ('image3.png'),
       ('image4.jpg');
SELECT * FROM info_files;


DELETE FROM jobs_files;
INSERT INTO jobs_files (file_id, job_id)
VALUES (1,1),
       (2,2),
       (3,3),
       (4,4);
SELECT * FROM jobs_files;

DELETE FROM jobs_positions;
INSERT INTO jobs_positions (job_id, position_id)
VALUES (1, 2),
       (2, 3),
       (3, 4),
       (2, 1);
SELECT * FROM jobs_positions;


DELETE FROM info_url;
INSERT INTO info_url (user_id, job_id, type_id, url)
VALUES (1,1,1, 'httpgithub.com'),
       (1,2,2, 'notion.io'),
       (2,3,3, 'tistory'),
       (2,4,4, 'velog');
SELECT * FROM info_url;



DELETE FROM user_port_url;
INSERT INTO user_port_url (user_id, type_id, url)
VALUES (1,1, 'git'),
       (1,2, 'notion.io'),
       (2,3 , 'http');
SELECT * FROM user_port_url;

