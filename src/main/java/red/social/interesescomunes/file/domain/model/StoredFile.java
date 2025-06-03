package red.social.interesescomunes.file.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoredFile  {
    private String originalName;
    private String storedName;
    private String contentType;
    private String path;
    private String url;
    private Long size;
}