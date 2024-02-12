CREATE TABLE organization(
    id bigint IDENTITY not null,
    active BIT,
    created_time datetime2(7)null,
    modifiedTime datetime2(7)null,
    referencerId varchar(255),
    organizationName varchar(255),
    password varchar(255),
    primary key (id)

);