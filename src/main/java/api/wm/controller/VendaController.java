package api.wm.controller;

import api.wm.domain.entity.Venda;
import api.wm.domain.dto.VendaRequestDTO;
import api.wm.domain.mapper.VendaMapper;
import api.wm.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaRequestDTO> criar(@RequestBody VendaRequestDTO venda) {
        return ResponseEntity.ok(
                VendaMapper.toDTO(
                        vendaService.salvar(
                                VendaMapper.to(venda)
                        )
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<VendaRequestDTO>> listar() {
        List<Venda> vendas = vendaService.listar();
        if (vendas.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                vendas.stream().map(VendaMapper::toDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaRequestDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                VendaMapper.toDTO(vendaService.buscarPorId(id))
        );
    }
}
