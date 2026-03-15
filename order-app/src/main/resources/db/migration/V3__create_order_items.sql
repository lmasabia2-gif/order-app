create table order_items (
    id uuid primary key,
    order_id uuid not null,
    product_id varchar(100) not null,
    product_name_snapshot varchar(255) not null,
    quantity integer not null,
    unit_price numeric(19,2) not null,
    line_total numeric(19,2) not null,
    created_at timestamp with time zone not null,
    constraint fk_order_items_order foreign key (order_id) references orders(id) on delete cascade
);

create index idx_order_items_order_id on order_items(order_id);
