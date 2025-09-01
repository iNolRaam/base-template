package com.inolraam.basetemplate.adapter.in.swagger;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.dto.UpdateTypeRightInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

public interface TypeRightSwagger {


    @Operation(
            summary = "Create type right",
            description = "Method to create a new type right",
            tags = {
                    "Endpoints for TypeRight"
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Description about request body",
                    required = true,
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TypeRightInput.class)
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
    ResponseEntity<Response> createTypeRight(TypeRightInput input, BindingResult result);

    @Operation(
            summary = "Delete type right",
            description = "Method to delete a type right by id",
            tags = {
                    "Endpoints for TypeRight"
            },
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

    @Operation(
            summary = "Update type right",
            description = "Method to update a type right by id",
            tags = {
                    "Endpoints for TypeRight"
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Description about request body",
                    required = true,
                    content = {
                            @Content(
                                    examples = {
                                            @ExampleObject(
                                                    value = "1, {s}"
                                            )
                                    },
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UpdateTypeRightInput.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The resource has been updated successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request is malformed."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource has not been found."
                    )
            }
    )
    public ResponseEntity<Response> updateTypeRight(long id, TypeRightInput input, BindingResult result);

    @Operation(
            summary = "Read type right",
            description = "Method to read a type right by id",
            tags = {
                    "Endpoints for TypeRight"
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The resource has been founded successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request is malformed."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource has not been found."
                    )
            }
    )
    public ResponseEntity<Response> readTypeRight(long id);
}
