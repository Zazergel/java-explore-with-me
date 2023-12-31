create TABLE IF NOT EXISTS apps
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

create TABLE IF NOT EXISTS stats
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    app_id    BIGINT REFERENCES apps (id) ON delete CASCADE,
    user_ip VARCHAR(15) NOT NULL,
    created TIMESTAMP NOT NULL,
    uri VARCHAR(255) NOT NULL
);