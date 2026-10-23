// CAMADA: Service
// RESPONSABILIDADE: aplica as regras de negocio do controle de estoque.
// REGRAS: R1 (estoque atual nao pode ser menor que o estoque minimo).
// ROTAS ATENDIDAS: POST, GET e GET /{id} de /controle-estoque em docs/contrato-da-api.md.
// SITUACAO: implementada.
package api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import api.dto.ControleEstoqueRequestDTO;
import api.dto.ControleEstoqueResponseDTO;
import api.mapper.ControleEstoqueMapper;
import api.model.ControleEstoque;
import api.repository.ControleEstoqueRepository;

@Service
public class ControleEstoqueService {

    private final ControleEstoqueRepository repository;
    private final ControleEstoqueMapper mapper;

    public ControleEstoqueService(ControleEstoqueRepository repository, ControleEstoqueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ControleEstoqueResponseDTO criar(ControleEstoqueRequestDTO request) {
        // R1: o estoque atual nao pode ser menor que o estoque minimo.
        if (request.estoqueAtual() < request.estoqueMinimo()) {
            throw new EstoqueAbaixoDoMinimoException(
                    "R1: o estoque atual nao pode ser menor que o estoque minimo");
        }
        ControleEstoque estoque = mapper.toEntity(request);
        return mapper.toResponse(repository.save(estoque));
    }

    public List<ControleEstoqueResponseDTO> listar() {
        return mapper.toResponseList(repository.findAll());
    }

    public ControleEstoqueResponseDTO buscarPorId(Integer codigo) {
        ControleEstoque estoque = repository.findById(codigo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Controle de estoque nao encontrado: " + codigo));
        return mapper.toResponse(estoque);
    }
}
