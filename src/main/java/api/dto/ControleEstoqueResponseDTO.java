// CAMADA: DTO
// RESPONSABILIDADE: dados que a API devolve para um item de estoque, incluindo o codigo.
// REGRAS: nenhuma.
// ROTAS ATENDIDAS: POST, GET e GET /{id} de /controle-estoque em docs/contrato-da-api.md.
// SITUACAO: implementada.
package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Item do controle de estoque devolvido pela API")
public record ControleEstoqueResponseDTO(

        @Schema(description = "Codigo do item no banco", example = "1")
        Integer codigo,

        @Schema(description = "Nome do produto", example = "Caneta esferografica")
        String produto,

        @Schema(description = "Quantidade atual em estoque", example = "120")
        Integer estoqueAtual,

        @Schema(description = "Estoque minimo desejado", example = "20")
        Integer estoqueMinimo

) {
}
