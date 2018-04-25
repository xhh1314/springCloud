drop table if exists taobao_order;
create table taobao_order(
  order_id int UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  user_id int UNSIGNED,
  delivery_address_id int UNSIGNED,
  aggregate_amount DOUBLE,
  return_amount DOUBLE,
  discount_amount DOUBLE,
  pay_amount double,
  create_time DATETIME,
  pay_time DATETIME,
  dispatching_id int UNSIGNED,
  status tinyint
);
create INDEX  idx_user_id on taobao_order(user_id);

DROP TABLE IF EXISTS taobao_order_item;
CREATE TABLE taobao_order_item(
  order_item_id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  product_id int UNSIGNED not null,
  order_id int UNSIGNED not null ,
  create_time DATETIME
);
create INDEX idx_order_id on taobao_order_item(order_id);


DROP table IF EXISTS scores;
create table scores(
  score_id int PRIMARY KEY AUTO_INCREMENT,
  student_id int,
  socre int
);
create INDEX idx_student_id on scores(student_id);