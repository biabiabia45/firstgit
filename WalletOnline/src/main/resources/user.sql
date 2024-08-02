DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    username     VARCHAR(255),
    password     VARCHAR(255),
    contact_info VARCHAR(255)
);
