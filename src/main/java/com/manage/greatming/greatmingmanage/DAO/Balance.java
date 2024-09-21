package com.manage.greatming.greatmingmanage.DAO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Balance {
    
    @NotNull
    private long balanceid;

    @NotNull
    private long userid;

    @NotNull
    private double sum;

    @NotNull
    private double company;

    @NotNull
    private double ranks;

    @NotNull
    private double other;

    private String description;


}
