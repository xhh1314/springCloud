drop table  IF EXISTS oauth_client_details ;
create table oauth_client_details(
  client_id INT unsigned   PRIMARY KEY AUTO_INCREMENT,
  client_key VARCHAR(255) not null,
  client_secret VARCHAR(255) not null,
  resources_id VARCHAR(255) DEFAULT '',
  scope VARCHAR(56) DEFAULT '',
  authorized_grant_type VARCHAR(56) DEFAULT '',
  web_server_redirect_uri VARCHAR(256) DEFAULT '' COMMENT '重定向uri',
  access_token_validity int not null COMMENT 'token过期时间,以秒为计算单位',
  refresh_token_validity int NOT NULL
);
create unique INDEX  uk_client_key on oauth_client_details(client_key);


drop TABLE  IF EXISTS oauth_access_token;
create table oauth_access_token(
  access_token VARCHAR(128) NOT NULL PRIMARY KEY ,
  refresh_token VARCHAR(128) not null,
  client_key VARCHAR(256),
  create_time DATETIME,
  expires_time int UNSIGNED
);
create INDEX index_refresh_token on oauth_access_token(refresh_token) ;
create INDEX index_client_key on oauth_access_token(client_key) ;



EXPLAIN  select * from oauth_access_token where client_id=1;

EXPLAIN SELECT * from oauth_access_token where client_id=1 and refresh_token_id=2;
EXPLAIN SELECT * from oauth_access_token where refresh_token_id=2 and  client_id=1;


drop table IF EXISTS oauth_refresh_token;
CREATE table oauth_refresh_token(
  refresh_token VARCHAR(128) NOT NULL  PRIMARY KEY ,
  client_key VARCHAR(256) NOT NULL ,
  create_time DATETIME,
  expires_time int UNSIGNED
);
create INDEX  index_client_key ON oauth_refresh_token(client_key);