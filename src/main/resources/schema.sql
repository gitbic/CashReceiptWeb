CREATE TABLE product
(
    id          bigserial,
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
    id          bigserial,
    username    varchar(100) NOT NULL,
    password    varchar(100) NOT NULL,
    card_number varchar(4),
    PRIMARY KEY (id),
    FOREIGN KEY (card_number) REFERENCES discount_card (card_number)
);

CREATE TABLE role
(
    id        bigserial,
    role_name varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);

CREATE TABLE purchase
(
    user_id        bigint NOT NULL,
    product_id     bigint NOT NULL,
    product_number int    NOT NULL,
    CONSTRAINT purchase_pkey PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);