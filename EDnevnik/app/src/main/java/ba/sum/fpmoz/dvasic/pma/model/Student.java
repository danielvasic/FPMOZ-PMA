package ba.sum.fpmoz.dvasic.pma.model;

public class Student {
    public String uid;
    public String name;
    public String surname;

    public Student() {
    }

    public Student(String uid, String name, String surname) {
        this.uid = uid;
        this.name = name;
        this.surname = surname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
