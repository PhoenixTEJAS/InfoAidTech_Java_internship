import java.io.Serializable;

public class Student implements Serializable {

    private String rollNumber;
    private String name;
    private String address;
    private String phoneNumber;

    public Student(String rollNumber, String name, String address, String phoneNumber) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Address: " + address + ", Phone Number: "
                + phoneNumber;
    }
}
