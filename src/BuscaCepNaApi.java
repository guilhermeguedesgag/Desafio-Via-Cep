import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonToken;
import modelo.cep.FormatoPadraoCep;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaCepNaApi {

    public void buscaNaApi(String cep){
        // na String Cep temos o CEP que foi informado
        //na classe anterior
        System.out.println("Imprimindo o cep:" + cep);



    var resultado = cep ;




        String enderecoApiCorreio = "https://viacep.com.br/ws/" + resultado + "/json/";

    // até  aqui fui eu
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(enderecoApiCorreio))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());


            // esse cara é responsável pela response
            String json = response.body();
            System.out.println(json);


            //primeiro instancie o carinha responsável por buildar o json
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            //depois coloque uma condição para imprimir, somente se a response for false para pegarmos o erro.
            if (jsonObject.has("Response")&& jsonObject.get("Response").getAsString().equals("False")){
                System.out.println("Erro na busca, entrada não encontrada. "+ jsonObject.get("Error").getAsString());


            }

            //esse cara Gson abaixo irá buildar o json já validado acima
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting().create();

            FormatoPadraoCep formatoPadraoCep = gson.fromJson(json,FormatoPadraoCep.class);
            System.out.println(formatoPadraoCep);

            //cara resposável por escrever os arquivos e gerar os arquivos
            //com as extensões escolhidas
            FileWriter cepEscrito =  new FileWriter("cep.json");
//            cepEscrito.write(formatoPadraoCep.toString());// esse cara escreve em String, quebra o json

            gson.toJson(formatoPadraoCep, cepEscrito);
            cepEscrito.close();


        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Aconteceu um erro: ");
            System.out.println(e.getMessage());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            System.out.println("Alguma chamada errada no Json ou no input");
        }

        //algumas impressões importantes
        System.out.println("O programa finalizou corretamente!");
        System.out.println("Endereço gerado da Api: "+ enderecoApiCorreio);




    }





}





