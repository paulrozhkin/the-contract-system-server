-- Start transaction and plan the tests.
BEGIN;
SELECT plan(14);

-- Run the tests.
SELECT has_table('contract_notifications');
SELECT has_table('contracts');
SELECT has_table('files');
SELECT has_table('rank_history');
SELECT has_table('roles');
SELECT has_table('user_roles');
SELECT has_table('users');

SELECT columns_are(
    'contract_notifications',
    ARRAY[ 'id', 'contract', 'old_status', 'new_status', 'confirmed', 'created', 'updated' ]
);

SELECT columns_are(
    'contracts',
    ARRAY[ 'id', 'customer', 'executor', 'name', 'reward', 'min_rank', 'address', 'created', 'status', 'description', 'customer_comment', 'registrar_comment', 'performed_comment', 'updated', 'cancellation_comment']

);

SELECT columns_are(
    'files',
    ARRAY[ 'id', 'original_name', 'local_name', 'created', 'updated' ]
);

SELECT columns_are(
    'rank_history',
    ARRAY[ 'id', 'adventurer', 'old_rank', 'new_rank', 'type', 'reason', 'distributor', 'contract', 'created', 'updated' ]
);

SELECT columns_are(
    'roles',
    ARRAY[ 'id', 'name', 'created', 'updated' ]
);

SELECT columns_are(
    'user_roles',
    ARRAY[ 'user_id', 'role_id' ]
);

SELECT columns_are(
    'users',
    ARRAY[ 'id', 'username', 'password', 'avatar', 'name', 'address', 'blocked', 'adventurer_status', 'adventurer_experience', 'adventurer_rank', 'created', 'updated', 'adventurer_reason' ]
);

-- Finish the tests and clean up.
SELECT * FROM finish();
ROLLBACK;