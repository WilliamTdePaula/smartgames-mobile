package models;

public class Produto {
    private int idProduto;
    private String nome;
    private String imagem;
    private String valor;
    private String detalhe;
    private String destaque;

    public Produto(){}

    public Produto(int idProduto, String nome, String imagem, String valor, String detalhe, String destaque) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.imagem = imagem;
        this.valor = valor;
        this.detalhe = detalhe;
        this.destaque = destaque;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getDestaque() {
        return destaque;
    }

    public void setDestaque(String destaque) {
        this.destaque = destaque;
    }
}
