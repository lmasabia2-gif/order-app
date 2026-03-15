create table order_status_history (
    id uuid primary key,
    order_id uuid not null,
    old_status varchar(40),
    new_status varchar(40) not null,
    changed_at timestamp with time zone not null,
    reason varchar(500),
    constraint fk_order_status_history_order foreign key (order_id) references orders(id) on delete cascade
);

create index idx_order_status_history_order_id on order_status_history(order_id);
