package com.tuspring.dao;

import com.tuspring.FileData;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;

public class FileDAO {

    private final DataSource dataSource;

    public FileDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveFile(String fileName, long fileSize, InputStream fileData) throws SQLException {
        String sql = "call save_file(?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, fileName);
            stmt.setLong(2, fileSize);
            stmt.setBinaryStream(3, fileData);

            stmt.execute();
        }
    }

    public void retrieveFile(long fileId) {
        String sql = "call get_file(?)";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("SELECT * FROM get_file(?)")) {

            stmt.setLong(1, 1);  // Replace 1 with a valid file ID

            stmt.execute();

            System.out.println("File Name: " + stmt.getString(2));
            System.out.println("File Size: " + stmt.getLong(3));

            byte[] fileDataBytes = stmt.getBytes(4);
            System.out.println("File Data: " + (fileDataBytes != null ? "Retrieved successfully" : "No data"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
