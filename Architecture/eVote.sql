--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-11-26 08:49:14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 193 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 193
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 192 (class 1259 OID 40962)
-- Name: bulletindevoteuni; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE bulletindevoteuni (
    bulletinid integer NOT NULL,
    electeurid bigint,
    electionid bigint NOT NULL,
    candidatid bigint
);


ALTER TABLE public.bulletindevoteuni OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 40960)
-- Name: bulettindevote_bulettinid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE bulettindevote_bulettinid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bulettindevote_bulettinid_seq OWNER TO postgres;

--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 191
-- Name: bulettindevote_bulettinid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE bulettindevote_bulettinid_seq OWNED BY bulletindevoteuni.bulletinid;


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
    "candidatType" integer,
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
-- TOC entry 2085 (class 0 OID 0)
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
-- TOC entry 2086 (class 0 OID 0)
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
    datedebut date,
    type integer,
    modedescrutin integer,
    datefin date
);


ALTER TABLE public.election OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 24614)
-- Name: organisateur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organisateur (
    organisateurid integer NOT NULL,
    nom character varying(254)[],
    telephone bit varying(254)[],
    siege bit varying(254)[]
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
-- TOC entry 1909 (class 2604 OID 40965)
-- Name: bulletinid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bulletindevoteuni ALTER COLUMN bulletinid SET DEFAULT nextval('bulettindevote_bulettinid_seq'::regclass);


--
-- TOC entry 1905 (class 2604 OID 24792)
-- Name: candidatid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat ALTER COLUMN candidatid SET DEFAULT nextval('candidat_candidatid_seq'::regclass);


--
-- TOC entry 1908 (class 2604 OID 32773)
-- Name: checkid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY checkvote ALTER COLUMN checkid SET DEFAULT nextval('check_checkid_seq'::regclass);


--
-- TOC entry 1949 (class 2606 OID 40967)
-- Name: bulettinid_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bulletindevoteuni
    ADD CONSTRAINT bulettinid_pk PRIMARY KEY (bulletinid);


--
-- TOC entry 1911 (class 2606 OID 41021)
-- Name: candElec; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT "candElec" UNIQUE (codecandidat, candidatid);


--
-- TOC entry 1913 (class 2606 OID 24797)
-- Name: candidat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT candidat_pkey PRIMARY KEY (candidatid);


--
-- TOC entry 1941 (class 2606 OID 24660)
-- Name: pk_cancir; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cancir
    ADD CONSTRAINT pk_cancir PRIMARY KEY (codecan, idcir);


--
-- TOC entry 1915 (class 2606 OID 24586)
-- Name: pk_canton; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY canton
    ADD CONSTRAINT pk_canton PRIMARY KEY (codecan);


--
-- TOC entry 1947 (class 2606 OID 32775)
-- Name: pk_check; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY checkvote
    ADD CONSTRAINT pk_check PRIMARY KEY (checkid);


--
-- TOC entry 1917 (class 2606 OID 24591)
-- Name: pk_circonscription; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY circonscription
    ADD CONSTRAINT pk_circonscription PRIMARY KEY (idcir);


--
-- TOC entry 1919 (class 2606 OID 24596)
-- Name: pk_commune; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY commune
    ADD CONSTRAINT pk_commune PRIMARY KEY (codecom);


--
-- TOC entry 1921 (class 2606 OID 24601)
-- Name: pk_departement; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY departement
    ADD CONSTRAINT pk_departement PRIMARY KEY (codedep);


--
-- TOC entry 1923 (class 2606 OID 24606)
-- Name: pk_electeur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY electeur
    ADD CONSTRAINT pk_electeur PRIMARY KEY (electeurid);


--
-- TOC entry 1925 (class 2606 OID 24611)
-- Name: pk_election; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY election
    ADD CONSTRAINT pk_election PRIMARY KEY (electionid);


--
-- TOC entry 1929 (class 2606 OID 24621)
-- Name: pk_organisateur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organisateur
    ADD CONSTRAINT pk_organisateur PRIMARY KEY (organisateurid);


--
-- TOC entry 1931 (class 2606 OID 24629)
-- Name: pk_parti; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY parti
    ADD CONSTRAINT pk_parti PRIMARY KEY (partiid);


--
-- TOC entry 1933 (class 2606 OID 24634)
-- Name: pk_pays; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pays
    ADD CONSTRAINT pk_pays PRIMARY KEY (codepays);


--
-- TOC entry 1935 (class 2606 OID 24642)
-- Name: pk_personne; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT pk_personne PRIMARY KEY (personneid);


--
-- TOC entry 1937 (class 2606 OID 24647)
-- Name: pk_region; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY region
    ADD CONSTRAINT pk_region PRIMARY KEY (codereg);


--
-- TOC entry 1943 (class 2606 OID 24668)
-- Name: pk_scrutin; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY scrutin
    ADD CONSTRAINT pk_scrutin PRIMARY KEY (scrutinid);


--
-- TOC entry 1945 (class 2606 OID 24676)
-- Name: pk_typeelection; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY typeelection
    ADD CONSTRAINT pk_typeelection PRIMARY KEY (typeelectionid);


--
-- TOC entry 1939 (class 2606 OID 24655)
-- Name: pk_utilisateur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT pk_utilisateur PRIMARY KEY (userid);


--
-- TOC entry 1926 (class 1259 OID 24613)
-- Name: scrutin_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX scrutin_fk ON election USING btree (modedescrutin);


--
-- TOC entry 1927 (class 1259 OID 24612)
-- Name: typeelection_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX typeelection_fk ON election USING btree (type);


--
-- TOC entry 1968 (class 2606 OID 40968)
-- Name: belecteurid_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bulletindevoteuni
    ADD CONSTRAINT belecteurid_fk FOREIGN KEY (electeurid) REFERENCES electeur(electeurid);


--
-- TOC entry 1966 (class 2606 OID 24777)
-- Name: fk_cancir_cancir_canton; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cancir
    ADD CONSTRAINT fk_cancir_cancir_canton FOREIGN KEY (codecan) REFERENCES canton(codecan) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1967 (class 2606 OID 24782)
-- Name: fk_cancir_cancir_circonsc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cancir
    ADD CONSTRAINT fk_cancir_cancir_circonsc FOREIGN KEY (idcir) REFERENCES circonscription(idcir) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1950 (class 2606 OID 24682)
-- Name: fk_candidat_candelect_election; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY candidat
    ADD CONSTRAINT fk_candidat_candelect_election FOREIGN KEY (electionid) REFERENCES election(electionid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1951 (class 2606 OID 24697)
-- Name: fk_canton_comcan2_commune; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY canton
    ADD CONSTRAINT fk_canton_comcan2_commune FOREIGN KEY (codecom) REFERENCES commune(codecom) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1952 (class 2606 OID 24702)
-- Name: fk_commune_depcom_departem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY commune
    ADD CONSTRAINT fk_commune_depcom_departem FOREIGN KEY (codedep) REFERENCES departement(codedep) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1953 (class 2606 OID 24707)
-- Name: fk_departem_regdep_region; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY departement
    ADD CONSTRAINT fk_departem_regdep_region FOREIGN KEY (codereg) REFERENCES region(codereg) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1955 (class 2606 OID 24717)
-- Name: fk_electeur_eleccir_circonsc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY electeur
    ADD CONSTRAINT fk_electeur_eleccir_circonsc FOREIGN KEY (idcir) REFERENCES circonscription(idcir) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1954 (class 2606 OID 24712)
-- Name: fk_electeur_helecpers_personne; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY electeur
    ADD CONSTRAINT fk_electeur_helecpers_personne FOREIGN KEY (electeurid) REFERENCES personne(personneid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1956 (class 2606 OID 24722)
-- Name: fk_election_eleccom_commune; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_eleccom_commune FOREIGN KEY (codecom) REFERENCES commune(codecom) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1957 (class 2606 OID 24727)
-- Name: fk_election_elecdep_departem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_elecdep_departem FOREIGN KEY (codedep) REFERENCES departement(codedep) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1958 (class 2606 OID 24732)
-- Name: fk_election_elecpays_pays; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_elecpays_pays FOREIGN KEY (codepays) REFERENCES pays(codepays) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1959 (class 2606 OID 24737)
-- Name: fk_election_elecreg2_region; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_elecreg2_region FOREIGN KEY (codereg) REFERENCES region(codereg) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1960 (class 2606 OID 24742)
-- Name: fk_election_orgelecti_organisa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_orgelecti_organisa FOREIGN KEY (organisateur) REFERENCES organisateur(organisateurid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1961 (class 2606 OID 24747)
-- Name: fk_election_scrutin_scrutin; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_scrutin_scrutin FOREIGN KEY (modedescrutin) REFERENCES scrutin(scrutinid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1962 (class 2606 OID 24752)
-- Name: fk_election_typeelect_typeelec; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY election
    ADD CONSTRAINT fk_election_typeelect_typeelec FOREIGN KEY (type) REFERENCES typeelection(typeelectionid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1963 (class 2606 OID 24757)
-- Name: fk_organisa_orguti_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organisateur
    ADD CONSTRAINT fk_organisa_orguti_utilisat FOREIGN KEY (organisateurid) REFERENCES utilisateur(userid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1964 (class 2606 OID 24767)
-- Name: fk_personne_persuti_utilisat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT fk_personne_persuti_utilisat FOREIGN KEY (personneid) REFERENCES utilisateur(userid) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1965 (class 2606 OID 24772)
-- Name: fk_region_paysreg_pays; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY region
    ADD CONSTRAINT fk_region_paysreg_pays FOREIGN KEY (codepays) REFERENCES pays(codepays) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-11-26 08:49:15

--
-- PostgreSQL database dump complete
--

