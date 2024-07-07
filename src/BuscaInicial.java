
    import java.util.Scanner;

    public class BuscaInicial {
        Scanner cepScanner = new Scanner(System.in);
        Scanner respostaScanner = new Scanner(System.in);
        private String respostaEscolha;


        public void iniciais() {
            //primeira frase do projeto
            System.out.println("Digite o CEP que deseja procurar: ");
            iniciaisDois();

        }

        public void iniciaisDois() {
            // cara responsável pela entrada de dados

            String cepProcurado = cepScanner.nextLine();

            if (cepProcurado.length() > 8) {
                System.out.println("Ops...você digitou uma quantidade de caracteres a mais, digite \n" +
                        "novamente apenas 8 dígitos: ");
                iniciaisDois();

            } else if (cepProcurado.length() < 8) {
                System.out.println(""" 
                        
                        Ops...você digitou uma quantidade de caracteres menor
                        do que a necessária para buscar o CEP, digite novamente
                        apenas 8 dígitos:                             
                                
                           
                       """);
                iniciaisDois();

            } else {

                System.out.println("Você digitou o CEP: " + cepProcurado + ", o CEP está correto?");



                do {

                    System.out.println("Digite 'Sim' ou 'Não': ");
                    respostaEscolha = respostaScanner.nextLine();

                    if (!respostaEscolha.equalsIgnoreCase("Sim") && (!respostaEscolha.equalsIgnoreCase("Não"))) {


                        System.out.println("Ops...não entendi...digite 'Sim' ou 'Não':");
                        respostaScanner.nextLine();
                    } else {
                        break;
                    }
                } while (!respostaEscolha.equalsIgnoreCase("Sim") && (!respostaEscolha.equalsIgnoreCase("Não")));


                    if (respostaEscolha.equalsIgnoreCase("Sim")){

                        BuscaCepNaApi buscaCepNaApi = new BuscaCepNaApi();
                        buscaCepNaApi.buscaNaApi();

                    } else if (respostaEscolha.equalsIgnoreCase("Não")) {
                        System.out.println("Digite o CEP novamente: ");
                        iniciaisDois();
                    }

                }
        }
    }










