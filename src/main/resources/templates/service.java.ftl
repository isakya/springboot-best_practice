package ${package.Service};
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.RolePageParam;
import com.izumi.modules.sys.dto.RoleParam;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
     /**
     * 添加${table.comment!}
     * @param param
     * @return
     */
     boolean save(${entity}Param param);

     /***
     * 更新${table.comment!}
     * @param param
     * @return
     */
     boolean update(${entity}Param param);
     /**
     * 自定义分页查询${table.comment!}
     * @param param
     * @return
     */
     CommonPage<${entity}> page(${entity}PageParam param);
}
</#if>