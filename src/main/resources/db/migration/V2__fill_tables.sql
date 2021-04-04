INSERT INTO discount_card
VALUES ('1234', 0.25),
       ('2431', 1.3),
       ('4132', 1.87),
       ('3421', 2.12),
       ('1423', 0.89);

INSERT INTO product
VALUES (28, 'Apple', 1.12, true),
       (30, 'Watermelon', 2.05, false),
       (8, 'Orange', 0.99, false),
       (19, 'Pear', 0.85, true),
       (26, 'Cherry', 3.18, false),
       (39, 'Strawberry', 5.2, true),
       (35, 'Nectarine', 3.17, false),
       (23, 'Grape', 2.88, true),
       (16, 'Mango', 6.05, true),
       (10, 'Blueberry', 2.89, false),
       (5, 'Pomegranate', 0.43, false),
       (20, 'Lemon', 4.12, true),
       (31, 'Plum', 12.63, true),
       (14, 'Banana', 1.47, false),
       (27, 'Raspberry', 2.71, true),
       (9, 'Mandarin', 6.25, false),
       (17, 'Jackfruit', 3.81, false),
       (6, 'Papaya', 4.93, true),
       (36, 'Kiwi', 5.52, true),
       (12, 'Pineapple', 1.08, false);

INSERT INTO "user"
VALUES (1, 'user1', '$2a$10$rbcr6uxKMJ8BAfBr42yqW.XqqQzhAHZxtqwmj/0OdP1snVbq4zkV.'),
       (2, 'user2', '$2a$10$rbcr6uxKMJ8BAfBr42yqW.XqqQzhAHZxtqwmj/0OdP1snVbq4zkV.'),
       (3, 'user3', '$2a$10$rbcr6uxKMJ8BAfBr42yqW.XqqQzhAHZxtqwmj/0OdP1snVbq4zkV.');

INSERT INTO "role"
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

INSERT INTO "user_role"
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 2);

