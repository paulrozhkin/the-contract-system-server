INSERT INTO users(username, password, name, address)
SELECT md5(RANDOM()::TEXT), md5(RANDOM()::TEXT), md5(RANDOM()::TEXT), md5(RANDOM()::TEXT)
FROM generate_series(1, 10000);

INSERT INTO contracts(customer, name, reward, min_rank, address, status, description, customer_comment, registrar_comment)
SELECT (random() * 10 + 1)::int, md5(RANDOM()::TEXT), (random() * 100 + 1)::int, 'Porcelain', md5(RANDOM()::TEXT), 'Accepted', md5(RANDOM()::TEXT), md5(RANDOM()::TEXT), md5(RANDOM()::TEXT)
FROM generate_series(1, 10000);