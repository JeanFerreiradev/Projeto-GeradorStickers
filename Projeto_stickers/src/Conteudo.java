public class Conteudo {
    private final String titulo;
    private final String urlImagem;
    private final String avaliacao;
    private String texto = "TOPZERA";
    private String estrelas = "⭐⭐⭐⭐⭐";

    public Conteudo(String titulo, String urlImagem, String avaliacao) {
        this.titulo = titulo;
        this.urlImagem = urlImagem;
        this.avaliacao = avaliacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public String getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(String estrelas) {
        this.estrelas = estrelas;
    }

}
