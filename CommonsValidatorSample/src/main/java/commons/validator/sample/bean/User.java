package commons.validator.sample.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ユーザー。
 */
@Data
@AllArgsConstructor
public class User {

    /** 名前 */
    private String name;

    /** 年齢 */
    private String age;

}
