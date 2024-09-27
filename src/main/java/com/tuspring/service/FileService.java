package com.tuspring.service;

import com.tuspring.FileData;
import com.tuspring.dao.FileDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.SQLException;

@Component
public class FileService {

    public static final Logger logger = LoggerFactory.getLogger(FileService.class);

    private final FileDAO fileDAO;

    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public void storeFile(String fileName) {
        try (InputStream inputFile = new FileInputStream(fileName)) {
            long fileSize = new File(fileName).length();
            long sizeInMb = fileSize / (1024 * 1024);

            if (sizeInMb > 200) {
                logger.info(String.format("Trying to store a large file with %s MB.", sizeInMb));
            } else {
                logger.info(String.format("Trying to store a file with %s MB.", sizeInMb));
            }

            fileDAO.saveFile(fileName, fileSize, inputFile);
            logger.info(String.format("File %s [%s MB] saved successfully!", fileName, sizeInMb));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public FileData fetchFileById(long fileId) {
        return fileDAO.retrieveFile(fileId);
    }

    public void showFileFirstLine(FileData fileData) throws IOException {
        logger.info(String.format("Content (First line) of %s:", fileData.getFileName()));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileData.getFileData(), "UTF-8"))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null && lineCount < 5) {
                System.out.println(line);
                lineCount++;
            }
        }
    }
}
