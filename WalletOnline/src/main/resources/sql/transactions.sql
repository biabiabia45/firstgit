DROP TABLE IF EXISTS transaction;
CREATE TABLE transaction
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    source_wallet_id BIGINT         NOT NULL,
    target_wallet_id BIGINT         NOT NULL,
    amount           DECIMAL(19, 2) NOT NULL
);
