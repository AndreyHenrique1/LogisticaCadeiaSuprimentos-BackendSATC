// CAMADA: Mapper
// RESPONSABILIDADE: converte entre ControleEstoque (entidade) e os DTOs.
// REGRAS: nenhuma.
// ROTAS ATENDIDAS: /controle-estoque em docs/contrato-da-api.md.
// SITUACAO: implementada.
package api.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import api.dto.ControleEstoqueRequestDTO;
import api.dto.ControleEstoqueResponseDTO;
import api.model.ControleEstoque;

@Component
public class ControleEstoqueMapper {

    public ControleEstoque toEntity(ControleEstoqueRequestDTO request) {
        return new ControleEstoque(null, request.produto(), request.estoqueAtual(), request.estoqueMinimo());
    }

    public ControleEstoqueResponseDTO toResponse(ControleEstoque estoque) {
        return new ControleEstoqueResponseDTO(
                estoque.getCodigo(),
                estoque.getProduto(),
                estoque.getEstoqueAtual(),
                estoque.getEstoqueMinimo());
    }

    public List<ControleEstoqueResponseDTO> toResponseList(List<ControleEstoque> estoques) {
        return estoques.stream().map(this::toResponse).toList();
    }
}
