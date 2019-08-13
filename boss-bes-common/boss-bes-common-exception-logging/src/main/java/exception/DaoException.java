package exception;

import vo.ResultEnum;

/**
 * Dao层 的异常抛出
 *
 * @author fishkk
 * @version 1.0.0
 * @since 2019/8/3
 */
public class DaoException extends AppException {

    public DaoException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public DaoException(String message, String code) {
        super(message, code);
    }
}
