package com.zt.study.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {

      /*  BigDecimal one  = BigDecimal.valueOf(8);
        BigDecimal two  = BigDecimal.valueOf(3);
        BigDecimal three = one.divide(two,6,ROUND_HALF_UP);

        Map<String,BigDecimal> map = new HashMap<>();
        map.put("3",two);
        two =  BigDecimal.valueOf(8);
        System.out.println(map);
        BigDecimal four = three.setScale(0,BigDecimal.ROUND_DOWN);
        System.out.println(one.doubleValue());
        System.out.println(two.doubleValue());
        System.out.println(three.doubleValue());
        System.out.println(four.doubleValue());

        int result = three.compareTo(four);//大于则返回1 小于-1  等于0
        System.out.println(result);*/
//        List<String> list = new ArrayList<>();
//        list.add(null);
//        list.add(null);
//        System.out.println(list);
        Map<String,DemoController> map = new HashMap<>();

        DemoController demoController = new DemoController();
        demoController.setDemo2(3);
        map.put("q",demoController);
        demoController.setDemo2(5);
        System.out.println(map.get("q").getDemo2());


        String data = "{\"tenant_id\":\""+11111+"\",\"vscode\":\""+1111+"\",\"openid\":\""+1111+"\"}";
        JSONObject jsonObject = JSON.parseObject(data);

        System.out.println(jsonObject);
}
}
