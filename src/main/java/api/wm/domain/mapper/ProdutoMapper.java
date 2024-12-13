package api.wm.domain.mapper;

import api.wm.domain.entity.Categoria;
import api.wm.domain.entity.Produto;
import api.wm.domain.dto.ProdutoRequestDTO;

public class ProdutoMapper {

    public static Produto to(ProdutoRequestDTO dto) {
        if (dto == null) return null;

        Categoria categoria = new Categoria();
        categoria.setId(dto.getIdCategoria());

        Produto produto = new Produto();
        produto.setId(null);
        produto.setNome(dto.getNome());
        produto.setCodigo(dto.getCodigo());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(categoria);
        produto.setQuantidade(dto.getQuantidade());
        return produto;
    }

    public static ProdutoRequestDTO toDTO(Produto produto) {
        if (produto == null) return null;
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        dto.setNome(produto.getNome());
        dto.setIdCategoria(produto.getCategoria().getId());
        dto.setCodigo(produto.getCodigo());
        dto.setPreco(produto.getPreco());
        dto.setQuantidade(produto.getQuantidade());
        return dto;
    }
}
