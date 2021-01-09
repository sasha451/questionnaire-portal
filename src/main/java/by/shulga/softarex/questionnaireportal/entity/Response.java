package by.shulga.softarex.questionnaireportal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private Long id;

    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ResponseEntry> responseEntryList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;

    public Response() {}

    public Response(List<ResponseEntry> responseEntryList, Customer customer) {
        this.responseEntryList = responseEntryList;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ResponseEntry> getResponseEntryList() {
        return responseEntryList;
    }

    public void setResponseEntryList(List<ResponseEntry> responseEntryList) {
        this.responseEntryList = responseEntryList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(id, response.id) &&
                Objects.equals(responseEntryList, response.responseEntryList) &&
                Objects.equals(customer, response.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responseEntryList, customer);
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", responseEntryList=" + responseEntryList +
                ", user=" + customer +
                '}';
    }
}
