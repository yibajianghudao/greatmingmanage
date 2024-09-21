package com.manage.greatming.greatmingmanage.DAO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String passwd; // 可以使用驼峰命名，mybits会自动识别，但是保险起见还是使用原名

    private String tag;

    private String ranks;
    
    private String company;

    private int kills;

    private int attendance;

    private double balance;

    private String userpic;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

}
