import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


public class App {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_NEGRITO = "\u001B[1m";
    public static final String ANSI_SUBLINHADO = "\u001B[4m";
    public static void main(String[] args) throws Exception {
       Locale.setDefault(Locale.US);
        
       // Utiliza um link externo
       var properties = new Properties();
       InputStream input = new FileInputStream("C:\\Users\\Jean\\Desktop\\api\\config.properties");
       properties.load(input);
       String linkExterno = properties.getProperty("apiLink");
       
       
        // Fazer uma conexão HTTP e buscar o top 250 filmes 
       ClienteHttp http = new ClienteHttp();
       String json = http.getData(linkExterno);

       // Extrair os dados que interessam (Título, poster e a classificação)
       ExtratorConteudo extrator = new ExtratorConteudoImdb();
       List<Conteudo> conteudos = extrator.extraiConteudos(json);

       // Exibir e manipular os dados
       StickerFactory factory = new StickerFactory();
       
       for (int i = 0; i < conteudos.size(); i++) {
        Conteudo conteudo = conteudos.get(i);
        String titulo = conteudo.getTitulo();
        String urlImagem = conteudo.getUrlImagem();
        String ranking = conteudo.getAvaliacao();
        String nomeArquivo = titulo.replace(":", "-") + ".png";
        String texto = conteudo.getTexto();

        InputStream inputstream = new URL(urlImagem).openStream();
        System.out.println(ANSI_PURPLE + ANSI_NEGRITO + ANSI_SUBLINHADO + "Título: " + titulo + ANSI_RESET);
        System.out.println("URL da imagem: " + urlImagem);
        System.out.println("Ranking: " + ranking);
        System.out.println(conteudo.getEstrelas());
        System.out.println();

        factory.criar(inputstream, "saida/" + nomeArquivo, texto);
       }

    }
}
