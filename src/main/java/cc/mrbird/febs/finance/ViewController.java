/**
 * Copyright 2020 www.arbexpress.cn
 * <p>
 * All right reserved
 * <p>
 * Create on 2020/11/19 19:47
 */
package cc.mrbird.febs.finance;


import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.finance.mapper.PersonalAssetsMapper;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @File: ViewController
 * @Author: chenxb
 * @Date: 2020/11/19 19:47
 * @Description:
 */
@Controller("financeView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "finance")
@RequiredArgsConstructor
public class ViewController extends BaseController {
    @Autowired
    private PersonalAssetsMapper personalAssetsMapper;

    @GetMapping("personalAssets")
    public String personalAssets() {
        return FebsUtil.view("finance/personalAssets");
    }

    @GetMapping("assetsAnalysis")
    public String assetsAnalysis() {
        return FebsUtil.view("finance/assetsAnalysis");
    }

    @GetMapping("assetsAdd")
    public String assetsAdd() {
        return FebsUtil.view("finance/assetsAdd");
    }

    @GetMapping("assetsDetail/{id}")
    public String assetsDetail(@PathVariable Integer id,Model model) {
        resolveAssetsModel(id, model);
        return FebsUtil.view("finance/assetsDetail");
    }

    @GetMapping("assetsModify/{id}")
    public String assetsModify(@PathVariable Integer id,Model model) {
        modifyAssetsModel(id, model);
        return FebsUtil.view("finance/assetsModify");
    }

    private void modifyAssetsModel(Integer id, Model model) {
        QueryWrapper<PersonalAssets> queryWrapper = new QueryWrapper<>();
        if (id!=null) {
            queryWrapper.lambda().eq(PersonalAssets::getId, id);
        }
        List<PersonalAssets> list=personalAssetsMapper.selectList(queryWrapper);
        PersonalAssets assets=list.get(0);
        model.addAttribute("assets",assets);
    }


    private void resolveAssetsModel(Integer id, Model model) {
        QueryWrapper<PersonalAssets> queryWrapper = new QueryWrapper<>();
        if (id!=null) {
            queryWrapper.lambda().eq(PersonalAssets::getId, id);
        }
        List<PersonalAssets> list=personalAssetsMapper.selectList(queryWrapper);
        PersonalAssets assets=list.get(0);
        assets.setTotalMoney(assets.getTotalMoney()/10000);
        assets.setGongJiJing(assets.getGongJiJing()/10000);
        assets.setHairdresserStock(assets.getHairdresserStock()/10000);
        assets.setArrears(assets.getArrears()/10000);
        assets.setConsume(assets.getConsume()/10000);
        assets.setHairdresserBonus(assets.getHairdresserBonus()/10000);
        assets.setCarLoan(assets.getCarLoan()/10000);
        model.addAttribute("assets",assets);
    }
}
