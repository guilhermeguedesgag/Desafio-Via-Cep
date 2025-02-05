import com.google.gson.*;
import modelo.cep.FormatoPadraoCep;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaCepNaApi {

    public void buscaNaApi(String cep){
        // na String Cep temos o CEP que foi informado na classe anterior


        String enderecoApiCorreio = "https://viacep.com.br/ws/" + cep + "/json/";


        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(enderecoApiCorreio))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());


            // esse cara é responsável pela response
            String json = response.body();
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            if(jsonObject.has("erro")) {
                System.out.println("CEP não encontrado, digite novamente: ");
                BuscaInicial buscaInicial = new BuscaInicial();
                buscaInicial.iniciaisDois();

            } else {

                System.out.println(json);

                //esse cara Gson abaixo irá buildar o json já validado acima
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting().create();

                //replace para que o cep digitado sai de uma forma pretty
                String cepFormatado = cep.replaceFirst("(\\d{5})(\\d{3})","$1-$2");

                //replace acima em uma sequencia de 8 digitos separa em dois grupos pelo hifem(-)
                System.out.println("Json gerado do CEP: " + cepFormatado);

                FormatoPadraoCep formatoPadraoCep = gson.fromJson(json,FormatoPadraoCep.class);
                System.out.println(formatoPadraoCep);



                //cara resposável por escrever os arquivos e gerar os arquivo com as extensões escolhidas
                try(FileWriter cepEscrito =  new FileWriter("cep.json")){
                gson.toJson(formatoPadraoCep, cepEscrito);}


                //algumas impressões importantes
                System.out.println("Endereço encontrado na API: "+ enderecoApiCorreio);
                System.exit(0);

            }


        } catch (NumberFormatException | NullPointerException e) {
            BuscaInicial buscaInicial = new BuscaInicial();
            System.out.println("CEP não encontrado, digite um CEP válido: ");
            buscaInicial.iniciaisDois();

        }  catch (IOException | InterruptedException e) {
            // Exception lançado pelo send do http
            System.out.println("Erro ao realizar exceção: "+e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Você digitou caracteres não permitidos..");

        } catch (IllegalStateException | JsonSyntaxException e) {
            System.out.println("Você digitou caracteres não compatíveis com o CEP, digite novamente: ");
            BuscaInicial buscaInicial = new BuscaInicial();
            buscaInicial.iniciaisDois();

        }



        }

    }











