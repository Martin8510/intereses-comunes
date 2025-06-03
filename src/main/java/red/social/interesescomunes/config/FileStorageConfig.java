package red.social.interesescomunes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import red.social.interesescomunes.file.infrastructure.output.storage.LocalFileStorageAdapter;

@Configuration
public class FileStorageConfig {
    @Bean
    public LocalFileStorageAdapter localFileStorageAdapter() {
        return new LocalFileStorageAdapter();
    }
}