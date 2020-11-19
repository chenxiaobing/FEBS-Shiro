/**
 * Copyright 2020 www.arbexpress.cn
 * <p>
 * All right reserved
 * <p>
 * Create on 2020/11/19 19:08
 */
package cc.mrbird.febs.finance;


import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @File: personalAssetsController
 * @Author: chenxb
 * @Date: 2020/11/19 19:08
 * @Description:
 */
@RestController
@RequestMapping("personalAssets")
public class personalAssetsController  extends BaseController {

    @GetMapping("list")
    public FebsResponse userList() {
        //Map<String, Object> dataTable = getDataTable(this.userService.findUserDetailList(user, request));
        Map<String, Object> dataTable =new HashMap();
        List<Assets> list=new ArrayList<>();
        Assets a1=new Assets();
        a1.setTotalMoney("111");a1.setGongjijing("222");a1.setConsume("111");
        list.add(a1);
        dataTable.put("rows", list);
        dataTable.put("total", 1);
        return new FebsResponse().success().data(dataTable);
    }
}
