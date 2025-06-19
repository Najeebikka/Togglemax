--
-- PostgreSQL database dump
--

-- Dumped from database version 14.18 (Homebrew)
-- Dumped by pg_dump version 14.18 (Homebrew)

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


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: candidate; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.candidate (
    id uuid NOT NULL,
    photo character varying(255),
    resume character varying(255),
    user_id uuid
);


ALTER TABLE public.candidate OWNER TO myuser;

--
-- Name: email_templates; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.email_templates (
    id uuid NOT NULL,
    category character varying(255) NOT NULL,
    email_body text NOT NULL,
    subject character varying(255) NOT NULL,
    template_name character varying(255) NOT NULL,
    CONSTRAINT email_templates_category_check CHECK (((category)::text = ANY ((ARRAY['INTERVIEW_INVITATION'::character varying, 'REJECTION'::character varying, 'SHORTLIST_CONFIRMATION'::character varying, 'OFFER_LETTER'::character varying, 'GENERAL_COMMUNICATION'::character varying])::text[])))
);


ALTER TABLE public.email_templates OWNER TO myuser;

--
-- Name: interview; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.interview (
    id uuid NOT NULL,
    interview_status character varying(255),
    scheduled_date timestamp(6) without time zone,
    shortlisted_status character varying(255),
    token character varying(255),
    candidate_id uuid,
    interviewer_id uuid,
    job_id uuid,
    CONSTRAINT interview_interview_status_check CHECK (((interview_status)::text = ANY ((ARRAY['NOT_SCHEDULED'::character varying, 'SCHEDULED'::character varying, 'COMPLETED'::character varying])::text[]))),
    CONSTRAINT interview_shortlisted_status_check CHECK (((shortlisted_status)::text = ANY ((ARRAY['PENDING'::character varying, 'SHORTLISTED'::character varying, 'REJECTED'::character varying])::text[])))
);


ALTER TABLE public.interview OWNER TO myuser;

--
-- Name: interview_question; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.interview_question (
    id uuid NOT NULL,
    interview_id uuid,
    question_id uuid
);


ALTER TABLE public.interview_question OWNER TO myuser;

--
-- Name: interview_result; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.interview_result (
    id uuid NOT NULL,
    uploaded_at timestamp(6) without time zone,
    video_url character varying(255),
    interview_id uuid
);


ALTER TABLE public.interview_result OWNER TO myuser;

--
-- Name: job; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.job (
    id uuid NOT NULL,
    count integer,
    job_title character varying(255),
    job_type character varying(255),
    posted_date date,
    status character varying(255),
    locations character varying(255),
    interviewer_id uuid
);


ALTER TABLE public.job OWNER TO myuser;

--
-- Name: question; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.question (
    id uuid NOT NULL,
    answer character varying(255),
    question character varying(255),
    subject character varying(255)
);


ALTER TABLE public.question OWNER TO myuser;

--
-- Name: users; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255),
    password character varying(255),
    role character varying(255),
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'INTERVIEWER'::character varying, 'CANDIDATE'::character varying])::text[])))
);


ALTER TABLE public.users OWNER TO myuser;

--
-- Data for Name: candidate; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.candidate (id, photo, resume, user_id) FROM stdin;
ee99ba26-0d33-4a14-bf44-97ad19010e29	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHDRlp-KGr_M94k_oor4Odjn2UzbAS7n1YoA&s		8d06f505-478b-47da-852d-fc6187f59e8b
5c50b4e2-8f25-4740-bc85-5dffac8636a0	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsynwv-5qtogtOwJbIjaPFJUmHpzhxgqIAug&s		24afe73c-f8c0-4c50-905c-cdd263301fc8
44555a3e-6957-4d95-84a1-3639f93dfcbd	data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxAPEA8Q...		840a20fe-63ee-4463-a26c-cbd9001b07de
76c84df3-a45c-43a3-aeaa-8378a3feb57f	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7nstASo8BdadWs3X-ji8e1O0hd5AMByZdGQ&s		15d555cf-f6a6-40b3-9c28-e8cf21ca2113
099faa9d-729f-4ca5-a29c-7a26c7918775	data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUSEhIW...		dbbb57a3-7036-4311-a649-01602dd8a2ad
6839367f-518b-4c73-98f4-6c1587374d56	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHDRlp-KGr_M94k_oor4Odjn2UzbAS7n1YoA&s		d3e03ca6-34a9-4833-8ba0-b93b6dc02626
0a9c6146-7493-4946-800b-6da5514850bb	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsynwv-5qtogtOwJbIjaPFJUmHpzhxgqIAug&s		29ae3052-52a5-4cbb-9b5a-f2e47f017612
9b14e5a1-5de7-4efa-8463-7ab3f8b3a594	data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxAPEA8Q...		2f71a345-85f5-423b-b040-2041d6e59359
c58b2296-5a5f-4bcc-88de-192341940ad8	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7nstASo8BdadWs3X-ji8e1O0hd5AMByZdGQ&s		bcb91296-2033-424b-a798-396d4b307255
56606da6-48cf-46d8-8fd4-5b91ea9b5298	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsynwv-5qtogtOwJbIjaPFJUmHpzhxgqIAug&s		e98f3c78-cbb7-4024-a2a2-1bf7903f8614
98485795-fb86-4d2b-a790-426d71d82cfc	data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUSEhIW...		89814a96-337d-4484-a4ec-b0a87470cfac
71cb1eca-3770-4097-986a-a9611457d508	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHDRlp-KGr_M94k_oor4Odjn2UzbAS7n1YoA&s		475dd45b-5e5f-45bd-9d19-c599092b21dc
c51609d1-a846-4c7b-ab5f-0bba84769fc1	https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHDRlp-KGr_M94k_oor4Odjn2UzbAS7n1YoA&s		3914e73a-dbb5-4ef9-9b7c-d177131a2b3f
\.


--
-- Data for Name: email_templates; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.email_templates (id, category, email_body, subject, template_name) FROM stdin;
7b729d12-deb0-4283-819f-f35096aa6d30	REJECTION	Dear Candidate,\n\nThank you for taking the time to apply and interview for the position at our company. After a thorough review and consideration of your application and qualifications, we regret to inform you that we have decided to move forward with other candidates for this role. We appreciate your interest in our organization and encourage you to apply for future opportunities that match your experience.\n\nWishing you success in your job search.\nBest regards,\nHR Team	Regarding Your Job Application	Rejection Notice
7865c207-eec8-492c-9e3c-364038268105	SHORTLIST_CONFIRMATION	Dear Candidate,\n\nWe are excited to inform you that you have been shortlisted for the next stage of our hiring process. Your qualifications and experience impressed our recruitment team. We will contact you shortly to schedule the next round of assessments. Please ensure your contact information is up to date and check your inbox for updates from us.\n\nThank you for your interest in our company.\nBest regards,\nHR Team	You Have Been Shortlisted!	Shortlist Confirmation
cab5ee41-cdc0-46a1-9ece-0a6800d831b1	OFFER_LETTER	Dear Candidate,\n\nWe are thrilled to extend to you a formal offer of employment for the position you applied for. Attached to this email, you will find the official offer letter outlining the terms and conditions of your employment including salary, job responsibilities, benefits, and joining date. We kindly request you to review the document thoroughly and respond with your decision within the stipulated time.\n\nCongratulations and welcome aboard!\nWarm regards,\nHR Team	Official Offer Letter from [Company]	Job Offer
b3c7c6d7-1de6-41f4-88e0-944f816514a4	INTERVIEW_INVITATION	Dear Candidate,\n\nThank you for applying to our organization. We are pleased to invite you for the interview process for the position you applied for. The interview will be conducted via Zoom on the specified date and time. Kindly confirm your availability by replying to this email. Please have your resume, a valid ID, and a copy of your work portfolio (if applicable) ready at the time of the interview.\n\nBest regards,\nHR Team\n\n\nClick the link to attend your interview: {{token}}	Interview Invitation	Interview Invite
\.


--
-- Data for Name: interview; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.interview (id, interview_status, scheduled_date, shortlisted_status, token, candidate_id, interviewer_id, job_id) FROM stdin;
c511b453-ea52-4c46-be68-1207ddfe1fae	SCHEDULED	2025-06-26 21:19:00	PENDING	899397b9-a720-4adc-ba2f-bfbd9e407d10	98485795-fb86-4d2b-a790-426d71d82cfc	b2b74349-23de-4e76-b0d4-83fc55504eaf	a87350a6-b8a7-4602-9ab4-fbd9b38ae8d2
953bcc7a-9863-42a4-93cd-2e096f2d2804	NOT_SCHEDULED	2025-06-10 10:00:00	PENDING	TOK001	ee99ba26-0d33-4a14-bf44-97ad19010e29	b2b74349-23de-4e76-b0d4-83fc55504eaf	a9661139-ceb2-4de1-b5fd-76ab14143605
-- e0fcc857-0be0-4b68-a56e-55f34016ed1e	SCHEDULED	2025-06-11 14:30:00	SHORTLISTED	TOK002	5c50b4e2-8f25-4740-bc85-5dffac8636a0	b2b74349-23de-4e76-b0d4-83fc55504eaf	bf15d709-340d-4a65-942b-3f7a0cf7858a
-- 7eacb1aa-5a23-4db8-9204-cd256310dafb	COMPLETED	2025-06-12 09:00:00	REJECTED	TOK003	44555a3e-6957-4d95-84a1-3639f93dfcbd	b2b74349-23de-4e76-b0d4-83fc55504eaf	4985dde1-1d8e-469d-9103-9c71c38aa8cd
-- 5387f073-af49-4109-a942-778003318eb8	SCHEDULED	2025-06-13 11:15:00	PENDING	TOK004	76c84df3-a45c-43a3-aeaa-8378a3feb57f	b2b74349-23de-4e76-b0d4-83fc55504eaf	004bdd41-0700-4ff6-9bc5-b9a0b57ffead
-- 125467c4-c315-4787-bcae-a4d109ed0946	SCHEDULED	2025-06-15 10:30:00	REJECTED	TOK006	6839367f-518b-4c73-98f4-6c1587374d56	b2b74349-23de-4e76-b0d4-83fc55504eaf	a87350a6-b8a7-4602-9ab4-fbd9b38ae8d2
-- 41b46f65-8041-46d1-85b5-5ada38dd918e	NOT_SCHEDULED	2025-06-16 13:00:00	PENDING	TOK007	0a9c6146-7493-4946-800b-6da5514850bb	b2b74349-23de-4e76-b0d4-83fc55504eaf	f8ac8aa2-ec31-438f-b8a1-f3a8427d80ad
-- b20d786b-e57a-4d54-97c2-a9f6e582cbb4	SCHEDULED	2025-06-17 09:45:00	SHORTLISTED	TOK008	9b14e5a1-5de7-4efa-8463-7ab3f8b3a594	b2b74349-23de-4e76-b0d4-83fc55504eaf	66095459-8737-4c60-a60d-4ebf2bf404bb
-- dbdbceff-aea7-423b-ad15-dffc28ad339f	COMPLETED	2025-06-18 15:30:00	REJECTED	TOK009	c58b2296-5a5f-4bcc-88de-192341940ad8	b2b74349-23de-4e76-b0d4-83fc55504eaf	967c8cc7-4a23-4923-bb0a-28902deead7c
-- ef0680ea-0312-400c-9fa8-c399766dbfa3	SCHEDULED	2025-06-19 10:00:00	SHORTLISTED	TOK010	56606da6-48cf-46d8-8fd4-5b91ea9b5298	b2b74349-23de-4e76-b0d4-83fc55504eaf	f273e6a6-e6e8-4855-993b-3617ab78cd82
-- 7533de6e-6a0f-4c08-9316-6679c388b12d	NOT_SCHEDULED	2025-06-10 10:00:00	PENDING	TOK001	71cb1eca-3770-4097-986a-a9611457d508	b2b74349-23de-4e76-b0d4-83fc55504eaf	a9661139-ceb2-4de1-b5fd-76ab14143605
90c2bb40-3e51-45bf-8ccc-c9a261977f45	SCHEDULED	2025-06-28 22:53:00	PENDING	85468d0d-a406-485b-a8a7-b22ed9a455e5	c51609d1-a846-4c7b-ab5f-0bba84769fc1	b2b74349-23de-4e76-b0d4-83fc55504eaf	a9661139-ceb2-4de1-b5fd-76ab14143605
\.


--
-- Data for Name: interview_question; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.interview_question (id, interview_id, question_id) FROM stdin;
4a6bf589-8bdb-43a7-9e88-f4c4b0aa2b99	953bcc7a-9863-42a4-93cd-2e096f2d2804	43599101-f346-400e-b425-c8de494548ae
d6f4b4c9-a06b-4ff9-839a-2aedc7721550	953bcc7a-9863-42a4-93cd-2e096f2d2804	43599101-f346-400e-b425-c8de494548ae
c433f46b-31fb-49f9-ace7-5bbd3bd142fb	953bcc7a-9863-42a4-93cd-2e096f2d2804	43599101-f346-400e-b425-c8de494548ae
142a5898-0c87-427c-8e4c-caa0603e1ac2	953bcc7a-9863-42a4-93cd-2e096f2d2804	b719e9bd-38cd-4848-8f18-ef7e18bf492f
29a1e62f-96f1-4ef5-9d0e-3ba6445d6e04	90c2bb40-3e51-45bf-8ccc-c9a261977f45	43599101-f346-400e-b425-c8de494548ae
6b6324b7-c86b-4be1-bbc8-11797f16fe2d	c511b453-ea52-4c46-be68-1207ddfe1fae	43599101-f346-400e-b425-c8de494548ae
d8480e3b-48f2-4a6c-8a64-75ab3d13445d	c511b453-ea52-4c46-be68-1207ddfe1fae	dc18e189-70c9-40b0-95d7-c86e3926883a
82c73e4c-1229-446b-842d-b0ba034562a6	c511b453-ea52-4c46-be68-1207ddfe1fae	0ef311eb-b6b4-4bcf-9694-8b01a2a26594
\.


--
-- Data for Name: interview_result; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.interview_result (id, uploaded_at, video_url, interview_id) FROM stdin;
bef56aa4-3a5d-47e5-9e2b-ad30d952a845	2025-06-11 21:21:48.92628	uploads/74612051-c708-4860-a327-61ab64a74a52_blob	c511b453-ea52-4c46-be68-1207ddfe1fae
\.


--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.job (id, count, job_title, job_type, posted_date, status, locations, interviewer_id) FROM stdin;
f273e6a6-e6e8-4855-993b-3617ab78cd82	5	Backend Developer	FULL_TIME	2025-04-15	OPEN	Kochi	b2b74349-23de-4e76-b0d4-83fc55504eaf
bf15d709-340d-4a65-942b-3f7a0cf7858a	4	DevOps Engineer	HYBRID	2025-03-30	CLOSED	Delhi	b2b74349-23de-4e76-b0d4-83fc55504eaf
a9661139-ceb2-4de1-b5fd-76ab14143605	1	UI/UX Designer	INTERNSHIP	2025-05-20	OPEN	Delhi	b2b74349-23de-4e76-b0d4-83fc55504eaf
4985dde1-1d8e-469d-9103-9c71c38aa8cd	2	QA Tester	REMOTE	2025-05-05	OPEN	Delhi	b2b74349-23de-4e76-b0d4-83fc55504eaf
004bdd41-0700-4ff6-9bc5-b9a0b57ffead	3	Full Stack Developer	FULL_TIME	2025-05-18	DRAFT	Delhi	b2b74349-23de-4e76-b0d4-83fc55504eaf
13812e12-cf33-42ea-a345-8ba75cdcac16	2	Data Analyst	INTERNSHIP	2025-04-10	CLOSED	Delhi	b2b74349-23de-4e76-b0d4-83fc55504eaf
a87350a6-b8a7-4602-9ab4-fbd9b38ae8d2	1	Machine Learning Engineer	FULL_TIME	2025-05-07	OPEN	Delhi	b2b74349-23de-4e76-b0d4-83fc55504eaf
f8ac8aa2-ec31-438f-b8a1-f3a8427d80ad	1	Product Manager	HYBRID	2025-05-03	OPEN	Delhi	b2b74349-23de-4e76-b0d4-83fc55504eaf
66095459-8737-4c60-a60d-4ebf2bf404bb	1	Product Manager	HYBRID	2025-05-03	OPEN	Delhi	b2b74349-23de-4e76-b0d4-83fc55504eaf
967c8cc7-4a23-4923-bb0a-28902deead7c	2	Frontend Developer	REMOTE	2025-04-28	DRAFT	Delhi	b2b74349-23de-4e76-b0d4-83fc55504eaf
\.


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.question (id, answer, question, subject) FROM stdin;
43599101-f346-400e-b425-c8de494548ae	Polymorphism is the ability of an object to take many forms.	What is polymorphism?	Java
b719e9bd-38cd-4848-8f18-ef7e18bf492f	A primary key is a unique identifier for a database record.	What is a primary key?	SQL
dc18e189-70c9-40b0-95d7-c86e3926883a	Dependency Injection is a design pattern used to implement IoC, allowing the creation of dependent objects outside a class and providing them to a class.	What is Dependency Injection?	Spring Boot
1fdc4b44-d9de-40a9-abd1-40d7a24d62a3	== checks reference equality, equals() checks value equality.	Explain the difference between == and equals()?	Java
0ef311eb-b6b4-4bcf-9694-8b01a2a26594	Lazy loading delays the loading of an object until it is needed.	What is lazy loading?	Hibernate
751cec16-17b6-43d7-a6b5-a0b52239d115	Polymorphism is the ability of an object to take many forms.	What is polymorphism?	Java
1a3d0b37-0365-48b7-b08a-a9ad9a1c312c	== checks reference equality, equals() checks value equality.	Explain the difference between == and equals()?	Java
9caccd32-1bed-47c4-a7a8-49c2e7d56326	Encapsulation, Inheritance, Polymorphism, and Abstraction.	What are the main principles of OOP?	Java
b88710cd-3237-4758-afbb-600bda849d25	It prevents method overriding, inheritance, or variable reassignment.	What is the use of the final keyword?	Java
7b3339a1-276d-48d2-8cb5-f6f46eaa6bbe	A special method used to initialize objects.	What is a constructor?	Java
0de5f355-1145-4188-9ca7-58179aabaca3	An interface is a contract that classes can implement, specifying methods without implementations.	What is an interface?	Java
826b3d7d-8472-4c6e-906a-4cd7ef9eab99	Abstract classes can have implemented methods; interfaces cannot (before Java 8).	What is the difference between abstract class and interface?	Java
b525453f-6921-4b0a-a76b-2fe38d41827f	Checked exceptions must be handled or declared; unchecked are runtime exceptions.	What are checked and unchecked exceptions?	Java
33ff0926-73e4-4cc0-a20e-7dfee8fac30b	A unique identifier for a database record.	What is a primary key?	SQL
68913af9-8edd-4ab3-959b-37c5f25da95c	A field in one table that refers to the primary key of another table.	What is a foreign key?	SQL
4ffa1821-0298-4727-a09f-16c3d5237ba5	Organizing data to reduce redundancy and improve integrity.	What is normalization?	SQL
ae15afc0-db36-4405-82ef-1b592fb99938	Operations to combine rows from two or more tables based on related columns.	What are joins in SQL?	SQL
a6697cf3-4073-4fe2-b001-b89e38b6ea73	A data structure to improve the speed of data retrieval.	What is an index?	SQL
f5c7e1b0-73eb-44cb-9849-b26eb4bf7918	DELETE removes rows one by one; TRUNCATE removes all rows instantly without logging each row.	What is the difference between DELETE and TRUNCATE?	SQL
7904e529-4a30-49b5-982d-be80d4f53862	A design pattern to supply dependencies from outside rather than creating them internally.	What is Dependency Injection?	Spring Boot
8acc59c1-9779-4d0e-b83c-1fa9a918f95e	An object managed by the Spring IoC container.	What is a Spring Bean?	Spring Boot
eaac70b5-2334-4654-bedc-191e4ffadf5a	@SpringBootApplication, @Component, @Service, @Repository, @Controller.	What are the main Spring Boot annotations?	Spring Boot
db8a57b6-4242-4037-aaf1-15421ce88a91	Spring Boot automatically configures beans based on classpath settings.	What is auto-configuration?	Spring Boot
3f2a149b-ae53-403d-83d0-02e91b6b0316	Automatically injects dependencies into Spring-managed beans.	What is the use of @Autowired?	Spring Boot
7fc10697-b952-4349-963c-fd82cabae89d	Objects are loaded on demand, not immediately.	What is lazy loading?	Hibernate
108b7a8a-d387-4888-af09-b066267b7a46	Hibernate Query Language, an object-oriented query language.	What is HQL?	Hibernate
be26df6c-cf0c-42b9-8a1f-0d4ef11747ad	get() returns null if not found, load() throws exception.	What is the difference between get() and load() methods?	Hibernate
eeacc97a-f969-4027-85e0-b0f631a4a050	A Java class mapped to a database table.	What is an entity?	Hibernate
2e0f2c98-c7ce-470f-82a4-7e705df6a26a	Operations that propagate from parent to child entities.	What is cascading?	Hibernate
acd789d3-a73b-4db5-adcf-980bb3ae3777	A function calling itself to solve a problem.	What is recursion?	General Programming
00a298b4-6f7f-4002-854e-b8e8556542af	A notation to describe algorithm time/space complexity.	What is Big O notation?	General Programming
12c92899-82ac-431a-a067-0a39425da8e7	Organized formats to store and manage data like arrays, lists, trees.	What are data structures?	General Programming
78696b07-83be-4bea-8177-723fe7d3eba2	Executing multiple threads concurrently in a program.	What is multithreading?	General Programming
110d9582-dcee-466a-bcb3-93072da14095	Systems like Git to manage code changes and history.	What is version control?	General Programming
e902be39-8a05-4a0b-876f-179dea631cb2	Python is a programing Language.	What is Python?	Python
63f0a40a-e901-400e-8bf7-f5e63c69e8cf	Python is Python.	What is Python?	Python
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: myuser
--

COPY public.users (id, email, name, password, role) FROM stdin;
9c2fe2b3-d77f-4819-a5b2-b2f9e586a7f4	vivek1@gmail.com	Vivek		CANDIDATE
bebeb5d7-2009-484b-aea8-c8980df7dd16	vivek4@gmail.com	Vivek1		CANDIDATE
8d06f505-478b-47da-852d-fc6187f59e8b	alice@example.com	Alice Candidate	password123	CANDIDATE
24afe73c-f8c0-4c50-905c-cdd263301fc8	bob@example.com	Bob Candidate	password123	CANDIDATE
840a20fe-63ee-4463-a26c-cbd9001b07de	carol@example.com	Carol Candidate	password123	CANDIDATE
15d555cf-f6a6-40b3-9c28-e8cf21ca2113	david@example.com	David Candidate	password123	CANDIDATE
dbbb57a3-7036-4311-a649-01602dd8a2ad	eva@example.com	Eva Candidate	password123	CANDIDATE
d3e03ca6-34a9-4833-8ba0-b93b6dc02626	frank@example.com	Frank Candidate	password123	CANDIDATE
29ae3052-52a5-4cbb-9b5a-f2e47f017612	grace@example.com	Grace Candidate	password123	CANDIDATE
2f71a345-85f5-423b-b040-2041d6e59359	hank@example.com	Hank Candidate	password123	CANDIDATE
bcb91296-2033-424b-a798-396d4b307255	ivy@example.com	Ivy Candidate	password123	CANDIDATE
e98f3c78-cbb7-4024-a2a2-1bf7903f8614	jack@example.com	Jack Candidate	password123	CANDIDATE
89814a96-337d-4484-a4ec-b0a87470cfac	kate@example.com	Kate Candidate	password123	CANDIDATE
b2b74349-23de-4e76-b0d4-83fc55504eaf	vivek@gmail.com	Vivek		INTERVIEWER
475dd45b-5e5f-45bd-9d19-c599092b21dc	knnajeem10@gmail.com	Najeeb	\N	CANDIDATE
3914e73a-dbb5-4ef9-9b7c-d177131a2b3f	vivekarimburath@gmail.com	Vivek1	\N	CANDIDATE
\.


--
-- Name: candidate candidate_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.candidate
    ADD CONSTRAINT candidate_pkey PRIMARY KEY (id);


--
-- Name: email_templates email_templates_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.email_templates
    ADD CONSTRAINT email_templates_pkey PRIMARY KEY (id);


--
-- Name: interview interview_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.interview
    ADD CONSTRAINT interview_pkey PRIMARY KEY (id);


--
-- Name: interview_question interview_question_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.interview_question
    ADD CONSTRAINT interview_question_pkey PRIMARY KEY (id);


--
-- Name: interview_result interview_result_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.interview_result
    ADD CONSTRAINT interview_result_pkey PRIMARY KEY (id);


--
-- Name: job job_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_pkey PRIMARY KEY (id);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: candidate uk67uyxu00tx9l55fptjvodc0gl; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.candidate
    ADD CONSTRAINT uk67uyxu00tx9l55fptjvodc0gl UNIQUE (user_id);


--
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: email_templates ukasgn7km5k3b63c4tqunx8jd8s; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.email_templates
    ADD CONSTRAINT ukasgn7km5k3b63c4tqunx8jd8s UNIQUE (template_name);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: interview_result fk85ao715si8h2pep18dklj6qi1; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.interview_result
    ADD CONSTRAINT fk85ao715si8h2pep18dklj6qi1 FOREIGN KEY (interview_id) REFERENCES public.interview(id);


--
-- Name: interview fkc1ito8leccmjacbefxovj1jxu; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.interview
    ADD CONSTRAINT fkc1ito8leccmjacbefxovj1jxu FOREIGN KEY (interviewer_id) REFERENCES public.users(id);


--
-- Name: candidate fkc23nbdgfce6rnt56ofltvxu71; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.candidate
    ADD CONSTRAINT fkc23nbdgfce6rnt56ofltvxu71 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: interview fkjod0wwyxvbi7qyx9cmlnt8xq4; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.interview
    ADD CONSTRAINT fkjod0wwyxvbi7qyx9cmlnt8xq4 FOREIGN KEY (candidate_id) REFERENCES public.candidate(id);


--
-- Name: interview_question fkl5v6eokn0q96jmiawuist43s1; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.interview_question
    ADD CONSTRAINT fkl5v6eokn0q96jmiawuist43s1 FOREIGN KEY (question_id) REFERENCES public.question(id);


--
-- Name: interview_question fkm66imt5n4b0y877vtv9a8f8og; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.interview_question
    ADD CONSTRAINT fkm66imt5n4b0y877vtv9a8f8og FOREIGN KEY (interview_id) REFERENCES public.interview(id);


--
-- Name: interview fkpla1hwb3ohjsi2owm08rn4cgk; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.interview
    ADD CONSTRAINT fkpla1hwb3ohjsi2owm08rn4cgk FOREIGN KEY (job_id) REFERENCES public.job(id);


--
-- Name: job fkq2r1ggl4sei108an6wofsunsp; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT fkq2r1ggl4sei108an6wofsunsp FOREIGN KEY (interviewer_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

