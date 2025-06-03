package red.social.interesescomunes.file.domain.exception;

public class FileNotFoundException extends FileStorageException {
    public FileNotFoundException(String message) {
        super(message);
    }
}