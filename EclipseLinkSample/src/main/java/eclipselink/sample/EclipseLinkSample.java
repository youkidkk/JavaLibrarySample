package eclipselink.sample;

import eclipselink.sample.entities.Child;
import eclipselink.sample.entities.Parent;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("javadoc")
public class EclipseLinkSample {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("sampleUnit")
                .createEntityManager();
        em.getTransaction().begin();
        Parent parent = new Parent();
        parent.setName("Parent");
        List<Child> childList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Child child = new Child();
            child.setName("Child" + i);
            child.setParent(parent);
            childList.add(child);
        }
        parent.setChildList(childList);
        em.persist(parent);
        em.getTransaction().commit();

        List<Parent> parentList = em.createNamedQuery("parentFindAll", Parent.class)
                .getResultList();
        for (Parent p : parentList) {
            System.out.println(p.getName());
            for (Child c : p.getChildList()) {
                System.out.println("\t" + c.getName());
            }
        }

    }

}
