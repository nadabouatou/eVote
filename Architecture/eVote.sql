--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-11 19:59:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 186 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 186
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 183 (class 1259 OID 24656)
-- Name: cancir; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cancir (
    codecan integer NOT NULL,
    idcir integer NOT NULL
);


ALTER TABLE public.cancir OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 24577)
-- Name: candidat; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE candidat (
    codecandidat integer NOT NULL,
    electionid integer NOT NULL,
    codeparti integer,
    codeelecteur integer
);


ALTER TABLE public.candidat OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 24582)
-- Name: canton; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE canton (
    codecan integer NOT NULL,
    codecom integer,
    denommination character varying(254)
);


ALTER TABLE public.canton OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 24587)
-- Name: circonscription; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE circonscription (
    idcir integer NOT NULL,
    numero integer
);


ALTER TABLE public.circonscription OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 24592)
-- Name: commune; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE commune (
    codecom integer NOT NULL,
    codedep integer,
    denommination character varying(254)
);


ALTER TABLE public.commune OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 24597)
-- Name: departement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE departement (
    codedep integer NOT NULL,
    codereg integer,
    denommination character varying(254)
);


ALTER TABLE public.departement OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 24602)
-- Name: electeur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE electeur (
    electeurid integer NOT NULL,
    idcir integer NOT NULL
);


ALTER TABLE public.electeur OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 24607)
-- Name: election; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE election (
    electionid integer NOT NULL,
    organisateur integer,
    codedep integer,
    codepays integer,
    codereg integer,
    codecom integer,
    nom character varying(254),
    date date,
    type integer,
    modedescrutin integer
);


ALTER TABLE public.election OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 24614)
-- Name: organisateur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organisateur (
    organisateurid integer NOT NULL,
    nom character varying(254),
    adresse character varying(254),
    telephone character varying(254)
);


ALTER TABLE public.organisateur OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 24622)
-- Name: parti; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE parti (
    partiid integer NOT NULL,
    denommination character varying(254),
    datedecreation date,
    siege character varying(254),
    telephone character varying(254)
);


ALTER TABLE public.parti OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 24630)
-- Name: pays; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pays (
    codepays integer NOT NULL,
    nom character varying(254)
);


ALTER TABLE public.pays OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 24635)
-- Name: personne; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE personne (
    personneid integer NOT NULL,
    nom character varying(254),
    prenoms character varying(254),
    adresse character varying(254),
    telephone character varying(254),
    datedenaissance character varying(254),
    sexe character varying(254),
    region character varying(254),
    departement character varying(254),
    commune character varying(254),
    canton character varying(254),
    circonscription character varying(254)
);


ALTER TABLE public.personne OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 24643)
-- Name: region; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE region (
    codereg integer NOT NULL,
    codepays integer,
    denommination character varying(254)
);


ALTER TABLE public.region OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 24661)
-- Name: scrutin; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE scrutin (
    scrutinid integer NOT NULL,
    description text
);


ALTER TABLE public.scrutin OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 24669)
-- Name: typeelection; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE typeelection (
    typeelectionid integer NOT NULL,
    description text
);


ALTER TABLE public.typeelection OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 24648)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE utilisateur (
    userid integer NOT NULL,
    login character varying(254),
    motdepasse character varying(254),
    flag integer
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- TOC entry 2061 (class 0 OID 24656)
-- Dependencies: 183
-- Data for Name: cancir; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cancir (codecan, idcir) FROM stdin;
15	1
15	2
\.


--
-- TOC entry 2048 (class 0 OID 24577)
-- Dependencies: 170
-- Data for Name: candidat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY candidat (codecandidat, electionid, codeparti, codeelecteur) FROM stdin;
4	2	8	\N
2	2	9	\N
4	2	\N	\N
\.


--
-- TOC entry 2049 (class 0 OID 24582)
-- Dependencies: 171
-- Data for Name: canton; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY canton (codecan, codecom, denommination) FROM stdin;
15	56	C
\.


--
-- TOC entry 2050 (class 0 OID 24587)
-- Dependencies: 172
-- Data for Name: circonscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY circonscription (idcir, numero) FROM stdin;
1	1
2	2
\.


--
-- TOC entry 2051 (class 0 OID 24592)
-- Dependencies: 173
-- Data for Name: commune; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY commune (codecom, codedep, denommination) FROM stdin;
56	75	Paris
\.


--
-- TOC entry 2052 (class 0 OID 24597)
-- Dependencies: 174
-- Data for Name: departement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY departement (codedep, codereg, denommination) FROM stdin;
75	11	Paris
\.


--
-- TOC entry 2053 (class 0 OID 24602)
-- Dependencies: 175
-- Data for Name: electeur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY electeur (electeurid, idcir) FROM stdin;
1	1
2	1
3	1
4	1
\.


--
-- TOC entry 2054 (class 0 OID 24607)
-- Dependencies: 176
-- Data for Name: election; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY election (electionid, organisateur, codedep, codepays, codereg, codecom, nom, date, type, modedescrutin) FROM stdin;
1	1	\N	1	\N	\N	Test	2014-11-10	1	1
2	7	\N	1	\N	\N	Présidentielles 2014	2014-11-10	1	1
\.


--
-- TOC entry 2055 (class 0 OID 24614)
-- Dependencies: 177
-- Data for Name: organisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY organisateur (organisateurid, nom, adresse, telephone) FROM stdin;
1	Orga1	adresse10	\N
7	orga2	adresse11	\N
\.


--
-- TOC entry 2056 (class 0 OID 24622)
-- Dependencies: 178
-- Data for Name: parti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY parti (partiid, denommination, datedecreation, siege, telephone) FROM stdin;
9	Parti2	1985-02-21	Siege	\N
8	Parti1	2001-04-24	siege	\N
\.


--
-- TOC entry 2057 (class 0 OID 24630)
-- Dependencies: 179
-- Data for Name: pays; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pays (codepays, nom) FROM stdin;
1	France
\.


--
-- TOC entry 2058 (class 0 OID 24635)
-- Dependencies: 180
-- Data for Name: personne; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY personne (personneid, nom, prenoms, adresse, telephone, datedenaissance, sexe, region, departement, commune, canton, circonscription) FROM stdin;
1	Ralaiarisoa	Ranto	37 Boulevard Jourdan	0601134994	27-03-1988	0	11	75	56	15	1
2	Name1	user1	adresses1	\N	24-01-1987	1	11	75	56	15	1
3	Name2	user2	adresse2	\N	26-06-1980	0	11	75	56	15	1
4	Name3	user3	adresse3	\N	24-05-1984	0	11	75	56	15	1
\.


--
-- TOC entry 2059 (class 0 OID 24643)
-- Dependencies: 181
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY region (codereg, codepays, denommination) FROM stdin;
11	1	Ile de France
\.


--
-- TOC entry 2062 (class 0 OID 24661)
-- Dependencies: 184
-- Data for Name: scrutin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY scrutin (scrutinid, description) FROM stdin;
1	Uninomonal à deux tours
\.


--
-- TOC entry 2063 (class 0 OID 24669)
-- Dependencies: 185
-- Data for Name: typeelection; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY typeelection (typeelectionid, description) FROM stdin;
1	Election Présidentielle
\.


--
-- TOC entry 2060 (class 0 OID 24648)
-- Dependencies: 182
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY utilisateur (userid, login, motdepasse, flag) FROM stdin;
1	ranto	ranto	1
2	user1	user1	1
3	user2	user2	1
4	user3	user4	1
5	user4	user5	1
6	user5	user5	1
7	ogr	org	3
8	parti1	parti1	2
9	parti2	parti2	2
\.


--
-- TOC entry 1914 (class 2606 OID 24660)
-- Name: pk_cancir; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cancir
    ADD CONSTRAINT pk_cancir PRIMARY KEY (codecan, idcir);


--
-- TOC entry 1888 (class 2606 OID 24586)
-- Name: pk_canton; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY canton
    ADD CONSTRAINT pk_canton PRIMARY KEY (codecan);


--
-- TOC entry 1890 (class 2606 OID 24591)
-- Name: pk_circonscription; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY circonscription
    ADD CONSTRAINT pk_circonscription PRIMARY KEY (idcir);


--
-- TOC entry 1892 (class 2606 OID 24596)
-- Name: pk_commune; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY commune
    ADD CONSTRAINT pk_commune PRIMARY KEY (codecom);


--
-- TOC entry 1894 (class 2606 OID 24601)
-- Name: pk_departement; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY departement
    ADD CONSTRAINT pk_departement PRIMARY KEY (codedep);


--
-- TOC entry 1896 (class 2606 OID 24606)
-- Name: pk_electeur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY electeur
    ADD CONSTRAINT pk_electeur PRIMARY KEY (electeurid);


--
-- TOC entry 1898 (class 2606 OID 24611)
-- Name: pk_election; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY election
    ADD CONSTRAINT pk_election PRIMARY KEY (electionid);


--
-- TOC entry 1902 (class 2606 OID 24621)
-- Name: pk_organisateur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organisateur
    ADD CONSTRAINT pk_organisateur PRIMARY KEY (organisateurid);


--
-- TOC entry 1904 (class 2606 OID 24629)
-- Name: pk_parti; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY parti
    ADD CONSTRAINT pk_parti PRIMARY KEY (partiid);


--
-- TOC entry 1906 (class 2606 OID 24634)
-- Name: pk_pays; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pays
    ADD CONSTRAINT pk_pays PRIMARY KEY (codepays);


--
-- TOC entry 1908 (class 2606 OID 24642)
-- Name: pk_personne; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT pk_personne PRIMARY KEY (personneid);


--
-- TOC entry 1910 (class 2606 OID 24647)
-- Name: pk_region; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY region
    ADD CONSTRAINT pk_region PRIMARY KEY (codereg);


--
-- TOC entry 1916 (class 2606 OID 24668)
-- Name: pk_scrutin; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY scrutin
    ADD CONSTRAINT pk_scrutin PRIMARY KEY (scrutinid);


--
-- TOC entry 1918 (class 2606 OID 24676)
-- Name: pk_typeelection; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY typeelection
    ADD CONSTRAINT pk_typeelection PRIMARY KEY (typeelectionid);


--
-- TOC entry 1912 (class 2606 OID 24655)
-- Name: pk_utilisateur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT pk_utilisateur PRIMARY KEY (userid);


--
-- TOC entry 1899 (class 1259 OID 24613)
-- Name: scrutin_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX scrutin_fk ON election USING btree (modedescrutin);


--
-- TOC entry 1900 (class 1259 OID 24612)
-- Name: typeelection_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX typeelection_fk ON election USING btree (type);


--
-- TOC entry 1939 (class 2606 OID 24777)
-- Name: fk_cancir_cancir_canton; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cancir
    ADD CONSTRAINT fk_cancir_cancir_canton FOREIGN KEY (codecan) REFERENCES canton(codecan) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1940 (class 2606 OID 24782)
-- Name: fk_cancir_cancir_circonsc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cancir
    ADD CONSTRAINT fk_cancir_cancir_circonsc FOREIGN KEY (idcir) REFERENCES circonscription(idcir) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1919 (class 2606 OID 24677)
-- Name: fk_candidat_candelec_electeur; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT fk_candidat_candelec_electeur FOREIGN KEY (codecandidat) REFERENCES electeur(electeurid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1920 (class 2606 OID 24682)
-- Name: fk_candidat_candelect_election; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT fk_candidat_candelect_election FOREIGN KEY (electionid) REFERENCES election(electionid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1921 (class 2606 OID 24687)
-- Name: fk_candidat_choix_electeur; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT fk_candidat_choix_electeur FOREIGN KEY (codeelecteur) REFERENCES electeur(electeurid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1922 (class 2606 OID 24692)
-- Name: fk_candidat_partcand_parti; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT fk_candidat_partcand_parti FOREIGN KEY (codeparti) REFERENCES parti(partiid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1923 (class 2606 OID 24697)
-- Name: fk_canton_comcan2_commune; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY canton
    ADD CONSTRAINT fk_canton_comcan2_commune FOREIGN KEY (codecom) REFERENCES commune(codecom) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1924 (class 2606 OID 24702)
-- Name: fk_commune_depcom_departem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY commune
    ADD CONSTRAINT fk_commune_depcom_departem FOREIGN KEY (codedep) REFERENCES departement(codedep) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1925 (class 2606 OID 24707)
-- Name: fk_departem_regdep_region; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY departement
    ADD CONSTRAINT fk_departem_regdep_region FOREIGN KEY (codereg) REFERENCES region(codereg) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1927 (class 2606 OID 24717)
-- Name: fk_electeur_eleccir_circonsc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY electeur
    ADD CONSTRAINT fk_electeur_eleccir_circonsc FOREIGN KEY (idcir) REFERENCES circonscription(idcir) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1926 (class 2606 OID 24712)
-- Name: fk_electeur_helecpers_personne; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY electeur
    ADD CONSTRAINT fk_electeur_helecpers_personne FOREIGN KEY (electeurid) REFERENCES personne(personneid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1928 (class 2606 OID 24722)
-- Name: fk_election_eleccom_commune; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_eleccom_commune FOREIGN KEY (codecom) REFERENCES commune(codecom) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1929 (class 2606 OID 24727)
-- Name: fk_election_elecdep_departem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_elecdep_departem FOREIGN KEY (codedep) REFERENCES departement(codedep) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1930 (class 2606 OID 24732)
-- Name: fk_election_elecpays_pays; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_elecpays_pays FOREIGN KEY (codepays) REFERENCES pays(codepays) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1931 (class 2606 OID 24737)
-- Name: fk_election_elecreg2_region; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_elecreg2_region FOREIGN KEY (codereg) REFERENCES region(codereg) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1932 (class 2606 OID 24742)
-- Name: fk_election_orgelecti_organisa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_orgelecti_organisa FOREIGN KEY (organisateur) REFERENCES organisateur(organisateurid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1933 (class 2606 OID 24747)
-- Name: fk_election_scrutin_scrutin; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_scrutin_scrutin FOREIGN KEY (modedescrutin) REFERENCES scrutin(scrutinid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1934 (class 2606 OID 24752)
-- Name: fk_election_typeelect_typeelec; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_typeelect_typeelec FOREIGN KEY (type) REFERENCES typeelection(typeelectionid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1935 (class 2606 OID 24757)
-- Name: fk_organisa_orguti_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organisateur
    ADD CONSTRAINT fk_organisa_orguti_utilisat FOREIGN KEY (organisateurid) REFERENCES utilisateur(userid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1936 (class 2606 OID 24762)
-- Name: fk_parti_partuti_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parti
    ADD CONSTRAINT fk_parti_partuti_utilisat FOREIGN KEY (partiid) REFERENCES utilisateur(userid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1937 (class 2606 OID 24767)
-- Name: fk_personne_persuti_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT fk_personne_persuti_utilisat FOREIGN KEY (personneid) REFERENCES utilisateur(userid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1938 (class 2606 OID 24772)
-- Name: fk_region_paysreg_pays; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY region
    ADD CONSTRAINT fk_region_paysreg_pays FOREIGN KEY (codepays) REFERENCES pays(codepays) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-11 19:59:47

--
-- PostgreSQL database dump complete
--

