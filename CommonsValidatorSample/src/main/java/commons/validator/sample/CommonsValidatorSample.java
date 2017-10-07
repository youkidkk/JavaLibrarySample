package commons.validator.sample;

import commons.validator.sample.bean.User;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResult;
import org.apache.commons.validator.ValidatorResults;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * commons-validator サンプル。
 */
public class CommonsValidatorSample {

    /** バリデーターリソース */
    private ValidatorResources resources;

    /**
     * コンストラクタ。
     *
     * @param resourceName バリデーターリソース名
     * @throws IOException IO例外時
     * @throws SAXException SAX例外時
     */
    public CommonsValidatorSample(String resourceName) throws IOException, SAXException {
        try (InputStream inStream = CommonsValidatorSample.class
                .getResourceAsStream("validator-name-required.xml")) {
            this.resources = new ValidatorResources(inStream);
        }
    }

    /**
     * バリデーション。
     *
     * @param user ユーザー
     * @throws Exception 例外時
     */
    public void validate(User user) throws Exception {
        Validator validator = new Validator(this.resources, "userCheck");
        validator.setParameter(Validator.BEAN_PARAM, user);

        ValidatorResults results = validator.validate();

        for (Object propertyName : results.getPropertyNames()) {
            ValidatorResult result = results.getValidatorResult((String) propertyName);
            Iterator<String> actions = result.getActions();
            while (actions.hasNext()) {
                String actionName = actions.next();
                if (!result.isValid(actionName)) {
                    ValidatorAction action = this.resources.getValidatorAction(actionName);
                    System.out.println(
                            MessageFormat.format("Error : " + action.getMsg(), propertyName));
                }
            }
        }
    }

    /**
     * メイン処理。
     *
     * @param args パラメータ。
     * @throws Exception 例外時
     */
    public static void main(String[] args) throws Exception {
        CommonsValidatorSample validator = new CommonsValidatorSample(
                "validator-name-required.xml");

        List<User> users = Arrays.asList(new User("", "20"), new User("hoge", "151"));

        for (User user : users) {
            System.out.println(user);
            validator.validate(user);
        }
    }

}
