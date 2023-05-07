package com.izumi.base;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;

@Data
@ApiModel
public class PageParam<T> {
    @ApiModelProperty(value = "当前页码")
    private Integer pageNum;
    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;
    @ApiModelProperty(value = "排序字段")
    private String orderBy;
    @ApiModelProperty(value = "是否查询总数")
    private Boolean isCount;
    // 列正则
    final static String REG_EXP = "(^_([a-zA-Z0-9\\s]_?)*$)|(^[a-zA-Z\\.](_?[a-zA-Z0-9\\.\\s])*_?$)";

    /**
     * 构建mybatis-plus分页对象
     */
    public IPage buildMpPage() {
        Page page = new Page(getPageNum(), getPageSize(), !Boolean.FALSE.equals(isCount));
        return page;
    }

    /**
     * 动态构建查询条件
     * @return
     */
    public QueryWrapper buildQueryWrapper() {
        QueryWrapper queryWrapper = Wrappers.query();
        // 获取所有属性 m_{type}_{column}
        // 有表别名时 m_{alias}_{type}_{column}
        Field fields [] = ReflectUtil.getFields(this.getClass());
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if(!fieldName.startsWith("m_")) continue;
            String arr [] = fieldName.split("_");
            if(arr.length != 3 && arr.length != 4) continue;
            String type = "";
            String column = "";
            if(arr.length == 3) {
                type = arr[1];
                // 小驼峰转为下划线
                column = StrUtil.toUnderlineCase(arr[2]);
            } else if(arr.length == 4) { // 表别名
                type = arr[2];
                column = arr[1] + "." + StrUtil.toUnderlineCase(arr[3]);
            }
            Object fieldValue = ReflectUtil.getFieldValue(this, fieldName);
            if(ObjectUtil.isEmpty((fieldValue))) continue;

            switch (type) {
                //    >= <=
                case "EQ":
                    queryWrapper.eq(column, fieldValue);
                    break;
                //    > <
                case "NE":
                    queryWrapper.ne(column, fieldValue);
                    break;
                //    >
                case "GT":
                    queryWrapper.gt(column, fieldValue);
                    break;
                //    >=
                case "GE":
                    queryWrapper.ge(column, fieldValue);
                    break;
                //    <
                case "LT":
                    queryWrapper.lt(column, fieldValue);
                    break;
                //    <=
                case "LE":
                    queryWrapper.le(column, fieldValue);
                    break;
                case "LIKE":
                    queryWrapper.like(column, fieldValue);
                    break;
                //  not like '%值%'
                case "NLIKE":
                    queryWrapper.notLike(column, fieldValue);
                    break;
                //    左模糊
                case "LLIKE":
                    queryWrapper.likeLeft(column, fieldValue);
                    break;
                //    右模糊
                case "RLIKE":
                    queryWrapper.likeRight(column, fieldValue);
                    break;
                //    区间
                case "BT":
                    if(fieldValue instanceof List) {
                        // 转换为List
                        List btValue = Convert.toList(fieldValue);
                        if(btValue.size() == 2) {
                            Object btValue1 = btValue.get(0);
                            Object btValue2 = btValue.get(1);
                            queryWrapper.between(column, btValue1, btValue2);
                        }
                    } else if(fieldValue instanceof String) {
                        String btArr [] = ((String) fieldValue).split(",");
                        Object btValue1 = btArr[0];
                        Object btValue2 = btArr[1];
                        queryWrapper.between(column, btValue1, btValue2);
                    }
                    break;
                //    包含
                case "IN":
                    if(fieldValue instanceof List) {
                        List inValue = Convert.toList(fieldValue);
                        queryWrapper.in(column, inValue);
                    } else if(fieldValue instanceof String) {
                        String inArr [] = ((String) fieldValue).split(",");
                        queryWrapper.in(column, CollectionUtil.toList(inArr));
                    }
                    break;
                //    不包含
                case "NIN":
                    if(fieldValue instanceof List) {
                        List inValue = Convert.toList(fieldValue);
                        queryWrapper.notIn(column, inValue);
                    } else if(fieldValue instanceof String) {
                        String ninArr [] = ((String) fieldValue).split(",");
                        queryWrapper.in(column, CollectionUtil.toList(ninArr));
                    }
                    break;
            }
        }
        // 构建排序
        // id desc
        // id asc
        // id desc, code asc
        if(StrUtil.isNotEmpty(orderBy)){
            String arr [] = orderBy.split(",");
            for(String s:arr) {
                String orderByList [] = s.split("\\s+");
                if (orderByList.length==1) {
                    String column = StrUtil.toUnderlineCase(orderByList[0]);
                    if (column.matches(REG_EXP)) {
                        queryWrapper.orderByAsc(column);
                    }
                } else {
                    String column = StrUtil.toUnderlineCase(orderByList[0]);
                    if (column.matches(REG_EXP)) {
                        if("desc".equalsIgnoreCase(orderByList[1])) {
                            queryWrapper.orderByDesc(column);
                        } else {
                            queryWrapper.orderByAsc(column);
                        }
                    }
                }
            }
        }
        return queryWrapper;
    }
}
