package com.inolraam.basetemplate.adapter.in.swagger;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseCode;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseDescription;
import com.inolraam.basetemplate.adapter.in.swagger.constant.Tag;
import com.inolraam.basetemplate.adapter.in.swagger.constant.profile.ProfileJson;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileInput;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ProfileSwagger {
    static final String REQUEST_BODY_DESC = "Description about request body";

    @Operation(
            summary = "Create profile",
            description = "Method to create a new profile",
            tags = {Tag.PROFILE},
            requestBody = @RequestBody(
                    description = REQUEST_BODY_DESC,
                    required = true,
                    content = {
                            @Content(
                                    examples = {@ExampleObject(value = ProfileJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProfileInput.class)
                            )
                    }
            ),
            responses = {
                    @ApiResponse(responseCode = ResponseCode.CREATED_201,
                            description = ResponseDescription.CREATE_DESCRIPTION,
                            content = @Content(
                                    examples = {@ExampleObject(value = ProfileJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProfileOutput.class)
                            )
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400)
            }
    )
    ResponseEntity<Response> createProfile(ProfileInput input, BindingResult result);

    @Operation(
            summary = "Delete profile",
            description = "Method to delete a profile by id",
            tags = {Tag.PROFILE},
            responses = {
                    @ApiResponse(responseCode = ResponseCode.NO_CONTENT_204,
                            description = ResponseDescription.DELETE_DESCRIPTION),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.BAD_REQUEST_400),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.NOT_FOUND_404)
            }
    )
    ResponseEntity<Response> deleteProfile(long id);

    @Operation(
            summary = "Update profile",
            description = "Method to update a profile by id",
            tags = {Tag.PROFILE},
            requestBody = @RequestBody(
                    description = REQUEST_BODY_DESC,
                    required = true,
                    content = {
                            @Content(
                                    examples = {@ExampleObject(value = ProfileJson.REQUEST_EXAMPLE)},
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProfileInput.class)
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
    ResponseEntity<Response> updateProfile(long id, ProfileInput input, BindingResult result);

    @Operation(
            summary = "Read profile",
            description = "Method to read a profile by id",
            tags = {Tag.PROFILE},
            responses = {
                    @ApiResponse(
                            responseCode = ResponseCode.OK_200,
                            description = ResponseDescription.READ_DESCRIPTION
                    ),
                    @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400),
                    @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.REF_NOT_FOUND_404)
            }
    )
    ResponseEntity<Response> readProfile(long id);
}