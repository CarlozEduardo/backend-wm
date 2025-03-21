package api.wm.domain.mapper;

import api.wm.domain.dto.CategoriaRequestDTO;
import api.wm.domain.dto.ProdutoResponseDTO;
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

    public static ProdutoResponseDTO toDTO(Produto produto) {
        if (produto == null) return null;

        CategoriaRequestDTO categoriaRequestDTO = new CategoriaRequestDTO(produto.getCategoria().getId(), produto.getCategoria().getNome());

        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setCategoria(categoriaRequestDTO);
        dto.setCodigo(produto.getCodigo());
        dto.setPreco(produto.getPreco());
        dto.setQuantidade(produto.getQuantidade());
        return dto;
    }
}
