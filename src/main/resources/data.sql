INSERT INTO attribute_value(id, code, value) VALUES
    (1, 'HEIGHT-1', '10 cm'),
    (2, 'HEIGHT-2', '20 cm');

INSERT INTO attribute(id, code, name, type) VALUES
    (1, 'HEIGHT', 'Taille', 'ENUM'),
    (2, 'COLOR', 'Couleur', 'STRING');

INSERT INTO attribute_values(attribute_id, values_id) VALUES
    (1,1),
    (1,2);

INSERT INTO category(id, code, name, type) VALUES
    (1,'PU100','Loisirs','PU'),
    (2,'PF1000','Sport','PF'),
    (3,'PSF1000','Raquette','PSF'),
    (4,'PSSF3000','Tenis','PSSF'),
    (5,'PSSF3001','Squash','PSSF');

INSERT INTO category_children VALUES
    (1,2),
    (2,3),
    (3,4),
    (3,5);

INSERT INTO category_attributes VALUES
    (3,1),
    (5,2);