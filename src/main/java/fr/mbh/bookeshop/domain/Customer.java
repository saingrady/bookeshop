/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.domain;

/**
 *
 * @author Mahmoud
 */
public class Customer {

    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String password;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "\nfirst name = " + firstName
                + "\nlast name = " + lastName
                + "\nemail = " + email
                + "\naddress = " + address
                + "\npassword = " + password ;
    }

}
