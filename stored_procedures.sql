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
