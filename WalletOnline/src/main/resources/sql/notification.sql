DROP TABLE IF EXISTS notification;

CREATE TABLE notification
(
    id        CHAR(36) PRIMARY KEY,
    user_id   CHAR(36)   NOT NULL,
    wallet_id CHAR(36)   NOT NULL,
    message   TEXT       NOT NULL,
    timestamp TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    read      TINYINT(1) NOT NULL DEFAULT 0
);