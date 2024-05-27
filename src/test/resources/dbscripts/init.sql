CREATE TABLE lk_province (
	id varchar(10),
	name nvarchar(50),

	CONSTRAINT [PK_lk_province] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],
);


CREATE TABLE lk_city (
	id varchar(10),
	name nvarchar(50),

	CONSTRAINT [PK_lk_city] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],
);


-- COD = cash on delivery
-- BANK = banking 

CREATE TABLE lk_payment_method (
	id varchar(5),
	name nvarchar(20),

	CONSTRAINT [PK_lk_payment_method] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],
);


-- PENDN = Pending
-- VERIF = Verified
-- DELIV = Delivering
-- COMPL = Complete
-- CANCL = Canceled
-- REFND = Refunded

CREATE TABLE lk_status (
	id varchar(5),
	name nvarchar(20),

	CONSTRAINT [PK_lk_status] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],
);


CREATE TABLE categories (
	id varchar(10) NOT NULL,
	name nvarchar(50) NOT NULL,
	created_on datetime NOT NULL CONSTRAINT [DF_categories_created_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
	updated_on datetime NOT NULL CONSTRAINT [DF_categories_updated_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),

	CONSTRAINT [PK_categories] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],
);


-- category_id == NULL -> UNCATERIZED
CREATE TABLE products (
	id int IDENTITY(0, 1),
	category_id varchar(10) NULL,
	name nvarchar(50) NOT NULL,
	created_on datetime NOT NULL CONSTRAINT [DF_products_created_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
	updated_on datetime NOT NULL CONSTRAINT [DF_products_updated_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
	description nvarchar(100) NULL,
	is_active bit NOT NULL CONSTRAINT [DF_products_is_active] DEFAULT (0),

	CONSTRAINT [PK_products] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],

	CONSTRAINT [FK_products_category] FOREIGN KEY ([category_id]) REFERENCES categories ([id])
	ON DELETE SET NULL,
);


CREATE TABLE staffs (
	id int IDENTITY(0, 1),
	is_admin bit NOT NULL CONSTRAINT [DF_staffs_is_admin] DEFAULT (0),
	firstname nvarchar(50) NOT NULL,
	lastname nvarchar(50) NOT NULL,
	phone varchar(15) NOT NULL,
	email varchar(50) NOT NULL,
	login_name varchar(15) NOT NULL,
	password_hash varbinary(64) NOT NULL,
	registered_on datetime NOT NULL CONSTRAINT [DF_staffs_registered_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
	last_login datetime NULL,
	is_active bit NOT NULL CONSTRAINT [DF_staffs_is_active] DEFAULT (1),

	CONSTRAINT [PK_staffs] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],
);


CREATE TABLE price_records (
	product_id int,
	price float NOT NULL,
	discount float NOT NULL, -- IN PERCENTAGE
	updated_on datetime NOT NULL CONSTRAINT [DF_price_records_updated_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
	updated_by int NOT NULL,

	CONSTRAINT [PK_price_records] PRIMARY KEY CLUSTERED (
	product_id ASC
	) ON [PRIMARY],

	CONSTRAINT [FK_price_records_product] FOREIGN KEY ([product_id]) REFERENCES products ([id]),

	CONSTRAINT [FK_price_records_staff] FOREIGN KEY ([updated_by]) REFERENCES staffs ([id]),

	CONSTRAINT [CK_price_records_price] CHECK (price >= 0),

	CONSTRAINT [CK_price_records_discount] CHECK (discount >= 0)
);


CREATE TABLE stock_records (
	product_id int NOT NULL,
	amount int NOT NULL,
	updated_on datetime NOT NULL CONSTRAINT [DF_stock_records_updated_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
	updated_by int NOT NULL,

	CONSTRAINT [PK_stock_records] PRIMARY KEY CLUSTERED (
	product_id ASC
	) ON [PRIMARY],

	CONSTRAINT [FK_stock_records_product] FOREIGN KEY ([product_id]) REFERENCES products ([id]),

	CONSTRAINT [FK_stock_records_staff] FOREIGN KEY ([updated_by]) REFERENCES staffs ([id]),

	CONSTRAINT [CK_price_records_amount] CHECK (amount >= 0),
);


CREATE TABLE customers (
	id int IDENTITY(0, 1),
	firstname nvarchar(50) NOT NULL,
	lastname nvarchar(50) NOT NULL,
	phone varchar(15) NOT NULL,
	email varchar(50) NOT NULL, -- LOGIN NAME == EMAIL
	password_hash varbinary(64) NOT NULL,
	address nvarchar(50) NULL,

	city_id varchar(10) NULL,
	province_id varchar(10) NULL,
	is_active bit NOT NULL CONSTRAINT [DF_customers_is_active] DEFAULT (1),

	CONSTRAINT [PK_customers] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],

	CONSTRAINT [UK_customers] UNIQUE NONCLUSTERED (
	phone ASC
	),

	CONSTRAINT [FK_customer_lk_city] FOREIGN KEY ([city_id]) REFERENCES lk_city ([id])
	ON DELETE SET NULL,

	CONSTRAINT [FK_customer_lk_province] FOREIGN KEY ([province_id]) REFERENCES lk_province ([id])
	ON DELETE SET NULL,
);


CREATE TABLE orders (
	id int IDENTITY(0, 1),
	status varchar(5) NOT NULL CONSTRAINT [DF_orders_status] DEFAULT 'PENDN',
	payment_method varchar(5) NOT NULL CONSTRAINT [DF_orders_payment_method] DEFAULT 'COD',
	created_on datetime NOT NULL CONSTRAINT [DF_orders_created_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
	updated_on datetime NOT NULL CONSTRAINT [DF_orders_updated_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
	staff_id int NOT NULL,
	customer_id int NOT NULL,
	note nvarchar(100) NULL,

	CONSTRAINT [PK_orders] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],

	CONSTRAINT [FK_orders_staff] FOREIGN KEY ([staff_id]) REFERENCES staffs ([id]),

	CONSTRAINT [FK_orders_customer] FOREIGN KEY ([customer_id]) REFERENCES customers ([id]),

	CONSTRAINT [FK_orders_lk_status] FOREIGN KEY ([status]) REFERENCES lk_status ([id])
	ON DELETE SET DEFAULT,

	CONSTRAINT [FK_orders_lk_payment_method] FOREIGN KEY ([payment_method]) REFERENCES lk_payment_method ([id])
	ON DELETE SET DEFAULT,
);


CREATE TABLE order_items (
	id int IDENTITY(0, 1),
	product_id int NOT NULL,
	order_id int NOT NULL,
	amount int NOT NULL,

	CONSTRAINT [PK_order_items] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],

	CONSTRAINT [FK_order_items_product] FOREIGN KEY ([product_id]) REFERENCES products ([id]),

	CONSTRAINT [FK_order_items_order] FOREIGN KEY ([order_id]) REFERENCES orders ([id])
	ON DELETE CASCADE,

	CONSTRAINT [CK_order_items_amount] CHECK (amount > 0),
);


CREATE TABLE supplies (
	id int IDENTITY(0, 1),
	created_on datetime CONSTRAINT [DF_supplies_created_on] DEFAULT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss'),
	note nvarchar(100) NULL,
	staff_id int NOT NULL,

	CONSTRAINT [PK_supplies] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],

	CONSTRAINT [FK_supplies_staff] FOREIGN KEY ([staff_id]) REFERENCES staffs ([id]),
);


CREATE TABLE suppy_items (
	id int IDENTITY(0, 1),
	product_id int NOT NULL,
	supply_id int NOT NULL,
	amount int NOT NULL,
	price float NOT NULL, -- single price

	CONSTRAINT [PK_supply_items] PRIMARY KEY CLUSTERED (
	id ASC
	) ON [PRIMARY],

	CONSTRAINT [FK_supply_items_product] FOREIGN KEY ([product_id]) REFERENCES products ([id]),

	CONSTRAINT [FK_supply_items_supply] FOREIGN KEY ([supply_id]) REFERENCES supplies ([id])
	ON DELETE CASCADE,

	CONSTRAINT [CK_supply_items_amount] CHECK (amount > 0),

	CONSTRAINT [CK_supply_items_price] CHECK (price >= 0),
);
