CREATE TABLE public.author (
	id bigserial NOT NULL,
	"name" varchar(100) NOT NULL,
	CONSTRAINT author_pkey PRIMARY KEY (id)
);


CREATE TABLE public.book (
	id bigserial NOT NULL,
	title varchar(50) NOT NULL,
	date_added timestamp NOT NULL,
	CONSTRAINT book_pkey PRIMARY KEY (id)
);


CREATE TABLE public.author_book (
	author_id int8 NOT NULL,
	book_id int8 NOT NULL,
	CONSTRAINT fk123vmbirb0ho9824t7cw6syol FOREIGN KEY (book_id) REFERENCES public.book(id),
	CONSTRAINT fkioufdohwqqxemkciuruoq9269 FOREIGN KEY (author_id) REFERENCES public.author(id)
);