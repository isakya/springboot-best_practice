package com.izumi.controller;

import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.dto.RolePageParam;
import com.izumi.dto.RoleParam;
import com.izumi.entity.Role;
import com.izumi.entity.User;
import com.izumi.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "角色管理")
public class RoleController {
    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/role/save")
    @ApiOperation(value = "添加角色")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) RoleParam role) {
        return CommonResult.ok();
    }

    /**
     * 删除角色
     * @param param
     * @return
     */
    @PostMapping("/role/remove")
    @ApiOperation(value = "删除角色")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        return CommonResult.ok();
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PostMapping("/role/update")
    @ApiOperation(value = "修改角色")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) RoleParam role) {
        return CommonResult.ok();
    }

    /**
     * 查询单个角色
     * @param param
     * @return
     */
    @PostMapping("/role/info")
    @ApiOperation(value = "查询单个角色")
    public CommonResult<Role> info(@RequestBody IdParam param) {
        Role role = new Role();
        role.setId(1L);
        role.setCode("default");
        role.setName("默认角色");
        return CommonResult.data(role);
    }

    /**
     * 分页查询角色列表
     * @param param
     * @return
     */
    @PostMapping("/role/page")
    @ApiOperation(value = "查询角色列表")
    public CommonResult<CommonPage<Role>> page(@RequestBody RolePageParam param) {
        CommonPage<Role> page = new CommonPage<>();
        page.setPageNum(1);
        page.setPageSize(10);
        page.setTotalPage(100);
        page.setRecordCount(10);
        page.setRows(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            role.setId(new Long(i));
            role.setName("role" + i);
            role.setCode("name" + i);
            page.getRows().add(role);
        }
        return CommonResult.data(page);
    }
}
