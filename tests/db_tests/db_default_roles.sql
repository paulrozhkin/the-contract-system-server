-- Start transaction and plan the tests.
BEGIN;
SELECT plan(3);

-- Run the tests.
SELECT set_eq(
    'SELECT name FROM roles',
    ARRAY[ 'ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_ADVENTURER', 'ROLE_REGISTRAR', 'ROLE_DISTRIBUTOR']
);

SELECT set_ne(
    'SELECT name FROM roles',
    ARRAY[ 'ROLE_ADMIN', 'RANDOM_ROLE_ASSERT_FAIL', 'ROLE_CUSTOMER', 'ROLE_ADVENTURER', 'ROLE_REGISTRAR', 'ROLE_DISTRIBUTOR']
);

SELECT set_ne(
    'SELECT name FROM roles',
    ARRAY[ 'RANDOM_ROLE_ASSERT_FAIL', 'RANDOM_ROLE_ASSERT_FAIL_2']
);

-- Finish the tests and clean up.
SELECT * FROM finish();
ROLLBACK;