-- Start transaction and plan the tests.
BEGIN;
SELECT plan(6);

-- Run the tests.
SELECT col_is_pk('contract_notifications', 'id');
SELECT col_is_pk('contracts', 'id');
SELECT col_is_pk('files', 'id');
SELECT col_is_pk('rank_history', 'id');
SELECT col_is_pk('roles', 'id');
SELECT col_is_pk('users', 'id');

-- Finish the tests and clean up.
SELECT * FROM finish();
ROLLBACK;