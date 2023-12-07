INSERT INTO "user" (
    id,
    name,
    surname,
    email,
    password,
    document_number,
    role_id,
    document_type_id,
    date_created,
    last_updated
) VALUES (
    1200,
    'John',
    'Doe',
    'john@doe.com',
    '{bcrypt}$2a$10$3Cu8vkRYMsQWWV04D0IkZuqkgjwBwAMyUISdJzW59vUTzbKfSDX8K',
    '123456789',
    1000,
    1100,
    '2023-09-02 16:30:00',
    '2023-09-02 16:30:00'
);

INSERT INTO "user" (
    id,
    name,
    surname,
    email,
    password,
    document_number,
    role_id,
    document_type_id,
    date_created,
    last_updated
) VALUES (
    1201,
    'Daniel',
    'Cobos',
    'daniel@cobos.com',
    '{bcrypt}$2a$10$3Cu8vkRYMsQWWV04D0IkZuqkgjwBwAMyUISdJzW59vUTzbKfSDX8K',
    '123456789',
    1000,
    1101,
    '2023-09-03 16:30:00',
    '2023-09-03 16:30:00'
);
