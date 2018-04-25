DROP TABLE  IF EXISTS inventory_logistics;

create table inventory_logistics(
  logstics_id INT AUTO_INCREMENT PRIMARY KEY ,
  product_id INT,
  order_id INT,
  delivery_address VARCHAR(255),
  create_time DATETIME,
  accepting_order_time DATETIME,
  delivery_time DATETIME,
  transit_particular VARCHAR(255),
  receiving_time DATETIME
);
create INDEX idx_product_id on inventory_logistics(product_id);
CREATE INDEX idx_order_id on inventory_logistics(order_id);