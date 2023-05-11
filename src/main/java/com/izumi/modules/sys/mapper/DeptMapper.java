package com.izumi.modules.sys.mapper;

import com.izumi.modules.sys.entity.Dept;
import com.izumi.modules.sys.vo.DeptVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    List<DeptVO> selectCustom(IPage<DeptVO>page, @Param(Constants.WRAPPER) Wrapper<Dept>wrapper);
}
