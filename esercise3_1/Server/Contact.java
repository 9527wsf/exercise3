package esercise3_1.Server;

// 联系人类
class Contact {
    private String name;
    private String address;
    private String phone;

    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "姓名: " + name + ", 地址: " + address + ", 电话: " + phone;
    }
}