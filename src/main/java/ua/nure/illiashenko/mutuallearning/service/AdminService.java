package ua.nure.illiashenko.mutuallearning.service;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

@Slf4j
@Service
public class AdminService {

    private final String dbName = "mutual_learning_db";
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${mySqlBinPath}")
    private String mySqlBinPath;

    public boolean backupDB() {
        try {
            File file = new File("backup");
            file.mkdir();
            String backupPath = file.getAbsolutePath();
            String savePath = "\"" + backupPath + "\\backup.sql\"";
            String executeCmd = mySqlBinPath + "\\mysqldump -u" + userName + " -p" + password + " --add-drop-database -B "
                + dbName+ " -r " + savePath;
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            return processComplete == 0;
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            throw new ServiceApiException(HttpStatus.BAD_REQUEST);
        }
    }

    public boolean restoreDB() {
        try {
            String executeCmd = mySqlBinPath + "\\mysql -u" + userName + " -p" + password + " -A -D "
                + dbName + " -e \"SOURCE backup\\backup.sql\"";
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            return processComplete == 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceApiException(HttpStatus.BAD_REQUEST);
        }
    }
}
