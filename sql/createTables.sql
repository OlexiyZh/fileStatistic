CREATE TABLE FILE_STAT (
	ID INTEGER NOT NULL,
	NAME TEXT,
	PRIMARY KEY(ID)
);

CREATE TABLE LINE_STAT (
	ID INTEGER NOT NULL,
	LINE TEXT,
	LINE_LENGTH INTEGER,
	LONGEST_WORD INTEGER,
	SHORTEST_WORD INTEGER,
	AVARAGE_LENGTH FLOAT,
	FILE_STAT_ID INTEGER,
	PRIMARY KEY(ID),
	FOREIGN KEY(FILE_STAT_ID) REFERENCES FILE_STAT(ID)
);

CREATE TABLE SEQUENCES (
	SEQ_NAME char(4) NOT NULL,
	SEQ_NUMBER int NOT NULL,
	PRIMARY KEY(SEQ_NAME)
);

INSERT INTO SEQUENCES (SEQ_NAME, SEQ_NUMBER)
VALUES('FILE', 1);
INSERT INTO SEQUENCES (SEQ_NAME, SEQ_NUMBER)
VALUES('LINE', 1);