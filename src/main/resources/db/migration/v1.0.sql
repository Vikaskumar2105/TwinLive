-- Drop the table if it exists
DROP TABLE IF EXISTS app_user;

-- Create the new table
CREATE TABLE twinuser (
    id SERIAL PRIMARY KEY,
    phone_number VARCHAR(15) NOT NULL,
    otp VARCHAR(10) NOT NULL,
    is_verified BOOLEAN DEFAULT false,
    otp_expiry TIMESTAMP
);
