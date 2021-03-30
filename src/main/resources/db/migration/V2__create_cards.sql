CREATE TABLE discount_cards
(
    card_number varchar(4)       NOT NULL,
    discount    double precision NOT NULL,
    PRIMARY KEY (card_number)
);

INSERT INTO discount_cards
VALUES ('1234', 0.25),
       ('2431', 1.3),
       ('4132', 1.87),
       ('3421', 2.12),
       ('1423', 0.89);


