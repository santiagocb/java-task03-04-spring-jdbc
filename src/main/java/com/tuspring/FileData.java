package com.tuspring;

import java.io.InputStream;

public class FileData {
    private final String fileName;
    private final long fileSize;
    private final InputStream fileData;

    public FileData(String fileName, long fileSize, InputStream fileData) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public InputStream getFileData() {
        return fileData;
    }
}
