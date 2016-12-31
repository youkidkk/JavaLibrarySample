package eclipselink.sample.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import java.util.List;

@SuppressWarnings("javadoc")
@Entity
@NamedQuery(name = "parentFindAll", query = "select p from Parent p")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @Getter
    @Setter
    private List<Child> childList;

}
