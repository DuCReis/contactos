public class Contact
{
    protected String name, email;
    protected int phone;

    Contact(String nome, String email, int phone){
        this.name = nome;
        this.email = email;
        this.phone = phone;
    }

    public String getName(){return this.name;}

    public int getPhone(){return this.phone;}

    public String getEmail(){return this.email;}

    public void setPhone(int phone){this.phone = phone;}

    public void setEmail(String email){this.email = email;}
}
