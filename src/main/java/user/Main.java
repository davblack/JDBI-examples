package user;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import java.time.LocalDate;

public class Main {

        public static void main(String[] args) {
            Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
            jdbi.installPlugin(new SqlObjectPlugin());
            try (Handle handle = jdbi.open()) {
                UserDao dao = handle.attach(UserDao.class);
                dao.createTable();

                User user1 = User.builder()
                        .name("James Bond")
                        .username("007")
                        .password("bigpp")
                        .email("jamesbond@bigpp.com")
                        .gender(User.Gender.MALE)
                        .dob(LocalDate.parse("1920-11-11"))
                        .enabled(true)
                        .build();
                dao.insertUser(user1);

                User user2 = User.builder()
                        .name("Bond James")
                        .username("700")
                        .password("ppbig")
                        .email("bigpp@jamesbond.com")
                        .gender(User.Gender.MALE)
                        .dob(LocalDate.parse("1911-12-01"))
                        .enabled(false)
                        .build();
                dao.insertUser(user2);

                User user3 = User.builder()
                        .name("Bondita Bondita")
                        .username("777")
                        .password("pupu")
                        .email("bondita@pupu.com")
                        .gender(User.Gender.FEMALE)
                        .dob(LocalDate.parse("1922-11-19"))
                        .enabled(true)
                        .build();
                dao.insertUser(user3);

                dao.list().stream().forEach(System.out::println);
                dao.findByUsername("700").stream().forEach(System.out::println);
                dao.findById(2).stream().forEach(System.out::println);
                dao.delete(user2);

             }
        }

}
