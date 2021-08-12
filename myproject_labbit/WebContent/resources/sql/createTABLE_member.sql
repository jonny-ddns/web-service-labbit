USE labbit;
DROP TABLE IF EXISTS tb_member;
CREATE TABLE tb_member (
	memId		VARCHAR(30),
	memName		VARCHAR(10),
	passwd		VARCHAR(30),
	birthday	CHAR(8),
	gender		CHAR(1),
	email		VARCHAR(50),
	phone		VARCHAR(15),
	joinDate	TIMESTAMP,
	updateDate	TIMESTAMP,
	isActive	CHAR(1),
	isDelete	CHAR(1),    
    PRIMARY KEY(memId)
);