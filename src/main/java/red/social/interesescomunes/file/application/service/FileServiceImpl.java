package red.social.interesescomunes.file.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import red.social.interesescomunes.file.application.input.IFileManagerPort;
import red.social.interesescomunes.file.application.output.IFileStoragePort;
import red.social.interesescomunes.file.domain.exception.FileNotFoundException;
import red.social.interesescomunes.file.domain.exception.FileStorageException;
import red.social.interesescomunes.file.domain.model.StoredFile;

@Service
public class FileServiceImpl implements IFileManagerPort {

    private final IFileStoragePort fileStorage;
    private final String baseUrl;

    public FileServiceImpl(IFileStoragePort fileStorage,
                           @Value("${app.file.base-url}") String baseUrl) {
        this.fileStorage = fileStorage;
        this.baseUrl = baseUrl;
    }

    @Override
    public StoredFile store(MultipartFile file, String context) {
        try {
            return fileStorage.store(file, context);
        } catch (Exception e) {
            throw new FileStorageException("Failed to store file: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] retrieve(String filename) {
        try {
            return fileStorage.retrieve(filename);
        } catch (Exception e) {
            throw new FileNotFoundException("File not found: " + filename);
        }
    }

    @Override
    public void delete(String filename) {
        try {
            fileStorage.delete(filename);
        } catch (Exception e) {
            throw new FileStorageException("Failed to delete file: " + filename, e);
        }
    }


    public String getFileUrl(String filename) {
        return baseUrl + "/" + filename;
    }
}
