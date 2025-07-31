-- 超市前台销售系统数据库表结构

-- 用户表
CREATE TABLE user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名',
  password VARCHAR(100) NOT NULL COMMENT '密码',
  role VARCHAR(20) NOT NULL COMMENT '角色：admin-系统管理员,product-商品管理员,cashier-收银员,member-会员',
  status TINYINT DEFAULT 1 COMMENT '状态：1-启用，0-停用',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 商品分类表
CREATE TABLE category (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL COMMENT '分类名称',
  parent_id INT DEFAULT NULL COMMENT '父分类ID',
  level INT DEFAULT 1 COMMENT '分类层级',
  sort_order INT DEFAULT 0 COMMENT '排序',
  status TINYINT DEFAULT 1 COMMENT '状态：1-启用，0-停用',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 商品表
CREATE TABLE product (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL COMMENT '商品名称',
  barcode VARCHAR(50) UNIQUE NOT NULL COMMENT '商品条码',
  category_id INT COMMENT '分类ID',
  price DECIMAL(10,2) NOT NULL COMMENT '售价',
  cost_price DECIMAL(10,2) COMMENT '成本价',
  shelf_code VARCHAR(20) COMMENT '货架编码',
  stock INT DEFAULT 0 COMMENT '库存数量',
  min_stock INT DEFAULT 10 COMMENT '最低库存警戒值',
  status TINYINT DEFAULT 1 COMMENT '状态：1-上架，0-下架',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (category_id) REFERENCES category(id)
);

-- 会员表
CREATE TABLE member (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL COMMENT '会员姓名',
  card_no VARCHAR(50) UNIQUE NOT NULL COMMENT '会员卡号',
  phone VARCHAR(20) COMMENT '手机号',
  level VARCHAR(20) DEFAULT 'BRONZE' COMMENT '会员等级：BRONZE-青铜,SILVER-白银,GOLD-黄金,PLATINUM-铂金',
  points INT DEFAULT 0 COMMENT '积分',
  total_consumption DECIMAL(10,2) DEFAULT 0 COMMENT '累计消费金额',
  status TINYINT DEFAULT 1 COMMENT '状态：1-正常，0-停用',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 会员等级规则表
CREATE TABLE member_level_rule (
  id INT PRIMARY KEY AUTO_INCREMENT,
  level VARCHAR(20) NOT NULL COMMENT '会员等级',
  level_name VARCHAR(50) NOT NULL COMMENT '等级名称',
  min_points INT NOT NULL COMMENT '最低积分要求',
  discount_rate DECIMAL(3,2) DEFAULT 1.00 COMMENT '折扣率',
  points_rate DECIMAL(3,2) DEFAULT 1.00 COMMENT '积分倍率',
  description TEXT COMMENT '等级描述'
);

-- 销售单表
CREATE TABLE sale (
  id INT PRIMARY KEY AUTO_INCREMENT,
  sale_no VARCHAR(50) UNIQUE NOT NULL COMMENT '销售单号',
  cashier_id INT NOT NULL COMMENT '收银员ID',
  member_id INT COMMENT '会员ID',
  total_amount DECIMAL(10,2) NOT NULL COMMENT '应收总额',
  discount_amount DECIMAL(10,2) DEFAULT 0 COMMENT '优惠金额',
  actual_amount DECIMAL(10,2) NOT NULL COMMENT '实收金额',
  pay_type VARCHAR(20) NOT NULL COMMENT '支付方式：cash-现金,card-银行卡,coupon-赠券',
  status VARCHAR(20) DEFAULT 'PENDING' COMMENT '状态：PENDING-待结账,COMPLETED-已完成,CANCELLED-已撤单,HOLD-已挂单',
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  complete_time DATETIME COMMENT '完成时间',
  FOREIGN KEY (cashier_id) REFERENCES user(id),
  FOREIGN KEY (member_id) REFERENCES member(id)
);

-- 销售明细表
CREATE TABLE sale_item (
  id INT PRIMARY KEY AUTO_INCREMENT,
  sale_id INT NOT NULL COMMENT '销售单ID',
  product_id INT NOT NULL COMMENT '商品ID',
  quantity INT NOT NULL COMMENT '数量',
  price DECIMAL(10,2) NOT NULL COMMENT '单价',
  amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
  FOREIGN KEY (sale_id) REFERENCES sale(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);

-- 货架变更日志表
CREATE TABLE shelf_log (
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_id INT NOT NULL COMMENT '商品ID',
  before_shelf VARCHAR(20) COMMENT '变更前货架',
  after_shelf VARCHAR(20) COMMENT '变更后货架',
  change_type VARCHAR(20) NOT NULL COMMENT '变更类型：UP-上架,MOVE-移架,DOWN-下架',
  operator_id INT NOT NULL COMMENT '操作员ID',
  change_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  remark TEXT COMMENT '备注',
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (operator_id) REFERENCES user(id)
);

-- 库存变更日志表
CREATE TABLE stock_log (
  
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_id INT NOT NULL COMMENT '商品ID',
  change_type VARCHAR(20) NOT NULL COMMENT '变更类型：SALE-销售,SUPPLY-补货,ADJUST-调整',
  before_stock INT NOT NULL COMMENT '变更前库存',
  after_stock INT NOT NULL COMMENT '变更后库存',
  change_quantity INT NOT NULL COMMENT '变更数量',
  operator_id INT NOT NULL COMMENT '操作员ID',
  change_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  remark TEXT COMMENT '备注',
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (operator_id) REFERENCES user(id)
);

-- 会员积分变更日志表
CREATE TABLE member_points_log (
  id INT PRIMARY KEY AUTO_INCREMENT,
  member_id INT NOT NULL COMMENT '会员ID',
  change_type VARCHAR(20) NOT NULL COMMENT '变更类型：EARN-获得,USE-使用,ADJUST-调整',
  before_points INT NOT NULL COMMENT '变更前积分',
  after_points INT NOT NULL COMMENT '变更后积分',
  change_points INT NOT NULL COMMENT '变更积分',
  sale_id INT COMMENT '关联销售单ID',
  operator_id INT NOT NULL COMMENT '操作员ID',
  change_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  remark TEXT COMMENT '备注',
  FOREIGN KEY (member_id) REFERENCES member(id),
  FOREIGN KEY (sale_id) REFERENCES sale(id),
  FOREIGN KEY (operator_id) REFERENCES user(id)
);

-- 插入初始数据
INSERT INTO user (username, password, role) VALUES 
('admin', '123', 'admin'),
('product1', '123', 'product'),
('cashier1', '123', 'cashier');

INSERT INTO category (name, parent_id, level) VALUES 
('食品', NULL, 1),
('饮料', NULL, 1),
('日用品', NULL, 1),
('零食', 1, 2),
('饮料', 2, 2);

INSERT INTO member_level_rule (level, level_name, min_points, discount_rate, points_rate, description) VALUES 
('BRONZE', '青铜会员', 0, 0.95, 1.00, '新会员，享受95折优惠'),
('SILVER', '白银会员', 1000, 0.90, 1.10, '积分满1000，享受9折优惠，积分1.1倍'),
('GOLD', '黄金会员', 5000, 0.85, 1.20, '积分满5000，享受85折优惠，积分1.2倍'),
('PLATINUM', '铂金会员', 10000, 0.80, 1.50, '积分满10000，享受8折优惠，积分1.5倍'); 