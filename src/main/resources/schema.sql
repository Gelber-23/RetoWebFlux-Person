-- -----------------------------------------------------
-- Table `people`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS people (
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  email       VARCHAR(100) NOT NULL,
  name        VARCHAR(100) NOT NULL,
  age         INT          NOT NULL
);

-- -----------------------------------------------------
-- Junction table `people_bootcamp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS people_bootcamp (
  id            BIGINT PRIMARY KEY AUTO_INCREMENT,
  people_id     BIGINT  NOT NULL,
  bootcamp_id   BIGINT  NOT NULL,

  CONSTRAINT FK_pb_people
    FOREIGN KEY (people_id)
    REFERENCES people (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  CONSTRAINT FK_pb_bootcamp
    FOREIGN KEY (bootcamp_id)
    REFERENCES bootcamps (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);