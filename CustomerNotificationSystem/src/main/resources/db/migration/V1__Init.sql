-- Create user table
CREATE TABLE IF NOT EXISTS users
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create customer table
CREATE TABLE IF NOT EXISTS customer
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    contact_number VARCHAR(255) NOT NULL
);

-- Create ENUM types for AddressType, NotificationType, and NotificationStatus
CREATE TYPE address_type AS ENUM ('EMAIL', 'SMS', 'POSTAL');
CREATE TYPE notification_type AS ENUM ('PROMOTION', 'TRANSACTION', 'ALERT', 'OTHER');
CREATE TYPE notification_status AS ENUM ('IN_PROGRESS', 'FINISHED', 'CANCELED');

-- Create Address table
CREATE TABLE address
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id  BIGINT      NOT NULL,
    type         address_type NOT NULL, -- enum for email, SMS, postal
    address_line TEXT        NOT NULL,
    active       BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

-- Create Preference table
CREATE TABLE preference
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id   BIGINT NOT NULL,
    email_opt_in  BOOLEAN DEFAULT FALSE,
    sms_opt_in    BOOLEAN DEFAULT FALSE,
    postal_opt_in BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

-- Create Notification table
CREATE TABLE notification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    notification_type VARCHAR(50) NOT NULL CHECK (notification_type IN ('PROMOTION', 'TRANSACTION', 'ALERT', 'OTHER')),
    status VARCHAR(50) NOT NULL CHECK (status IN ('IN_PROGRESS', 'FINISHED', 'CANCELED')),
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);


