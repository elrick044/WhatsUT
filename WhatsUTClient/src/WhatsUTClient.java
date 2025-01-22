import java.rmi.Naming;
import java.util.Scanner;

public class WhatsUTClient {
    public static void main(String[] args) {
        try {
            WhatsUTServer server = (WhatsUTServer) Naming.lookup("rmi://localhost/WhatsUTServer");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Bem-vindo ao WhatsUT!");

            while (true) {
                System.out.println("\n1. Registrar usuário");
                System.out.println("2. Login");
                System.out.println("3. Ver usuários online");
                System.out.println("4. Criar grupo");
                System.out.println("5. Enviar mensagem privada");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (choice) {
                    case 1:
                        System.out.print("Digite o nome de usuário: ");
                        String username = scanner.nextLine();
                        System.out.print("Digite a senha: ");
                        String password = scanner.nextLine();
                        if (server.registerUser(username, password)) {
                            System.out.println("Usuário registrado com sucesso!");
                        } else {
                            System.out.println("Usuário já existe!");
                        }
                        break;

                    case 2:
                        System.out.print("Digite o nome de usuário: ");
                        username = scanner.nextLine();
                        System.out.print("Digite a senha: ");
                        password = scanner.nextLine();
                        if (server.loginUser(username, password)) {
                            System.out.println("Login bem-sucedido!");
                        } else {
                            System.out.println("Credenciais inválidas!");
                        }
                        break;

                    case 3:
                        System.out.println("Usuários online: " + server.getOnlineUsers());
                        break;

                    case 4:
                        System.out.print("Digite o nome do grupo: ");
                        String groupName = scanner.nextLine();
                        System.out.print("Digite seu nome de usuário (como admin): ");
                        String admin = scanner.nextLine();
                        if (server.createGroup(groupName, admin)) {
                            System.out.println("Grupo criado com sucesso!");
                        } else {
                            System.out.println("Grupo já existe!");
                        }
                        break;

                    case 5:
                        System.out.print("Digite o destinatário: ");
                        String recipient = scanner.nextLine();
                        System.out.print("Digite sua mensagem: ");
                        String message = scanner.nextLine();
                        if (server.sendMessageToUser("Cliente", recipient, message)) {
                            System.out.println("Mensagem enviada!");
                        } else {
                            System.out.println("Falha ao enviar mensagem.");
                        }
                        break;

                    case 6:
                        System.out.println("Encerrando cliente...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
