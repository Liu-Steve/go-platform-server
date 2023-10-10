-- 创建用户表
create table GO_USER
(
    ID                BIGINT auto_increment,
    USERNAME          varchar(50) not null,
    PASSWORD          varchar(50) not null,
    EMAIL             varchar(50),
    SALT              BIGINT not null,
    STATUS            BIGINT not null default 0,
    CREATED_DATE      date,
    UPDATED_DATE      date,
    PRIMARY KEY (ID)
);

-- 创建对战表
create table GO_GAME_LOG
(
    ID                  BIGINT auto_increment,
    USER_ID             BIGINT not null,
    BEGIN_DATE          date,
    OPPONENT_ID         BIGINT not null,
    OPPONENT_NAME       varchar(50),
    COLOR               BIGINT,
    CHESSBOARD          varchar(500),
    RESULT              BIGINT not null,
    primary key (ID)
);