-- Start transaction and plan the tests.
BEGIN;
SELECT plan(2);

-- Run the tests.
SELECT lives_ok(
    'INSERT INTO users(username, password, name, address) VALUES (md5(RANDOM()::TEXT), md5(RANDOM()::TEXT), md5(RANDOM()::TEXT), md5(RANDOM()::TEXT))',
    'New user created'
);

SELECT throws_imatching(
    'INSERT INTO users(password, name, address) VALUES (md5(RANDOM()::TEXT), md5(RANDOM()::TEXT), md5(RANDOM()::TEXT))',
    'null value in column "username" violates not-null constraint'
);

-- Finish the tests and clean up.
SELECT * FROM finish();
ROLLBACK;