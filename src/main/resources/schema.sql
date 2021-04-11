INSERT INTO discount_card
VALUES ('1234', 0.25),
       ('2431', 1.3),
       ('4132', 1.87),
       ('3421', 2.12),
       ('1423', 0.89);

INSERT INTO product(name, price, is_discount)
VALUES ('Apple', 1.12, true),
       ('Watermelon', 2.05, false),
       ('Orange', 0.99, false),
       ('Pear', 0.85, true),
       ('Cherry', 3.18, false),
       ('Strawberry', 5.2, true),
       ('Nectarine', 3.17, false),
       ('Grape', 2.88, true),
       ('Mango', 6.05, true),
       ('Blueberry', 2.89, false),
       ('Pomegranate', 0.43, false),
       ('Lemon', 4.12, true),
       ('Plum', 12.63, true),
       ('Banana', 1.47, false),
       ('Raspberry', 2.71, true),
       ('Mandarin', 6.25, false),
       ('Jackfruit', 3.81, false),
       ('Papaya', 4.93, true),
       ('Kiwi', 5.52, true),
       ('Pineapple', 1.08, false);

INSERT INTO "user" (username, password, card_number)
VALUES ('user1', '$2a$10$rbcr6uxKMJ8BAfBr42yqW.XqqQzhAHZxtqwmj/0OdP1snVbq4zkV.', '1234'),
       ('user2', '$2a$10$rbcr6uxKMJ8BAfBr42yqW.XqqQzhAHZxtqwmj/0OdP1snVbq4zkV.', '2431'),
       ('user3', '$2a$10$rbcr6uxKMJ8BAfBr42yqW.XqqQzhAHZxtqwmj/0OdP1snVbq4zkV.', '4132');

INSERT INTO "role" (role_name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO "user_role"
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 2);

