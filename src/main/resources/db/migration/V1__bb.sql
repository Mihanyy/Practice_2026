-- Таблица пользователей
CREATE TABLE users (
            id BIGSERIAL PRIMARY KEY,
            name VARCHAR(100) NOT NULL,
            login VARCHAR(50)  NOT NULL UNIQUE,
            email VARCHAR(255) NOT NULL UNIQUE,
            password_hash VARCHAR(255) NOT NULL,
            role VARCHAR(10) NOT NULL,
            is_blocked BOOLEAN NOT NULL,
            created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Таблица объявлений
CREATE TABLE listings (
            id  BIGSERIAL PRIMARY KEY,
            user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
            category VARCHAR(100) NOT NULL,
            title VARCHAR(150) NOT NULL,
            description TEXT,
            price VARCHAR(100) NOT NULL,
            status VARCHAR(25) NOT NULL,
            created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
            updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Индексы
CREATE INDEX idx_listings_user_id ON listings(user_id);
CREATE INDEX idx_listings_status ON listings(status);
CREATE INDEX idx_listings_category ON listings(category);
CREATE INDEX idx_listings_created_at ON listings(created_at DESC);

-- Триггер для автообновления updated_at при изменении объявления
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER
LANGUAGE plpgsql
AS
$$
BEGIN
    NEW.updated_at = now();
RETURN NEW;
END;
$$;

CREATE TRIGGER trg_listings_updated_at
    BEFORE UPDATE ON listings
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();
