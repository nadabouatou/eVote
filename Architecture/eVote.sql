--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-25 20:32:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 2103 (class 0 OID 0)
-- Dependencies: 191
-- Name: bulettindevote_bulettinid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('bulettindevote_bulettinid_seq', 32, true);


--
-- TOC entry 2078 (class 0 OID 24587)
-- Dependencies: 172
-- Data for Name: circonscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY circonscription (idcir, numero) FROM stdin;
1	1
2	2
\.


--
-- TOC entry 2088 (class 0 OID 24648)
-- Dependencies: 182
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY utilisateur (userid, login, motdepasse, flag) FROM stdin;
1	ranto	ranto	2
24	electeur1	electeur1	1
25	electeur2	electeur2	1
26	electeur3	electeur3	1
27	electeur4	electeur4	1
28	electeur5	electeur5	1
\.


--
-- TOC entry 2086 (class 0 OID 24635)
-- Dependencies: 180
-- Data for Name: personne; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY personne (personneid, nom, prenoms, adresse, telephone, sexe, region, departement, commune, canton, circonscription, datedenaissance) FROM stdin;
1	RALAIARISOA	Ranto	37 Boulevard Jourdan	0601134994	M	11	75	56	15	1	1988-03-27
24	NomElecteur1	PrenomsElecteur1			M	11	75	56	15	2	1985-12-12
25	NomElecteur2	PrenomsElecteur2			M	11	75	56	15	2	1985-12-12
26	NomElecteur3	PrenomsElecteur3			M	11	75	56	15	2	1985-12-12
27	NomElecteur4	PrenomsElecteur4			M	11	75	56	15	2	1985-12-12
28	NomElecteur5	PrenomsElecteur5			M	11	75	56	15	2	1985-12-12
\.


--
-- TOC entry 2081 (class 0 OID 24602)
-- Dependencies: 175
-- Data for Name: electeur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY electeur (electeurid, idcir) FROM stdin;
24	2
25	2
26	2
27	2
28	2
\.


--
-- TOC entry 2098 (class 0 OID 40962)
-- Dependencies: 192
-- Data for Name: bulletindevoteuni; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY bulletindevoteuni (bulletinid, electeurid, electionid, candidatid) FROM stdin;
28	\N	26	24
29	\N	26	24
30	\N	26	26
31	\N	26	25
32	\N	26	24
\.


--
-- TOC entry 2085 (class 0 OID 24630)
-- Dependencies: 179
-- Data for Name: pays; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pays (codepays, nom) FROM stdin;
1	France
\.


--
-- TOC entry 2087 (class 0 OID 24643)
-- Dependencies: 181
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY region (codereg, codepays, denommination) FROM stdin;
11	1	Ile de France
12	1	Test
\.


--
-- TOC entry 2080 (class 0 OID 24597)
-- Dependencies: 174
-- Data for Name: departement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY departement (codedep, codereg, denommination) FROM stdin;
75	11	Paris
\.


--
-- TOC entry 2079 (class 0 OID 24592)
-- Dependencies: 173
-- Data for Name: commune; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY commune (codecom, codedep, denommination) FROM stdin;
56	75	Paris
\.


--
-- TOC entry 2077 (class 0 OID 24582)
-- Dependencies: 171
-- Data for Name: canton; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY canton (codecan, codecom, denommination) FROM stdin;
15	56	C
\.


--
-- TOC entry 2089 (class 0 OID 24656)
-- Dependencies: 183
-- Data for Name: cancir; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cancir (codecan, idcir) FROM stdin;
15	1
15	2
\.


--
-- TOC entry 2083 (class 0 OID 24614)
-- Dependencies: 177
-- Data for Name: organisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY organisateur (organisateurid, nom, telephone, siege) FROM stdin;
1	\N	\N	\N
\.


--
-- TOC entry 2090 (class 0 OID 24661)
-- Dependencies: 184
-- Data for Name: scrutin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY scrutin (scrutinid, description) FROM stdin;
1	Uninominal à deux tours
0	Test
\.


--
-- TOC entry 2091 (class 0 OID 24669)
-- Dependencies: 185
-- Data for Name: typeelection; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY typeelection (typeelectionid, description) FROM stdin;
2	Election régionale
3	Election communale
4	Election départementale
1	Election Nationale
\.


--
-- TOC entry 2082 (class 0 OID 24607)
-- Dependencies: 176
-- Data for Name: election; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY election (electionid, organisateur, codedep, codepays, codereg, codecom, nom, date, type, modedescrutin) FROM stdin;
24	\N	\N	1	\N	\N	Election test POCA	2014-11-22	2	1
26	\N	\N	1	\N	\N	Best Burger Ever	2014-11-23	1	1
29	\N	\N	1	\N	\N	testobs	2015-11-24	2	0
30	\N	\N	1	\N	\N	Meilleur ouvrier de France	2014-11-26	1	1
31	\N	\N	1	\N	\N	Election du meilleur Université de France	2014-11-26	1	1
\.


--
-- TOC entry 2076 (class 0 OID 24577)
-- Dependencies: 170
-- Data for Name: candidat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY candidat (codecandidat, electionid, "candidatType", candidatid) FROM stdin;
24	26	\N	96
25	26	\N	97
26	26	\N	98
25	31	\N	99
\.


--
-- TOC entry 2104 (class 0 OID 0)
-- Dependencies: 186
-- Name: candidat_candidatid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('candidat_candidatid_seq', 99, true);


--
-- TOC entry 2105 (class 0 OID 0)
-- Dependencies: 187
-- Name: check_checkid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('check_checkid_seq', 43, true);


--
-- TOC entry 2094 (class 0 OID 32770)
-- Dependencies: 188
-- Data for Name: checkvote; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY checkvote (checkid, electionid, electeurid) FROM stdin;
39	26	24
40	26	25
41	26	26
42	26	27
43	26	28
\.


--
-- TOC entry 2106 (class 0 OID 0)
-- Dependencies: 190
-- Name: elec_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('elec_id', 31, true);


--
-- TOC entry 2084 (class 0 OID 24622)
-- Dependencies: 178
-- Data for Name: parti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY parti (partiid, denommination, datedecreation, siege, telephone) FROM stdin;
\.


--
-- TOC entry 2107 (class 0 OID 0)
-- Dependencies: 189
-- Name: userid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userid_seq', 28, true);


-- Completed on 2014-11-25 20:32:08

--
-- PostgreSQL database dump complete
--

