package com.size.springboot3.param.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/5/31 00:52
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "用户名不能为空")
    private String username;


    @NotBlank(message = "密码不能为空")
    private String password;
}
