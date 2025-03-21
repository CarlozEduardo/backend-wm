package api.wm.service;

import api.wm.domain.entity.Categoria;
import api.wm.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired private ProdutoService produtoService;

    public Categoria salvar(Categoria categoria) {
        if (categoriaRepository.existsByNome(categoria.getNome())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome já existente");

        if (categoria.getNome().isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na escrita do nome");

        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404))
        );
    }

    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        if (!categoria.getProdutos().isEmpty()) throw new ResponseStatusException(HttpStatus.CONFLICT, "Existem produtos associados a categoria!");

        categoriaRepository.deleteById(id);
    }

    public Categoria atualizar(Long id, Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe esse id");
        }

        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }
}
