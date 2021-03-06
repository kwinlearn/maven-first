# maven-first 这是我的第一个 maven 项目
### 建库建表 sql
```
CREATE DATABASE mybatis DEFAULT CHARACTER
SET utf8 COLLATE utf8_general_ci;

USE mybatis;

CREATE TABLE country (
	id INT NOT NULL AUTO_INCREMENT,
	countryname VARCHAR (255) NULL,
	countrycode VARCHAR (255) NULL,
	PRIMARY KEY (id)
);

INSERT country (countryname, countrycode)
VALUES
	('中国', 'CN'),
	('美国', 'US'),
	('俄罗斯', 'RU'),
	('英国', 'GB'),
	('法国', 'FR');
	
CREATE TABLE sys_user (
	id BIGINT NOT NULL auto_increment COMMENT '用户ID',
	user_name VARCHAR (50) COMMENT '用户名',
	user_password VARCHAR (50) COMMENT '密码',
	user_email VARCHAR (50) COMMENT '邮箱',
	user_info text COMMENT '简介',
	head_img BLOB COMMENT '头像',
	create_time datetime COMMENT '创建时间',
	PRIMARY KEY (id)
);

ALTER TABLE sys_user COMMENT '用户表';

CREATE TABLE sys_role (
	id BIGINT NOT NULL auto_increment COMMENT '角色ID',
	role_name VARCHAR (50) COMMENT '角色名',
	enabled INT COMMENT '有效标志',
	create_by BIGINT COMMENT '创建人',
	create_time datetime COMMENT '创建时间',
	PRIMARY KEY (id)
);

ALTER TABLE sys_role COMMENT '角色表';

CREATE TABLE sys_privilege (
	id BIGINT NOT NULL auto_increment COMMENT '权限ID',
	privilege_name VARCHAR (50) COMMENT '权限名称',
	privilege_url VARCHAR (200) COMMENT '权限URL',
	PRIMARY KEY (id)
);

ALTER TABLE sys_privilege COMMENT '权限表';

CREATE TABLE sys_user_role (
	user_id BIGINT COMMENT '用户ID',
	role_id BIGINT COMMENT '角色ID'
);

ALTER TABLE sys_user_role COMMENT '用户角色关联表';

CREATE TABLE sys_role_privilege (
	role_id BIGINT COMMENT '角色ID',
	privilege_id BIGINT COMMENT '权限ID'
);

ALTER TABLE sys_role_privilege COMMENT '角色权限关联表';

# 用户表添加数据
INSERT INTO sys_user
VALUES
	(
		1,
		'admin',
		'123456',
		'admin@mybatis.com',
		'管理员',
		NULL,
		'2017-07-20 15:23'
	);

INSERT INTO sys_user
VALUES
	(
		1001,
		'test',
		'123456',
		'test@mybatis.com',
		'测试用户',
		NULL,
		'2017-07-20 15:24'
	);

# 角色表添加数据
INSERT INTO sys_role
VALUES
	(
		1,
		'管理员',
		1,
		1,
		'2017-07-20 15:25'
	);

INSERT INTO sys_role
VALUES
	(
		2,
		'普通用户',
		1,
		1,
		'2017-07-20 15:33'
	);

# 用户角色表添加数据
INSERT INTO sys_user_role
VALUES
	(1, 1);

INSERT INTO sys_user_role
VALUES
	(1, 2);

INSERT INTO sys_user_role
VALUES
	(1001, 2);

# 权限表添加数据
INSERT INTO sys_privilege
VALUES
	(1, '用户管理', '/users');

INSERT INTO sys_privilege
VALUES
	(2, '角色管理', '/roles');

INSERT INTO sys_privilege
VALUES
	(3, '系统日志', '/logs');

INSERT INTO sys_privilege
VALUES
	(
		4,
		'人员维护',
		'/persons'
	);

INSERT INTO sys_privilege
VALUES
	(
		5,
		'单位维护',
		'/companies'
	);

# 角色权限添加数据
INSERT INTO sys_role_privilege
VALUES
	(1, 1);

INSERT INTO sys_role_privilege
VALUES
	(1, 3);

INSERT INTO sys_role_privilege
VALUES
	(1, 2);

INSERT INTO sys_role_privilege
VALUES
	(2, 4);

INSERT INTO sys_role_privilege
VALUES
	(2, 5);
ALTER TABLE sys_user
MODIFY COLUMN user_email VARCHAR(50) NULL DEFAULT 'test@mybatis.com'
COMMENT '邮箱'
AFTER user_password;
```

### trim 使用方法
```
where 和 set 标签都可以通过 trim 标签来实现，并且底层就是通过 TrimSqlNode 实现的
where 标签对应 trim 的实现如下
<trim prefix="WHERE" prefixOverrides="AND |OR ">
...
</trim>
   这里的 AND 和 OR 后面的空格不能省略，为了避免匹配到 andes、 orders 等单词
   实际的 profixeOverrides 包含 "AND"、"OR"、"AND\n"、"OR\n"、"AND\r"、"OR\r"、"AND\t"、"OR\t"
   ，不仅仅是上面提到的两个带空格的前缀
trim 标签有如下属性
	prefix: 当 trim 元素内包含内容时，会给内容增加 prefix 指定的前缀
	prefixOverrides: 当 trim 元素内包含内容时，会把内容中匹配的前缀字符串去掉
	suffix: 当 trim 元素内包含内容时，会给内容增加 suffix 指定的前缀
	suffixOverrides: 当 trim 元素内包含内容时，会把内容中匹配的前缀字符串去掉
```

### foreach
```
foreach 包含以下属性：
	collection: 必填，值为要迭代循环的变量名
	item: 变量名，值为迭代对象中去取出的每一个值
	index: 索引的属性名，在集合数组情况下值为当前索引值，当迭代循环的对象是 Map 类型时，这个值为 Map 的 Key
	open: 整个循环内容开头的字符串
	close: 整个循环内容结尾的字符串
	separator: 每次循环的分隔符
```
