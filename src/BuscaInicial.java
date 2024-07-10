
    import java.util.Scanner;

    public class BuscaInicial {
        Scanner scanner = new Scanner(System.in);

        public String respostaEscolha;
        String cepProcurado="";


        public void iniciais() {
            //primeira frase do projeto
            System.out.println("\nDigite o CEP que deseja procurar: ");
            iniciaisDois();

        }



        public void iniciaisDois() {
            // cara responsável pela entrada de dados

            while (true) {
                // ao invés de colocar métodos de retornos, inseri
                //um while para scanner's não ficarem como null
                cepProcurado = scanner.nextLine();

                if (cepProcurado.length() > 8) {
                    System.out.println("\nOps...você digitou uma quantidade de caracteres a mais, digite \n" +
                            "novamente apenas 8 dígitos: ");


                } else if (cepProcurado.length() < 8) {
                    System.out.println("""
                              \nOps...você digitou uma quantidade de caracteres menor
                              do que a necessária para buscar o CEP, digite novamente
                              apenas 8 dígitos:
                            """);

                } else {
                    System.out.println("\nVocê digitou o CEP: " + cepProcurado + ", o CEP está correto? ");

                }
                break;


            }


            do {

                System.out.println("\nDigite 'Sim' ou 'Não': ");
                respostaEscolha = scanner.nextLine();

                if (!respostaEscolha.equalsIgnoreCase("Sim") && (!respostaEscolha.equalsIgnoreCase("Não"))) {


                    System.out.println("\nOps...não entendi...digite 'Sim' ou 'Não':");

                } else {
                    break;
                }
            } while (!respostaEscolha.equalsIgnoreCase("Sim") && (!respostaEscolha.equalsIgnoreCase("Não")));


            if (respostaEscolha.equalsIgnoreCase("Sim")) {

                BuscaCepNaApi buscaCepNaApi = new BuscaCepNaApi();

                //passei como parametro o 'cepProcurado' para ele armazenar
                buscaCepNaApi.buscaNaApi(cepProcurado);

            } else if (respostaEscolha.equalsIgnoreCase("Não")) {
                System.out.println("\nDigite o CEP novamente: ");
                iniciaisDois();
            }

        }







    }












