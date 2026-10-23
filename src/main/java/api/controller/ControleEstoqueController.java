// CAMADA: Controller
// RESPONSABILIDADE: expõe as rotas do controle de estoque.
// REGRAS: nenhuma.
// ROTAS ATENDIDAS: POST, GET e GET /{id} de /controle-estoque em docs/contrato-da-api.md.
// SITUACAO: implementada.
package api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import api.dto.ControleEstoqueRequestDTO;
import api.dto.ControleEstoqueResponseDTO;
import api.service.ControleEstoqueService;

@RestController
@RequestMapping("/controle-estoque")
public class ControleEstoqueController {

    private final ControleEstoqueService service;

    public ControleEstoqueController(ControleEstoqueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ControleEstoqueResponseDTO> criar(@Valid @RequestBody ControleEstoqueRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<ControleEstoqueResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ControleEstoqueResponseDTO> buscarPorId(@PathVariable Integer codigo) {
        return ResponseEntity.ok(service.buscarPorId(codigo));
    }
}
