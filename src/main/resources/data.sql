DELETE FROM TRANSACTIONS;

DELETE FROM WALLETS;

INSERT INTO
    WALLETS (
        ID, FULL_NAME, DOCUMENT, EMAIL, "TYPE", BALANCE
    )
VALUES (
        1, 'Joao - User', '12345678900', 'joao@test.com', 1, 1000.00
    );

INSERT INTO
    WALLETS (
        ID, FULL_NAME, DOCUMENT, EMAIL, "TYPE", BALANCE
    )
VALUES (
        2, 'Maria - Lojista', '12345678901', 'maria@test.com', 2, 1000.00
    );