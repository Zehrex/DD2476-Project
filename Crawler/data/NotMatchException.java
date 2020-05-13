1
https://raw.githubusercontent.com/rubywooJ/beyond/master/src/main/java/cn/tsxygfy/beyond/exception/NotMatchException.java
package cn.tsxygfy.beyond.exception;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author ruby woo
 * @version v1.0.0
 * @see cn.tsxygfy.beyond.exception
 * @since 2020-04-06 16:54:33
 */
public class NotMatchException extends BaseException {
    public NotMatchException(String message) {
        super(message);
    }

    public NotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
