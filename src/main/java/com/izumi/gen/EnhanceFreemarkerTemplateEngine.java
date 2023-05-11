package com.izumi.gen;


import cn.hutool.core.convert.Convert;
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
        customFile.forEach((key, value) -> {
            String fileName = String.format(otherPath + File.separator + entityName + "%s", key);
            boolean isFileOverride = Convert.toBool(objectMap.get(key+"FileOverride"),false);
            this.outputFile(new File(fileName), objectMap, value, isFileOverride);
        });
    }
}