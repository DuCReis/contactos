import java.util.Objects;
import java.util.Scanner;

public class Main
{
    public static void main (String[] args) {
        ContactList teste = new ContactList();
        Scanner in = new Scanner(System.in);
        String command, nome, email;
        int telefone;
        do
        {
            System.out.print("> ");
            command = in.nextLine().toUpperCase();
            switch (command)
            {
                case "AC" ->{
                    do
                    {
                        System.out.println("Inserir contato completo?(Nome, telefone, email) y/n");
                        System.out.print("> ");
                        command = in.nextLine().toUpperCase();

                        switch (command)
                        {
                            case "Y" -> {
                                System.out.println("Insira o nome: ");
                                System.out.print("> ");
                                nome = in.nextLine();
                                System.out.println("Insira o numero de telefone: ");
                                System.out.print("> ");
                                telefone = Integer.parseInt(in.nextLine());
                                System.out.println("Insira o email: ");
                                System.out.print("> ");
                                email = in.nextLine();
                                boolean ver = teste.addContact(nome, email, telefone);
                                if(ver)
                                    System.out.println("O contacto foi inserido com sucesso!");
                                else
                                    System.out.println("O contacto não foi adicionado!");
                                command = "Y";
                            }
                            case "N" -> {
                                System.out.println("Insira o nome: ");
                                System.out.print("> ");
                                nome = in.nextLine();
                                boolean ver = teste.addContact(nome);
                                if(ver)
                                    System.out.println("O contacto foi inserido com sucesso!");
                                else
                                    System.out.println("O contacto não foi adicionado!");
                                command = "Y";
                            }
                        }
                    }while (!command.equals("Y"));
                }
                case "DC" ->{
                    System.out.println("Insira o nome do contacto que quer remover: ");
                    System.out.print("> ");
                    nome = in.nextLine();
                    boolean ver = teste.deleteContact(nome);
                    if (ver)
                        System.out.println("O contacto foi removido com sucesso!");
                    else
                        System.out.println("Esse contacto não existe!");
                }
                case "SP" ->{
                    System.out.println("Insira o nome do contacto: ");
                    System.out.print("> ");
                    nome = in.nextLine();
                    System.out.println("Insira o numero de telefone: ");
                    System.out.print("> ");
                    telefone = Integer.parseInt(in.nextLine());
                    boolean ver = teste.setPhone(nome, telefone);
                    if (ver)
                        System.out.println("O numero de telefone foi adicionado com sucesso!");
                    else
                        System.out.println("Esse contacto não existe!");
                }
                case "SM" ->{
                    System.out.println("Insira o nome do contacto: ");
                    System.out.print("> ");
                    nome = in.nextLine();
                    System.out.println("Insira o email: ");
                    System.out.print("> ");
                    email = in.nextLine();
                    boolean ver = teste.setEmail(nome, email);
                    if(ver)
                        System.out.println("Foi adicioanado com sucesso!");
                    else
                        System.out.println("Esse contacto não existe!");
                }
                case "GP" ->{
                    System.out.println("Insira o nome do contacto: ");
                    System.out.print("> ");
                    nome = in.nextLine();
                    telefone = teste.getPhone(nome);
                    if(telefone != -1)
                        System.out.println(telefone);
                    else
                        System.out.println("Este contacto não tem numero atribuido ou não existe!");
                }
                case "GM" ->{
                    System.out.println("Insira o nome do contacto: ");
                    System.out.print("> ");
                    nome = in.nextLine();
                    email = teste.getEmail(nome);
                    System.out.println(Objects.requireNonNullElse(email, "Este contacto não tem email ou não existe!"));
                }
                case "LA" -> teste.getAll();
                case "SF" ->{
                    System.out.println("Insira o nome do ficheiro para guardar os contactos: ");
                    System.out.print("> ");
                    nome = in.nextLine();
                    teste.saveToFile(nome);
                    System.out.println("Foi guardado com sucesso!");
                }
                case "LF" ->{
                    System.out.println("Insira o nome do ficheiro para carregar os contactos: ");
                    System.out.print("> ");
                    nome = in.nextLine();
                    teste.loadFromFile(nome);
                    System.out.println("Foi carregado com sucesso!");
                }
            }
        }while (!command.equals("QUIT"));
    }
}
