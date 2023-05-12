package com.izumi.modules.sys.service.impl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.MenuPageParam;
import com.izumi.modules.sys.dto.MenuParam;
import com.izumi.modules.sys.dto.SyncRouteParam;
import com.izumi.modules.sys.vo.MenuVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.izumi.modules.sys.entity.Menu;
import com.izumi.modules.sys.mapper.MenuMapper;
import com.izumi.modules.sys.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    @Transactional(rollbackFor = Exception.class) // 表示所有的异常都要回滚
    public boolean save(MenuParam param) {
        Menu Menu = new Menu();
        BeanUtils.copyProperties(param, Menu);
        return super.save(Menu);
    }

    @Override
    public boolean update(MenuParam param) {
        Menu Menu = new Menu();
        BeanUtils.copyProperties(param, Menu);
        return super.updateById(Menu);
    }

    @Override
    public CommonPage<MenuVO> page(MenuPageParam param) {
        IPage<MenuVO> page = param.buildMpPage();
        QueryWrapper<Menu> queryWrapper = param.buildQueryWrapper();
        List<MenuVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }

    @Override
    public void syncRoute(String appCode, List<SyncRouteParam> paramList) {
        System.out.println(paramList);
        // 同步规则：
        // 1. 默认只同步四级数据（可用递归，也可使用4层遍历）
        // 2. 以appCode,code为唯一标识进行同步
        // 3. 只同步isSync=true或isSync=1的数据
        // 4. 同步时，有变化时，要更新，也可全字段更新
        // 5. ext扩展信息存放到variable
        List<String> pidList = CollectionUtil.newArrayList("0");
        saveMenu(appCode,0L, pidList, paramList,2);
    }
    /**
     * 根据应用编码和菜单编码查询
     * @param appCode
     * @param code
     * @return
     */
    private Menu findOneByAppCodeAndCode(String appCode,String code) {
        LambdaQueryWrapper<Menu> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Menu::getAppCode,appCode).eq(Menu::getCode,code);
        return baseMapper.selectOne(lambdaQueryWrapper);
    }

    /**
     * 保存菜单
     * @param appCode
     * @param parentId
     * @param pidList
     * @param list
     * @param maxLevel
     */
    private void saveMenu(String appCode, Long parentId,List<String> pidList,List<SyncRouteParam> list,int maxLevel) {
        if(CollectionUtil.isEmpty(list) || maxLevel>4) return;
        list.stream().filter(item->{
            return Boolean.TRUE.equals(item.getIsSync());
        }).forEach(item->{
            item.setAppCode(appCode);
            // 遍历第一级
            Menu menu = findOneByAppCodeAndCode(item.getAppCode(),item.getCode());
            if(menu==null) {
                // 新增
                menu = new Menu();
                menu.setParentId(parentId);
                BeanUtil.copyProperties(item,menu,"parentId");
                menu.setVariable(JSONUtil.toJsonStr(item.getExt()));
                menu.setPids(StrUtil.join(",", pidList));
                baseMapper.insert(menu);
            } else {
                // 更新
                BeanUtil.copyProperties(item,menu,"id","parentId");
                menu.setVariable(JSONUtil.toJsonStr(item.getExt()));
                menu.setPids(StrUtil.join(",", pidList));
                baseMapper.updateById(menu);
            }
            List<String> newPidList = new ArrayList<>();
            newPidList.addAll(pidList);
            newPidList.add(menu.getId().toString());
            saveMenu(appCode,menu.getId(),newPidList,item.getChildren(),maxLevel+1);
        });
    }
}
