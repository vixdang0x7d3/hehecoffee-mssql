INSERT INTO [categories] ([id], [name], [created_on], [updated_on])
VALUES ('C01', N'Hat', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'));

INSERT INTO [categories] ([id], [name], [created_on], [updated_on])
VALUES ('C02', N'Xay', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'));


INSERT INTO [products] ([category_id], [name], [created_on], [updated_on], [description], [is_active])
VALUES ('C02', N'Ca Phe So 1', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), N'say something about ca phe so 1', 1);

INSERT INTO [products] ([category_id], [name], [created_on], [updated_on], [description], [is_active])
VALUES ('C02', N'Ca Phe So 2', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), N'say something about ca phe so 2', 1);

INSERT INTO [products] ([category_id], [name], [created_on], [updated_on], [description], [is_active])
VALUES ('C02', N'Ca Phe So 3', FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'), N'say something about ca phe so 3', 1);
