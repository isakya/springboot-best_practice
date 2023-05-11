package com.izumi.gen;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Map;

/**
 * 代码生成器支持自定义[DTO\VO等]模版
 */
public final class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String otherPath = this.getPathInfo(OutputFile.other);
        objectMap.put("hasOtherDate", false);
        tableInfo.getFields().forEach(tableField -> {
            if(!"createTime,updateTime".contains(tableField.getPropertyName()) && tableField.getColumnType().getType().equalsIgnoreCase("Date")){
                objectMap.put("hasOtherDate", true);
            }
        });
        // 是否为中间表
        final boolean r = StrUtil.isNotEmpty(tableInfo.getComment()) && tableInfo.getComment().startsWith("r_");
        objectMap.put("r", r);
        customFile.forEach((key, value) -> {
            String fileName = String.format(otherPath + File.separator + entityName + "%s", key);
            if("VO.java".equalsIgnoreCase(key)) {
                fileName = fileName.replace("dto", "vo");
            }
            boolean isFileOverride = Convert.toBool(objectMap.get(key+"FileOverride"),false);
            if(r) {
                if(!"PageParam.java".equalsIgnoreCase(key)) {
                    this.outputFile(new File(fileName), objectMap, value, isFileOverride);
                }
            } else {
                this.outputFile(new File(fileName), objectMap, value, isFileOverride);
            }
        });
    }

    @Override
    protected void outputController(@org.jetbrains.annotations.NotNull TableInfo tableInfo, @org.jetbrains.annotations.NotNull Map<String, Object> objectMap) {
        // 中间表不生成controller，用表的注释前缀 r_ 来识别中间表
        if(StrUtil.isNotEmpty(tableInfo.getComment()) && tableInfo.getComment().startsWith("r_")) {
            return;
        }
        super.outputController(tableInfo, objectMap);
    }
}