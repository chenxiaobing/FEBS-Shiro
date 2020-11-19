/**
 * Copyright 2020 www.arbexpress.cn
 * <p>
 * All right reserved
 * <p>
 * Create on 2020/11/19 20:21
 */
package cc.mrbird.febs.finance;


import lombok.Data;

import java.io.Serializable;

/**
 * @File: Assets
 * @Author: chenxb
 * @Date: 2020/11/19 20:21
 * @Description:
 */
@Data
public class Assets  implements Serializable {
    private String totalMoney;
    private String gongjijing;
    private String hairdresserStock;
    private String arrears;
    private String consume;
    private String hairdresserBonus;
    private String time;
}
