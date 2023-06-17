CREATE TABLE IF NOT EXISTS person (
    id_person VARCHAR(250) NOT NULL,
    name_person VARCHAR(25) NOT NULL,
    last_name_person VARCHAR(25) NOT NULL,
    birth_date_person DATE NOT NULL,
    email_person VARCHAR(250) UNIQUE NOT NULL,
    phone_person VARCHAR(100) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    rol_type TEXT CHECK (rol_type IN ('COACH', 'ADMINISTRATOR', 'CLIENT')),
    CONSTRAINT pk_person PRIMARY KEY (id_person)
);

CREATE TABLE IF NOT EXISTS course (
    id_course SERIAL PRIMARY KEY,
    description_course VARCHAR(250) NOT NULL,
    name_course VARCHAR(25) NOT NULL,
    creator_course VARCHAR(250) NOT NULL,
    is_active BOOLEAN NOT NULL,
    FOREIGN KEY (creator_course) REFERENCES person(id_person)
);