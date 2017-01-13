SET SESSION FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS finance_payflow;
DROP TABLE IF EXISTS finance_payaccount;

/*create table*/
CREATE TABLE finance_payflow
(
	id varchar(64) NOT NULL COMMENT '编号',
	payname varchar(20) NOT NULL COMMENT '款项',
	fromway int NOT NULL COMMENT '付款对象',
	toway int NOT NULL COMMENT '支付对象',
	amount float(8,2) NOT NULL COMMENT '金额',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
)
CREATE TABLE finance_account
(
	id varchar(64) NOT NULL COMMENT '编号',
	type int NOT NULL COMMENT '账户类型',
	name varchar(20) NOT NULL COMMENT '账户名称',
	amount float(8,2) NOT NULL COMMENT '金额',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
)