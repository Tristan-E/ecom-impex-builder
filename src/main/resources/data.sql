INSERT INTO attribute_value(id, code, value) VALUES
    (1, 'HEIGHT-1', '10 cm'),
    (2, 'HEIGHT-2', '20 cm');

INSERT INTO attribute(id, code, name, type) VALUES
    (1, 'HEIGHT', 'Taille', 'ENUM'),
    (2, 'COLOR', 'Couleur', 'STRING');

INSERT INTO attribute_values(attribute_id, values_id) VALUES
    (1,1),
    (1,2);

INSERT INTO category(id, name, type, parent_id) VALUES
    (1,'Loisirs','PU',null),
    (2,'Sport','PF',1),
    (3,'Raquette','PSF',2),
    (4,'Tenis','PSSF',3),
    (5,'Squash','PSSF',3);

INSERT INTO category_children VALUES
    (1,2),
    (2,3),
    (3,4),
    (3,5);

INSERT INTO category_attributes VALUES
    (3,1),
    (5,2);