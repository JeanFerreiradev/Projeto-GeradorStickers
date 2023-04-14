import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo{
    public List<Conteudo>extraiConteudos(String json) {
        
        // Extrair os dados que interessam (Título, poster e a classificação)
       JsonParser parser = new JsonParser();
       List<Map<String, String>> listaDeAtributos = parser.parse(json);
       List<Conteudo>conteudos = new ArrayList<>();
       
       // Popular lista de conteudos
       for (Map<String, String> atributos : listaDeAtributos) {
        String titulo = atributos.get("title");
        String urlImagem = atributos.get("url");
        var conteudo = new Conteudo(titulo, urlImagem, "10.0");
        conteudos.add(conteudo);
       }
      
       return conteudos;
    }
}
