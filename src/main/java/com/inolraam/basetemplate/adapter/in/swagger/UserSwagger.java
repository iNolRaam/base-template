package com.inolraam.basetemplate.adapter.in.swagger;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseCode;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseDescription;
import com.inolraam.basetemplate.adapter.in.swagger.constant.Tag;
import com.inolraam.basetemplate.adapter.in.swagger.constant.user.UserJson;
import com.inolraam.basetemplate.usecase.user.dto.UserInput;
import com.inolraam.basetemplate.usecase.user.dto.UserOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/**
 * Swagger documentation interface for User endpoints.
 * 
 * @author Generated
 * @version 1.0
 */
public interface UserSwagger {
    String REQUEST_BODY_DESC = "User data for the operation";

    @Operation(
            summary = "Create user",
            description = "Method to create a new user with at least one profile",
            tags = {Tag.USER},
            requestBody = @RequestBody(
                    description = REQUEST_BODY_DESC,
                    required = true,
                    content = {
                            @Content(
                                    examples = {@ExampleObject(name = "User Creation Example", value = UserJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserInput.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(responseCode = ResponseCode.CREATED_201,
                            description = ResponseDescription.CREATE_DESCRIPTION,
                            content = @Content(
                                    examples = {@ExampleObject(name = "Created User", value = UserJson.RESPONSE_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserOutput.class)
                            )
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400)
            }
    )
    ResponseEntity<Response> createUser(UserInput input, BindingResult result);

    @Operation(
            summary = "Delete user",
            description = "Method to delete a user by id",
            tags = {Tag.USER},
            responses = {
                    @ApiResponse(responseCode = ResponseCode.ACCEPTED_202,
                            description = ResponseDescription.DELETE_DESCRIPTION),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.BAD_REQUEST_400),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.NOT_FOUND_404)
            }
    )
    ResponseEntity<Response> deleteUser(long id);

    @Operation(
            summary = "Update user",
            description = "Method to update a user by id",
            tags = {Tag.USER},
            requestBody = @RequestBody(
                    description = REQUEST_BODY_DESC,
                    required = true,
                    content = {
                            @Content(
                                    examples = {@ExampleObject(name = "User Update Example", value = UserJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserInput.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(
                            responseCode = ResponseCode.OK_200,
                            description = ResponseDescription.UPDATE_DESCRIPTION,
                            content = @Content(
                                    examples = {@ExampleObject(name = "Updated User", value = UserJson.RESPONSE_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserOutput.class)
                            )
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.REF_NOT_FOUND_404)
            }
    )
    ResponseEntity<Response> updateUser(long id, UserInput input, BindingResult result);

    @Operation(
            summary = "Read user",
            description = "Method to read a user by id",
            tags = {Tag.USER},
            responses = {
                    @ApiResponse(
                            responseCode = ResponseCode.OK_200,
                            description = ResponseDescription.READ_DESCRIPTION,
                            content = @Content(
                                    examples = {@ExampleObject(name = "User Details", value = UserJson.RESPONSE_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserOutput.class)
                            )
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.REF_NOT_FOUND_404)
            }
    )
    ResponseEntity<Response> readUser(long id);
}