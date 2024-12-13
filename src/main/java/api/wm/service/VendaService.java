package api.wm.service;

import api.wm.domain.dto.ProdutoRequestDTO;
import api.wm.domain.entity.Produto;
import api.wm.domain.entity.Venda;
import api.wm.repository.ProdutoRepository;
import api.wm.repository.VendaRepository;
import api.wm.domain.dto.VendaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda salvar(Venda venda) {
        if (venda.getProdutos().isEmpty()) throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Lista vazia!");

        for (Produto produto : venda.getProdutos()) {
            Produto produtoDoBanco = produtoRepository.findByCodigo(produto.getCodigo())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Não existe um produto com esse código"));

            if(produtoDoBanco.getQuantidade() < produto.getQuantidade()) throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Produto sem estoque suficiente");

            produtoDoBanco.setQuantidade(produtoDoBanco.getQuantidade() - produto.getQuantidade());
            produtoRepository.save(produtoDoBanco);
            // -----------------------------------------------------------------
            produto.setId(produtoDoBanco.getId());
            produto.setNome(produto.getNome());
            produto.setPreco(produto.getPreco());
            produto.setCategoria(produto.getCategoria());
        }

        return vendaRepository.save(venda);
    }

    public List<Venda> listar() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404))
        );
    }
}
