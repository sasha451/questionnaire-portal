package by.shulga.softarex.questionnaireportal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Response extends BaseEntity {

    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL)
    private List<ResponseEntry> responseEntryList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;

    public Response() {}

    public Response(List<ResponseEntry> responseEntryList, Customer customer) {
        this.responseEntryList = responseEntryList;
        this.customer = customer;
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
}
