create table if not exists liburua(
  `isbn` VARCHAR(50) NOT NULL,
  `izenburua` VARCHAR(45) NULL,
  `argitaletxea` VARCHAR(100) NULL,
  `orriKop` INT NULL,
  `irudia` VARCHAR(50) NULL,
  PRIMARY KEY (`isbn`));

INSERT INTO liburutegia.liburua (isbn, izenburua) VALUES ('1491910399', 'R for Data Science');
INSERT INTO liburutegia.liburua (isbn, izenburua) VALUES ('1491946008', 'Fluent Python');
INSERT INTO liburutegia.liburua (isbn, izenburua) VALUES ('9781491906187', 'Data Algorithms');

