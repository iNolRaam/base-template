CREATE TABLE base_template.type_rights
(
    id            bigint                 NOT NULL DEFAULT nextval('base_template.seq_type_rights_id'::regclass),
    name          character varying(100) NOT NULL,
    visible       boolean                NOT NULL DEFAULT true,
    created_by    character varying(100) NOT NULL,
    created_at    time without time zone NOT NULL,
    updated_by    character varying(100),
    lst_updated_at time without time zone,
    CONSTRAINT pk_type_rights_id PRIMARY KEY (id),
    CONSTRAINT uk_type_rights_name UNIQUE (name)
);

ALTER TABLE IF EXISTS base_template.type_rights
    OWNER to postgres;

CREATE TABLE base_template.rights
(
    id            bigint                 NOT NULL DEFAULT nextval('base_template.seq_rights_id'::regclass),
    id_type_right bigint                 NOT NULL,
    name          character varying(100) NOT NULL,
    visible       boolean                NOT NULL DEFAULT true,
    created_by    character varying(100) NOT NULL,
    created_at    time without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by    character varying(100),
    lst_updated_at time without time zone,
    CONSTRAINT pk_rights_id PRIMARY KEY (id),
    CONSTRAINT uk_rights_name UNIQUE (name),
    CONSTRAINT fk_rights_id_type_right FOREIGN KEY (id_type_right)
        REFERENCES base_template.type_rights (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

ALTER TABLE IF EXISTS base_template.rights
    OWNER to postgres;

CREATE TABLE base_template.roles
(
    id            bigint                 NOT NULL DEFAULT nextval('base_template.seq_roles_id'::regclass),
    name          character varying(100) NOT NULL,
    visible       boolean                NOT NULL DEFAULT true,
    created_by    character varying(100) NOT NULL,
    created_at    time without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by    character varying(100),
    lst_updated_at time without time zone,
    CONSTRAINT pk_roles_id PRIMARY KEY (id),
    CONSTRAINT uk_roles_name UNIQUE (name)
);

ALTER TABLE IF EXISTS base_template.roles
    OWNER to postgres;


CREATE TABLE base_template.profiles
(
    id            bigint                 NOT NULL DEFAULT nextval('base_template.seq_profiles_id'::regclass),
    name          character varying(100) NOT NULL,
    visible       boolean                NOT NULL DEFAULT true,
    created_by    character varying(100) NOT NULL,
    created_at    time without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by    character varying(100),
    lst_updated_at time without time zone,
    CONSTRAINT pk_profiles_id PRIMARY KEY (id),
    CONSTRAINT uk_profiles_name UNIQUE (name)
);

ALTER TABLE IF EXISTS base_template.profiles
    OWNER to postgres;



CREATE TABLE base_template.roles_rights
(
    id_role       bigint                 NOT NULL,
    id_right      bigint                 NOT NULL,
    created_by    character varying(100) NOT NULL,
    created_at    time without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by    character varying(100),
    lst_updated_at time without time zone,
    CONSTRAINT pk_roles_rights_comb PRIMARY KEY (id_role, id_right),
    CONSTRAINT fk_roles_rights_id_right FOREIGN KEY (id_right)
        REFERENCES base_template.rights (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT fk_roles_rights_id_role FOREIGN KEY (id_role)
        REFERENCES base_template.roles (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

ALTER TABLE IF EXISTS base_template.roles_rights
    OWNER to postgres;

CREATE TABLE base_template.profiles_roles
(
    id_profile    bigint                 NOT NULL,
    id_role       bigint                 NOT NULL,
    created_by    character varying(100) NOT NULL,
    created_at    time without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by    character varying(100),
    lst_updated_at time without time zone,
    CONSTRAINT pk_profiles_roles_comb PRIMARY KEY (id_profile, id_role),
    CONSTRAINT fk_profiles_roles_id_role FOREIGN KEY (id_role)
        REFERENCES base_template.roles (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT fk_profiles_roles_id_profile FOREIGN KEY (id_profile)
        REFERENCES base_template.profiles (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

ALTER TABLE IF EXISTS base_template.profiles_roles
    OWNER to postgres;