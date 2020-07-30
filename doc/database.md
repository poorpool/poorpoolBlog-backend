# 数据库设计

t_user

```
-- auto-generated definition
create table t_user
(
    user_id            bigint auto_increment comment '用户id',
    user_ip            varchar(64)  null comment '上次登录ip',
    user_name          varchar(64)  not null comment '用户登录名',
    user_password      varchar(100) not null comment 'sha512加密后的密码',
    user_nickname      varchar(64)  not null comment '用户昵称',
    user_email         varchar(255) not null comment '用户邮箱',
    user_avatar        varchar(255) null comment '用户头像路径',
    user_register_date datetime     null comment '注册时间',
    constraint t_user_user_id_uindex
        unique (user_id),
    constraint t_user_user_name_uindex
        unique (user_name)
)
    comment '用户表';

alter table t_user
    add primary key (user_id);
```
t_article

```
-- auto-generated definition
create table t_article
(
    article_id            bigint auto_increment comment '博文id',
    user_id               bigint   not null comment '发表者id',
    article_date          datetime null comment '发表时间',
    article_title         text     null comment '博文标题',
    article_content       longtext null comment '博文正文',
    article_comment_count int      null comment '博文评论数',
    article_like_count    bigint   null comment '博文点赞数',
    constraint t_article_article_id_uindex
        unique (article_id)
)
    comment '博文表';

alter table t_article
    add primary key (article_id);


```

t_comment

```
-- auto-generated definition
create table t_comment
(
    comment_id      bigint auto_increment comment '评论id',
    user_id         bigint           not null comment '评论者id',
    article_id      bigint           not null comment '评论的博客id',
    comment_like_count    bigint default 0 null comment '点赞数',
    comment_date    datetime         null comment '评论时间',
    comment_content text             null comment '评论内容',
    parent_id       bigint           null comment '父评论id',
    constraint t_comment_comment_id_uindex
        unique (comment_id)
)
    comment '评论表';

alter table t_comment
    add primary key (comment_id);

```

t_category

```
-- auto-generated definition
create table t_category
(
    category_id   bigint auto_increment comment '分类id',
    category_name varchar(255) null comment '分类名',
    parent_id     bigint       null comment '父分类id',
    constraint t_category_category_id_uindex
        unique (category_id)
)
    comment '分类表';

alter table t_category
    add primary key (category_id);

```

t_article_category

```
-- auto-generated definition
create table t_article_category
(
    id          bigint auto_increment comment '唯一的键',
    article_id  bigint not null comment '文章id',
    category_id bigint not null comment '分类id',
    constraint t_article_category_id_uindex
        unique (id)
)
    comment '文章分类表';

alter table t_article_category
    add primary key (id);

```

t_label

```
create table t_label
(
    label_id   bigint auto_increment primary key comment '标签id',
    label_name varchar(255) null comment '标签名',
    constraint t_label_label_id_uindex
        unique (label_id)
)
    comment '标签表';
```

t_article_label

```
create table t_article_label
(
    id          bigint auto_increment primary key comment '唯一的键',
    article_id  bigint not null comment '文章id',
    label_id bigint not null comment '标签id',
    constraint t_article_label_id_uindex
        unique (id)
)
    comment '文章分类表';
```