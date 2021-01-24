/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.0).
 * https://openapi-generator.tech Do not edit the class manually.
 */
package com.thiago.githubreader.adapters.web.api;

import com.thiago.githubreader.adapters.web.model.ReadRepoRequestApiModel;
import com.thiago.githubreader.adapters.web.model.ReadRepoResponseApiModel;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Validated
@Api(value = "readrepo", description = "the readrepo API")
public interface ReadrepoApi {

    /**
     * POST /readrepo : Read repo
     * Scrapes a github repo, returning the number of lines and size of all files grouped by file extension.
     *
     * @param readRepoRequestApiModel  (optional)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Unprocessable Entity (WebDAV) (status code 422)
     */
    @ApiOperation(value = "Read repo", nickname = "readrepo", notes = "Scrapes a github repo, returning the number of" +
            " lines and size of all files grouped by file extension.", response = ReadRepoResponseApiModel.class,
            tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ReadRepoResponseApiModel.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable Entity (WebDAV)")})
    @RequestMapping(value = "/readrepo",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<ReadRepoResponseApiModel> readrepo(@ApiParam(value = "") @Valid @RequestBody(required = false) ReadRepoRequestApiModel readRepoRequestApiModel);

}
