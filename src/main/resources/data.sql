-- FOR TESTS (with maven only)
DELETE FROM pricing_dump;
DELETE FROM product_dump;
DELETE FROM order_line;
DELETE FROM orders;
DELETE FROM customer;

INSERT INTO customer VALUES(1, 'Putka Oy', 'Vankilakatu 1', '00100', 'Helsinki', 'Finland');
INSERT INTO orders VALUES(1, 1);
INSERT INTO orders VALUES(2, 1);
INSERT INTO order_line VALUES(1, 1, 1, 'Nokia Lumia 1020', 1);

INSERT INTO order_line VALUES(4, 2, 2, 'Nokia Lumia 1520', 1);