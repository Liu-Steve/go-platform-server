package com.goplatform.server.controller;

import com.goplatform.server.exception.GoServerException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试 API", description = "为前端提供连通性测试")
@RestController
@RequestMapping("${apiPrefix}/hello")
public class HelloController {

    @Operation(summary = "Hello", description = "name 为 400 时返回码为 400，其他返回码为 200")
    @Parameters(value = {
            @Parameter(name = "name", description = "测试字符串", in = ParameterIn.PATH)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "name 不为 400 时返回正常结果"),
            @ApiResponse(responseCode = "400", description = "name 为 400 时触发")
    })
    @GetMapping("/{name}")
    public ResponseEntity<String> hello(@PathVariable String name) {
        if (name.equals("400"))
//            return ResponseEntity.badRequest().body("Bad Request");
            throw new GoServerException(400, "Bad Request");
        return ResponseEntity.ok("OK " + name);
    }

}
