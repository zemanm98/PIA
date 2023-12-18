/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package cz.kiv.pia.api.controller;

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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-18T23:10:12.209664200+01:00[Europe/Prague]")
@Validated
@Tag(name = "test", description = "the test API")
public interface HelloApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /hello : Says hello on behalf of given user
     *
     * @param from blah (optional)
     * @return Successful operation (status code 200)
     *         or Not supported HTTP method. (status code 405)
     *         or Not acceptable response representation. (status code 406)
     */
    @Operation(
        operationId = "sayHello",
        summary = "Says hello on behalf of given user",
        tags = { "test" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "text/html", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "405", description = "Not supported HTTP method."),
            @ApiResponse(responseCode = "406", description = "Not acceptable response representation.")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/hello",
        produces = { "text/html" }
    )
    default ResponseEntity<String> sayHello(
        @Parameter(name = "from", description = "blah", in = ParameterIn.QUERY) @Valid @RequestParam(value = "from", required = false) String from
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf(""))) {
                    String exampleString = "";
                    ApiUtil.setExampleResponse(request, "", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
