CREATE TABLE t_user(
                       ID INT NOT NULL,
                       USER_NAME VARCHAR(255),
                       PASS_WORD VARCHAR(255),
                       NICK_NAME VARCHAR(255),
                       REVISION INT,
                       CREATED_BY INT,
                       CREATED_TIME DATE,
                       UPDATED_BY INT,
                       UPDATED_TIME DATE,
                       PRIMARY KEY (ID)
);

COMMENT ON TABLE t_user IS '用户表';
COMMENT ON COLUMN t_user.ID IS 'id';
COMMENT ON COLUMN t_user.USER_NAME IS '用户姓名';
COMMENT ON COLUMN t_user.PASS_WORD IS '用户密码';
COMMENT ON COLUMN t_user.NICK_NAME IS '用户昵称';
COMMENT ON COLUMN t_user.REVISION IS '乐观锁';
COMMENT ON COLUMN t_user.CREATED_BY IS '创建人';
COMMENT ON COLUMN t_user.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN t_user.UPDATED_BY IS '更新人';
COMMENT ON COLUMN t_user.UPDATED_TIME IS '更新时间';

CREATE TABLE t_classic(
                          ID INT NOT NULL,
                          fav_nums INT,
                          content VARCHAR(900),
                          image VARCHAR(255),
                          index VARCHAR(255),
                          pubdate DATE,
                          title VARCHAR(255),
                          type VARCHAR(32),
                          REVISION INT,
                          CREATED_BY INT,
                          CREATED_TIME DATE,
                          UPDATED_BY INT,
                          UPDATED_TIME DATE,
                          PRIMARY KEY (ID)
);

COMMENT ON TABLE t_classic IS '期刊';
COMMENT ON COLUMN t_classic.ID IS '租户号';
COMMENT ON COLUMN t_classic.fav_nums IS '喜欢人数';
COMMENT ON COLUMN t_classic.content IS '内容';
COMMENT ON COLUMN t_classic.image IS '图片url';
COMMENT ON COLUMN t_classic.index IS '期号';
COMMENT ON COLUMN t_classic.pubdate IS '发布日期';
COMMENT ON COLUMN t_classic.title IS '标题';
COMMENT ON COLUMN t_classic.type IS '类型';
COMMENT ON COLUMN t_classic.REVISION IS '乐观锁';
COMMENT ON COLUMN t_classic.CREATED_BY IS '创建人';
COMMENT ON COLUMN t_classic.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN t_classic.UPDATED_BY IS '更新人';
COMMENT ON COLUMN t_classic.UPDATED_TIME IS '更新时间';

CREATE TABLE t_user_classic(
                               ID INT NOT NULL,
                               USER_ID VARCHAR2(255),
                               CLASSIC_ID VARCHAR2(255),
                               IS_FAV VARCHAR2(1),
                               REVISION INT,
                               CREATED_BY INT,
                               CREATED_TIME DATE,
                               UPDATED_BY INT,
                               UPDATED_TIME DATE,
                               PRIMARY KEY (ID)
);

COMMENT ON TABLE t_user_classic IS '用户喜欢表';
COMMENT ON COLUMN t_user_classic.ID IS 'id';
COMMENT ON COLUMN t_user_classic.USER_ID IS '用户ID';
COMMENT ON COLUMN t_user_classic.CLASSIC_ID IS '期刊ID';
COMMENT ON COLUMN t_user_classic.IS_FAV IS '是否喜欢';
COMMENT ON COLUMN t_user_classic.REVISION IS '乐观锁';
COMMENT ON COLUMN t_user_classic.CREATED_BY IS '创建人';
COMMENT ON COLUMN t_user_classic.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN t_user_classic.UPDATED_BY IS '更新人';
COMMENT ON COLUMN t_user_classic.UPDATED_TIME IS '更新时间';

CREATE TABLE t_book(
                       ID INT NOT NULL,
                       author VARCHAR2(255),
                       fav_nums INT,
                       image VARCHAR2(255),
                       title VARCHAR2(255),
                       old_id INT,
                       REVISION INT,
                       CREATED_BY INT,
                       CREATED_TIME DATE,
                       UPDATED_BY INT,
                       UPDATED_TIME DATE,
                       PRIMARY KEY (ID)
);

COMMENT ON TABLE t_book IS '书籍表';
COMMENT ON COLUMN t_book.ID IS 'id';
COMMENT ON COLUMN t_book.author IS '作者';
COMMENT ON COLUMN t_book.fav_nums IS '喜欢人数';
COMMENT ON COLUMN t_book.image IS '图片地址';
COMMENT ON COLUMN t_book.title IS '书籍标题';
COMMENT ON COLUMN t_book.old_id IS 'oldid';
COMMENT ON COLUMN t_book.REVISION IS '乐观锁';
COMMENT ON COLUMN t_book.CREATED_BY IS '创建人';
COMMENT ON COLUMN t_book.CREATED_TIME IS '创建时间';
COMMENT ON COLUMN t_book.UPDATED_BY IS '更新人';
COMMENT ON COLUMN t_book.UPDATED_TIME IS '更新时间';


