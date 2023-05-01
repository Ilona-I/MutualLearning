CREATE SCHEMA mutual_learning_db;

CREATE TABLE `mutual_learning_db`.`user` (
             `login` VARCHAR(45) NOT NULL,
             `password` VARCHAR(45) NOT NULL,
             `name` VARCHAR(45) NULL,
             `email` VARCHAR(45) NULL,
             `info` VARCHAR(1000) NULL,
             `role` VARCHAR(45) NOT NULL,
             `level` VARCHAR(45) NULL,
             `status` VARCHAR(45) NOT NULL,
             PRIMARY KEY (`login`),
             UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);

CREATE TABLE `mutual_learning_db`.`article` (
            `id` INT NOT NULL AUTO_INCREMENT,
            `title` VARCHAR(45) NOT NULL,
            `type` VARCHAR(45) NOT NULL,
            `creationDateTime` TIMESTAMP NOT NULL,
            `lastUpdateDateTime` TIMESTAMP NULL,
            `status` VARCHAR(45) NOT NULL,
            PRIMARY KEY (`id`),
            UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

CREATE TABLE `mutual_learning_db`.`comment` (
            `id` INT NOT NULL AUTO_INCREMENT,
            `article_id` INT NOT NULL,
            `creationDateTime` TIMESTAMP NOT NULL,
            `lastUpdateDateTime` TIMESTAMP NULL,
            `link` INT NULL,
            PRIMARY KEY (`id`),
            UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
            INDEX `article_comment_idx` (`article_id` ASC) VISIBLE,
            CONSTRAINT `article_comment`
                FOREIGN KEY (`article_id`)
                    REFERENCES `mutual_learning_db`.`article` (`id`)
                    ON DELETE CASCADE
                    ON UPDATE CASCADE);

CREATE TABLE `mutual_learning_db`.`mark` (
         `id` INT NOT NULL AUTO_INCREMENT,
         `title` VARCHAR(45) NOT NULL,
         `description` VARCHAR(200) NULL,
         PRIMARY KEY (`id`),
         UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

CREATE TABLE `mutual_learning_db`.`test` (
         `id` INT NOT NULL AUTO_INCREMENT,
         `article_id` INT NOT NULL,
         `title` VARCHAR(45) NOT NULL,
         `description` VARCHAR(500) NULL,
         PRIMARY KEY (`id`),
         UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
         CONSTRAINT `article_test_fk`
         FOREIGN KEY (`article_id`)
         REFERENCES `mutual_learning_db`.`article` (`id`)
         ON DELETE CASCADE
         ON UPDATE CASCADE);

CREATE TABLE `mutual_learning_db`.`question` (
         `id` INT NOT NULL AUTO_INCREMENT,
         `test_id` INT NOT NULL,
         `text` VARCHAR(100) NOT NULL,
         `type` VARCHAR(45) NOT NULL,
         UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
         PRIMARY KEY (`id`),
         INDEX `question_test_fk_idx` (`test_id` ASC) VISIBLE,
         CONSTRAINT `question_test_fk`
             FOREIGN KEY (`test_id`)
                 REFERENCES `mutual_learning_db`.`test` (`id`)
                 ON DELETE CASCADE
                 ON UPDATE CASCADE);

CREATE TABLE `mutual_learning_db`.`answer` (
       `id` INT NOT NULL AUTO_INCREMENT,
       `question_id` INT NOT NULL,
       `text` VARCHAR(200) NOT NULL,
       `point` INT NOT NULL,
       PRIMARY KEY (`id`),
       UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
       INDEX `answer_question_fk_idx` (`question_id` ASC) VISIBLE,
       CONSTRAINT `answer_question_fk`
           FOREIGN KEY (`question_id`)
               REFERENCES `mutual_learning_db`.`question` (`id`)
               ON DELETE CASCADE
               ON UPDATE CASCADE);

CREATE TABLE `mutual_learning_db`.`article_part` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `article_id` INT NOT NULL,
     `number` INT NOT NULL,
     `text` TEXT NOT NULL,
     `type` VARCHAR(45) NOT NULL,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
     INDEX `article_part_fk_idx` (`article_id` ASC) VISIBLE,
     CONSTRAINT `article_part_fk`
         FOREIGN KEY (`article_id`)
             REFERENCES `mutual_learning_db`.`article` (`id`)
             ON DELETE CASCADE
             ON UPDATE CASCADE);

CREATE TABLE `mutual_learning_db`.`article_mark` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `article_id` INT NOT NULL,
     `mark_id` INT NOT NULL,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
     INDEX `mark_fk_idx` (`mark_id` ASC) VISIBLE,
     INDEX `article_fk_idx` (`article_id` ASC) VISIBLE,
     CONSTRAINT `mark_fk`
         FOREIGN KEY (`mark_id`)
             REFERENCES `mutual_learning_db`.`mark` (`id`)
             ON DELETE NO ACTION
             ON UPDATE NO ACTION,
     CONSTRAINT `article_fk`
         FOREIGN KEY (`article_id`)
             REFERENCES `mutual_learning_db`.`article` (`id`)
             ON DELETE CASCADE
             ON UPDATE CASCADE);

CREATE TABLE `mutual_learning_db`.`user_article` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `login` VARCHAR(45) NOT NULL,
     `article_id` INT NOT NULL,
     `role` VARCHAR(45) NOT NULL,
     `point` INT NULL,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
     INDEX `user_article_fk_idx` (`login` ASC) VISIBLE,
     INDEX `article_user_fk_idx` (`article_id` ASC) VISIBLE,
     CONSTRAINT `user_article_fk`
         FOREIGN KEY (`login`)
             REFERENCES `mutual_learning_db`.`user` (`login`)
             ON DELETE CASCADE
             ON UPDATE CASCADE,
     CONSTRAINT `article_user_fk`
         FOREIGN KEY (`article_id`)
             REFERENCES `mutual_learning_db`.`article` (`id`)
             ON DELETE CASCADE
             ON UPDATE CASCADE);

CREATE TABLE `mutual_learning_db`.`comment_part` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `comment_id` INT NOT NULL,
     `number` INT NOT NULL,
     `text` TEXT NOT NULL,
     `type` VARCHAR(45) NOT NULL,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
     INDEX `comment_fk_idx` (`comment_id` ASC) VISIBLE,
     CONSTRAINT `comment_fk`
         FOREIGN KEY (`comment_id`)
             REFERENCES `mutual_learning_db`.`comment` (`id`)
             ON DELETE CASCADE
             ON UPDATE CASCADE);

CREATE TABLE `mutual_learning_db`.`user_comment` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `login` VARCHAR(45) NOT NULL,
     `comment_id` INT NOT NULL,
     `role` VARCHAR(45) NOT NULL,
     `point` INT NULL,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
     INDEX `user_comment_fk_idx` (`login` ASC) VISIBLE,
     INDEX `comment_user_fk_idx` (`comment_id` ASC) VISIBLE,
     CONSTRAINT `user_comment_fk`
         FOREIGN KEY (`login`)
             REFERENCES `mutual_learning_db`.`user` (`login`)
             ON DELETE NO ACTION
             ON UPDATE NO ACTION,
     CONSTRAINT `comment_user_fk`
         FOREIGN KEY (`comment_id`)
             REFERENCES `mutual_learning_db`.`comment` (`id`)
             ON DELETE CASCADE
             ON UPDATE CASCADE);