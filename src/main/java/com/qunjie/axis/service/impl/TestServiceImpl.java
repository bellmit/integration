package com.qunjie.axis.service.impl;

import com.qunjie.axis.service.TestService;

import java.util.List;

/**
 * Created by whs on 2020/12/3.
 */
public class TestServiceImpl implements TestService {
    @Override
    public String test(String test) {
        System.out.println(test);
        return test;
    }

    @Override
    public String tst(List<String> args) {
        System.out.println(args.size());
        for (int i = 0 ; i < args.size();i++){
            System.out.println("=======");
            System.out.println(args.get(i));
        }
        return args.toString();
    }
}
