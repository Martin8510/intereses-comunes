package red.social.interesescomunes.file.application.input;

import org.springframework.web.multipart.MultipartFile;
import red.social.interesescomunes.file.domain.model.StoredFile;

public interface IFileManagerPort {
    StoredFile store(MultipartFile file, String context) throws Exception;
    byte[] retrieve(String filepath) throws Exception;
    void delete(String filepath) throws Exception;
}
