package api.wm.service;

import api.wm.domain.entity.Produto;
import api.wm.repository.CategoriaRepository;
import api.wm.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto salvar(Produto produto) {
        if (produtoRepository.existsByCodigo(produto.getCodigo())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código já existente");

        produto.setCategoria(
                categoriaRepository.findById(produto.getCategoria().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Não existe essa categoria"))
        );

        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404))
        );
    }

    public Produto atualizar(Long id, Produto produto) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe esse id");
        }

        produto.setId(id);
        produto.setCategoria(
                categoriaRepository.findById(produto.getCategoria().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Não existe essa categoria"))
        );

        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe esse id");
        produtoRepository.deleteById(id);
    }


    public Produto atualizarQtd(Long id, Double qtd, String func) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe esse id"));
        if (func == "AUMENTAR") {
            produto.setQuantidade(produto.getQuantidade() + qtd);
        } else if (func == "DIMINUIR") {
            produto.setQuantidade(produto.getQuantidade() - qtd);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Erro na func");
        }

        return produtoRepository.save(produto);
    }
}
