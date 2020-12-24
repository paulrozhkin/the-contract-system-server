-- Start transaction and plan the tests.
BEGIN;
SELECT plan(44);

-- Run the tests.
-- 6
SELECT col_not_null('contract_notifications', 'contract');
SELECT col_not_null('contract_notifications', 'old_status');
SELECT col_not_null('contract_notifications', 'new_status');
SELECT col_not_null('contract_notifications', 'created');
SELECT col_not_null('contract_notifications', 'updated');
SELECT col_is_null('contract_notifications', 'confirmed');

-- 12
SELECT col_not_null('contracts', 'name');
SELECT col_not_null('contracts', 'reward');
SELECT col_is_null('contracts', 'min_rank');
SELECT col_is_null('contracts', 'address');
SELECT col_not_null('contracts', 'created');
SELECT col_not_null('contracts', 'status');
SELECT col_is_null('contracts', 'description');
SELECT col_is_null('contracts', 'customer_comment');
SELECT col_is_null('contracts', 'registrar_comment');
SELECT col_is_null('contracts', 'performed_comment');
SELECT col_not_null('contracts', 'updated');
SELECT col_is_null('contracts', 'cancellation_comment');

-- 4
SELECT col_not_null('files', 'original_name');
SELECT col_not_null('files', 'local_name');
SELECT col_not_null('files', 'created');
SELECT col_not_null('files', 'updated');

-- 7
SELECT col_not_null('rank_history', 'adventurer');
SELECT col_not_null('rank_history', 'old_rank');
SELECT col_not_null('rank_history', 'new_rank');
SELECT col_is_null('rank_history', 'distributor');
SELECT col_is_null('rank_history', 'contract');
SELECT col_not_null('rank_history', 'created');
SELECT col_not_null('rank_history', 'updated');

-- 3
SELECT col_not_null('roles', 'created');
SELECT col_not_null('roles', 'updated');

-- 14
SELECT col_not_null('users', 'username');
SELECT col_not_null('users', 'password');
SELECT col_not_null('users', 'updated');
SELECT col_is_null('users', 'avatar');
SELECT col_is_null('users', 'name');
SELECT col_is_null('users', 'address');
SELECT col_not_null('users', 'blocked');
SELECT col_is_null('users', 'adventurer_status');
SELECT col_is_null('users', 'adventurer_experience');
SELECT col_is_null('users', 'adventurer_rank');
SELECT col_is_null('users', 'adventurer_reason');
SELECT col_not_null('users', 'created');
SELECT col_not_null('users', 'updated');

-- Finish the tests and clean up.
SELECT * FROM finish();
ROLLBACK;