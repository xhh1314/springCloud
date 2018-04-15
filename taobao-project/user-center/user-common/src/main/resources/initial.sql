DROP table IF EXISTS user;
CREATE TABLE user (
  user_id          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  username         VARCHAR(25) NOT NULL,
  nickname         VARCHAR(25) NOT NULL,
  password         VARCHAR(50) NOT NULL,
  email            VARCHAR(25) NOT NULL,
  phone            VARCHAR(20) NOT NULL,
  delivery_address VARCHAR(255) DEFAULT '',
  create_time      DATETIME,
  status           TINYINT
);
CREATE INDEX index_username
  ON user (username);
CREATE INDEX index_phone
  ON user (phone);
CREATE INDEX index_email
  ON user (email);
