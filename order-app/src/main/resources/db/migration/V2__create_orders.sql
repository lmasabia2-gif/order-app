create table orders (
    id uuid primary key,
    order_number varchar(50) not null unique,
    customer_id uuid not null,
    status varchar(40) not null,
    subtotal_amount numeric(19,2) not null,
    tax_amount numeric(19,2) not null,
    shipping_amount numeric(19,2) not null,
    discount_amount numeric(19,2) not null,
    total_amount numeric(19,2) not null,
    currency varchar(3) not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone,
    version integer not null,
    constraint fk_orders_customer foreign key (customer_id) references customers(id)
);

create index idx_orders_customer_id on orders(customer_id);
create index idx_orders_status on orders(status);
