DROP TABLE if EXISTS go_user_roles;
DROP TABLE if EXISTS go_user;
DROP TABLE if EXISTS go_game_log;
-- 创建用户表
create table GO_USER
(
    ID                BIGINT not null ,
    USERNAME          varchar(255) not null,
    PASSWORD          varchar(255) not null,
    EMAIL             varchar(255),
    STATUS            INT not null default 0,
    CREATED_DATE      date,
    UPDATED_DATE      date,

    PRIMARY KEY (ID)
);

-- 创建对战表
create table GO_GAME_LOG
(
    ID                  BIGINT not null ,
    USER_ID             BIGINT not null,
    BEGIN_DATE          date,
    OPPONENT_ID         BIGINT not null,
    OPPONENT_NAME       varchar(255),
    COLOR               BIGINT,
    CHESSBOARD          varchar(2048),
    RESULT              BIGINT not null,
    primary key (ID)
);