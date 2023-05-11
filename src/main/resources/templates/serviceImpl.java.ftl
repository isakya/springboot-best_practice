package ${package.ServiceImpl};
<#if !r>
import ${g.basePackage}.base.CommonPage;
import ${package.Other}.${entity}PageParam;
import ${package.Other}.${entity}Param;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
</#if>
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    <#if !r>
    @Override
    @Transactional(rollbackFor = Exception.class) // 表示所有的异常都要回滚
    public boolean save(${entity}Param param) {
        ${entity} ${entity} = new ${entity}();
        BeanUtils.copyProperties(param, ${entity});
        return super.save(${entity});
    }

    @Override
    public boolean update(${entity}Param param) {
        ${entity} ${entity} = new ${entity}();
        BeanUtils.copyProperties(param, ${entity});
        return super.updateById(${entity});
    }

    @Override
    public CommonPage<${entity}> page(${entity}PageParam param) {
        IPage<${entity}> page = param.buildMpPage();
        QueryWrapper<${entity}> queryWrapper = param.buildQueryWrapper();
        List<${entity}> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
    </#if>
}
</#if>
