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
  token_id INT unsigned   primary key auto_increment,
  token VARCHAR(256) NOT NULL ,
  refresh_token_id int unsigned,
  client_key VARCHAR(256),
  create_time DATETIME,
  expires_time int UNSIGNED
);
create INDEX index_token_refreshTokenId_clientKey on oauth_access_token(token, refresh_token_id, client_key) ;
create INDEX index_token_refreshTokenId on oauth_access_token(token,refresh_token_id ) ;
drop index index_token_refreshTokenId on oauth_access_token;

drop index index_client_id ON  oauth_access_token;
create index index_client_id on oauth_access_token (client_id);

EXPLAIN  select * from oauth_access_token where client_id=1;

EXPLAIN SELECT * from oauth_access_token where client_id=1 and refresh_token_id=2;
EXPLAIN SELECT * from oauth_access_token where refresh_token_id=2 and  client_id=1;


drop table IF EXISTS oauth_refresh_token;
CREATE table oauth_refresh_token(
  refresh_token_id INT unsigned  primary key auto_increment,
  refresh_token VARCHAR(256) NOT NULL ,
  client_key VARCHAR(256) NOT NULL ,
  create_time DATETIME
);
ALTER table oauth_refresh_token add COLUMN expires_time int UNSIGNED;
create INDEX  uk_client_key ON oauth_refresh_token(client_key);