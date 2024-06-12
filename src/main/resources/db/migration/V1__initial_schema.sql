-- Define custom type for account_type
CREATE TYPE account_type_enum AS ENUM (
    'SALARY_ACCOUNT',
    'JOINT_ACCOUNT',
    'CURRENT_ACCOUNT'
);

-- Define custom type for status
CREATE TYPE account_status_enum AS ENUM (
    'PENDING',
    'BANNED',
    'ACTIVATED',
    'CLOSED',
    'KYC_PENDING',
    'FAILED'
);

-- Create Account Table
CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    masked_id VARCHAR(20) UNIQUE NOT NULL,
    type account_type_enum, -- should be not null. for now bypassing
    -- password_hash BYTEA NOT NULL;
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    document_no VARCHAR(50) not null,
    status account_status_enum not null,
    balance DECIMAL(15, 2) not null;
);
 

-- Define custom enum for status
CREATE TYPE transaction_status_enum AS ENUM (
    'PENDING',
    'DONE',
    'CANCELED',
    'FAILED'
);

-- Define custom enum for transaction_type
CREATE TYPE transaction_type_enum AS ENUM (
    'NORMAL_PURCHASE',
    'PURCHASE_WITH_INSTALLMENTS',
    'WITHDRAWAL',
    'CREDIT_VOUCHER'
);


CREATE TABLE transaction_type (
    id SERIAL PRIMARY KEY,
    type transaction_type_enum UNIQUE NOT NULL
);

INSERT INTO transaction_type (type) VALUES 
    ('NORMAL_PURCHASE'),
    ('PURCHASE_WITH_INSTALLMENTS'),
    ('WITHDRAWAL'),
    ('CREDIT_VOUCHER');

-- Define custom enum for transaction_mode
CREATE TYPE transaction_mode_enum AS ENUM (
    'ONLINE',
    'OFFLINE',
    'ATM',
    'MOBILE'
);

-- Create Transaction Table
CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(15, 2) NOT NULL,
    -- initiated_by VARCHAR(64) NOT NULL, -- user id can come here
    initiated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status transaction_status_enum not null,
    account_id INT NOT NULL,
    transaction_type INT not null,
    transaction_mode transaction_mode_enum,
    FOREIGN KEY (account_id) REFERENCES accounts(id),
    FOREIGN KEY (transaction_type_id) REFERENCES transaction_type(id)
);

-- Create User Table
-- CREATE TABLE users (
--     id SERIAL PRIMARY KEY,
--     phone VARCHAR(20),
--     email VARCHAR(255) UNIQUE NOT NULL,
--     address VARCHAR(255),
--     state VARCHAR(100),
--     country VARCHAR(100),
--     district VARCHAR(100),
--     pan VARCHAR(20),
--     adhar_card VARCHAR(20),
--     name VARCHAR(255),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--     pincode VARCHAR(10),
--     type VARCHAR(50)
-- );

-- Account(One) to Many(User Mapping)
-- CREATE TABLE account_user_mapping (
--     id SERIAL PRIMARY KEY,
--     account_id INT NOT NULL,
--     user_id VARCHAR(64) NOT NULL,
--     CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES accounts(id),
--     CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
--     UNIQUE (account_id, user_id)
-- );
-- Nominee
-- Branch
-- Invoice
