package cc.mrbird.febs.finance;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonalAssetDTO implements Serializable {

    private String date;

    private Double sumMoney;

    private Double totalMoney;

    private Double gongJiJing;

    private Double hairdresserStock;

    private Double arrears;

    private Double consume;

    private Double hairdresserBonus;

    private Double carLoan;
}
