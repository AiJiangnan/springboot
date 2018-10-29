package com.ajn.springboot.studyspringboot.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 艾江南
 * @date 2018/8/29
 */
@Data
@TableName("admin")
public class User {

    @NotNull(message = "ID不能为空")
    private String id;
    private String username;
    private String password;
    @DateTimeFormat(pattern = "yyyyMMdd HH:mm:ss")
    private Date createTime;
    private BigDecimal amount;
    private String total;

}
