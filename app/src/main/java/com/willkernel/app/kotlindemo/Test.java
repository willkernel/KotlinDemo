package com.willkernel.app.kotlindemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willkernel
 * on 2018/3/25.
 */

public class Test {
    public void test() {
        List<String> strList = new ArrayList<>();
        List<Object> objList = new ArrayList<>();
        objList.addAll(strList);

    }
}
