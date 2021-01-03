BEGIN;
INSERT INTO users(username, password, name, address)
VALUES ('test_user@gmail.com', '$2a$10$9dUk4KvXuokc9Pj.6Ko0z.BFTgMKryauaSyIO/dLYH6Mc3Mr1k7ne',
        'name test', 'test');

with user_test_id as (
    select id
    from users
    where username = 'test_user@gmail.com'
)
INSERT INTO user_roles(user_id, role_id)
SELECT id, 1 from user_test_id
    UNION ALL
SELECT id, 2 from user_test_id
    UNION ALL
SELECT id, 3 from user_test_id
        UNION ALL
SELECT id, 4 from user_test_id
        UNION ALL
SELECT id, 5 from user_test_id;
COMMIT;