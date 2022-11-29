--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0 (Debian 15.0-1.pgdg110+1)
-- Dumped by pg_dump version 15.0 (Debian 15.0-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: task; Type: TABLE; Schema: public; Owner: user_name
--

CREATE TABLE public.task (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    name text NOT NULL,
    id_user uuid
);


ALTER TABLE public.task OWNER TO user_name;

--
-- Name: task_detail; Type: TABLE; Schema: public; Owner: user_name
--

CREATE TABLE public.task_detail (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    name text NOT NULL,
    "time" timestamp without time zone,
    due_time timestamp without time zone,
    description text,
    status text,
    "row" text,
    id_task uuid,
    id_user uuid
);


ALTER TABLE public.task_detail OWNER TO user_name;

--
-- Name: user_account; Type: TABLE; Schema: public; Owner: user_name
--

CREATE TABLE public.user_account (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    is_admin boolean DEFAULT false
);


ALTER TABLE public.user_account OWNER TO user_name;

--
-- Name: user_token; Type: TABLE; Schema: public; Owner: user_name
--

CREATE TABLE public.user_token (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    id_user uuid,
    token text
);


ALTER TABLE public.user_token OWNER TO user_name;

--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: user_name
--

COPY public.task (id, name, id_user) FROM stdin;
027e2130-1b31-47b5-9819-bb01d65bf1d0	To Do	35cb41dd-3edb-4483-a1fe-fc315243d2f8
9cf6f7d4-598a-429c-8d80-979a20a07e25	In Progress	35cb41dd-3edb-4483-a1fe-fc315243d2f8
0413d760-402b-47fd-970e-e7d4e02fb20c	Error	35cb41dd-3edb-4483-a1fe-fc315243d2f8
5bd34224-6c1a-4336-b337-864f288fba4c	Done	35cb41dd-3edb-4483-a1fe-fc315243d2f8
\.


--
-- Data for Name: task_detail; Type: TABLE DATA; Schema: public; Owner: user_name
--

COPY public.task_detail (id, name, "time", due_time, description, status, "row", id_task, id_user) FROM stdin;
\.


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: user_name
--

COPY public.user_account (id, username, password, is_admin) FROM stdin;
35cb41dd-3edb-4483-a1fe-fc315243d2f8	gustavo	fa6ad6d4525528338ba5b0ccd695bfd94c5f1b7bb1cf8257d7dd80f010c20c97	t
\.


--
-- Name: task_detail task_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: user_name
--

ALTER TABLE ONLY public.task_detail
    ADD CONSTRAINT task_detail_pkey PRIMARY KEY (id);


--
-- Name: task task_pkey; Type: CONSTRAINT; Schema: public; Owner: user_name
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: user_name
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);


--
-- Name: user_token user_token_pkey; Type: CONSTRAINT; Schema: public; Owner: user_name
--

ALTER TABLE ONLY public.user_token
    ADD CONSTRAINT user_token_pkey PRIMARY KEY (id);


--
-- Name: task_detail task_detail_id_task_fkey; Type: FK CONSTRAINT; Schema: public; Owner: user_name
--

ALTER TABLE ONLY public.task_detail
    ADD CONSTRAINT task_detail_id_task_fkey FOREIGN KEY (id_task) REFERENCES public.task(id);


--
-- Name: task_detail task_detail_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: user_name
--

ALTER TABLE ONLY public.task_detail
    ADD CONSTRAINT task_detail_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.user_account(id);


--
-- Name: task task_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: user_name
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.user_account(id);


--
-- Name: user_token user_token_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: user_name
--

ALTER TABLE ONLY public.user_token
    ADD CONSTRAINT user_token_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.user_account(id);


--
-- PostgreSQL database dump complete
--

