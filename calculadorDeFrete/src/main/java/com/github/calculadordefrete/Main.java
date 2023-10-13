package calculadorDeFrete.src.main.java.com.github.calculadordefrete;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        x = RetornaCidade();
        y = RetornaCidade();
        int tipo;
        String tipoTexto = "";
        double preco = 1;
        int distancia;

        String caminho = "distancia.csv";
        distancia = LerArquivo(caminho, x, y);
        System.out.println(distancia);

        ArrayList<Caminhoes> tiposCaminhoes = new ArrayList<>();
        tiposCaminhoes.add(new Caminhoes("pequeno", 5.83, 1));//R$ 5,83 por km, 1 tonelada.
        tiposCaminhoes.add(new Caminhoes("medio", 13.42, 4));
        tiposCaminhoes.add(new Caminhoes("grande", 20.21, 10));

        System.out.println("Selecione o porte do caminhão:");
        System.out.println("[1] Pequeno");
        System.out.println("[2] Médio");
        System.out.println("[3] grande");

        tipo = scanner.nextInt();

        if (tipo == 1) {
            tipoTexto = "pequeno";
        }
        if (tipo == 2) {
            tipoTexto = "medio";
        }
        if (tipo == 3) {
            tipoTexto = "grande";
        }

        for (Caminhoes item : tiposCaminhoes)
        {
            if (item.getTipo().equals(tipoTexto))
            {
                preco = item.getPrecoKM();
                break;
            }
        }
        System.out.println("\nDistância entre as cidades: " + distancia + "km");
        System.out.println("O valor do trajeto: R$" + preco * distancia);

    }

    static int RetornaCidade() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a cidade de origem:");

        System.out.println("[0] ARACAJU");
        System.out.println("[1] BELEM");
        System.out.println("[2] BELO HORIZONTE");
        System.out.println("[3] BRASILIA");
        System.out.println("[4] CAMPO GRANDE");
        System.out.println("[5] CUIABA");
        System.out.println("[6] CURITIBA");
        System.out.println("[7] FLORIANOPOLIS");
        System.out.println("[8] FORTALEZA");
        System.out.println("[9] GOIANIA");
        System.out.println("[10] JOAO PESSOA");
        System.out.println("[11] MACEIO");
        System.out.println("[12] MANAUS");
        System.out.println("[13] NATAL");
        System.out.println("[14] PORTO ALEGRE");
        System.out.println("[15] PORTO VELHO");
        System.out.println("[16] RECIFE");
        System.out.println("[17] RIO BRANCO");
        System.out.println("[18] RIO DE JANEIRO");
        System.out.println("[19] SALVADOR");
        System.out.println("[20] SAO LUIS");
        System.out.println("[21] SAO PAULO");
        System.out.println("[22] TERESINA");
        System.out.println("[23] VITORIA");

        int cidade = scanner.nextInt();

        return cidade;

    }

    static int RetornaProduto() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Produtos> produtosLoja = new ArrayList<>();
        produtosLoja.add(new Produtos("Celular", 0.7)); // nome do produto, peso por kg
        produtosLoja.add(new Produtos("Geladeira", 50));
        produtosLoja.add(new Produtos("Air Fryer", 3.5));
        produtosLoja.add(new Produtos("Cadeira", 5.0));
        produtosLoja.add(new Produtos("Luminária", 0.8));
        produtosLoja.add(new Produtos("Lavadora de Roupa", 15));
        produtosLoja.add(new Produtos("Playstation 5", 3.9));
        produtosLoja.add(new Produtos("Nintendo Switch", 0.3));

        System.out.println("Escolha a quantidade de produtos que deseja transportar:");
        int quantidade = scanner.nextInt();


        for (int rodadas = 0; rodadas < quantidade; rodadas++) {
            System.out.println("Escolha o produto:");

            // Exiba as opções de produtos com números
            for (int i = 0; i < produtosLoja.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + produtosLoja.get(i).getNome() + " (" + produtosLoja.get(i).getPeso() + "kg)");
            }

            int escolhaProduto = scanner.nextInt();

            if (escolhaProduto >= 1 && escolhaProduto <= produtosLoja.size()) {
                System.out.println("Quantos desse você gostaria de pegar?");
                int quantidadeDeProdutos = scanner.nextInt();
                // Faça algo com a escolha do produto e a quantidade aqui
                double pesoDosProdutos = escolhaProduto * quantidadeDeProdutos;
                double pesoTotal = pesoDosProdutos * quantidade;

            } else {
                System.out.println("Escolha de produto inválida.");
            }

        }

        return 0;
    }

    public static void custoTransporte(pesoTotal, distancia) {
        double transporte;
        String modeloCaminhao;

        if (pesoTotal <= 1000) {
            transporte = distancia * 5.83;
            modeloCaminhao = "Pequeno";
        } else if (pesoTotal <= 4000) {
            transporte = distancia * 13.42;
            modeloCaminhao = "Médio";
        } else if (pesoTotal <= 10000) {
            transporte = distancia * 29.21;
            modeloCaminhao = "Grande";
        } else {
            int caminhoesNecessarios = (int) Math.ceil(pesoTotal / 10);
            transporte = caminhoesNecessarios * distancia * 29.21;
            modeloCaminhao = "Grande (x" + caminhoesNecessarios + ")";
        }

        //  resultado
        System.out.println("O melhor modelo de caminhão para a entrega é: " + modeloCaminhao);
        System.out.println("Custo do transporte: R$" + transporte);
    }
    

    public static int LerArquivo(String arquivo, int x, int y)
    {
        int[][] distancias = null;
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int linhaCount = 0;
            while ((linha = br.readLine()) != null)
            {
                String[] numerosString = linha.split(";");
                if (distancias == null)
                {
                    distancias = new int[numerosString.length][numerosString.length];
                }
                for (int i = 0; i < numerosString.length; i++)
                {
                    distancias[linhaCount][i] = Integer.parseInt(numerosString[i]);
                }
                linhaCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distancias[x][y];
    }

}




