SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS feed_comments;
DROP TABLE IF EXISTS feed_likes;
DROP TABLE IF EXISTS feed_photos;
DROP TABLE IF EXISTS feed_tags;
DROP TABLE IF EXISTS feeds;
DROP TABLE IF EXISTS info_careerdetails;
DROP TABLE IF EXISTS jobs_files;
DROP TABLE IF EXISTS info_files;
DROP TABLE IF EXISTS info_url;
DROP TABLE IF EXISTS info_resume;
DROP TABLE IF EXISTS jobs_positions;
DROP TABLE IF EXISTS info_jobs;
DROP TABLE IF EXISTS pg_comments;
DROP TABLE IF EXISTS pg_likes;
DROP TABLE IF EXISTS pg_positions;
DROP TABLE IF EXISTS pg_skills;
DROP TABLE IF EXISTS pg_types_url;
DROP TABLE IF EXISTS pr_positions;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS posts_files;
DROP TABLE IF EXISTS project_gallery;
DROP TABLE IF EXISTS pr_comments;
DROP TABLE IF EXISTS pr_likes;
DROP TABLE IF EXISTS pr_skills;
DROP TABLE IF EXISTS pr_types_urls;
DROP TABLE IF EXISTS project_recruits;
DROP TABLE IF EXISTS qna_comment_likes;
DROP TABLE IF EXISTS qna_comments;
DROP TABLE IF EXISTS qna_posts;
DROP TABLE IF EXISTS study_skills;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS study_comments;
DROP TABLE IF EXISTS study_files;
DROP TABLE IF EXISTS study_likes;
DROP TABLE IF EXISTS study_posts;
DROP TABLE IF EXISTS user_port_url;
DROP TABLE IF EXISTS urltype;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS pg_files;
DROP TABLE IF EXISTS pr_files;
DROP TABLE IF EXISTS project_files;




/* Create Tables */

CREATE TABLE feeds
(
	feed_id int NOT NULL AUTO_INCREMENT,
	feed_title varchar(200) NOT NULL,
	feed_content text NOT NULL,
	feed_state varchar(100) NOT NULL check ( feed_state IN ('comp', 'temp', 'del')),
	feed_regdate datetime DEFAULT NOW(),
	feed_likeCnt int DEFAULT 0,
	PRIMARY KEY (feed_id)
);


CREATE TABLE feed_comments
(
	comment_id int NOT NULL AUTO_INCREMENT,
	feed_id int NOT NULL,
	user_id int NOT NULL,
	parent_id int,
	content varchar(1000) NOT NULL,
	regdate datetime DEFAULT NOW(),
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
	photo_order int NOT NULL,
	PRIMARY KEY (photo_id)
);


CREATE TABLE feed_tags
(
	tag_id varchar(200) NOT NULL,
	feed_id int NOT NULL,
	PRIMARY KEY (tag_id, feed_id),
	UNIQUE (tag_id)
);


CREATE TABLE info_careerdetails
(
    career_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	job_id int NOT NULL,
	company_name varchar(100) NOT NULL,
	working_start datetime NOT NULL,
	working_end datetime DEFAULT NOW(),
	PRIMARY KEY (career_id,user_id, job_id)
);


CREATE TABLE info_files
(
	file_id int NOT NULL AUTO_INCREMENT,
	file_image_url varchar(1000) NOT NULL,
	PRIMARY KEY (file_id)
);


CREATE TABLE info_jobs
(
	job_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	company_name varchar(200) NOT NULL,
	company_intro text NOT NULL,
	main_job text NOT NULL,
	required text NOT NULL,
	job_location varchar(300) NOT NULL,
	job_title varchar(300) NOT NULL,
	job_startdate datetime DEFAULT NOW(),
	job_enddate datetime,
	PRIMARY KEY (job_id)
);


CREATE TABLE info_resume
(
	user_id int NOT NULL,
	job_id int NOT NULL,
	resume_title varchar(200) NOT NULL,
	resume_content text NOT NULL,
	graduate_school varchar(100) NOT NULL,
	graduate_year datetime NOT NULL,
	career int DEFAULT 0 NOT NULL,
	applydate datetime DEFAULT NOW() NOT NULL,
	update_date datetime DEFAULT NOW(),
	isWritten bit(1) DEFAULT 0 NOT NULL,
	PRIMARY KEY (user_id, job_id)
);


CREATE TABLE info_url
(
	user_id int NOT NULL,
	job_id int NOT NULL,
	type_id int NOT NULL,
	url varchar(1000) NOT NULL,
	PRIMARY KEY (user_id, job_id, type_id)
);


CREATE TABLE jobs_files
(
	file_id int NOT NULL,
	job_id int NOT NULL,
	PRIMARY KEY (file_id, job_id)
);


CREATE TABLE jobs_positions
(
	job_id int NOT NULL,
	position_id int NOT NULL,
	PRIMARY KEY (job_id, position_id)
);


CREATE TABLE pg_comments
(
	pg_c_id int NOT NULL AUTO_INCREMENT,
	pg_id int NOT NULL,
	user_id int NOT NULL,
	pg_c_comment text NOT NULL,
	pg_c_regdate datetime DEFAULT NOW(),
	PRIMARY KEY (pg_c_id)
);


CREATE TABLE pg_likes
(
	user_id int NOT NULL,
	pg_id int NOT NULL,
	PRIMARY KEY (user_id, pg_id)
);


CREATE TABLE pg_positions
(
	position_id int NOT NULL,
	pg_id int NOT NULL,
	PRIMARY KEY (position_id, pg_id)
);


CREATE TABLE pg_skills
(
	skill_id int NOT NULL,
	pg_id int NOT NULL,
	PRIMARY KEY (skill_id, pg_id)
);


CREATE TABLE pg_types_url
(
	type_id int NOT NULL,
	pg_id int NOT NULL,
	url varchar(1000) NOT NULL,
	PRIMARY KEY (type_id, pg_id)
);


CREATE TABLE positions
(
	position_id int NOT NULL AUTO_INCREMENT,
	position_name varbinary(100) NOT NULL,
	PRIMARY KEY (position_id),
	UNIQUE (position_name)
);


CREATE TABLE posts_files
(
	post_id int NOT NULL,
	file_id int NOT NULL,
	PRIMARY KEY (post_id, file_id)
);


CREATE TABLE project_gallery
(
	pg_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	pg_title varchar(200) NOT NULL,
	pg_content text NOT NULL,
	pg_startdate datetime NOT NULL,
	pg_duration int NOT NULL,
	viewCnt int DEFAULT 0 NOT NULL,
	pg_regdate datetime DEFAULT NOW(),
	likeCnt int DEFAULT 0 NOT NULL,
	PRIMARY KEY (pg_id)
);


CREATE TABLE project_recruits
(
	pr_id int NOT NULL AUTO_INCREMENT,
	pr_title varchar(200) NOT NULL,
	pr_content text NOT NULL,
	pr_method varchar(100) NOT NULL check (pr_method IN ('온라인', '오프라인', '온오프라인')),
	pr_predict int NOT NULL,
	viewCnt int DEFAULT 0,
	likeCnt int DEFAULT 0,
	PRIMARY KEY (pr_id)
);


CREATE TABLE pr_comments
(
	pr_c_id int NOT NULL AUTO_INCREMENT,
	pr_id int NOT NULL,
	user_id int NOT NULL,
	pr_c_comment text NOT NULL,
	pr_c_regdate datetime DEFAULT NOW() NOT NULL,
	PRIMARY KEY (pr_c_id)
);


CREATE TABLE pr_likes
(
	user_id int NOT NULL,
	pr_id int NOT NULL,
	PRIMARY KEY (user_id, pr_id)
);


CREATE TABLE pr_positions
(
	position_id int NOT NULL,
	pr_id int NOT NULL,
	PRIMARY KEY (position_id, pr_id)
);


CREATE TABLE pr_skills
(
	skill_id int NOT NULL,
	pr_id int NOT NULL,
	PRIMARY KEY (skill_id, pr_id)
);


CREATE TABLE pr_types_urls
(
	type_id int NOT NULL,
	pr_id int NOT NULL,
	url VARCHAR(1000) NOT NULL,
	PRIMARY KEY (type_id, pr_id)
);


CREATE TABLE qna_comments
(
	comment_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	post_id int NOT NULL,
	comment text NOT NULL,
	comment_regdate datetime DEFAULT NOW(),
	comment_modidate datetime DEFAULT NOW(),
	PRIMARY KEY (comment_id)
);


CREATE TABLE qna_comment_likes
(
	user_id int NOT NULL,
	comment_id int NOT NULL,
	PRIMARY KEY (user_id, comment_id)
);


CREATE TABLE qna_posts
(
	post_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	post_title varchar(300) NOT NULL,
	post_content text NOT NULL,
	post_modidate datetime DEFAULT NOW(),
	post_regdate datetime DEFAULT NOW(),
	PRIMARY KEY (post_id)
);


CREATE TABLE skills
(
	skill_id int NOT NULL AUTO_INCREMENT,
	skill_name varchar(100) NOT NULL,
	skill_img_url varchar(1000) NOT NULL,
	PRIMARY KEY (skill_id),
	UNIQUE (skill_name)
);


CREATE TABLE study_comments
(
	comment_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	post_id int NOT NULL,
	comment text NOT NULL,
	comment_regdate datetime DEFAULT NOW(),
	PRIMARY KEY (comment_id)
);


CREATE TABLE study_files
(
	file_id int NOT NULL AUTO_INCREMENT,
	file_url varchar(1000) NOT NULL,
	PRIMARY KEY (file_id)
);


CREATE TABLE study_likes
(
	post_id int NOT NULL,
	user_id int NOT NULL,
	PRIMARY KEY (post_id, user_id)
);


CREATE TABLE study_posts
(
	post_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	post_title varchar(200) NOT NULL,
	post_content text NOT NULL,
	post_veiwCnt int DEFAULT 0,
	post_regdate datetime DEFAULT NOW(),
	post_enddate datetime NOT NULL,
	post_member int NOT NULL,
	PRIMARY KEY (post_id)
);


CREATE TABLE study_skills
(
	post_id int NOT NULL,
	skill_id int NOT NULL,
	PRIMARY KEY (post_id, skill_id)
);


CREATE TABLE urltype
(
	type_id int NOT NULL AUTO_INCREMENT,
	type_name varchar(30) NOT NULL,
	PRIMARY KEY (type_id)
);


CREATE TABLE users
(
	user_id int NOT NULL AUTO_INCREMENT,
	user_login_id varchar(100) NOT NULL,
	user_password varchar(100) NOT NULL,
	nickname varchar(100),
	email varchar(200) NOT NULL,
	tel varchar(30) NOT NULL,
	address varchar(300) NOT NULL,
	isCompany bit(1) DEFAULT 0 NOT NULL,
	PRIMARY KEY (user_id),
	UNIQUE (user_login_id)
);


CREATE TABLE user_port_url
(
	user_id int NOT NULL,
	type_id int NOT NULL,
	url varchar(1000) NOT NULL,
	PRIMARY KEY (user_id, type_id)
);

CREATE TABLE project_files
(
    file_id int NOT NULL AUTO_INCREMENT,
    file_url varchar(1000) NOT NULL,
    PRIMARY KEY (file_id)
);

CREATE TABLE pr_files
(
    pr_id int NOT NULL,
    file_id int NOT NULL,
    PRIMARY KEY (pr_id, file_id)
);

CREATE TABLE pg_files
(
    pg_id int NOT NULL,
    file_id int NOT NULL,
    PRIMARY KEY (pg_id, file_id)
);




/* Create Foreign Keys */

ALTER TABLE pg_files
    ADD FOREIGN KEY (file_id)
        REFERENCES project_files (file_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;

ALTER TABLE pg_files
    ADD FOREIGN KEY (pg_id)
        REFERENCES project_gallery (pg_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;

ALTER TABLE pr_files
    ADD FOREIGN KEY (file_id)
        REFERENCES project_files (file_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;

ALTER TABLE pr_files
    ADD FOREIGN KEY (pr_id)
        REFERENCES project_recruits (pr_id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


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


ALTER TABLE feed_tags
	ADD FOREIGN KEY (feed_id)
	REFERENCES feeds (feed_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE jobs_files
	ADD FOREIGN KEY (file_id)
	REFERENCES info_files (file_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE info_resume
	ADD FOREIGN KEY (job_id)
	REFERENCES info_jobs (job_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE jobs_files
	ADD FOREIGN KEY (job_id)
	REFERENCES info_jobs (job_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE jobs_positions
	ADD FOREIGN KEY (job_id)
	REFERENCES info_jobs (job_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE info_careerdetails
	ADD FOREIGN KEY (user_id, job_id)
	REFERENCES info_resume (user_id, job_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE info_url
	ADD FOREIGN KEY (user_id, job_id)
	REFERENCES info_resume (user_id, job_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE jobs_positions
	ADD FOREIGN KEY (position_id)
	REFERENCES positions (position_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pg_positions
	ADD FOREIGN KEY (position_id)
	REFERENCES positions (position_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pr_positions
	ADD FOREIGN KEY (position_id)
	REFERENCES positions (position_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pg_comments
	ADD FOREIGN KEY (pg_id)
	REFERENCES project_gallery (pg_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pg_likes
	ADD FOREIGN KEY (pg_id)
	REFERENCES project_gallery (pg_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE pg_positions
	ADD FOREIGN KEY (pg_id)
	REFERENCES project_gallery (pg_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pg_skills
	ADD FOREIGN KEY (pg_id)
	REFERENCES project_gallery (pg_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pg_types_url
	ADD FOREIGN KEY (pg_id)
	REFERENCES project_gallery (pg_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pr_comments
	ADD FOREIGN KEY (pr_id)
	REFERENCES project_recruits (pr_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pr_likes
	ADD FOREIGN KEY (pr_id)
	REFERENCES project_recruits (pr_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pr_positions
	ADD FOREIGN KEY (pr_id)
	REFERENCES project_recruits (pr_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pr_skills
	ADD FOREIGN KEY (pr_id)
	REFERENCES project_recruits (pr_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pr_types_urls
	ADD FOREIGN KEY (pr_id)
	REFERENCES project_recruits (pr_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE qna_comment_likes
	ADD FOREIGN KEY (comment_id)
	REFERENCES qna_comments (comment_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE qna_comments
	ADD FOREIGN KEY (post_id)
	REFERENCES qna_posts (post_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pg_skills
	ADD FOREIGN KEY (skill_id)
	REFERENCES skills (skill_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pr_skills
	ADD FOREIGN KEY (skill_id)
	REFERENCES skills (skill_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE study_skills
	ADD FOREIGN KEY (skill_id)
	REFERENCES skills (skill_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE posts_files
	ADD FOREIGN KEY (file_id)
	REFERENCES study_files (file_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE posts_files
	ADD FOREIGN KEY (post_id)
	REFERENCES study_posts (post_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE study_comments
	ADD FOREIGN KEY (post_id)
	REFERENCES study_posts (post_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE study_likes
	ADD FOREIGN KEY (post_id)
	REFERENCES study_posts (post_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE study_skills
	ADD FOREIGN KEY (post_id)
	REFERENCES study_posts (post_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE info_url
	ADD FOREIGN KEY (type_id)
	REFERENCES urltype (type_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pg_types_url
	ADD FOREIGN KEY (type_id)
	REFERENCES urltype (type_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pr_types_urls
	ADD FOREIGN KEY (type_id)
	REFERENCES urltype (type_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE user_port_url
	ADD FOREIGN KEY (type_id)
	REFERENCES urltype (type_id)
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


ALTER TABLE info_jobs
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE info_resume
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pg_comments
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pg_likes
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE project_gallery
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pr_comments
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE pr_likes
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE qna_comments
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE qna_comment_likes
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE qna_posts
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE study_comments
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE study_likes
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE study_posts
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE user_port_url
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;



