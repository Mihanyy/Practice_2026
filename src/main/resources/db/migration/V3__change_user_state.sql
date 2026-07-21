ALTER TABLE users RENAME COLUMN is_blocked TO state;

ALTER TABLE users ALTER COLUMN state TYPE VARCHAR(50);

UPDATE users
SET state = CASE
    WHEN state = 'false' THEN 'UNBLOCKED'
    WHEN state = 'true'  THEN 'BLOCKED'
END;