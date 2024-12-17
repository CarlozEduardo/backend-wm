package api.wm.controller;

import api.wm.domain.entity.Venda;
import api.wm.domain.dto.VendaRequestDTO;
import api.wm.domain.mapper.VendaMapper;
import api.wm.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaRequestDTO> criar(@RequestBody VendaRequestDTO venda) {
        // https://d2u2qhufg0q9tn.cloudfront.net/assets/arquivos/manual_4ce638a5-22e5-4a0d-a820-0108152ced91_ELGIN%20Printer%20Driver_v-1.7.6.rar
        // driver para windows

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
