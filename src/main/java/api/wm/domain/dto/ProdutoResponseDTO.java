package api.wm.domain.dto;

public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String codigo;
    private Double preco;
    private Double quantidade;
    private CategoriaRequestDTO categoria;

    public ProdutoResponseDTO(Long id, String nome, String codigo, Double preco, Double quantidade, CategoriaRequestDTO categoria) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public ProdutoResponseDTO () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public CategoriaRequestDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaRequestDTO categoria) {
        this.categoria = categoria;
    }
}
