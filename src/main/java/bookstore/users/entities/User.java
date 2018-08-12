package bookstore.users.entities;


import bookstore.infrastructure.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "customer_street")),
            @AttributeOverride(name = "city", column = @Column(name = "customer_city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "customer_zip_code")),
            @AttributeOverride(name = "country", column = @Column(name = "customer_country"))
    })
    private UserAddress userAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "order_street")),
            @AttributeOverride(name = "city", column = @Column(name = "order_city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "order_zip_code")),
            @AttributeOverride(name = "country", column = @Column(name = "order_country"))
    })
    private UserAddress orderAddress;
    @Column(name = "first_name")
    private String firstName;
    private String lastName;
    private String birthDate;
    private String pesel;
    private String email;
    private String passwordHash;
    private String phone;
    private boolean preferEmails;
}
