package pl.kraleppa.model.request;

import lombok.Getter;
import lombok.Setter;
import pl.kraleppa.model.entity.MyUser;

@Getter
@Setter
public class Credentials {
    private String email;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String zip;

    public Credentials(MyUser myUser){
        email = myUser.getEmail();
        name = myUser.getName();
        surname = myUser.getSurname();
        address = myUser.getAddress();
        city = myUser.getCity();
        zip = myUser.getZip();
    }
}
