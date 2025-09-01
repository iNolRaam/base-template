package com.inolraam.basetemplate.adapter.in.swagger;

import com.inolraam.basetemplate.usecase.right.dto.RightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface RightSwagger {
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
    public ResponseEntity<RightOutput> createRight(RightInput input);
}
