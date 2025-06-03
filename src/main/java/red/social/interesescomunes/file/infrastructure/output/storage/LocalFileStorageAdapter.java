package red.social.interesescomunes.file.infrastructure.output.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import red.social.interesescomunes.file.application.output.IFileStoragePort;
import red.social.interesescomunes.file.domain.model.StoredFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class LocalFileStorageAdapter implements IFileStoragePort {

    @Value("${app.file.storage.path:./uploads}")
    private String storagePath;

    @Override
    public StoredFile store(MultipartFile file, String context) throws Exception {
        Path contextPath = Paths.get(storagePath, context);
        Files.createDirectories(contextPath);

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ?
                originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        String storedName = UUID.randomUUID() + fileExtension;

        Path targetLocation = contextPath.resolve(storedName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return StoredFile.builder()
                .originalName(originalFilename)
                .storedName(storedName)
                .contentType(file.getContentType())
                .path(targetLocation.toString())
                .url(context + "/" + storedName)
                .size(file.getSize())
                .build();
    }

    @Override
    public byte[] retrieve(String filepath) throws Exception {
        Path fullPath = Paths.get(storagePath, filepath);
        if (!Files.exists(fullPath)) {
            throw new IOException("File not found: " + filepath);
        }
        return Files.readAllBytes(fullPath);
    }

    @Override
    public void delete(String filepath) throws Exception {
        Path fullPath = Paths.get(storagePath, filepath);
        Files.deleteIfExists(fullPath);
    }
}