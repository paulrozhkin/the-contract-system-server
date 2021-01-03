-- Start transaction and plan the tests.
BEGIN;
SELECT plan(43);

-- Run the tests.
-- 6
SELECT col_type_is('contract_notifications', 'contract', 'integer');
SELECT col_type_is('contract_notifications', 'old_status', 'text');
SELECT col_type_is('contract_notifications', 'new_status', 'text');
SELECT col_type_is('contract_notifications', 'created', 'timestamp with time zone');
SELECT col_type_is('contract_notifications', 'updated', 'timestamp with time zone');
SELECT col_type_is('contract_notifications', 'confirmed', 'boolean');

-- 12
SELECT col_type_is('contracts', 'name', 'text');
SELECT col_type_is('contracts', 'reward', 'integer');
SELECT col_type_is('contracts', 'min_rank', 'text');
SELECT col_type_is('contracts', 'address', 'text');
SELECT col_type_is('contracts', 'created', 'timestamp with time zone');
SELECT col_type_is('contracts', 'status', 'text');
SELECT col_type_is('contracts', 'description', 'text');
SELECT col_type_is('contracts', 'customer_comment', 'text');
SELECT col_type_is('contracts', 'registrar_comment', 'text');
SELECT col_type_is('contracts', 'performed_comment', 'text');
SELECT col_type_is('contracts', 'updated', 'timestamp with time zone');
SELECT col_type_is('contracts', 'cancellation_comment', 'text');

-- 4
SELECT col_type_is('files', 'original_name', 'text');
SELECT col_type_is('files', 'local_name', 'text');
SELECT col_type_is('files', 'created', 'timestamp with time zone');
SELECT col_type_is('files', 'updated', 'timestamp with time zone');

-- 7
SELECT col_type_is('rank_history', 'adventurer', 'integer');
SELECT col_type_is('rank_history', 'old_rank', 'text');
SELECT col_type_is('rank_history', 'new_rank', 'text');
SELECT col_type_is('rank_history', 'distributor', 'integer');
SELECT col_type_is('rank_history', 'contract', 'integer');
SELECT col_type_is('rank_history', 'created', 'timestamp with time zone');
SELECT col_type_is('rank_history', 'updated', 'timestamp with time zone');

-- 3
SELECT col_type_is('roles', 'created', 'timestamp with time zone');
SELECT col_type_is('roles', 'updated', 'timestamp with time zone');

-- 14
SELECT col_type_is('users', 'username', 'text');
SELECT col_type_is('users', 'password', 'text');
SELECT col_type_is('users', 'avatar', 'integer');
SELECT col_type_is('users', 'name', 'text');
SELECT col_type_is('users', 'address', 'text');
SELECT col_type_is('users', 'blocked', 'boolean');
SELECT col_type_is('users', 'adventurer_status', 'text');
SELECT col_type_is('users', 'adventurer_experience', 'integer');
SELECT col_type_is('users', 'adventurer_rank', 'text');
SELECT col_type_is('users', 'adventurer_reason', 'text');
SELECT col_type_is('users', 'created', 'timestamp with time zone');
SELECT col_type_is('users', 'updated', 'timestamp with time zone');

-- Finish the tests and clean up.
SELECT * FROM finish();
ROLLBACK;