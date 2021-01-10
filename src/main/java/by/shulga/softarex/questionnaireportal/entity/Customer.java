package by.shulga.softarex.questionnaireportal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Response> responseList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Field> fieldList = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public Customer() {}

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Customer(List<Response> responseList, String email, String password) {
        this.responseList = responseList;
        this.email = email;
        this.password = password;
    }

    public Customer(List<Response> responseList, String email, String password, String firstName, String lastName,
                    String phoneNumber) {
        this.responseList = responseList;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Customer(List<Response> responseList, List<Field> fieldList, String email, String password,
                    String firstName, String lastName, String phoneNumber) {
        this.responseList = responseList;
        this.fieldList = fieldList;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public Long getId() {
        return id;
    }

    public List<Response> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Response> responseList) {
        this.responseList = responseList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
