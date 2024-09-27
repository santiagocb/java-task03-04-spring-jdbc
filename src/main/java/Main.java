import com.tuspring.FileData;
import com.tuspring.config.ApplicationConfig;
import com.tuspring.config.DatabaseSetup;
import com.tuspring.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        DatabaseSetup dbSetUp = context.getBean(DatabaseSetup.class);
        dbSetUp.dropTables();
        dbSetUp.createTables();

        FileService fileService = context.getBean(FileService.class);

        String lightWeightFilePath = "files/lightweight-file.txt";

        String heavyFilePath = "files/heavy-file.dmg";

        // Storing and reading lightweight file
        fileService.storeFile(lightWeightFilePath);

        FileData lightFileData = fileService.fetchFileById(1);
        logger.info(String.format("Lightweight file name: %s", lightFileData.getFileName()));
        logger.info(String.format("Lightweight file size: %s bytes", lightFileData.getFileSize()));
        fileService.showFileFirstLine(lightFileData);

        // Storing and reading heavy file
        fileService.storeFile(heavyFilePath);

        FileData heavyFileData = fileService.fetchFileById(2);
        logger.info(String.format("Heavy file name: %s", heavyFileData.getFileName()));
        logger.info(String.format("Heavy file size: %s bytes", heavyFileData.getFileSize()));
        fileService.showFileFirstLine(heavyFileData);

        context.close();
    }
}
