package com.izumi;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class GenCode {
    public static void main(String[] args) {
        // System.err.println(System.getProperty("user.dir")); // 项目根目录
        String projectRootDir = System.getProperty("user.dir");
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/app?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true", "root", "root")
                .globalConfig(builder -> {
                    builder.author("izumi") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件 3.5.2时已弃用
                            .disableOpenDir() // 禁止打开目录
                            .outputDir(projectRootDir + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.izumi.modules") // 设置父包名
                            .xml("mapper.mapping")
                            .moduleName("sys"); // 设置父包模块名
                            // .pathInfo(Collections.singletonMap(OutputFile.xml, "H://code")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_role") // 设置需要生成的表名
                            .addTablePrefix("sys_"); // 设置过滤表前缀
                    // 实体类生成策略
                    builder.entityBuilder()
                            .enableLombok() // 启用lombok
                            .idType(IdType.ASSIGN_ID) // 配置主键策略
                            .fileOverride(); // 实体类模板可覆盖
                    // mapper生成策略配置
                    builder.mapperBuilder()
                            .enableMapperAnnotation() // 启用@Mapper注解
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .fileOverride(); //可覆盖
                })
                .templateConfig(builder -> {
                    // builder.disable(); // 禁止所有模板
                    // builder.disable(TemplateType.ENTITY); // 禁止生成ENTITY
                    // builder.disable(TemplateType.MAPPER); // 禁止生成MAPPER
                    builder.disable(TemplateType.SERVICE); // 禁止生成SERVICE
                    builder.disable(TemplateType.SERVICEIMPL); // 禁止生成SERVICEIMPL
                    // builder.disable(TemplateType.XML); // 禁止生成XML
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
