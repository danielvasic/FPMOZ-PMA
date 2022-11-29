package ba.sum.fpmoz.filmoteka.model;

import java.util.Date;

public class UserProfile {
    private String firstname;
    private String lastname;
    private String email;
    private Date dateOfBirth;

    public UserProfile() {}

    public UserProfile(String firstname, String lastname, String email, Date dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
