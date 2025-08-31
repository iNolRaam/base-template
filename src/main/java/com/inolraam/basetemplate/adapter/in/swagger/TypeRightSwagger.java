package com.inolraam.basetemplate.adapter.in.swagger;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.validation.MessageCodes;
import com.inolraam.basetemplate.usecase.typeright.dto.CreateTypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TypeRightSwagger {

    @Operation(
            summary = "Create type right",
            description = "Method to create a new type right",
            tags = {
                    "typeRight"
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Description about request body",
                    required = true,
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateTypeRightInput.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "New resource has been created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TypeRightOutput.class)
                            )
                    )
            }
    )
    ResponseEntity<Response> createTypeRight(CreateTypeRightInput input, BindingResult result);

    @Operation(
            summary = "Delete type right",
            description = "Method to delete a type right by id",
            tags = {
                    "typeRight"
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Description about request body",
                    required = true,
                    content = {
                            @Content(
                                    examples = {
                                            @ExampleObject(
                                                    value = "1"
                                            )
                                    },
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Long.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "The resource has been deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request is malformed"
                    )
            }
    )
    ResponseEntity<Response> deleteTypeRight(long id);
}
