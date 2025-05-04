package core.helpers;

import net.datafaker.Faker;

public class RandomUserData {

    Faker faker = new Faker();

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail(){
        return faker.internet().emailAddress();
    }

}
