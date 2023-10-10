package space.dragondj.go.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.dragondj.go.service.IHelloService;

@RestController
@RequestMapping(path = "hello", produces = "application/json")
@Tag(name = "测试", description = "测试用接口")
@ApiResponses(@ApiResponse(responseCode = "200", description = "接口请求成功"))
public class HelloController {

    IHelloService service;

    @Autowired
    void HelloService(IHelloService service) {
        this.service = service;
    }

    @Operation(summary = "输出 Hello World! 以及名字")
    @GetMapping("/{name}")
    public String helloWorld(@PathVariable String name) {
        return service.getHello(name);
    }

}
