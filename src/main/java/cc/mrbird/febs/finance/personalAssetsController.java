/**
 * Copyright 2020 www.arbexpress.cn
 * <p>
 * All right reserved
 * <p>
 * Create on 2020/11/19 19:08
 */
package cc.mrbird.febs.finance;


import cc.mrbird.febs.common.annotation.DataPermission;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.finance.mapper.PersonalAssetsMapper;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.lang.reflect.Field;
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
@Slf4j
@RestController
@RequestMapping("personalAssets")
public class personalAssetsController  extends BaseController {

    @Autowired
    private PersonalAssetsMapper personalAssetsMapper;

    @GetMapping("list")
    public FebsResponse userList(String time, QueryRequest request) {
        Map<String, Object> dataTable =new HashMap();
        QueryWrapper<PersonalAssets> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(time)) {
            queryWrapper.lambda().eq(PersonalAssets::getDate, time);
        }
        queryWrapper.orderByDesc("date");
        Page<PersonalAssets> page = new Page<>(request.getPageNum(), request.getPageSize());
        IPage<PersonalAssets> pageResult = personalAssetsMapper.selectPage(page,queryWrapper);

        List<PersonalAssets> list=pageResult.getRecords();
        list.forEach(x->{
            x.setTotalMoney(x.getTotalMoney()/10000);
            x.setGongJiJing(x.getGongJiJing()/10000);
            x.setHairdresserStock(x.getHairdresserStock()/10000);
            x.setArrears(x.getArrears()/10000);
            x.setConsume(x.getConsume()/10000);
            x.setHairdresserBonus(x.getHairdresserBonus()/10000);
            x.setCarLoan(x.getCarLoan()/10000);
        });
        dataTable.put("rows", list);
        dataTable.put("total", pageResult.getTotal());
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping("add")
    public FebsResponse add(@Valid PersonalAssets assets) {
        QueryWrapper<PersonalAssets> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(assets.getDate())) {
            queryWrapper.lambda().eq(PersonalAssets::getDate, assets.getDate());
            List<PersonalAssets> list=personalAssetsMapper.selectList(queryWrapper);
            if (list != null && list.size() > 0) {
                return new FebsResponse().fail().message("已经存在");
            }
        }
        String date=getCurrentTime();
        assets.setCreateTime(date);
        assets.setUpdateTime(date);
        this.personalAssetsMapper.insert(assets);
        return new FebsResponse().success();
    }

    @PostMapping("modify")
    public FebsResponse modify(@Valid PersonalAssets assets) {
        String date=getCurrentTime();
        assets.setUpdateTime(date);
        this.personalAssetsMapper.updateById(assets);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{id}")
    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String id) {
        this.personalAssetsMapper.deleteById(id);
        return new FebsResponse().success();
    }

    @GetMapping("dates/{itemType}")
    public FebsResponse dates(@NotBlank(message = "{required}") @PathVariable String itemType) throws Exception {
        Map<String, Object> dataTable =new HashMap();
        log.info("itemType:"+itemType);
        QueryWrapper<PersonalAssets> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("date");
        List<PersonalAssets> result = personalAssetsMapper.selectList(queryWrapper);
        List<Dates> dates=new ArrayList<>(result.size());
        Field fieldSet = PersonalAssets.class.getDeclaredField(itemType);
        fieldSet.setAccessible(true);
        result.forEach(x->{
            try {
                Dates date = new Dates();
                date.setDate(x.getDate());
                date.setValue((double) fieldSet.get(x)/10000);
                log.info("value:"+date.getValue());
                dates.add(date);
            }
            catch (IllegalAccessException e){
                e.printStackTrace();
            }
        });
        dataTable.put("assets", dates);
        return new FebsResponse().success().data(dataTable);
    }

}


