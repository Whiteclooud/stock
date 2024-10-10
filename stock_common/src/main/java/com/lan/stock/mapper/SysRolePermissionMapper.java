package com.lan.stock.mapper;

import com.lan.stock.pojo.entity.SysRolePermission;

/**
* @author lan
* @description 针对表【sys_role_permission(角色权限表)】的数据库操作Mapper
* @createDate 2024-10-09 16:50:30
* @Entity com.lan.stock.pojo.entity.SysRolePermission
*/
public interface SysRolePermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

}
