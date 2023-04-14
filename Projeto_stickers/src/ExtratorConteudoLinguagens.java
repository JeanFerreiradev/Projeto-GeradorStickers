import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoLinguagens implements ExtratorConteudo {
    public List<Conteudo>extraiConteudos(String json) {
        // Extrair os dados que interessam (Título, poster e a classificação)
       JsonParser parser = new JsonParser();
       List<Map<String, String>> listaDeAtributos = parser.parse(json);
       List<Conteudo>conteudos = new ArrayList<>();
       
       // Popular lista de conteudos
       for (Map<String, String> atributos : listaDeAtributos) {
        String titulo = atributos.get("title");
        String urlImagem = atributos.get("image");
        String avaliacao = atributos.get("ranking");
        var conteudo = new Conteudo(titulo, urlImagem, avaliacao);
        conteudos.add(conteudo);
        
       }
      
       return conteudos;
    }
    
}

