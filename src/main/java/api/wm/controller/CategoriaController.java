package api.wm.controller;

import api.wm.domain.entity.Categoria;
import api.wm.domain.dto.CategoriaRequestDTO;
import api.wm.domain.mapper.CategoriaMapper;
import api.wm.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaRequestDTO> criar(@RequestBody CategoriaRequestDTO categoria) {
        return ResponseEntity.ok(
                CategoriaMapper.toDTO(
                        categoriaService.salvar(
                                CategoriaMapper.to(categoria)
                        )
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = categoriaService.listar();

        if (categorias.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRequestDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                CategoriaMapper.toDTO(
                        categoriaService.buscarPorId(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaRequestDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        return ResponseEntity.ok(
                CategoriaMapper.toDTO(
                        categoriaService.atualizar(id, CategoriaMapper.to(categoriaRequestDTO))
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
