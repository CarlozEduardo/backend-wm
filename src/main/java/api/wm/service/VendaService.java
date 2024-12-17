package api.wm.service;

import api.wm.domain.dto.ProdutoRequestDTO;
import api.wm.domain.entity.Produto;
import api.wm.domain.entity.Venda;
import api.wm.repository.ProdutoRepository;
import api.wm.repository.VendaRepository;
import api.wm.domain.dto.VendaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CupomService cupomService;

    public Venda salvar(Venda venda) {
        if (venda.getProdutos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista de produtos vazia!");
        }

        List<Produto> produtosAtualizados = new ArrayList<>();

        for (Produto produto : venda.getProdutos()) {
            Produto produtoDoBanco = produtoRepository.findByCodigo(produto.getCodigo())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

            if (produtoDoBanco.getQuantidade() < produto.getQuantidade()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto sem estoque suficiente");
            }

            produtoDoBanco.setQuantidade(produtoDoBanco.getQuantidade() - produto.getQuantidade());
            produtosAtualizados.add(produtoDoBanco);
        }

        venda.setProdutos(produtosAtualizados);

        Venda vendaSalva = vendaRepository.save(venda);
        produtoRepository.saveAll(produtosAtualizados);


        try {
            cupomService.imprimirCupom(vendaSalva);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao imprimir cupom fiscal", e);
        }

        return vendaSalva;
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
