
create table USERS
(
    ID        NUMBER generated as identity
        constraint USERS_PK
            primary key,
    FIRSTNAME VARCHAR2(20) not null,
    LASTNAME  VARCHAR2(20) not null,
    EMAIL     VARCHAR2(35) not null,
    PASSWORD  VARCHAR2(80) not null,
    ROLE_ID   NUMBER
        constraint USERS_ROLES_ROLE_ID_FK
            references ROLES
                on delete cascade
)
/

create table ROLES
(
    ROLE_ID   NUMBER       not null
        constraint ROLES_PK
            primary key,
    ROLE_NAME VARCHAR2(20) not null
)
/

