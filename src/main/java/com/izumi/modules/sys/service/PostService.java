package com.izumi.modules.sys.service;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.PostPageParam;
import com.izumi.modules.sys.dto.PostParam;
import com.izumi.modules.sys.vo.PostVO;
import com.izumi.modules.sys.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位 服务类
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
public interface PostService extends IService<Post> {
     /**
     * 添加岗位
     * @param param
     * @return
     */
     boolean save(PostParam param);

     /***
     * 更新岗位
     * @param param
     * @return
     */
     boolean update(PostParam param);
     /**
     * 自定义分页查询岗位
     * @param param
     * @return
     */
     CommonPage<PostVO> page(PostPageParam param);
}
