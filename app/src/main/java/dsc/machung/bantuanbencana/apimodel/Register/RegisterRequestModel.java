package dsc.machung.bantuanbencana.apimodel.Register;

public class RegisterRequestModel {
    private String username;
    private String password;
    private String email;
    private String name;
    private String telp;
    private String address;

    public RegisterRequestModel(String username, String password, String email, String name, String telp, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.telp = telp;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }
}
