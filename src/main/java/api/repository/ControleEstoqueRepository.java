// CAMADA: Repository
// RESPONSABILIDADE: salva e busca itens do controle de estoque no banco.
// REGRAS: nenhuma.
// ROTAS ATENDIDAS: /controle-estoque em docs/contrato-da-api.md.
// SITUACAO: implementada.
package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import api.model.ControleEstoque;

public interface ControleEstoqueRepository extends JpaRepository<ControleEstoque, Integer> {
}
