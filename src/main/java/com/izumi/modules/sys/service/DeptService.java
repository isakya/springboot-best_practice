package com.izumi.modules.sys.service;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.DeptPageParam;
import com.izumi.modules.sys.dto.DeptParam;
import com.izumi.modules.sys.vo.DeptVO;
import com.izumi.modules.sys.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
public interface DeptService extends IService<Dept> {
     /**
     * 添加部门
     * @param param
     * @return
     */
     boolean save(DeptParam param);

     /***
     * 更新部门
     * @param param
     * @return
     */
     boolean update(DeptParam param);
     /**
     * 自定义分页查询部门
     * @param param
     * @return
     */
     CommonPage<DeptVO> page(DeptPageParam param);
}
