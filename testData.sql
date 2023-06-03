INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('1', '2023-06-01 12:34:56', 'ACTIVE', 'Заголовок статті №1', 'ARTICLE');
INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('2', '2023-06-02 12:34:56', 'ACTIVE', 'Заголовок статті №2', 'ARTICLE');
INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('3', '2023-06-03 12:34:56', 'ACTIVE', 'Заголовок статті №3', 'QUESTION');
INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('4', '2023-06-03 12:44:56', 'ACTIVE', 'Заголовок статті №4', 'QUESTION');
INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('5', '2023-06-03 12:54:56', 'ACTIVE', 'Заголовок статті №5', 'ARTICLE');
INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('6', '2023-06-04 12:34:56', 'ACTIVE', 'Заголовок статті №6', 'ARTICLE');
INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('7', '2023-06-04 13:34:56', 'ACTIVE', 'Заголовок статті №7', 'ARTICLE');
INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('8', '2023-06-04 14:34:56', 'ACTIVE', 'Заголовок статті №8', 'ARTICLE');
INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('9', '2023-06-04 15:34:56', 'ACTIVE', 'Заголовок статті №9', 'ARTICLE');
INSERT INTO `mutual_learning_db`.`article` (`id`, `creation_date_time`, `status`, `title`, `type`) VALUES ('10', '2023-06-04 16:34:56', 'ACTIVE', 'Заголовок статті №10', 'ARTICLE');


INSERT INTO `mutual_learning_db`.`article_part` (`id`, `sequence_number`, `text`, `type`, `article_id`) VALUES ('1', '1', 'У сучасних мережевих інформаційних системах для роботи із загальною базою даних використовують архітектуру «клієнт-сервер». При цьому в мережі розміщують сервер баз даних. Ним виступає комп\'ютер (або комп\'ютери), який містить бази даних, СКБД та пов\'язане з ними програмне забезпечення, і налаштований для надання користувачам інформаційної системи доступу до бази даних. Клієнти, які працюють із даними (вони можуть бути розташовані на різних комп\'ютерах мережі), надсилають відповідні запити серверу. Сервер їх отримує, опрацьовує, та надсилає відповідь клієнту. Сучасні СКБД (MySQL, PostgreSQL, Microsoft SQL Server та інші) працюють відповідно до цієї архітектури. Сервер баз даних, як правило, є достатньо потужною багатопроцесорною системою, яка використовує масиви дисків RAID для підвищення надійності зберігання даних. Використання дискових масивів RAID дозволяє відновити дані, навіть якщо один з дисків вийшов з ладу. ', 'TEXT', '1');
INSERT INTO `mutual_learning_db`.`article_part` (`id`, `link`, `sequence_number`, `text`, `type`, `article_id`) VALUES ('2', '2-1.pdf', '2', 'This is PDF file!', 'FILE', '1');
INSERT INTO `mutual_learning_db`.`article_part` (`id`, `link`, `sequence_number`, `text`, `type`, `article_id`) VALUES ('3', '3-11.txt', '3', 'This is TXT file!', 'FILE', '1');
INSERT INTO `mutual_learning_db`.`article_part` (`id`, `link`, `sequence_number`, `type`, `article_id`) VALUES ('4', '4-1.png', '4', 'IMAGE', '1');
INSERT INTO `mutual_learning_db`.`article_part` (`id`, `sequence_number`, `text`, `type`, `article_id`) VALUES ('5', '5', ' <div style=\"margin-left: 5px; width: 10%;\">', 'CODE', '1');


INSERT INTO `mutual_learning_db`.`mark` (`id`, `creator`, `description`, `title`, `type`) VALUES ('1', 'moderator1', ' Сукупність даних, організованих відповідно до концепції, яка описує характеристику цих даних і взаємозв\'язки між їх елементами; ця сукупність підтримує щонайменше одну з областей застосування ', 'Database', 'SYSTEM');
INSERT INTO `mutual_learning_db`.`mark` (`id`, `creator`, `description`, `title`, `type`) VALUES ('2', 'user1', 'Сукупність організаційних і технічних засобів для збереження та обробки інформації з метою забезпечення інформаційних потреб користувачів', 'Інформаційна система', 'CUSTOM');
INSERT INTO `mutual_learning_db`.`mark` (`id`, `creator`, `description`, `title`, `type`) VALUES ('3', 'user2', 'Система зв\'язку між двома чи більше комп\'ютерами.', 'Комп\'ю́терна мере́жа', 'CUSTOM');
INSERT INTO `mutual_learning_db`.`mark` (`id`, `creator`, `description`, `title`, `type`) VALUES ('4', 'moderator1', 'Моніторинг мережі — робота системи, яка виконує постійне спостереження за комп\'ютерною мережею у пошуках повільних або несправних систем і яка при виявленні збоїв повідомляє про них мережевого адміністратора за допомогою пошти, пейджера або інших засобів сповіщення. Наприклад, для того, щоб визначити стан вебсервера, програма, що виконує моніторинг, може періодично відправляти запит HTTP на здобуття сторінки; для поштових серверів можна відправити тестове повідомлення по SMTP і отримати по IMAP або Pop3.', 'Моніторинг', 'SYSTEM');
INSERT INTO `mutual_learning_db`.`mark` (`id`, `creator`, `description`, `title`, `type`) VALUES ('5', 'user3', 'Spring Framework — це програмний каркас з відкритим кодом та контейнери з підтримкою інверсії управління для платформи Java. Основні особливості Spring Framework можуть бути використані будь-яким додатком Java, але є розширення для створення вебдодатків на платформі Java EE.', 'Spring Framework', 'CUSTOM');
INSERT INTO `mutual_learning_db`.`mark` (`id`, `creator`, `description`, `title`, `type`) VALUES ('6', 'moderator2', 'Java — це сукупність комп\'ютерного програмного забезпечення та інструментів, розроблених Джеймсом Гослінгом у компанії Sun Microsystems, що забезпечує систему для розробки програмного забезпечення та розгортання його в середовищі міжплатформних обчислень.', 'Java', 'SYSTEM');

INSERT INTO `mutual_learning_db`.`article_mark` (`id`, `article_id`, `mark_id`) VALUES ('1', '1', '1');
INSERT INTO `mutual_learning_db`.`article_mark` (`id`, `article_id`, `mark_id`) VALUES ('2', '1', '2');
INSERT INTO `mutual_learning_db`.`article_mark` (`id`, `article_id`, `mark_id`) VALUES ('3', '1', '3');
INSERT INTO `mutual_learning_db`.`article_mark` (`id`, `article_id`, `mark_id`) VALUES ('4', '1', '4');
INSERT INTO `mutual_learning_db`.`article_mark` (`id`, `article_id`, `mark_id`) VALUES ('5', '2', '3');
INSERT INTO `mutual_learning_db`.`article_mark` (`id`, `article_id`, `mark_id`) VALUES ('6', '2', '4');
INSERT INTO `mutual_learning_db`.`article_mark` (`id`, `article_id`, `mark_id`) VALUES ('7', '2', '6');
INSERT INTO `mutual_learning_db`.`article_mark` (`id`, `article_id`, `mark_id`) VALUES ('8', '3', '1');
INSERT INTO `mutual_learning_db`.`article_mark` (`id`, `article_id`, `mark_id`) VALUES ('9', '3', '2');

INSERT INTO `mutual_learning_db`.`user` (`login`, `email`, `info`, `name`, `password`, `role`, `status`) VALUES ('admin', 'email@gmail.com', 'Адміністратор системи', 'Admiin', 'password', 'ADMIN', 'ACTIVE');
INSERT INTO `mutual_learning_db`.`user` (`login`, `email`, `info`, `name`, `password`, `role`, `status`) VALUES ('moderator1', 'email@gmail.com', 'Модератор системи', 'Moderator', 'password', 'MODERATOR', 'ACTIVE');
INSERT INTO `mutual_learning_db`.`user` (`login`, `email`, `info`, `name`, `password`, `role`, `status`) VALUES ('moderator2', 'email@gmail.com', 'Модератор системи', 'Moderator', 'password', 'MODERATOR', 'ACTIVE');
INSERT INTO `mutual_learning_db`.`user` (`login`, `email`, `info`, `name`, `password`, `role`, `status`) VALUES ('user1', 'email@gmail.com', 'Люблю читати', 'Anna', 'password', 'USER', 'ACTIVE');
INSERT INTO `mutual_learning_db`.`user` (`login`, `email`, `info`, `name`, `password`, `role`, `status`) VALUES ('user2', 'email@gmail.com', 'У вільний час пишу вірші', 'Юлія', 'password', 'USER', 'ACTIVE');
INSERT INTO `mutual_learning_db`.`user` (`login`, `email`, `info`, `name`, `password`, `role`, `status`) VALUES ('user3', 'email@gmail.com', 'Навчаюсь в університеті', 'Юрій', 'password', 'USER', 'ACTIVE');

INSERT INTO `mutual_learning_db`.`user_article` (`id`, `role`, `article_id`, `user_login`) VALUES ('1', 'ARTICLE_CREATOR', '1', 'user1');
INSERT INTO `mutual_learning_db`.`user_article` (`id`, `role`, `article_id`, `user_login`) VALUES ('2', 'QUESTION_CREATOR', '2', 'user1');
INSERT INTO `mutual_learning_db`.`user_article` (`id`, `role`, `article_id`, `user_login`) VALUES ('3', 'QUESTION_ANSWERER', '2', 'user2');


