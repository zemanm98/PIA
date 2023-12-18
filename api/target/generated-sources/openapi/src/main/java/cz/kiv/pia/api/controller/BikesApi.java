/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package cz.kiv.pia.api.controller;

import cz.kiv.pia.api.model.BikeDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-18T19:22:30.081980700+01:00[Europe/Prague]")
@Validated
@Tag(name = "bikes", description = "the bikes API")
public interface BikesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /bikes : Retrieves all bikes currently in the system.
     *
     * @return Bikes are retrieved. (status code 200)
     *         or Not supported HTTP method. (status code 405)
     *         or Not acceptable response representation. (status code 406)
     *         or Server error. (status code 500)
     */
    @Operation(
        operationId = "retrieveBikes",
        summary = "Retrieves all bikes currently in the system.",
        tags = { "bikes" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Bikes are retrieved.", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BikeDTO.class)))
            }),
            @ApiResponse(responseCode = "405", description = "Not supported HTTP method."),
            @ApiResponse(responseCode = "406", description = "Not acceptable response representation."),
            @ApiResponse(responseCode = "500", description = "Server error.")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/bikes",
        produces = { "application/json" }
    )
    default ResponseEntity<List<BikeDTO>> retrieveBikes(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"location\" : { \"latitude\" : 1.4658129805029452, \"longitude\" : 6.027456183070403 }, \"id\" : 5, \"lastServiceDate\" : \"2000-01-23\" }, { \"location\" : { \"latitude\" : 1.4658129805029452, \"longitude\" : 6.027456183070403 }, \"id\" : 5, \"lastServiceDate\" : \"2000-01-23\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
