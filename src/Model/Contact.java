package Model;

import java.util.Objects;

public class Contact {
    private final String firstName, lastName, phone, notes;

    public Contact(String firstName, String lastName, String phone, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.notes = notes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(phone, contact.phone) &&
                Objects.equals(notes, contact.notes);
    }

}
