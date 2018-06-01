
CREATE TABLE customer (
  customer_id number(10) not null AUTO_INCREMENT,
  company_name varchar2(50) not null,
  street varchar2(50) not null,
  postal_code varchar2(5) not null,
  city varchar2(50) not null,
  country varchar2(50) not null,
  CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

CREATE TABLE orders (
  order_id number(10) not null AUTO_INCREMENT,
  customer_id number(10) not null,
  CONSTRAINT orders_pk PRIMARY KEY (order_id),
  CONSTRAINT fk_customer
    FOREIGN KEY (customer_id)
    REFERENCES customer(customer_id)
);

CREATE TABLE order_line (
  order_line_id number(10) not null AUTO_INCREMENT,
  order_id number(10) not null,
  product_id varchar2(50) not null,
  product_name varchar2 (50) not null,
  quantity number(10) not null,
  CONSTRAINT order_line_pk PRIMARY KEY (order_line_id),
  CONSTRAINT fk_order
    FOREIGN KEY(order_id)
    REFERENCES orders(order_id)
);

CREATE TABLE product_dump (
  product_group varchar2(30) not null,
  product_json clob not null,
  PRIMARY KEY (product_group)
);

CREATE TABLE pricing_dump (
  pricing_group varchar2(30) not null,
  price_json clob not null,
  PRIMARY KEY (pricing_group)
);