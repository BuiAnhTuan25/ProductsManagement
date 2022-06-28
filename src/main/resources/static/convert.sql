REPLACE INTO product (id, description, name, price, sku, status, category_id)
VALUES (1001,'Ownsworth','Beer - Camerons Cream Ale',50001,'Haleigh',0,5);

insert into
on duplicate key update ('name' = 'tuan',...)