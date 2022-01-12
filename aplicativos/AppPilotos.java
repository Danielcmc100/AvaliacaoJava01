package aplicativos;
import java.io.IOException;
import java.util.Scanner;

import classes.Piloto;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 2;
        int opcao, qtdCadastrados = 0;
        Piloto[] pilotos = new Piloto[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                //Cadastre seu piloto aqui
                System.out.print("\nNome: ");
                String nome = in.nextLine();
                String cpf;
                do{
                    System.out.print("CPF: ");
                    cpf = in.nextLine();
                    if(cpf.length() != 11)
                    {
                        System.out.println("CPF invalido");
                    }
                }
                while (cpf.length() != 11);
                cpf = FormatarCPF(cpf);
                System.out.print("Brevê: ");
                String breve = in.nextLine();
                pilotos[qtdCadastrados] = new Piloto(nome, cpf,breve);
                qtdCadastrados++;
                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);
            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba os pilotos aqui
                System.out.println();
                for(int i = 0; i < qtdCadastrados; i++){
                    System.out.printf("\nNome:%s CPF:%s Brevê:%s",  pilotos[i].getNome(),pilotos[i].getCpf(),pilotos[i].getBreve());
                }

                voltarMenu(in);
            } else if (opcao == 3) {
                System.out.print("CPF: ");
                String cpf = in.nextLine();
                Boolean temPiloto = false;
                int i = 0;
                for(;i < qtdCadastrados;i++){
                    if(pilotos[i].getCpf().equals(cpf)){
                        temPiloto = true;
                        break;
                    }
                } 
                if(temPiloto){
                    System.out.printf("\nNome:%s CPF:%s Brevê:%s",  pilotos[i].getNome(),pilotos[i].getCpf(),pilotos[i].getBreve());
                }
                else{
                    System.out.println("\nPiloto não encontrado");
                }
                voltarMenu(in);
            } else if (opcao == 4) {
                System.out.printf("\nO tamanho máximo de cadastros é %s, digite um novo:",MAX_ELEMENTOS);
                int max = in.nextInt();
                in.nextLine();
                if(MAX_ELEMENTOS >= max)
                {
                    System.out.print("\nO número digitado é menor ou igual ao máximo");
                    voltarMenu(in);
                    continue;
                }

                Piloto[] newPilotos = new Piloto[max];
                for(int i = 0; i < qtdCadastrados; i++){
                    newPilotos[i] = pilotos[i];
                }
                pilotos = newPilotos;
                newPilotos = null;
                MAX_ELEMENTOS = max;
                System.out.println("\nMáximo de pilotos aumentado com sucesso");
                voltarMenu(in);
            }
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }

    private static String FormatarCPF(String cpf){
        String newCPF = "";
        for(int i = 0; i < 11;i++){
            if(i == 3 || i == 6)
            {
                newCPF +=".";
            }else if(i == 9){
                newCPF +="-";
            }
            newCPF += cpf.charAt(i);
        }
        return newCPF;
    }
}