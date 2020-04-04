package dsc.machung.bantuanbencana;

public class UserModel {

    String username;
    String password;
    int isLogin;
    String photo;
    String name;
    String email;
    String telp;
    String address;
    int anonymous;
    int total;

    public UserModel() { }

    public UserModel(String username, String password, int isLogin, String photo, String name, String email,
                     String telp, String address, int anonymous, int total){
        this.username = username;
        this.password = password;
        this.isLogin = isLogin;
        this.photo = photo;
        this.name = name;
        this.email = email;
        this.telp = telp;
        this.address = address;
        this.anonymous = anonymous;
        this.total = total;
    }

    public int getAnonymous() {
        return anonymous;
    }

    public int getTotal() {
        return total;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTelp() {
        return telp;
    }

    public String getUsername() {
        return username;
    }

    public int getLogin() {
        return isLogin;
    }

    public void setLogin(int login) {
        isLogin = login;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAnonymous(int anonymous) {
        anonymous = anonymous;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
