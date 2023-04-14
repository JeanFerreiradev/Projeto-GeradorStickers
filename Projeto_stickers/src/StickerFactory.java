import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerFactory {

    public void criar(InputStream input, String nomeArquivo, String texto) throws IOException {
        // Leitura da imagem
        // InputStream input = new FileInputStream(new File("entrada/filme.jpg"));
        // InputStream input = new
        // URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imageOG = ImageIO.read(input);

        // Criar nova imagem em memória com transparência e tamanho novo.
        int largura = imageOG.getWidth();
        int altura = imageOG.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TYPE_INT_ARGB);

        // Copiar imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imageOG, 0, 0, null);

        // Escrever uma frase na nova imagem
        int porcentagemLargura = 30;
        int tamanhoMinimo = 20;
        int tamanhoMaximo = 70;
        int tamanhoFonte = (int) (largura * porcentagemLargura / 100.0);
        if (tamanhoFonte < tamanhoMinimo) {
            tamanhoFonte = tamanhoMinimo;
        } else if (tamanhoFonte > tamanhoMaximo) {
            tamanhoFonte = tamanhoMaximo;
        }
        Font fonte = new Font("Impact", Font.BOLD, tamanhoFonte);
        graphics.setFont(fonte);
        int larguraTexto = graphics.getFontMetrics().stringWidth(texto);
        int posicaoX = (largura - larguraTexto) / 2;
        int posicaoY = novaAltura - 100;
        graphics.setColor(Color.YELLOW);
        graphics.drawString(texto, posicaoX, posicaoY);

        // Contorno da frase
        FontRenderContext contorno = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(texto, fonte, contorno);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoX, posicaoY);
        graphics.setTransform(transform);
        BasicStroke outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);
        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        // Escrever nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }
}
