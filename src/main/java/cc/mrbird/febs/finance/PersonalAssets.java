package cc.mrbird.febs.finance;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("personal_assets")
public class PersonalAssets  implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("date")
    private String date;

    @TableField("total_money")
    private Double totalMoney;

    @TableField("gong_ji_jing")
    private Double gongJiJing;

    @TableField("hairdresser_stock")
    private Double hairdresserStock;

    @TableField("arrears")
    private Double arrears;

    @TableField("consume")
    private Double consume;

    @TableField("hairdresser_bonus")
    private Double hairdresserBonus;

    @TableField("car_loan")
    private Double carLoan;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;
}
