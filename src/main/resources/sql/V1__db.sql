create sequence hibernate_sequence;

create table items
(
    id          bigint         not null
        primary key,
    description varchar(255),
    price       numeric(19, 2) not null,
    title       varchar(255)
        constraint uk_rptahm7f468px5em81fhm1dg0
            unique
);

create table storehouse
(
    id       bigint  not null
        primary key,
    quantity integer not null
        constraint storehouse_quantity_check
            check (quantity >= 0),
    item_id  bigint  not null
        constraint uk_8cm0xasaf3peb3svoem4x63oe
            unique
        constraint fk3tmp43ibdmaxgu6oic6orfb36
            references items
);


create table users
(
    id      bigserial
        primary key,
    name    varchar(255),
    surname varchar(255)
);

create table orders
(
    id      bigserial
        primary key,
    user_id bigint
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users
);

create table order_item
(
    id       bigserial
        primary key,
    quantity integer not null
        constraint order_item_quantity_check
            check (quantity >= 0),
    item_id  bigint
        constraint fko5d8io03ue2y89j3wbnju0let
            references items,
    order_id bigint
        constraint fkt4dc2r9nbvbujrljv3e23iibt
            references orders
);

