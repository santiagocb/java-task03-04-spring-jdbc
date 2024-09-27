package com.tuspring.dao;

import com.tuspring.FileData;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

@Repository
public class FileDAO {

    private final DataSource dataSource;

    public FileDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveFile(String fileName, long fileSize, InputStream fileData) throws SQLException {

        String SAVE_FILE_SQL = "call save_file(?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(SAVE_FILE_SQL)) {

            stmt.setString(1, fileName);
            stmt.setLong(2, fileSize);
            stmt.setBinaryStream(3, fileData);

            stmt.execute();
        }
    }

    public FileData retrieveFile(long fileId) {

        FileData file = null;
        String GET_FILE_SQL = "SELECT * FROM get_file(?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_FILE_SQL)) {

            stmt.setLong(1, fileId);

            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                String fileName = resultSet.getString(1);
                long fileSize = resultSet.getLong(2);
                InputStream fileData = resultSet.getBinaryStream(3);
                file = new FileData(fileName, fileSize, fileData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public List<String> getAllFileIds() {
        throw new UnsupportedOperationException();
    }
}
