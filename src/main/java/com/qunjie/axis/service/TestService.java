package com.qunjie.axis.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by whs on 2020/12/3.
 */
@Service
public interface TestService {

    String test(String test);

    String tst(List<String> args);
}
