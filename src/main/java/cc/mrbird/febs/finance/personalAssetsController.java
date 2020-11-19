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
import cc.mrbird.febs.finance.mapper.PersonalAssetsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Autowired
    private PersonalAssetsMapper personalAssetsMapper;

    @GetMapping("list")
    public FebsResponse userList() {
        Map<String, Object> dataTable =new HashMap();
        QueryWrapper<PersonalAssets> queryWrapper = new QueryWrapper<>();
        List<PersonalAssets> list=personalAssetsMapper.selectList(queryWrapper);
        dataTable.put("rows", list);
        dataTable.put("total", 1);
        return new FebsResponse().success().data(dataTable);
    }
}
