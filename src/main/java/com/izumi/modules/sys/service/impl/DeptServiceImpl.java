package com.izumi.modules.sys.service.impl;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.DeptPageParam;
import com.izumi.modules.sys.dto.DeptParam;
import com.izumi.modules.sys.vo.DeptVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.izumi.modules.sys.entity.Dept;
import com.izumi.modules.sys.mapper.DeptMapper;
import com.izumi.modules.sys.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Override
    @Transactional(rollbackFor = Exception.class) // 表示所有的异常都要回滚
    public boolean save(DeptParam param) {
        Dept Dept = new Dept();
        BeanUtils.copyProperties(param, Dept);
        return super.save(Dept);
    }

    @Override
    public boolean update(DeptParam param) {
        Dept Dept = new Dept();
        BeanUtils.copyProperties(param, Dept);
        return super.updateById(Dept);
    }

    @Override
    public CommonPage<DeptVO> page(DeptPageParam param) {
        IPage<DeptVO> page = param.buildMpPage();
        QueryWrapper<Dept> queryWrapper = param.buildQueryWrapper();
        List<DeptVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
}
