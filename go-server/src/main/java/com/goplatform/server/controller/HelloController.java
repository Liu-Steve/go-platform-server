package com.goplatform.server.controller;

import com.goplatform.server.pojo.domain.Result;
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

    /**
     * 测试 API，返回 'OK {name}' 字符串
     *
     * @param name 测试参数
     * @return 字符串 'OK {name}'
     */
    @Operation(summary = "Hello", description = "返回码为 200，返回内容为'OK {name}'")
    @Parameters(value = {
            @Parameter(name = "name", description = "测试字符串", in = ParameterIn.PATH)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "返回正常结果")
    })
    @GetMapping("/{name}")
    public Result hello(@PathVariable String name) {
        return Result.ok("OK " + name);
    }

}
