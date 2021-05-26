package cn.lei.provider;

import cn.lei.springbootservice.HelloService;
import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 注意这里导包，导入dubbo的  而不是spring的
 */
@Service(version = "1.0.0", retries = 2)
public class HelloServiceImpl implements HelloService {

    @HystrixCommand
    @Override
    public String sayHello() {
        return "你好，SpringBoot Dubbo!!!";
    }


}
