package jackson.sample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * パーソンクラス
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuppressWarnings("javadoc")
public class Person {

    private String name;
    private String email;

    private List<Person> friends;

}
