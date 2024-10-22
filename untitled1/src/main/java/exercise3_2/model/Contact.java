package exercise3_2.model;

public class Contact {
    private String name;
    private String address;
    private String phone;
    private int id;

    public Contact(int id, String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.id = id;
    }

    // Getter 方法
    public int getId() {return id;}

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    // Setter 方法
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
