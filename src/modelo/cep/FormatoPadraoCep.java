package modelo.cep;

public record FormatoPadraoCep(String cep, String logradouro,
                               String complemento, String bairro,
                               String localidade, String uf, String ibge,
                               String gia, String ddd, String siafi) {



    // coloquei um toString para o conteúdo impresso sair de forma organizada.
    @Override
    public String toString() {
        return "\nSegue abaixo os dados do CEP localizado:\n\n"+
                "CEP: " + cep + "\n" +
                "Logradouro: " + logradouro + "\n" +
                "Complemento: " + complemento + "\n" +
                "Bairro: " + bairro + "\n" +
                "Localidade: " + localidade + "\n" +
                "UF: " + uf + "\n" +
                "IBGE: " + ibge + "\n" +
                "GIA: " + gia +
                "\n\n";
    }
}
