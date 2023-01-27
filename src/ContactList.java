import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ContactList
{
    Contact[] clist, clist2;
    public int nelements, maxelements;

    ContactList(){
        clist = new Contact[2];
        this.nelements = 0;
        this.maxelements = 2;
    }

    private void aumentarArray()
    {
        clist2 = new Contact[maxelements*2];
        if (nelements >= 0) System.arraycopy(clist, 0, clist2, 0, nelements);
        clist = clist2;
        maxelements *= 2;
    }

    private int fast_search (String nome)
    {
        int low = 0, high = nelements - 1 , middle;
        while (low <= high)
        {
            middle = low + ((high - low)/2);

            if (nome.compareTo(clist[middle].getName()) > 0)
                low = middle + 1;
            else if (nome.compareTo(clist[middle].getName()) < 0)
                high = middle - 1;
            else if(nome.compareTo(clist[middle].getName()) == 0)
                return middle;
        }
        return -1;
    }

    public boolean addContact(String nome, String email, int phone)
    {
        Contact contact = new Contact(nome, email, phone);
        int idx;

        if(fast_search(nome) == -1) {
            if (nelements >= maxelements)
                aumentarArray();

            for (idx = 0; idx < nelements; idx++)
                if (nome.compareTo(clist[idx].getName()) < 0)
                    break;

            if (nelements - idx >= 0)
                System.arraycopy(clist, idx, clist, idx + 1, nelements - idx);

            clist[idx] = contact;
            nelements++;
            return true;
        }
        return false;
    }

    public boolean addContact(String nome)
    {
        return this.addContact(nome, null, -1);
    }

    public boolean deleteContact(String nome)
    {
        int idx = this.fast_search(nome);
        if(idx != -1)
        {
            if (nelements - idx >= 0)
                System.arraycopy(clist, idx + 1, clist, idx, nelements - idx);
            nelements--;
            return true;
        }
        return false;
    }

    public int getPhone(String nome)
    {
        int idx = this.fast_search(nome);
        if(idx != -1)
        {
            if(clist[idx].getPhone() != 0) {
                return clist[idx].getPhone();
            }else{
                return -1;
            }
        }
        return -1;
    }

    public String getEmail(String nome)
    {
        int idx = this.fast_search(nome);
        if(idx != -1)
        {
            return clist[idx].getEmail();
        }
        return null;
    }

    public boolean setPhone(String nome, int phone)
    {
        int idx = this.fast_search(nome);
        if(fast_search(nome) != -1)
        {
            clist[idx].setPhone(phone);
            return true;
        }
        return false;
    }

    public boolean setEmail(String nome, String email)
    {
        int idx = this.fast_search(nome);
        if(idx != -1)
        {
            clist[idx].setEmail(email);
            return true;
        }
        return false;
    }

    public void getAll()
    {
        for (int i = 0; i < nelements; i++)
            System.out.println(clist[i].getName());
    }

    public void saveToFile(String filename) {
        try (PrintWriter out = new PrintWriter(filename + ".file")) {
            out.println(nelements);
            for (int i = 0; i < nelements; i++) {
                out.println(clist[i].getName());
                if(clist[i].getEmail() == null)
                    out.println("null");
                else
                    out.println(clist[i].getEmail());
                out.println(clist[i].getPhone());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename)
    {
        try{
            int count, telefone;
            String nome, email;
            Scanner scanner = new Scanner(new FileReader(filename+".file"));
            count = Integer.parseInt(scanner.nextLine());
            for(int i = 0; i< count; i++)
            {
                nome = scanner.nextLine();
                email = scanner.nextLine();
                telefone = Integer.parseInt(scanner.nextLine());
                if(email.equals("null"))
                    addContact(nome);
                else
                    addContact(nome, email, telefone);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
