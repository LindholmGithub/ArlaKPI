package BE;

public class FileInfo {
    private String fileName;
    private String fileType;
    private String filePath;

    /**
     * constructor for the FileInfo BE Class
     * @param fileName
     * @param fileType
     * @param filePath
     */
    public FileInfo( String fileName, String fileType, String filePath) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    /**
     * Getter for the name of the file
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * setter for the fileName
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * getter for the fileType
     * @return
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * setter for the fileType
     * @param fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * getter for the filepath
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * setter for the filepath
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * To string method to show the fileName in our lists
     * @return
     */
    @Override
    public String toString() {
        return fileName;
    }
}
