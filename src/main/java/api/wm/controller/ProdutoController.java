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
    public ResponseEntity<ProdutoRequestDTO> criar(@RequestBody ProdutoRequestDTO produto) {
        return ResponseEntity.ok(
                ProdutoMapper.toDTO(
                        produtoService.salvar(
                                ProdutoMapper.to(produto)
                        )
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = produtoService.listar();
        if (produtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRequestDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                ProdutoMapper.toDTO(
                        produtoService.buscarPorId(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRequestDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(
                ProdutoMapper.toDTO(
                        produtoService.atualizar(id, ProdutoMapper.to(produtoRequestDTO))
                )
        );
    }

    @PatchMapping("/{id}/diminuir/{qtd}")
    public ResponseEntity<ProdutoRequestDTO> diminuir(@PathVariable Long id, @PathVariable Double qtd) {
        return ResponseEntity.ok(
                ProdutoMapper.toDTO(
                        produtoService.atualizarQtd(id, qtd, "DIMINUIR")
                )
        );
    }

    @PatchMapping("/{id}/aumentar/{qtd}")
    public ResponseEntity<ProdutoRequestDTO> adicionar(@PathVariable Long id, @PathVariable Double qtd) {
        return ResponseEntity.ok(
                ProdutoMapper.toDTO(
                        produtoService.atualizarQtd(id, qtd, "AUMENTAR")
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
