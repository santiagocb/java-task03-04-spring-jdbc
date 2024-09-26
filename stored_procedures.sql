CREATE OR REPLACE PROCEDURE save_file(
  IN file_name VARCHAR(255),
  IN file_size BIGINT,
  IN file_data BYTEA
)
LANGUAGE plpgsql
AS $$
BEGIN
  INSERT INTO FileShare (file_name, file_size, file_data)
  VALUES (file_name, file_size, file_data);
END;
$$;

DROP FUNCTION IF EXISTS get_file(bigint);
CREATE OR REPLACE FUNCTION get_file(
    file_id BIGINT
) RETURNS TABLE (
    file_name VARCHAR,
    file_size BIGINT,
    file_data BYTEA
 )
AS $$
BEGIN
  RETURN QUERY SELECT file_name, file_size, file_data
  FROM FileShare
  WHERE id = file_id;
END; $$
LANGUAGE plpgsql;
