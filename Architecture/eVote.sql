--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-17 07:24:24

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE "eVote";
--
-- TOC entry 2092 (class 1262 OID 24576)
-- Name: eVote; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "eVote" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';


ALTER DATABASE "eVote" OWNER TO postgres;

\connect "eVote"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 191 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 191
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
    candidatid integer NOT NULL
);


ALTER TABLE public.candidat OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 24790)
-- Name: candidat_candidatid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE candidat_candidatid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.candidat_candidatid_seq OWNER TO postgres;

--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 186
-- Name: candidat_candidatid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE candidat_candidatid_seq OWNED BY candidat.candidatid;


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
-- TOC entry 188 (class 1259 OID 32770)
-- Name: checkvote; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE checkvote (
    checkid integer NOT NULL,
    electionid integer,
    electeurid integer
);


ALTER TABLE public.checkvote OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 32768)
-- Name: check_checkid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE check_checkid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.check_checkid_seq OWNER TO postgres;

--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 187
-- Name: check_checkid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE check_checkid_seq OWNED BY checkvote.checkid;


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
-- TOC entry 190 (class 1259 OID 32981)
-- Name: elec_id; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE elec_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.elec_id OWNER TO postgres;

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
    electionid integer DEFAULT nextval('elec_id'::regclass) NOT NULL,
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
    sexe character varying(254),
    region character varying(254),
    departement character varying(254),
    commune character varying(254),
    canton character varying(254),
    circonscription character varying(254),
    datedenaissance date
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
-- TOC entry 189 (class 1259 OID 32802)
-- Name: userid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE userid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.userid_seq OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 24648)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE utilisateur (
    userid integer DEFAULT nextval('userid_seq'::regclass) NOT NULL,
    login character varying(254),
    motdepasse character varying(254),
    flag integer
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- TOC entry 1899 (class 2604 OID 24792)
-- Name: candidatid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat ALTER COLUMN candidatid SET DEFAULT nextval('candidat_candidatid_seq'::regclass);


--
-- TOC entry 1902 (class 2604 OID 32773)
-- Name: checkid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY checkvote ALTER COLUMN checkid SET DEFAULT nextval('check_checkid_seq'::regclass);


--
-- TOC entry 2080 (class 0 OID 24656)
-- Dependencies: 183
-- Data for Name: cancir; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cancir VALUES (15, 1);
INSERT INTO cancir VALUES (15, 2);


--
-- TOC entry 2067 (class 0 OID 24577)
-- Dependencies: 170
-- Data for Name: candidat; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO candidat VALUES (20, 4, 18, 90);
INSERT INTO candidat VALUES (21, 4, 19, 91);
INSERT INTO candidat VALUES (20, 4, NULL, 92);
INSERT INTO candidat VALUES (17, 4, 23, 93);


--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 186
-- Name: candidat_candidatid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('candidat_candidatid_seq', 93, true);


--
-- TOC entry 2068 (class 0 OID 24582)
-- Dependencies: 171
-- Data for Name: canton; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO canton VALUES (15, 56, 'C');


--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 187
-- Name: check_checkid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('check_checkid_seq', 13, true);


--
-- TOC entry 2085 (class 0 OID 32770)
-- Dependencies: 188
-- Data for Name: checkvote; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO checkvote VALUES (9, 2, 1);
INSERT INTO checkvote VALUES (10, 2, 2);
INSERT INTO checkvote VALUES (11, 2, 3);
INSERT INTO checkvote VALUES (12, 2, 4);
INSERT INTO checkvote VALUES (13, 2, 17);


--
-- TOC entry 2069 (class 0 OID 24587)
-- Dependencies: 172
-- Data for Name: circonscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO circonscription VALUES (1, 1);
INSERT INTO circonscription VALUES (2, 2);


--
-- TOC entry 2070 (class 0 OID 24592)
-- Dependencies: 173
-- Data for Name: commune; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO commune VALUES (56, 75, 'Paris');


--
-- TOC entry 2071 (class 0 OID 24597)
-- Dependencies: 174
-- Data for Name: departement; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO departement VALUES (75, 11, 'Paris');


--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 190
-- Name: elec_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('elec_id', 23, true);


--
-- TOC entry 2072 (class 0 OID 24602)
-- Dependencies: 175
-- Data for Name: electeur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO electeur VALUES (17, 1);
INSERT INTO electeur VALUES (20, 1);
INSERT INTO electeur VALUES (21, 1);


--
-- TOC entry 2073 (class 0 OID 24607)
-- Dependencies: 176
-- Data for Name: election; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO election VALUES (1, 19, NULL, NULL, 11, NULL, 'Election régionale', '2014-11-15', 2, 1);
INSERT INTO election VALUES (2, 19, NULL, NULL, NULL, 56, 'Election de la commune de Paris', '2014-11-15', 3, 1);
INSERT INTO election VALUES (3, 19, NULL, NULL, 12, NULL, 'Test off', '2014-11-15', 2, 1);
INSERT INTO election VALUES (4, 19, NULL, NULL, 12, NULL, 'Test', '2015-11-25', 2, 1);
INSERT INTO election VALUES (5, 19, NULL, NULL, 12, NULL, 'Test2', '2014-12-25', 2, 1);
INSERT INTO election VALUES (8, 19, NULL, NULL, 11, NULL, 'Election du conseil régional', '2014-12-25', 2, 1);
INSERT INTO election VALUES (9, 19, NULL, 1, NULL, NULL, 'Election législative', '2014-12-25', 2, 1);
INSERT INTO election VALUES (10, 19, 75, NULL, NULL, NULL, 'Election départementale', '2014-12-25', 2, 1);
INSERT INTO election VALUES (11, 19, NULL, NULL, NULL, 56, 'Election com', '2014-12-25', 2, 1);


--
-- TOC entry 2074 (class 0 OID 24614)
-- Dependencies: 177
-- Data for Name: organisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO organisateur VALUES (19, 'Organisateur N°1', NULL, NULL);


--
-- TOC entry 2075 (class 0 OID 24622)
-- Dependencies: 178
-- Data for Name: parti; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO parti VALUES (18, 'La Team Rocket', '2003-11-24', 'Pokemon Land', '');
INSERT INTO parti VALUES (19, 'La ligue des méchants', '1988-12-25', 'Quelque part sur Terre', 'Demander à Hadès');
INSERT INTO parti VALUES (23, 'Equipe B', '1988-12-25', 'Siège', NULL);


--
-- TOC entry 2076 (class 0 OID 24630)
-- Dependencies: 179
-- Data for Name: pays; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pays VALUES (1, 'France');


--
-- TOC entry 2077 (class 0 OID 24635)
-- Dependencies: 180
-- Data for Name: personne; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO personne VALUES (17, 'RALAIARISOA', 'Ranto', '37 Boulevard Jourdan', '', '', '11', '75', '56', '15', '1', '2014-03-27');
INSERT INTO personne VALUES (20, 'Jean Michel', '', '37 Boulevard Jourdan', '', '', '', '', '', '', '1', '1972-03-27');
INSERT INTO personne VALUES (21, 'Peter Pan', '', '37 Boulevard Jourdan', '', '', '', '', '', '', '1', '1972-03-27');


--
-- TOC entry 2078 (class 0 OID 24643)
-- Dependencies: 181
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO region VALUES (11, 1, 'Ile de France');
INSERT INTO region VALUES (12, 1, 'Test');


--
-- TOC entry 2081 (class 0 OID 24661)
-- Dependencies: 184
-- Data for Name: scrutin; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO scrutin VALUES (1, 'Uninomonal à deux tours');


--
-- TOC entry 2082 (class 0 OID 24669)
-- Dependencies: 185
-- Data for Name: typeelection; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO typeelection VALUES (2, 'Election régionale');
INSERT INTO typeelection VALUES (3, 'Election communale');
INSERT INTO typeelection VALUES (4, 'Election départementale');
INSERT INTO typeelection VALUES (1, 'Election Nationale');


--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 189
-- Name: userid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userid_seq', 23, true);


--
-- TOC entry 2079 (class 0 OID 24648)
-- Dependencies: 182
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO utilisateur VALUES (17, 'ranto', 'ranto', 1);
INSERT INTO utilisateur VALUES (20, 'jean', 'jean', 1);
INSERT INTO utilisateur VALUES (21, 'peter', 'peter', 1);
INSERT INTO utilisateur VALUES (18, 'Parti1', 'parti1', 2);
INSERT INTO utilisateur VALUES (19, 'Parti2', 'parti2', 2);
INSERT INTO utilisateur VALUES (22, 'admin', 'admin', 3);
INSERT INTO utilisateur VALUES (23, 'parti3', 'parti3', 2);


--
-- TOC entry 1904 (class 2606 OID 24797)
-- Name: candidat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT candidat_pkey PRIMARY KEY (candidatid);


--
-- TOC entry 1932 (class 2606 OID 24660)
-- Name: pk_cancir; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cancir
    ADD CONSTRAINT pk_cancir PRIMARY KEY (codecan, idcir);


--
-- TOC entry 1906 (class 2606 OID 24586)
-- Name: pk_canton; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY canton
    ADD CONSTRAINT pk_canton PRIMARY KEY (codecan);


--
-- TOC entry 1938 (class 2606 OID 32775)
-- Name: pk_check; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY checkvote
    ADD CONSTRAINT pk_check PRIMARY KEY (checkid);


--
-- TOC entry 1908 (class 2606 OID 24591)
-- Name: pk_circonscription; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY circonscription
    ADD CONSTRAINT pk_circonscription PRIMARY KEY (idcir);


--
-- TOC entry 1910 (class 2606 OID 24596)
-- Name: pk_commune; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY commune
    ADD CONSTRAINT pk_commune PRIMARY KEY (codecom);


--
-- TOC entry 1912 (class 2606 OID 24601)
-- Name: pk_departement; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY departement
    ADD CONSTRAINT pk_departement PRIMARY KEY (codedep);


--
-- TOC entry 1914 (class 2606 OID 24606)
-- Name: pk_electeur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY electeur
    ADD CONSTRAINT pk_electeur PRIMARY KEY (electeurid);


--
-- TOC entry 1916 (class 2606 OID 24611)
-- Name: pk_election; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY election
    ADD CONSTRAINT pk_election PRIMARY KEY (electionid);


--
-- TOC entry 1920 (class 2606 OID 24621)
-- Name: pk_organisateur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organisateur
    ADD CONSTRAINT pk_organisateur PRIMARY KEY (organisateurid);


--
-- TOC entry 1922 (class 2606 OID 24629)
-- Name: pk_parti; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY parti
    ADD CONSTRAINT pk_parti PRIMARY KEY (partiid);


--
-- TOC entry 1924 (class 2606 OID 24634)
-- Name: pk_pays; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pays
    ADD CONSTRAINT pk_pays PRIMARY KEY (codepays);


--
-- TOC entry 1926 (class 2606 OID 24642)
-- Name: pk_personne; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT pk_personne PRIMARY KEY (personneid);


--
-- TOC entry 1928 (class 2606 OID 24647)
-- Name: pk_region; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY region
    ADD CONSTRAINT pk_region PRIMARY KEY (codereg);


--
-- TOC entry 1934 (class 2606 OID 24668)
-- Name: pk_scrutin; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY scrutin
    ADD CONSTRAINT pk_scrutin PRIMARY KEY (scrutinid);


--
-- TOC entry 1936 (class 2606 OID 24676)
-- Name: pk_typeelection; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY typeelection
    ADD CONSTRAINT pk_typeelection PRIMARY KEY (typeelectionid);


--
-- TOC entry 1930 (class 2606 OID 24655)
-- Name: pk_utilisateur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT pk_utilisateur PRIMARY KEY (userid);


--
-- TOC entry 1917 (class 1259 OID 24613)
-- Name: scrutin_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX scrutin_fk ON election USING btree (modedescrutin);


--
-- TOC entry 1918 (class 1259 OID 24612)
-- Name: typeelection_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX typeelection_fk ON election USING btree (type);


--
-- TOC entry 1958 (class 2606 OID 24777)
-- Name: fk_cancir_cancir_canton; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cancir
    ADD CONSTRAINT fk_cancir_cancir_canton FOREIGN KEY (codecan) REFERENCES canton(codecan) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1959 (class 2606 OID 24782)
-- Name: fk_cancir_cancir_circonsc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cancir
    ADD CONSTRAINT fk_cancir_cancir_circonsc FOREIGN KEY (idcir) REFERENCES circonscription(idcir) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1939 (class 2606 OID 24677)
-- Name: fk_candidat_candelec_electeur; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT fk_candidat_candelec_electeur FOREIGN KEY (codecandidat) REFERENCES electeur(electeurid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1940 (class 2606 OID 24682)
-- Name: fk_candidat_candelect_election; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT fk_candidat_candelect_election FOREIGN KEY (electionid) REFERENCES election(electionid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1941 (class 2606 OID 24692)
-- Name: fk_candidat_partcand_parti; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT fk_candidat_partcand_parti FOREIGN KEY (codeparti) REFERENCES parti(partiid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1942 (class 2606 OID 24697)
-- Name: fk_canton_comcan2_commune; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY canton
    ADD CONSTRAINT fk_canton_comcan2_commune FOREIGN KEY (codecom) REFERENCES commune(codecom) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1943 (class 2606 OID 24702)
-- Name: fk_commune_depcom_departem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY commune
    ADD CONSTRAINT fk_commune_depcom_departem FOREIGN KEY (codedep) REFERENCES departement(codedep) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1944 (class 2606 OID 24707)
-- Name: fk_departem_regdep_region; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY departement
    ADD CONSTRAINT fk_departem_regdep_region FOREIGN KEY (codereg) REFERENCES region(codereg) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1946 (class 2606 OID 24717)
-- Name: fk_electeur_eleccir_circonsc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY electeur
    ADD CONSTRAINT fk_electeur_eleccir_circonsc FOREIGN KEY (idcir) REFERENCES circonscription(idcir) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1945 (class 2606 OID 24712)
-- Name: fk_electeur_helecpers_personne; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY electeur
    ADD CONSTRAINT fk_electeur_helecpers_personne FOREIGN KEY (electeurid) REFERENCES personne(personneid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1947 (class 2606 OID 24722)
-- Name: fk_election_eleccom_commune; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_eleccom_commune FOREIGN KEY (codecom) REFERENCES commune(codecom) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1948 (class 2606 OID 24727)
-- Name: fk_election_elecdep_departem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_elecdep_departem FOREIGN KEY (codedep) REFERENCES departement(codedep) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1949 (class 2606 OID 24732)
-- Name: fk_election_elecpays_pays; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_elecpays_pays FOREIGN KEY (codepays) REFERENCES pays(codepays) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1950 (class 2606 OID 24737)
-- Name: fk_election_elecreg2_region; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_elecreg2_region FOREIGN KEY (codereg) REFERENCES region(codereg) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1951 (class 2606 OID 24742)
-- Name: fk_election_orgelecti_organisa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_orgelecti_organisa FOREIGN KEY (organisateur) REFERENCES organisateur(organisateurid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1952 (class 2606 OID 24747)
-- Name: fk_election_scrutin_scrutin; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_scrutin_scrutin FOREIGN KEY (modedescrutin) REFERENCES scrutin(scrutinid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1953 (class 2606 OID 24752)
-- Name: fk_election_typeelect_typeelec; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_typeelect_typeelec FOREIGN KEY (type) REFERENCES typeelection(typeelectionid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1954 (class 2606 OID 24757)
-- Name: fk_organisa_orguti_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organisateur
    ADD CONSTRAINT fk_organisa_orguti_utilisat FOREIGN KEY (organisateurid) REFERENCES utilisateur(userid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1955 (class 2606 OID 24762)
-- Name: fk_parti_partuti_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parti
    ADD CONSTRAINT fk_parti_partuti_utilisat FOREIGN KEY (partiid) REFERENCES utilisateur(userid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1956 (class 2606 OID 24767)
-- Name: fk_personne_persuti_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT fk_personne_persuti_utilisat FOREIGN KEY (personneid) REFERENCES utilisateur(userid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1957 (class 2606 OID 24772)
-- Name: fk_region_paysreg_pays; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY region
    ADD CONSTRAINT fk_region_paysreg_pays FOREIGN KEY (codepays) REFERENCES pays(codepays) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-17 07:24:25

--
-- PostgreSQL database dump complete
--

