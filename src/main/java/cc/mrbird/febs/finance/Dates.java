package cc.mrbird.febs.finance;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dates implements Serializable {
    private String date;
    private double value;
}