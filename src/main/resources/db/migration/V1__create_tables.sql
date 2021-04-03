CREATE TABLE product
(
    id          bigint           NOT NULL,
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
    id               bigint       NOT NULL,
    username         varchar(100) NOT NULL,
    password         varchar(100) NOT NULL,
    password_confirm varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role
(
    id        bigint       NOT NULL,
    role_name varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role_user
(
    role_id bigint REFERENCES role (id) ON UPDATE CASCADE ON DELETE CASCADE,
    user_id bigint REFERENCES "user" (id) ON UPDATE CASCADE,
    CONSTRAINT pkey PRIMARY KEY (role_id, user_id)
);
