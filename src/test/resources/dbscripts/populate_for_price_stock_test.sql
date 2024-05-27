INSERT INTO staffs ([is_admin], [firstname], [lastname], [phone], [email], [login_name], [password_hash], [registered_on], [last_login], [is_active])
VALUES (
    1,
    N'Peter',
    N'Parker',
    '8888888888',
    'parker@email.com',
    'admin',
    cast('c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec' AS varbinary(64)),
    FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
    FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
    1
);


INSERT INTO [categories] ([id], [name], [created_on], [updated_on])
VALUES ('C01', N'Hat', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'));

INSERT INTO [categories] ([id], [name], [created_on], [updated_on])
VALUES ('C02', N'Xay', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'));




INSERT INTO [products] ([category_id], [name], [created_on], [updated_on], [description], [is_active])
VALUES ('C02', N'Ca Phe So 1', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), N'say something about ca phe so 1', 1);

INSERT INTO [price_records] ([product_id], [price], [discount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe So 1'), 39.99, 0.0, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));

INSERT INTO [stock_records] ([product_id], [amount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe So 1'), 10, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));




INSERT INTO [products] ([category_id], [name], [created_on], [updated_on], [description], [is_active])
VALUES ('C02', N'Ca Phe So 2', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), N'say something about ca phe so 2', 1);

INSERT INTO [price_records] ([product_id], [price], [discount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe So 2'), 49.99, 0.2, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));

INSERT INTO [stock_records] ([product_id], [amount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe So 2'), 15, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));




INSERT INTO [products] ([category_id], [name], [created_on], [updated_on], [description], [is_active])
VALUES ('C02', N'Ca Phe So 3', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), N'say something about ca phe so 3', 1);

INSERT INTO [price_records] ([product_id], [price], [discount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe So 3'), 29.99, 0.2, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));

INSERT INTO [stock_records] ([product_id], [amount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe So 3'), 30, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));




INSERT INTO [products] ([category_id], [name], [created_on], [updated_on], [description], [is_active])
VALUES ('C01', N'Ca Phe Hat So 1', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), N'some description about ca phe hat so 1', 1);

INSERT INTO [price_records] ([product_id], [price], [discount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe Hat So 1'), 19.99, 0, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));

INSERT INTO [stock_records] ([product_id], [amount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe Hat So 1'), 20, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));




INSERT INTO [products] ([category_id], [name], [created_on], [updated_on], [description], [is_active])
VALUES ('C01', N'Ca Phe Hat So 2', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), N'some description about ca phe hat so 2', 1);

INSERT INTO [price_records] ([product_id], [price], [discount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe Hat So 2'), 17.99, 0, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));

INSERT INTO [stock_records] ([product_id], [amount], [updated_on], [updated_by])
VALUES ( (SELECT id FROM products WHERE [name] = N'Ca Phe Hat So 2'), 30, FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), (SELECT id FROM staffs WHERE [login_name] = 'admin'));
