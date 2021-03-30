CREATE TABLE products
(
    id          int              NOT NULL,
    name        varchar(100)     NOT NULL,
    price       double precision NOT NULL,
    is_discount boolean          NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE discount_cards
(
    card_number varchar(4)       NOT NULL,
    discount    double precision NOT NULL,
    PRIMARY KEY (card_number)
);
