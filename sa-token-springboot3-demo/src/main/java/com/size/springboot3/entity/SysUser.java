package com.size.springboot3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}