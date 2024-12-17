package com.prime.app.module.auth.service.acl;

import com.prime.app.module.auth.entity.AccessRights;
import com.prime.app.module.common.enums.TenantType;

import java.util.List;

public interface AccessRightsService {

//	void checkAccessControlListMasterData(List<AccessRights> list);

//	AccessRights save(AccessRights accessRights);

//	List<AccessRights> getAccessControlListForOwner();

//	List<AccessRights> getAccessControlListForClient();

//	List<AccessRightsTreeViewDto> getAccessControlList(TenantType tenantType) throws ApplicationException;

	List<AccessRights> getAllAccessControlList(TenantType tenantType);

//	Set<AccessRightsTreeViewDto> getSelectedAclList(String userRole, TenantType tenantType) throws ApplicationException;

//	List<AccessRights> findByParentIdOrderByAclOrder(String string);

}
