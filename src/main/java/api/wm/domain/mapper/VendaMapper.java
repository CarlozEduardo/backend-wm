package api.wm.domain.mapper;

import api.wm.domain.entity.Produto;
import api.wm.domain.entity.Venda;
import api.wm.domain.dto.VendaRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class VendaMapper {
    public static Venda to(VendaRequestDTO dto) {
        if (dto == null) return null;

        List<Produto> produtos = new ArrayList<>();
        for (VendaRequestDTO.ProdutoDTO produto : dto.getProdutos()) {
            Produto produtoI = new Produto();
            produtoI.setCodigo(produto.getCodigo());
            produtoI.setQuantidade(produto.getQuantidade());
            produtos.add(produtoI);
        }

        Venda venda = new Venda();
        venda.setId(null);
        venda.setCpf(dto.getCpf());
        venda.setProdutos(produtos);
        return venda;
    }

    public static VendaRequestDTO toDTO(Venda venda) {
        if (venda == null) return null;

        List<VendaRequestDTO.ProdutoDTO> produtos = new ArrayList<>();
        for (Produto produto : venda.getProdutos()) {
            VendaRequestDTO.ProdutoDTO produtoI = new VendaRequestDTO.ProdutoDTO();
            produtoI.setCodigo(produto.getCodigo());
            produtoI.setQuantidade(produto.getQuantidade());
            produtos.add(produtoI);
        }

        VendaRequestDTO dto = new VendaRequestDTO();
        dto.setCpf(venda.getCpf());
        dto.setProdutos(produtos);
        return dto;
    }
}
