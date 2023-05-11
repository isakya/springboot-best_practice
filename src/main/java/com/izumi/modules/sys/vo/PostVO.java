package com.izumi.modules.sys.vo;

import com.izumi.modules.sys.entity.Post;
import io.swagger.annotations.ApiModel;
import lombok.Data;
/**
* <p>
* 岗位
* </p>
*
* @author izumi
* @since 2023-05-12
*/
@Data
@ApiModel(value = "PostVO对象", description = "岗位VO")
public class PostVO extends Post {
}
