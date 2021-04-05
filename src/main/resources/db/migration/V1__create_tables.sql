CREATE TABLE product
(
    id          bigserial        NOT NULL,
    name        varchar(100)     NOT NULL,
    price       double precision NOT NULL,
    is_discount boolean          NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE discount_card
(
    card_number varchar(4)       NOT NULL,
    discount    double precision NOT NULL,
    PRIMARY KEY (card_number)
);

CREATE TABLE "user"
(
    id       bigserial    NOT NULL,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role
(
    id        bigserial    NOT NULL,
    role_name varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    user_id bigint REFERENCES "user" (id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_id bigint REFERENCES role (id) ON UPDATE CASCADE,
    CONSTRAINT user_role_pkey PRIMARY KEY (role_id, user_id)
);

CREATE TABLE purchase
(
    user_id        bigint REFERENCES "user" (id) ON UPDATE CASCADE ON DELETE CASCADE,
    product_id     bigint REFERENCES product (id) ON UPDATE CASCADE ON DELETE CASCADE,
    product_number int NOT NULL,
    CONSTRAINT purchase_pkey PRIMARY KEY (user_id, product_id)
);
