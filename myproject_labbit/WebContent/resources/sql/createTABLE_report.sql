USE labbit;
DROP TABLE IF EXISTS report;
CREATE TABLE report (
	boardNum 	INT AUTO_INCREMENT,
	memId		VARCHAR(30),
	boardTitle	VARCHAR(50),
	writeDate	TIMESTAMP,
	modifyDate	TIMESTAMP,
	uploadFiles	VARCHAR(200),
	isActive	CHAR(1),
	boardLike	INT,
	boardReport	INT,
	content		TEXT,
    PRIMARY KEY(boardNum)
);