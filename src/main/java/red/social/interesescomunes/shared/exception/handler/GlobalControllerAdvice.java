package red.social.interesescomunes.shared.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import red.social.interesescomunes.shared.exception.http.BadRequestException;
import red.social.interesescomunes.shared.exception.http.ForbiddenException;
import red.social.interesescomunes.shared.exception.http.NotFoundException;
import red.social.interesescomunes.shared.exception.model.ErrorResponse;
import red.social.interesescomunes.shared.exception.model.FieldErrorDetail;
import red.social.interesescomunes.shared.exception.validation.ConflictException;
import red.social.interesescomunes.shared.exception.validation.ValidationException;
import red.social.interesescomunes.utils.ErrorCatalog;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse badRequest(HttpServletRequest request, BadRequestException exception){
        return ErrorResponse.builder()
                .exception(exception.getClass().getSimpleName())
                .code(exception.getCode())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public ErrorResponse forbiddenRequest(HttpServletRequest request, ForbiddenException exception){
        return ErrorResponse.builder()
                .exception(exception.getClass().getSimpleName())
                .code(exception.getCode())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse notFoundRequest(HttpServletRequest request, NotFoundException exception){
        return ErrorResponse.builder()
                .exception(exception.getClass().getSimpleName())
                .code(exception.getCode())
                .message(exception.getMessage())
                .details(List.of(exception.getDetail()))
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ErrorResponse notFoundRequest(HttpServletRequest request, ConflictException exception){
        return ErrorResponse.builder()
                .exception(exception.getClass().getSimpleName())
                .code(exception.getCode())
                .message(exception.getMessage())
                .details(List.of(exception.getDetail()))
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                      HttpServletRequest request) {
        List<FieldErrorDetail> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new FieldErrorDetail(
                        error.getField(),
                        this.determineErrorCode(error),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());

        ValidationException validationException = new ValidationException(fieldErrors);

        return ErrorResponse.builder()
                .exception(validationException.getClass().getSimpleName())
                .code(ErrorCatalog.VALIDATION_ERROR.getCode())
                .message(ErrorCatalog.VALIDATION_ERROR.getMessage())
                .path(request.getRequestURI())
                .fieldErrors(fieldErrors)
                .timestamp(LocalDateTime.now())
                .build();
    }

    private String determineErrorCode(FieldError fieldError) {
        if (fieldError.getCode() == null) {
            return ErrorCatalog.INVALID_FIELD_FORMAT.getCode();
        }

        switch (fieldError.getCode()) {
            case "NotBlank":
            case "NotNull":
                return ErrorCatalog.MISSING_REQUIRED_FIELD.getCode();
            case "Size":
            case "Length":
                return ErrorCatalog.FIELD_SIZE_EXCEEDED.getCode();
            case "Email":
                return ErrorCatalog.INVALID_EMAIL_FORMAT.getCode();
            case "Pattern":
                return ErrorCatalog.INVALID_FIELD_FORMAT.getCode();
            case "DateTimeFormat":
                return ErrorCatalog.INVALID_DATE_FORMAT.getCode();
            default:
                return ErrorCatalog.INVALID_FIELD_FORMAT.getCode();
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception){
        return ErrorResponse.builder()
                .exception(exception.getClass().getSimpleName())
                .code(ErrorCatalog.GENERIC_ERROR.getCode())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
