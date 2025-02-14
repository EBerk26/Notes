public class GetAndSet {
    private Secret secret1;
    public static void main(String[] args) {
        new GetAndSet();
    }
    public GetAndSet() {
        secret1 = new Secret();
        System.out.println(secret1.getPassword());
        secret1.setPassword("New and improved password");
        System.out.println(secret1.getPassword());
    }
}


class Secret {
    private String password;

    //setters and getters
    public String getPassword() {
        return password;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }


    public Secret() {
        password = "BigCheeze";
    }
}
