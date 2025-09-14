package com.inolraam.basetemplate.adapter.in.swagger;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseCode;
import com.inolraam.basetemplate.adapter.in.swagger.constant.ResponseDescription;
import com.inolraam.basetemplate.adapter.in.swagger.constant.Tag;
import com.inolraam.basetemplate.usecase.right.dto.RightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface RightSwagger {
        static final String REQUEST_BODY_DESC = "Description about request body";

        @Operation(summary = "Create right", description = "Method to create a new right", tags = {
                        Tag.RIGHT }, requestBody = @RequestBody(description = REQUEST_BODY_DESC, required = true, content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RightInput.class))
                        }), responses = {
                                        @ApiResponse(responseCode = ResponseCode.CREATED_201, description = ResponseDescription.CREATE_DESCRIPTION, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RightOutput.class))),
                                        @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400)
                        })
        ResponseEntity<Response> createRight(RightInput input, BindingResult result);

        @Operation(summary = "Delete right", description = "Method to delete a right by id", tags = {
                        Tag.RIGHT }, responses = {
                                        @ApiResponse(responseCode = ResponseCode.NO_CONTENT_204, description = ResponseDescription.DELETE_DESCRIPTION),
                                        @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.BAD_REQUEST_400),
                                        @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.NOT_FOUND_404)
                        })
        ResponseEntity<Response> deleteRight(long id);

        @Operation(summary = "Update right", description = "Method to update a right by id", tags = {
                        Tag.RIGHT }, requestBody = @RequestBody(description = REQUEST_BODY_DESC, required = true, content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RightInput.class))
                        }), responses = {
                                        @ApiResponse(responseCode = ResponseCode.OK_200, description = ResponseDescription.UPDATE_DESCRIPTION, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RightOutput.class))),
                                        @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.BAD_REQUEST_400),
                                        @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.NOT_FOUND_404)
                        })
        ResponseEntity<Response> updateRight(long id, RightInput input, BindingResult result);

        @Operation(summary = "Read right", description = "Method to read a right by id", tags = {
                        Tag.RIGHT }, responses = {
                                        @ApiResponse(responseCode = ResponseCode.OK_200, description = ResponseDescription.READ_DESCRIPTION, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RightOutput.class))),
                                        @ApiResponse(responseCode = ResponseCode.BAD_REQUEST_400, ref = ResponseCode.REF_BAD_REQUEST_400),
                                        @ApiResponse(responseCode = ResponseCode.NOT_FOUND_404, ref = ResponseCode.REF_NOT_FOUND_404)
                        })
        ResponseEntity<Response> readRight(long id);
}
