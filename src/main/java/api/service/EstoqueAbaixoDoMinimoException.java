// CAMADA: Service
// RESPONSABILIDADE: excecao lancada quando uma regra de negocio do controle de estoque e violada.
// REGRAS: R1 (estoque atual nao pode ser menor que o estoque minimo).
// ROTAS ATENDIDAS: POST /controle-estoque em docs/contrato-da-api.md.
// SITUACAO: implementada.
package api.service;

public class EstoqueAbaixoDoMinimoException extends RegraDeNegocioException {

    public EstoqueAbaixoDoMinimoException(String mensagem) {
        super(mensagem);
    }
}
