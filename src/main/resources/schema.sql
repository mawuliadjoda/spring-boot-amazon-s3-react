CREATE TABLE IF NOT EXISTS "user" (
   id INT NOT NULL,
   uid varchar(250) NOT NULL,
   first_name varchar(250) NOT NULL,
   last_name varchar(250) NOT NULL,
   phone_number varchar(250) NOT NULL,
   PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS post
(
    post_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    caption character varying(255) COLLATE pg_catalog."default",
    distance_zero double precision NOT NULL,
    image_id character varying(255) COLLATE pg_catalog."default",
    image_id2 character varying(255) COLLATE pg_catalog."default",
    image_id3 character varying(255) COLLATE pg_catalog."default",
    image_url character varying(255) COLLATE pg_catalog."default",
    image_url2 character varying(255) COLLATE pg_catalog."default",
    image_url3 character varying(255) COLLATE pg_catalog."default",
    location character varying(255) COLLATE pg_catalog."default",
    tags character varying(255) COLLATE pg_catalog."default",
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    user_id character varying(255) COLLATE pg_catalog."default",
    user_tel character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT post_pkey PRIMARY KEY (post_id)
);