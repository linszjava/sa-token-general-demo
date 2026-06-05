package com.size.springboot3.controller.use;

import cn.dev33.satoken.stp.StpUtil;
import com.size.springboot3.result.R;
import com.size.springboot3.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/6/1 02:00
 * @description 权限测试 主要测试StpUtil.xxx
 */
@RestController
@RequestMapping("/jur")
public class JurAuthController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 1. 测试 查询权限
     *
     */
    @GetMapping("/queryPermission")
    public R<Map<String, List<String>>> queryPermission() {
        List<String> permissionList = StpUtil.getPermissionList();
        List<String> roleList = StpUtil.getRoleList();
        List<String> loginRoleList = StpUtil.getRoleList(StpUtil.getLoginIdAsLong());
        Map<String, List<String>> resultMap = new HashMap<>();
        resultMap.put("permissionList", permissionList);
        resultMap.put("roleList", roleList);
        resultMap.put("loginRoleList", loginRoleList);
        return R.ok(resultMap);
    }

    /**
     * 2. 测试 是否有该权限
     */
    @GetMapping("/hasPermission")
    public R<Map<String, Boolean>> hasPermission() {
        boolean hasPermission = StpUtil.hasPermission("user-add");
        boolean hasPermissionOr = StpUtil.hasPermissionOr("user-add", "user-delete1");
        boolean hasPermissionAnd = StpUtil.hasPermissionAnd("user-add", "user-delete");

        // checkPermission  void
        StpUtil.checkPermission("user-add");
//        StpUtil.checkPermissionOr("user-add", "user-delete1");
//        StpUtil.checkPermissionAnd("user-add", "user-delete");

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("hasPermission", hasPermission);
        resultMap.put("hasPermissionOr", hasPermissionOr);
        resultMap.put("hasPermissionAnd", hasPermissionAnd);

        return R.ok(resultMap);
    }

    /**
     * 3、测试角色
     */
    @GetMapping("/queryRole")
    public R<Map<String, Boolean>> queryRole() {
        boolean hasRole = StpUtil.hasRole("admin");
        boolean hasRoleAnd = StpUtil.hasRoleAnd("admin", "super-admin1");
        boolean hasRoleOr = StpUtil.hasRoleOr("admin", "super-admin1");

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("hasRole", hasRole);
        resultMap.put("hasRoleAnd", hasRoleAnd);
        resultMap.put("hasRoleOr", hasRoleOr);

        StpUtil.checkRole("admin");

        return R.ok(resultMap);
    }
}


/**  1. @GetMapping("/queryPermission") 测试结果
 * {
 *   "code": 200,
 *   "msg": "操作成功",
 *   "data": {
 *     "loginRoleList": [
 *       "admin",
 *       "super-admin"
 *     ],
 *     "permissionList": [
 *       "101",
 *       "user-add",
 *       "user-delete",
 *       "user-update",
 *       "user-check",
 *       "manager.*"
 *     ],
 *     "roleList": [
 *       "admin",
 *       "super-admin"
 *     ]
 *   }
 * }
 *
 *
 * 2.  @GetMapping("/hasPermission") 测试结果
 * {
 *   "code": 200,
 *   "msg": "操作成功",
 *   "data": {
 *     "hasPermissionOr": true,
 *     "hasPermissionAnd": true,
 *     "hasPermission": true
 *   }
 * }
 *
 * 3. @GetMapping("/queryRole") 测试结果
 * {
 *   "code": 200,
 *   "msg": "操作成功",
 *   "data": {
 *     "hasRoleAnd": false,
 *     "hasRoleOr": true,
 *     "hasRole": true
 *   }
 * }
 */