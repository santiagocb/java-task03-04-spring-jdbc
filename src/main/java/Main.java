import com.tuspring.FileData;
import com.tuspring.config.ApplicationConfig;
import com.tuspring.config.DatabaseSetup;
import com.tuspring.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        DatabaseSetup dbSetUp = context.getBean(DatabaseSetup.class);
        dbSetUp.dropTables();
        dbSetUp.createTables();

        FileService fileService = context.getBean(FileService.class);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a the name of the heavy file with its corresponding extension: ");
        String heavyFileName = scanner.nextLine();

        String filePathPrefix = "files/";

        String lightWeightFilePath = filePathPrefix + "lightweight-file.txt";
        String heavyFilePath = filePathPrefix + heavyFileName;

        // Storing and reading lightweight file
        fileService.storeFile(lightWeightFilePath);

        FileData lightFileData = fileService.fetchFileById(1);
        logger.info(String.format("[1] File name: %s", lightFileData.getFileName()));
        logger.info(String.format("[1] File size: %s bytes", lightFileData.getFileSize()));
        fileService.showFileFirstLines(lightFileData);

        // Storing and reading heavy file
        fileService.storeFile(heavyFilePath);

        FileData heavyFileData = fileService.fetchFileById(2);
        logger.info(String.format("[2] File name: %s", heavyFileData.getFileName()));
        logger.info(String.format("[2] File size: %s bytes", heavyFileData.getFileSize()));
        fileService.showFileFirstLines(heavyFileData);

        context.close();
    }
}
