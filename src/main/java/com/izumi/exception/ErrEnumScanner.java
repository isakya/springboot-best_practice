package com.izumi.exception;

import cn.hutool.core.lang.Dict;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 扫描自定义错误码枚举处理类
 * @author mldong
 *
 */
@Component
public class ErrEnumScanner implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;
    private List<Dict> datas = new ArrayList();
    private ResourcePatternResolver resolver = ResourcePatternUtils
            .getResourcePatternResolver(resourceLoader);
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
            resourceLoader);
    private static final String FULLTEXT_SACN_PACKAGE_PATH = "com.izumi";
    @PostConstruct
    private void init() {
        doScan();
    }
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    private void doScan() {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                .concat(ClassUtils
                        .convertClassNameToResourcePath(
                                SystemPropertyUtils
                                        .resolvePlaceholders(FULLTEXT_SACN_PACKAGE_PATH))
                        .concat("/**/*.class"));
        try {
            Resource[] resources = resolver.getResources(packageSearchPath);
            MetadataReader metadataReader = null;
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    metadataReader = metadataReaderFactory
                            .getMetadataReader(resource);
                    if (metadataReader.getClassMetadata().isFinal()) {// 当类型不是抽象类或接口在添加到集合
                        Class<?> clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                        ErrEnum enumCode = clazz.getAnnotation(ErrEnum.class);
                        if(enumCode!=null) {
                            String name = enumCode.name();
                            String value = enumCode.value();
                            int bizCode = enumCode.bizCode();
                            int max = enumCode.max();
                            int min = enumCode.min();
                            Dict dict = Dict.create();
                            dict.put("name", name);
                            dict.put("value", value);
                            dict.put("bizCode", bizCode);
                            dict.put("max", max);
                            dict.put("min", min);
                            dict.put("items", Arrays.stream(clazz.getEnumConstants()).filter(item->{
                                return item instanceof CommonError;
                            }).map(item->{
                                CommonError itemEnum = (CommonError)item;
                                Dict itemDict = Dict.create();
                                itemDict.put("name",itemEnum.toString());
                                itemDict.put("code",itemEnum.getCode());
                                itemDict.put("message",itemEnum.getMessage());
                                return itemDict;
                            }).collect(Collectors.toList()));
                            datas.add(dict);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("错误码扫描失败");
        }
    }

    public List<Dict> getDatas() {
        return datas;
    }
}
