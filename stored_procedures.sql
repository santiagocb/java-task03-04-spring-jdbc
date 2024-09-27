DROP PROCEDURE IF EXISTS save_file(character varying,bigint,bytea);
CREATE OR REPLACE PROCEDURE save_file(
  IN fileName VARCHAR(255),
  IN fileSize BIGINT,
  IN fileData BYTEA
)
LANGUAGE plpgsql
AS $$
BEGIN
  INSERT INTO FileShare (fileName, fileSize, fileData)
  VALUES (fileName, fileSize, fileData);
END;
$$;

DROP FUNCTION IF EXISTS get_file(bigint);
CREATE OR REPLACE FUNCTION get_file(
    file_id BIGINT
) RETURNS TABLE (
    fileName VARCHAR,
    fileSize BIGINT,
    fileData BYTEA
)
AS $$
BEGIN
  RETURN QUERY
  SELECT f.fileName, f.fileSize, f.fileData
  FROM FileShare f
  WHERE id = file_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE get_file_prod(
  IN file_id BIGINT,
  OUT fileName VARCHAR(255),
  OUT fileSize BIGINT,
  OUT file_data BYTEA
)
LANGUAGE plpgsql
AS $$
BEGIN
  SELECT f.fileName, f.fileSize, f.fileData
  FROM FileShare f
  WHERE id = file_id;
END;
$$;
