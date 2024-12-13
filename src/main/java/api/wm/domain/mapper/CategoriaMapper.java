package api.wm.domain.mapper;

import api.wm.domain.entity.Categoria;
import api.wm.domain.dto.CategoriaRequestDTO;

public class CategoriaMapper {
    public static Categoria to(CategoriaRequestDTO categoriaRequestDTO) {
        if (categoriaRequestDTO == null) return null;
        Categoria categoria = new Categoria();
        categoria.setId(null);
        categoria.setNome(categoriaRequestDTO.getNome());
        return categoria;
    }

    public static CategoriaRequestDTO toDTO(Categoria categoria) {
        if (categoria == null) return null;
        CategoriaRequestDTO categoriaRequestDTO = new CategoriaRequestDTO();
        categoriaRequestDTO.setNome(categoria.getNome());
        return categoriaRequestDTO;
    }
}
