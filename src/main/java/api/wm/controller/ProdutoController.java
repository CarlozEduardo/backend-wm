package api.wm.controller;

import api.wm.domain.entity.Produto;
import api.wm.domain.dto.ProdutoRequestDTO;
import api.wm.domain.mapper.ProdutoMapper;
import api.wm.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody ProdutoRequestDTO produto) {
        return ResponseEntity.ok(
                produtoService.salvar(
                        ProdutoMapper.to(produto)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ProdutoRequestDTO>> listar() {
        List<Produto> produtos = produtoService.listar();
        if (produtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(produtos.stream().map(ProdutoMapper::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                produtoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(
                produtoService.atualizar(id, ProdutoMapper.to(produtoRequestDTO))
        );
    }

    @PatchMapping("/mudarpreco/{preco}")
    public ResponseEntity<List<Produto>> mudarPrecoDaListaProdutos(@PathVariable double preco, @RequestBody List<String> listCOD) {
        List<Produto> produtos = produtoService.atualizarPreco(preco, listCOD);
        if (produtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
