
CREATE DATABASE IF NOT EXISTS mantis_test CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE mantis_test;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS profiles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bio VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT fk_profile_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS projects (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS issues (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    project_id BIGINT,
    CONSTRAINT fk_issue_project FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_project (
    user_id BIGINT,
    project_id BIGINT,
    PRIMARY KEY (user_id, project_id),
    CONSTRAINT fk_user_project_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_project_project FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);
