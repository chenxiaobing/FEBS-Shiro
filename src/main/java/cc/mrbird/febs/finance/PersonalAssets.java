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
    private String totalMoney;

    @TableField("gong_ji_jing")
    private String gongJiJing;

    @TableField("hairdresser_stock")
    private String hairdresserStock;

    @TableField("arrears")
    private String arrears;

    @TableField("consume")
    private String consume;

    @TableField("hairdresser_bonus")
    private String hairdresserBonus;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;
}
