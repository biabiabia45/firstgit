DROP TABLE IF EXISTS wallet;
CREATE TABLE wallet (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT,
                        balance DECIMAL(19, 2)
);
