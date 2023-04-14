import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoImdb implements ExtratorConteudo {
    public List<Conteudo>extraiConteudos(String json) {
        // Extrair os dados que interessam (Título, poster e a classificação)
       JsonParser parser = new JsonParser();
       List<Map<String, String>> listaDeAtributos = parser.parse(json);
       List<Conteudo>conteudos = new ArrayList<>();
       
       // Popular lista de conteudos
       for (Map<String, String> atributos : listaDeAtributos) {
        String titulo = atributos.get("title");
        String urlImagem = atributos.get("image");
        String avaliacao = atributos.get("imDbRating");
        var conteudo = new Conteudo(titulo, urlImagem, avaliacao);
        conteudos.add(conteudo);
       
        if (Double.parseDouble(avaliacao) <= 10.0 && Double.parseDouble(avaliacao) >= 8.0){
            conteudo.setTexto("ESSE FILME É BRABO");
        } else if (Double.parseDouble(avaliacao) <= 7.9 && Double.parseDouble(avaliacao) > 6.0){
            conteudo.setTexto("ESSE FILME É MAIS OU MENOS");
        } else if (Double.parseDouble(avaliacao) <= 5.9 && Double.parseDouble(avaliacao) > 4.0){
            conteudo.setTexto("ESSE FILME NÃO É BOM");
        } else if (Double.parseDouble(avaliacao) <= 4.9 && Double.parseDouble(avaliacao) > 2.0){
            conteudo.setTexto("ESSE FILME RUIM");
        } else {
            conteudo.setTexto("ESSE FILME É MUITO RUIM");
        }
        

        if (Double.parseDouble(avaliacao) <= 10.0 && Double.parseDouble(avaliacao) >= 8.0){
            conteudo.setEstrelas("⭐⭐⭐⭐⭐");
        } else if (Double.parseDouble(avaliacao) <= 7.9 && Double.parseDouble(avaliacao) > 6.0) {
            conteudo.setEstrelas("⭐⭐⭐⭐");
        } else if (Double.parseDouble(avaliacao) <= 5.9 && Double.parseDouble(avaliacao) > 4.0){
            conteudo.setEstrelas("⭐⭐⭐");
        } else if (Double.parseDouble(avaliacao) <= 4.9 && Double.parseDouble(avaliacao) > 2.0){
            conteudo.setEstrelas("⭐⭐");
        } else {
            conteudo.setEstrelas("⭐");
        }
       }
      
       return conteudos;
    }
    
}

