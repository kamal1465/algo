package com.kamals.algo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequestMapping("api/v1/algo")
@RestController
public class AlgoController
{
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(path = "/{service}/{method}")
    public Object perform(@PathVariable("service") String serviceName,
                          @PathVariable("method") String methodName)
    {
        Object service = applicationContext.getBean(serviceName);
        Object result = null;
        try
        {
            Method method = service.getClass().getMethod(methodName);
            result = method.invoke(service);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}

