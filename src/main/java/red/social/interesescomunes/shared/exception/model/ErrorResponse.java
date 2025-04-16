package red.social.interesescomunes.shared.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private  String exception;
    private  String code;
    private  String message;
    private  String path;
    private  List<String> details;
    private  LocalDateTime timestamp;
    private  List<FieldErrorDetail> fieldErrors;
}
