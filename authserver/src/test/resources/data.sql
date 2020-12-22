INSERT INTO PUBLIC.USERS (AUTHORITY, PASSWORD, USERNAME)
VALUES ('read', '$2a$10$F79Z2jO0qWmv/vmjHoQrieBNJn.W2PfEdT3NvDadQATfmqn82iCfu', 'john');


INSERT INTO PUBLIC.CLIENTS (ID, CLIENT_ID, SECRET, GRANT_TYPES, SCOPES, ACCESS_TOKEN_VALIDITY, AUTHORITIES,
                            REDIRECT_URIS, REFRESH_TOKEN_VALIDITY, RESOURCE_IDS)
VALUES (1, 'client-p', '$2a$10$jSzgoq7MvWFKZ6OK8Zg0Q.ebVEErJHJ./j1.9abPJAQ0lsQdUpsoW', 'password,refresh_token', 'read',
        null, null, null, null, null);

INSERT INTO PUBLIC.CLIENTS (ID, CLIENT_ID, SECRET, GRANT_TYPES, SCOPES, ACCESS_TOKEN_VALIDITY, AUTHORITIES,
                            REDIRECT_URIS, REFRESH_TOKEN_VALIDITY, RESOURCE_IDS)
VALUES (2, 'client-ac', '$2a$10$QniHXRUYLvm3Te0Wp7AAhu1S9HIEDeqOu4KhjqnzVtrgnwGyBfxfa',
        'authorization_code,refresh_token', 'read', null, null, null, null, null);

INSERT INTO PUBLIC.CLIENTS (ID, CLIENT_ID, SECRET, GRANT_TYPES, SCOPES, ACCESS_TOKEN_VALIDITY, AUTHORITIES,
                            REDIRECT_URIS, REFRESH_TOKEN_VALIDITY, RESOURCE_IDS)
VALUES (3, 'client-c', '$2a$10$bjGOZImHaZC38t2eaMXT1uFjdayAjRzna2J.kLZTwocSDaKUocly6', 'client_credentials', 'info',
        null, null, null, null, null);
