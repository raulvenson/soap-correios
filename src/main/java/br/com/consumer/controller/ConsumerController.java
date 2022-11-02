package br.com.consumer.controller;

import br.com.consumer.exception.RestResponseDetails;
import br.com.consumer.exception.RestResponseException;
import br.com.consumer.repository.ConsumerRepository;
import br.com.consumer.util.ConsumerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Raul Venson
 */
@RestController
@Tag(name = "Consumer")
public class ConsumerController {

    @GetMapping(path = "/cep", produces = {"application/json"})
    @Operation(summary = "Retorna o endereço de um CEP")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Succes"),
        @ApiResponse(responseCode = "400", description = "BadRequest", content = @Content(schema = @Schema(implementation = RestResponseDetails.class)))})
    public ResponseEntity consultaCEP(@RequestParam(name = "cep", required = false) String cep) throws Exception {
        try {
            new ConsumerUtils().validaCep(cep);
            return ResponseEntity.status(HttpStatus.OK).body(new ConsumerRepository().getCep(cep));
        } catch (Exception ex) {
            throw new RestResponseException(ex);
        }
    }
}
