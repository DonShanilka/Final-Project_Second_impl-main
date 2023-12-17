package lk.ijse.semisterfinal.Tm;

public class CustomerTm {
    private String id;
    private String name;
    private String address;
    private String tel;
    private String payment;
    private String itemId;

    public CustomerTm() {
    }

    public CustomerTm(String id, String name, String address, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public CustomerTm(String txtCustId, String txtCustName, String txtCustAddress, String txtCustMobile, String txtCustPayment, String txtCustitemId) {
        this.id = txtCustId;
        this.name = txtCustName;
        this.address = txtCustAddress;
        this.tel = txtCustMobile;
        this.payment = txtCustPayment;
        this.itemId = txtCustitemId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "CustomerTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
