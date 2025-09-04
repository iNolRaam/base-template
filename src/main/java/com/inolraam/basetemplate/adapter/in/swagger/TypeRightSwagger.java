package com.inolraam.basetemplate.adapter.in.swagger;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseCode;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseDescription;
import com.inolraam.basetemplate.adapter.in.swagger.constant.Tag;
import com.inolraam.basetemplate.adapter.in.swagger.constant.typeright.TypeRightJson;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.dto.UpdateTypeRightInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface TypeRightSwagger {
    static final String REQUEST_BODY_DESC = "Description about request body";

    @Operation(
            summary = "Create type right",
            description = "Method to create a new type right",
            tags = {Tag.TYPE_RIGHT},
            requestBody = @RequestBody(
                    description = REQUEST_BODY_DESC,
                    required = true,
                    content = {
                            @Content(
                                    examples = {@ExampleObject(value = TypeRightJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TypeRightInput.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(responseCode = ResponseCode.CREATED_201,
                            description = ResponseDescription.CREATE_DESCRIPTION,
                            content = @Content(
                                    examples = {@ExampleObject(value = TypeRightJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TypeRightOutput.class)
                            )
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400)
            }
    )
    ResponseEntity<Response> createTypeRight(TypeRightInput input, BindingResult result);

    @Operation(
            summary = "Delete type right",
            description = "Method to delete a type right by id",
            tags = {Tag.TYPE_RIGHT},
            responses = {
                    @ApiResponse(responseCode = ResponseCode.NO_CONTENT_204,
                            description = ResponseDescription.DELETE_DESCRIPTION),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.BAD_REQUEST_400),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.NOT_FOUND_404)
            }
    )
    ResponseEntity<Response> deleteTypeRight(long id);

    @Operation(
            summary = "Update type right",
            description = "Method to update a type right by id",
            tags = {Tag.TYPE_RIGHT},
            requestBody = @RequestBody(
                    description = REQUEST_BODY_DESC,
                    required = true,
                    content = {
                            @Content(
                                    examples = {@ExampleObject(value = TypeRightJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateTypeRightInput.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = ResponseCode.OK_200,
                            description = ResponseDescription.UPDATE_DESCRIPTION
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.BAD_REQUEST_400),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.NOT_FOUND_404)
            }
    )
    public ResponseEntity<Response> updateTypeRight(long id, TypeRightInput input, BindingResult result);

    @Operation(
            summary = "Read type right",
            description = "Method to read a type right by id",
            tags = {Tag.TYPE_RIGHT},
            responses = {
                    @ApiResponse(
                            responseCode = ResponseCode.OK_200,
                            description = ResponseDescription.READ_DESCRIPTION
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.REF_NOT_FOUND_404)
            }
    )
    public ResponseEntity<Response> readTypeRight(long id);
}
