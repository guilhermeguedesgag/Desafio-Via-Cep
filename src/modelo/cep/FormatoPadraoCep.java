package modelo.cep;

public record FormatoPadraoCep(String cep, String logradouro,
                               String complemento, String bairro,
                               String localidade, String uf, String ibge,
                               String gia, String ddd, String siafi) {


    @Override
    public String cep() {
        return cep;
    }
}
