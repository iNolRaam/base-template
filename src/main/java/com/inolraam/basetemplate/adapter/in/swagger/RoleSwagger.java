package com.inolraam.basetemplate.adapter.in.swagger;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseCode;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseDescription;
import com.inolraam.basetemplate.adapter.in.swagger.constant.Tag;
import com.inolraam.basetemplate.adapter.in.swagger.constant.role.RoleJson;
import com.inolraam.basetemplate.usecase.role.dto.RoleInput;
import com.inolraam.basetemplate.usecase.role.dto.RoleOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface RoleSwagger {
    static final String REQUEST_BODY_DESC = "Description about request body";

    @Operation(
            summary = "Create role",
            description = "Method to create a new role",
            tags = {Tag.ROLE},
            requestBody = @RequestBody(
                    description = REQUEST_BODY_DESC,
                    required = true,
                    content = {
                            @Content(
                                    examples = {@ExampleObject(value = RoleJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RoleInput.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(responseCode = ResponseCode.CREATED_201,
                            description = ResponseDescription.CREATE_DESCRIPTION,
                            content = @Content(
                                    examples = {@ExampleObject(value = RoleJson.RESPONSE_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RoleOutput.class)
                            )
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400)
            }
    )
    ResponseEntity<Response> createRole(RoleInput input, BindingResult result);

    @Operation(
            summary = "Update role",
            description = "Method to update a role",
            tags = {Tag.ROLE},
            requestBody = @RequestBody(
                    description = REQUEST_BODY_DESC,
                    required = true,
                    content = {
                            @Content(
                                    examples = {@ExampleObject(value = RoleJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RoleInput.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(responseCode = ResponseCode.OK_200,
                            description = ResponseDescription.UPDATE_DESCRIPTION,
                            content = @Content(
                                    examples = {@ExampleObject(value = RoleJson.RESPONSE_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RoleOutput.class)
                            )
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.REF_NOT_FOUND_404)
            }
    )
    ResponseEntity<Response> updateRole(long id, RoleInput input, BindingResult result);

    @Operation(
            summary = "Delete role",
            description = "Method to delete a role",
            tags = {Tag.ROLE},
            responses = {
                    @ApiResponse(responseCode = ResponseCode.ACCEPTED_202, description = ResponseDescription.DELETE_DESCRIPTION),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.REF_NOT_FOUND_404)
            }
    )
    ResponseEntity<Response> deleteRole(long id);

    @Operation(
            summary = "Read role",
            description = "Method to read a role",
            tags = {Tag.ROLE},
            responses = {
                    @ApiResponse(responseCode = ResponseCode.OK_200,
                            description = ResponseDescription.READ_DESCRIPTION,
                            content = @Content(
                                    examples = {@ExampleObject(value = RoleJson.RESPONSE_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RoleOutput.class)
                            )
                    ),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.REF_NOT_FOUND_404)
            }
    )
    ResponseEntity<Response> readRole(long id);
}