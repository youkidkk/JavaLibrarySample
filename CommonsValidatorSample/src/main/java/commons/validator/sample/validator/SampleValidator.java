package commons.validator.sample.validator;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.util.ValidatorUtils;

/**
 * サンプルバリデーター。
 */
public class SampleValidator {

    /**
     * 必須バリデーター。
     *
     * @param bean 対象のBean
     * @param field 対象のフィールド
     * @return 結果
     */
    public static boolean validateRequired(Object bean, Field field) {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        return !GenericValidator.isBlankOrNull(value);
    }

    /**
     * 年齢バリデーター。
     *
     * @param bean 対象のBean
     * @param field 対象のフィールド
     * @return 結果
     */
    public static boolean validateAge(Object bean, Field field) {
        String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

        if (!GenericValidator.isInt(value)) {
            return false;
        } else if (!GenericValidator.isInRange(Integer.parseInt(value), 0, 150)) {
            return false;
        } else {
            return true;
        }
    }

}
