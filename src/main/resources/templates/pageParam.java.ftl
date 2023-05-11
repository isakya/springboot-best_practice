package ${package.Other};

import ${g.basePackage}.base.PageParam;
import ${package.Entity}.${entity};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ${entity}PageParam extends PageParam<${entity}> {
<#list table.fields as field>
    <#if !field.keyFlag>
    @ApiModelProperty(value = "${field.comment}【等值查询】")
    private ${field.propertyType} m_EQ_${field.propertyName};
    </#if>
    <#if !field.keyFlag && field.columnType.type == "String">
    @ApiModelProperty(value = "${field.comment}【模糊查询】")
    private ${field.propertyType} m_LIKE_${field.propertyName};
    </#if>
</#list>
}
