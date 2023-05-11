package ${package.Controller};

import ${g.basePackage}.base.CommonPage;
import ${g.basePackage}.base.CommonResult;
import ${g.basePackage}.base.IdParam;
import ${g.basePackage}.base.IdsParam;
import ${package.Other}.${entity}PageParam;
import ${package.Other}.${entity}Param;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${package.Entity?replace("entity","vo")}.${entity}VO;
import ${g.basePackage}.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
@RestController
@Api(tags = "${table.comment!}管理")
@RequiredArgsConstructor
@RequestMapping("/${package.ModuleName}")
public class ${entity}Controller {
    private final ${entity}Service ${table.entityPath}Service;

    /**
     * 添加${table.comment!}
     * @param param
     * @return
     */
    @PostMapping("/${table.entityPath}/save")
    @ApiOperation(value = "添加${table.comment!}")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) ${entity}Param param) {
        ${table.entityPath}Service.save(param);
        return CommonResult.ok();
    }

    /**
     * 删除${table.comment!}
     * @param param
     * @return
     */
    @PostMapping("/${table.entityPath}/remove")
    @ApiOperation(value = "删除${table.comment!}")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        ${table.entityPath}Service.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }

    /**
     * 修改${table.comment!}
     * @param param
     * @return
     */
    @PostMapping("/${table.entityPath}/update")
    @ApiOperation(value = "修改${table.comment!}")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) ${entity}Param param) {
        ${table.entityPath}Service.update(param);
        return CommonResult.ok();
    }

    /**
     * 查询单个${table.comment!}
     * @param param
     * @return
     */
    @PostMapping("/${table.entityPath}/getById")
    @ApiOperation(value = "查询单个${table.comment!}")
    public CommonResult<${entity}> getById(@RequestBody IdParam param) {
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(param.getId());
        return CommonResult.data(${table.entityPath});
    }

    /**
     * 分页查询${table.comment!}列表
     * @param param
     * @return
     */
    @PostMapping("/${table.entityPath}/page")
    @ApiOperation(value = "查询${table.comment!}列表")
    public CommonResult<CommonPage<${entity}VO>> page(@RequestBody ${entity}PageParam param) {
        return CommonResult.data(${table.entityPath}Service.page(param));
    }
}
