package com.izumi.dto;

import com.izumi.base.PageParam;
import com.izumi.entity.Role;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
public class RolePageParam extends PageParam<Role> {
    private String m_EQ_code;
    private String m_EQ_name;
    private String m_LIKE_name;
    private List<Long> m_BT_id;
    private List<Integer> m_IN_roleType;
}
