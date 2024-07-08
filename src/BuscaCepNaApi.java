import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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


    // teste para ver se extá printando
        System.out.println("Aqui imprimiu o get Cep: " + resultado );


        String enderecoApiCorreio = "https://viacep.com.br/ws/" + resultado + "/json/ ";

    // até  aqui fui eu
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(enderecoApiCorreio))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());



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


            FileWriter cepEscrito =  new FileWriter("cep.txt");
            cepEscrito.write(formatoPadraoCep.toString());
            cepEscrito.close();


        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Aconteceu um erro: ");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Algum erro de argumento na busca, verifique o endereço");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }




//            FormatoPadraoCep formatoPadraoCep = gson.fromJson(enderecoApiCorreio, FormatoPadraoCep.class);
//
//            FormatoPadraoCep formatoPadraoCep = new FormatoPadraoCep();
//
//
//            FileWriter escrita = new FileWriter("filmes.xlsx");
//            escrita.write(meuTitulo.toString());
//            escrita.close();




        System.out.println("O programa finalizou corretamente!");
        System.out.println("Endereço gerado da Api: "+ enderecoApiCorreio);
        System.out.println("String: cep"+ cep);
        System.out.println("String: resultado " + resultado);
    }






    }





