package cn.lei.consumer;

import cn.lei.springbootservice.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    /**
     * @Reference是dubbo的注解，也是注入，他一般注入的是分布式的远程服务的对象，需要dubbo配置使用。
     */
    @Reference(version = "1.0.0")
    private HelloService helloService;

    //, produces = "text/html;charset=UTF-8"
    @RequestMapping(value = "/sayhello")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "fallbackForSayHello")
    public String sayHello() {

        //完成对服务的调用
        String str = helloService.sayHello();
        System.out.println("控制台输出：" + str);
        return "str：" + str;
    }

    public String fallbackForSayHello(){
        return "你好，非常抱歉,Dubbo出错!!!";
    }

}
