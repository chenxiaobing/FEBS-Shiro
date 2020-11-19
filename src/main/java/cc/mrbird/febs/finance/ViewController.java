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
import cc.mrbird.febs.common.utils.FebsUtil;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("personalAssets")
    public String online() {
        return FebsUtil.view("finance/personalAssets");
    }
}
