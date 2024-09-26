import com.tuspring.FileData;
import com.tuspring.config.ApplicationConfig;
import com.tuspring.config.DatabaseSetup;
import com.tuspring.dao.FileDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;


public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        DatabaseSetup dbSetUp = context.getBean(DatabaseSetup.class);
        dbSetUp.dropTables();
        dbSetUp.createTables();

        // Step 2: Create FileDAO instance
        FileDAO fileDAO = new FileDAO(context.getBean(DataSource.class));

        // Step 3: Save a file to the database
        try (InputStream inputFile = new FileInputStream("file.txt")) {
            String fileName = "file.txt";
            long fileSize = new File("file.txt").length();

            // Save the file using the DAO
            fileDAO.saveFile(fileName, fileSize, inputFile);
            logger.info(String.format("File %s [%s] saved successfully!", fileName, fileSize));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        // Step 4: Retrieve file from the database and show its metadata and first few lines

        long fileId = 1;  // Change this to the ID of the file you want to retrieve
        fileDAO.retrieveFile(fileId);

        /*
        // Show file metadata
        System.out.println("File Name: " + fileData.getFileName());
        System.out.println("File Size: " + fileData.getFileSize() + " bytes");

        // Show the first few lines of the file (if it's a text file)
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(fileData.getFileData(), "UTF-8"))) {

            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null && lineCount < 5) {
                System.out.println(line);
                lineCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */
        context.close();
    }
}
