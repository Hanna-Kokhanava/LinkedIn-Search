package utils.tools;

import java.io.File;

/**
 * Created on 29.03.2018
 */
public class FileManager {
    public enum ResultFolder {
        REPORT_FOLDER("reports");

        private String folderName;

        ResultFolder(String folderName) {
            this.folderName = folderName;
        }

        @Override
        public String toString() {
            return this.folderName;
        }

        public File getLocalDir() {
            return getDirectory(this);
        }

    }

    private static File getDirectory(ResultFolder folder) {
        File directory = new File(folder.toString());
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Unable to create destination folder : " + folder.toString());
            return null;
        }
        return directory;
    }
}
