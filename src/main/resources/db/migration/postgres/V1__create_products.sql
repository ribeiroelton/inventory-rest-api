CREATE TABLE IF NOT EXISTS suppliers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14)  NOT NULL,
    ie VARCHAR(14)
);

CREATE INDEX idx_supplier_name ON suppliers(name);
CREATE INDEX idx_supplier_cnpj ON suppliers(cnpj);

CREATE TABLE IF NOT EXISTS brands(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE INDEX idx_brands_name ON brands(name);

CREATE TABLE IF NOT EXISTS products(
    id SERIAL PRIMARY KEY,
    supplier_id INT NOT NULL,
    brand_id INT NOT NULL,
    name VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(200) NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    photo_url VARCHAR(300) NULL,
   CONSTRAINT fk_supplier
      FOREIGN KEY(supplier_id)
      REFERENCES suppliers(id)
      ON DELETE RESTRICT,
   CONSTRAINT fk_brand
      FOREIGN KEY(brand_id)
      REFERENCES brands(id)
      ON DELETE RESTRICT
);

CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_products_supplier_id ON products(supplier_id);
CREATE INDEX idx_products_brand_id ON products(brand_id);



