package meuProjeto;
import java.util.Scanner;

public class projeto {

    public static boolean validarQuantidade(String input) {
        try {
            int numero = Integer.parseInt(input);
            return numero >= 0;
        } catch (NumberFormatException var2) {
            return false;
        }
    }


    public static void mostrarLivros(String[][] matriz, int linhas, int colunas) {
        System.out.println("ID | Titulo | Quantidade | Autor");

        for(int i = 0; i < linhas; ++i) {
            if(matriz[i][0] != null) {
                for(int j = 0; j < colunas; ++j) {
                    System.out.print(matriz[i][j] + " | ");
                }
                System.out.println();
            }
        }
        System.out.println("-----------------------");
    }


    public static void inserirLivro(String[][] matriz, int linhas, int colunas) {
        System.out.println("Inserir Livro");
        Scanner scanner = new Scanner(System.in);
        //incrementa a linha
        matriz[linhas][0] = String.valueOf(linhas + 1);
        System.out.print("Titulo: ");
        matriz[linhas][1] = scanner.next();
        boolean quantidadeValida = false;

        do {
            System.out.print("Ano: ");
            String input = scanner.next();
            quantidadeValida = validarQuantidade(input);
            if (!quantidadeValida) {
                System.out.println("Quantidade invalida! Insira um número positivo.");
            } else {
                int quantidade = Integer.parseInt(input);
                matriz[linhas][2] = String.valueOf(quantidade);
            }
        } while(!quantidadeValida);

        System.out.print("Autor: ");
        matriz[linhas][3] = scanner.next();
        System.out.println("Livro adicionado.");
    }


    public static void mensagem(String texto){
        System.out.println("voce precisa adicionar um livro antes de visualizar a lista de livros");
    }


    public static void removerLivro(String[][] matriz, int linhas, String tituloLivro) {
        boolean encontrado = false;

        for(int i = 0; i < linhas; ++i) {
            if (matriz[i][0] != null && matriz[i][1].equalsIgnoreCase(tituloLivro)) {
                encontrado = true;
                matriz[i][1] = null;
                matriz[i][2] = null;
                matriz[i][3] = null;
            }
        }

        if (encontrado) {
            System.out.println("Livro removido.");
        } else {
            System.out.println("Livro nao encontrado.");
        }
    }


    public static void editarLivro(String[][] matriz, int linhas) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do livro a ser editado: ");
        String nomeLivro = scanner.next();

        boolean encontrado = false;

        for(int i = 0; i < linhas; ++i) {
            if (matriz[i][1] != null && matriz[i][1].equalsIgnoreCase(nomeLivro)) {
                encontrado = true;
                System.out.println("Dados atuais do livro:");
                System.out.println("Numero: " + matriz[i][0]);
                System.out.println("Titulo: " + matriz[i][1]);
                System.out.println("Autor: " + matriz[i][2]);
                System.out.println("Ano de publicacao: " + matriz[i][3]);
                System.out.println("Digite os novos dados do livro:");
                System.out.print("Titulo: ");
                matriz[i][1] = scanner.next();
                System.out.print("Autor: ");
                matriz[i][2] = scanner.next();
                System.out.print("Ano de publicacao: ");
                matriz[i][3] = scanner.next();
                System.out.println("Dados do livro atualizados.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Livro nao encontrado.");
        }
    }


    public static void procurarLivro(String[][] matriz, int linhas) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do livro a ser procurado: ");
        String nomeLivro = scanner.next();

        boolean encontrado = false;

        for(int i = 0; i < linhas; ++i) {
            if (matriz[i][1] != null && matriz[i][1].equalsIgnoreCase(nomeLivro)) {
                encontrado = true;
                System.out.println("-------------------");
                System.out.println("Dados do livro encontrado:");
                System.out.println("Numero: " + matriz[i][0]);
                System.out.println("Titulo: " + matriz[i][1]);
                System.out.println("Autor: " + matriz[i][2]);
                System.out.println("Ano de publicacao: " + matriz[i][3]);
                System.out.println("-------------------");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void contarLivros(String[][] matriz, int linhas) {
        int contador = 0;

        for(int i = 0; i < linhas; i++) {
            if(matriz[i][0] != null) {
                contador++;
            }
        }

        System.out.println("O total de livros na biblioteca e: " + contador);
    }

    public static void main(String[] args) {
        int livros = 0;
        int colunas = 4;
        Scanner scanner = new Scanner(System.in);
        int max = 100;
        String[][] lista = new String[max][colunas];
        System.out.println("Bem-vindo a Biblioteca virtual");
        System.out.println("Selecione uma opcao abaixo:");

        int opcao;
        do {
            System.out.println("Escolha uma opcao:\n1 - Mostrar lista de livros\n2 - Inserir livro\n3 - Remover livro \n4 - Editar livro\n5 - Procurar livro especifico \n6 - Contar livros \n0 - Sair");

            while(!scanner.hasNextInt()) {
                System.out.println("Opcao invalida! Insira um numero inteiro.");
                scanner.next();
            }

            opcao = scanner.nextInt();
            if (opcao == 0) {
                System.out.println("Obrigado, esperamos que voce volte logo");
            }

            switch(opcao) {
                case 0:
                    break;
                case 1:
                    if (livros == 0) {
                    mensagem("");
                    } else {
                    mostrarLivros(lista, livros, colunas);
                    }
                    break;
                case 2:
                    inserirLivro(lista, livros, colunas);
                    ++livros;
                    break;
                case 3:
                    if (livros == 0) {
                        System.out.println("Antes de remover um livro e necessário ter um para remover");;
                    }
                     else {
                    System.out.println("Insira o titulo do livro a ser removido:");
                    String nomeLivro = scanner.next();
                    removerLivro(lista, livros, nomeLivro);
                    }
                    break;
                case 4:
                    if (livros == 0) {
                    mensagem("Nenhum livro registrado.");
                    } else {
                    editarLivro(lista, livros);
                    }
                    break;
                case 5:
                    if (livros == 0){
                        System.out.println("Nenhum livro cadastrado para visualizar");
                    }
                    else {
                        procurarLivro(lista, livros);
                    }
                    break;
                case 6:
                    if (livros == 0){
                        System.out.println("Nenhum livro cadastrado para contar");
                    }
                    else{
                        contarLivros(lista, livros);
                    }
                default:
                    System.out.println("Opcao invalida!");
            }
        } while(opcao != 0);

    }
}
