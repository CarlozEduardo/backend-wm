package api.wm.domain.dto;


public class CategoriaRequestDTO {
    private String nome;

    public CategoriaRequestDTO(String nome) {
        this.nome = nome;
    }

    public CategoriaRequestDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
