// CAMADA: Model
// RESPONSABILIDADE: representa a tabela controle_estoque no banco.
// REGRAS: nenhuma (validações de formato ficam no DTO e a regra de negócio no Service).
// ROTAS ATENDIDAS: /controle-estoque em docs/contrato-da-api.md.
// SITUACAO: implementada.
package api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "controle_estoque")
public class ControleEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String produto;
    private Integer estoqueAtual;
    private Integer estoqueMinimo;

    protected ControleEstoque() {
    }

    public ControleEstoque(Integer codigo, String produto, Integer estoqueAtual, Integer estoqueMinimo) {
        this.codigo = codigo;
        this.produto = produto;
        this.estoqueAtual = estoqueAtual;
        this.estoqueMinimo = estoqueMinimo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getProduto() {
        return produto;
    }

    public Integer getEstoqueAtual() {
        return estoqueAtual;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }
}
