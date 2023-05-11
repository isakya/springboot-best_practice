package com.izumi.modules.sys.service.impl;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.PostPageParam;
import com.izumi.modules.sys.dto.PostParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.izumi.modules.sys.entity.Post;
import com.izumi.modules.sys.mapper.PostMapper;
import com.izumi.modules.sys.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Override
    @Transactional(rollbackFor = Exception.class) // 表示所有的异常都要回滚
    public boolean save(PostParam param) {
        Post Post = new Post();
        BeanUtils.copyProperties(param, Post);
        return super.save(Post);
    }

    @Override
    public boolean update(PostParam param) {
        Post Post = new Post();
        BeanUtils.copyProperties(param, Post);
        return super.updateById(Post);
    }

    @Override
    public CommonPage<Post> page(PostPageParam param) {
        IPage<Post> page = param.buildMpPage();
        QueryWrapper<Post> queryWrapper = param.buildQueryWrapper();
        List<Post> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
}
