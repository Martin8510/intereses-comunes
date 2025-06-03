package red.social.interesescomunes.membergroup.domain.exception;

import red.social.interesescomunes.shared.exception.http.NotFoundException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class MemberNotInGroupException extends NotFoundException {
    private static final String code = ErrorCatalog.MEMBER_GROUP_NOT_FOUND.getCode();
    private static final String message = ErrorCatalog.MEMBER_GROUP_NOT_FOUND.getMessage();

    public MemberNotInGroupException(String detail) {
        super(message, detail, code);
    }
}