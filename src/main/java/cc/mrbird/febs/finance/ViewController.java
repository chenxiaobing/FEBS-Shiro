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
    public String online() {
        return FebsUtil.view("finance/personalAssets");
    }

    @GetMapping("assetsAdd/{id}")
    public String assetsAdd(@PathVariable String id, Model model) {
        resolveUserModel(id, model, true);
        return FebsUtil.view("finance/assetsAdd");
    }

    private void resolveUserModel(String id, Model model, Boolean transform) {
        if("-1".equals(id)){
            return;
        }
        QueryWrapper<PersonalAssets> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(id)) {
            queryWrapper.lambda().eq(PersonalAssets::getId, id);
        }
        List<PersonalAssets> list=personalAssetsMapper.selectList(queryWrapper);
        PersonalAssets assets=list.get(0);
    }
}
