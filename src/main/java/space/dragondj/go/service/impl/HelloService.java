package space.dragondj.go.service.impl;

import org.springframework.stereotype.Service;
import space.dragondj.go.service.IHelloService;

@Service
public class HelloService implements IHelloService {

    @Override
    public String getHello(String name) {
        return "Hello World! " + name;
    }

}
