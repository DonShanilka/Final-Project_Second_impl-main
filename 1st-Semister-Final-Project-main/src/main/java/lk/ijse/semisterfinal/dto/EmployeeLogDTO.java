package lk.ijse.semisterfinal.dto;

public class EmployeeLogDTO {
    private String e_id;
    private String e_name;
    private String e_password;


    public EmployeeLogDTO(String eId, String eName, String ePassword) {
        e_id = eId;
        e_name = eName;
        e_password = ePassword;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_password() {
        return e_password;
    }

    public void setE_password(String e_password) {
        this.e_password = e_password;
    }

    @Override
    public String toString() {
        return "EmployeeLogDTO{" +
                "e_id='" + e_id + '\'' +
                ", e_name='" + e_name + '\'' +
                ", e_password='" + e_password + '\'' +
                '}';
    }

}
