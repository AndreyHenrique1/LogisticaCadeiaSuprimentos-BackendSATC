// CAMADA: DTO
// RESPONSABILIDADE: dados que o cliente envia para criar ou atualizar um item de estoque.
// REGRAS: campos obrigatórios e nao negativos (validadas pelo @Valid no Controller).
// ROTAS ATENDIDAS: POST /controle-estoque em docs/contrato-da-api.md.
// SITUACAO: implementada.
package api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Dados para cadastrar ou atualizar um item do controle de estoque")
public record ControleEstoqueRequestDTO(

        @Schema(description = "Nome do produto", example = "Caneta esferografica")
        @NotBlank(message = "Produto e obrigatorio")
        String produto,

        @Schema(description = "Quantidade atual em estoque", example = "120")
        @NotNull(message = "Estoque atual e obrigatorio")
        @PositiveOrZero(message = "Estoque atual nao pode ser negativo")
        Integer estoqueAtual,

        @Schema(description = "Estoque minimo desejado", example = "20")
        @NotNull(message = "Estoque minimo e obrigatorio")
        @PositiveOrZero(message = "Estoque minimo nao pode ser negativo")
        Integer estoqueMinimo

) {
}
