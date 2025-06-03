package red.social.interesescomunes.file.application.output;

import org.springframework.web.multipart.MultipartFile;
import red.social.interesescomunes.file.domain.model.StoredFile;

public interface IFileStoragePort {
    StoredFile store(MultipartFile file, String context) throws Exception;
    byte[] retrieve(String filepath) throws Exception;
    void delete(String filepath) throws Exception;
}
