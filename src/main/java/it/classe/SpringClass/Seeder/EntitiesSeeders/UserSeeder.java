package it.classe.SpringClass.Seeder.EntitiesSeeders;

import it.classe.SpringClass.Model.Users;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class UserSeeder extends AbstractSeeder<Users> {

    public UserSeeder(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Users> getEntityClass() {
        return Users.class;
    }

    @Override
    protected Users createEntity(int index) {

        Users user = new Users();

        user.setUsername(
                "user" + index
        );

        user.setPassword(
                "pass" + index
        );

        user.setEmail(
                "user" + index + "@example.com"
        );

        return user;
    }
}