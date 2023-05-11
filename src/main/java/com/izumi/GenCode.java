package com.izumi;
import cn.hutool.core.convert.Convert;
import cn.hutool.setting.Setting;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.izumi.gen.EnhanceFreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GenCode {
    public static void main(String[] args) {
        // System.err.println(System.getProperty("user.dir")); // 项目根目录
        String projectRootDir = System.getProperty("user.dir");
        Setting setting = new Setting("gencode.setting");
        String dbUrl = setting.get("db.url");
        String dbUsername = setting.get("db.username");
        String dbPassword = setting.get("db.password");
        FastAutoGenerator.create(dbUrl, dbUsername, dbPassword)
                .globalConfig(builder -> {
                    builder.author(setting.get("author")) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件 3.5.2时已弃用
                            .disableOpenDir() // 禁止打开目录
                            .dateType(DateType.ONLY_DATE)
                            .outputDir(projectRootDir + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(setting.get("packageParent")) // 设置父包名
                            .xml("mapper.mapping")
                            .other("dto") // 设置dto包名
                            .moduleName(setting.get("moduleName")); // 设置父包模块名
                            // .pathInfo(Collections.singletonMap(OutputFile.xml, "H://code")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    String tableName = setting.get("tableName");
                    if(tableName.endsWith("%")) {
                        builder.likeTable(new LikeTable(tableName)); // 模糊查询
                    } else {
                        Arrays.stream(tableName.split(",")).forEach(item->{
                            builder.addInclude(item); // 设置需要生成的表名
                        });
                    }
                    builder.addTablePrefix(setting.get("tablePrefix")); // 设置过滤表前缀
                    // 实体类生成策略
                    Entity.Builder entityBuilder = builder.entityBuilder()
                            .enableLombok() // 启用lombok
                            .idType(IdType.ASSIGN_ID); // 配置主键策略
                            // .fileOverride(); // 实体类模板可覆盖
                    if (Convert.toBool(setting.get("entityFileOverride"), true)) {
                        entityBuilder.fileOverride();
                    }
                    // mapper生成策略配置
                    Mapper.Builder mapperBuilder = builder.mapperBuilder()
                            .enableMapperAnnotation(); // 启用@Mapper注解
                            // .enableBaseResultMap()
                            // .enableBaseColumnList();
                            // .fileOverride(); //可覆盖
                    if (Convert.toBool(setting.get("mapperFileOverride"), false)) {
                        mapperBuilder.fileOverride();
                    }
                    // service生成策略配置
                    Service.Builder serviceBuilder = builder.serviceBuilder()
                            .formatServiceFileName("%sService"); // 文件命名规则
                            // .fileOverride(); // 可以覆盖
                    if (Convert.toBool(setting.get("serviceFileOverride"), false)) {
                        serviceBuilder.fileOverride();
                    }
                    // controller生成策略
                    Controller.Builder controllerBuilder = builder.controllerBuilder();
                            // .fileOverride(); // 可以覆盖
                    if (Convert.toBool(setting.get("controllerFileOverride"), false)) {
                        controllerBuilder.fileOverride();
                    }
                })
                .templateConfig(builder -> {
                    // builder.disable(); // 禁止所有模板
                    // builder.disable(TemplateType.ENTITY); // 禁止生成ENTITY
                    // builder.disable(TemplateType.MAPPER); // 禁止生成MAPPER
                    // builder.disable(TemplateType.SERVICE); // 禁止生成SERVICE
                    // builder.disable(TemplateType.SERVICEIMPL); // 禁止生成SERVICEIMPL
                    // builder.disable(TemplateType.XML); // 禁止生成XML
                    // builder.disable(TemplateType.CONTROLLER);
                })
                .templateEngine(new EnhanceFreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    // DTO
                    customFile.put("Param.java", "/templates/param.java.ftl");
                    customFile.put("PageParam.java", "/templates/pageParam.java.ftl");
                    customFile.put("VO.java", "/templates/vo.java.ftl");
                    // consumer.fileOverride(); // 可以覆盖
                    Map<String,Object> customMap = new HashMap<>();
                    customMap.put("g",setting);
                    customMap.put("Param.javaFileOverride",setting.get("paramFileOverride"));
                    customMap.put("PageParam.javaFileOverride",setting.get("pageParamFileOverride"));
                    customMap.put("VO.javaFileOverride",setting.get("voFileOverride"));
                    consumer.customMap(customMap);
                    consumer.customFile(customFile);
                })
                .execute();
    }
}
