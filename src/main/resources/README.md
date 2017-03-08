-- Table: user
CREATE TABLE user (
  id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  fio VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table: record
CREATE TABLE record (
    id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    phonehome VARCHAR(100) DEFAULT NULL,
    adress VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user (id)
)
  ENGINE = InnoDB;