package com.prime.app.module.auth.service.acl;

import com.prime.app.module.auth.entity.AccessRights;
import com.prime.app.module.auth.repository.AccessRightsRepository;
import com.prime.app.module.auth.service.userrole.UserRoleService;
import com.prime.app.module.common.enums.TenantType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccessRightsServiceImpl implements AccessRightsService {

    private final AccessRightsRepository accessRightsRepository;

    private final UserRoleService userRoleService;

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void checkAccessControlListMasterData(List<AccessRights> list) {
//        list.forEach(acl -> {
//            if (StringUtils.isNotEmpty(acl.getAclValue())) {
//                Optional<AccessRights> check = accessRightsRepository.findByAclValue(acl.getAclValue());
//                if (check.isEmpty()) {
//                    if (acl.getParent() != null) {
//                        Optional<AccessRights> parent = accessRightsRepository.findByAclValue(acl.getParent().getAclValue());
//                        parent.ifPresent(acl::setParent);
//                    }
//                    save(acl);
//                } else {
//                    check.get().setAclName(acl.getAclName());
//                    accessRightsRepository.save(check.get());
//                }
//            }
//        });
//    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public AccessRights save(AccessRights accessRights) {
//        return accessRightsRepository.save(accessRights);
//    }

//    @Override
//    public List<AccessRights> getAccessControlListForOwner() {
//        List<AccessRights> list = accessRightsRepository.findByOwnerAndParentIsNullOrderByAclOrder(true);
//        return buildList(list);
//    }

//    private List<AccessRights> buildList(List<AccessRights> list) {
//        list.forEach(item -> {
//            if (item.getChildren() == null) {
//                item.setChildren(accessRightsRepository.findByParentIdOrderByAclOrder(item.getAclValue()));
//                item.getChildren().forEach(child -> child.setChildren(accessRightsRepository.findByParentIdOrderByAclOrder(child.getAclValue())));
//            }
//        });
//        return list;
//    }

//    @Override
//    public List<AccessRights> getAccessControlListForClient() {
//        return accessRightsRepository.findByClientAndParentIsNullOrderByAclOrder(true);
//    }

//    @Override
//    public List<AccessRightsTreeViewDto> getAccessControlList(TenantType tenantType) throws ApplicationException {
//        return getAccessRights(tenantType).stream().map(this::toDto).toList();
//    }

//    private List<AccessRights> getAccessRights(TenantType tenantType) throws ApplicationException {
//        return switch (tenantType) {
//            case SUPER_ADMIN -> getAccessControlListForOwner();
////            case CLIENT -> getAccessControlListForClient();
//            default -> throw new ApplicationException(MessageUtils.get("invalid.tenant.type"));
//        };
//    }

    @Override
    public List<AccessRights> getAllAccessControlList(TenantType tenantType) {
        return switch (tenantType) {
            case SUPER_ADMIN -> accessRightsRepository.findByOwner(true);
            case ADMIN -> accessRightsRepository.findByOwner(true);
            default -> new ArrayList<>();
        };
    }

//    private AccessRightsTreeViewDto toDto(AccessRights destination) {
//        if (destination == null) {
//            return null;
//        }
//
//        AccessRightsTreeViewDto dto = new AccessRightsTreeViewDto();
//        dto.setText(destination.getAclName());
//        dto.setValue(destination.getAclValue());
//        dto.setChecked(destination.isChecked());
//
//        List<AccessRightsTreeViewDto> childrenDtos = destination.getChildren().stream().map(this::toDto).filter(Objects::nonNull).toList();
//
//        if (!childrenDtos.isEmpty()) {
//            dto.setChildren(childrenDtos);
//        }
//
//        return dto;
//    }


//    @Override
//    public Set<AccessRightsTreeViewDto> getSelectedAclList(String userRole, TenantType tenantType) throws ApplicationException {
//        List<AccessRights> acls = getAccessRights(tenantType);
//        List<String> assignedAcls = userRoleService.getAcls(userRole).stream().map(AccessRights::getAclValue).toList();
//
//        List<AccessRights> finalAclList = markAclListAsSelected(acls, assignedAcls);
//
//        return finalAclList.stream().map(this::toDto).collect(Collectors.toCollection(LinkedHashSet::new));
//    }

//    private List<AccessRights> markAclListAsSelected(List<AccessRights> fullList, List<String> assignedAcls) {
//        if (CollectionUtil.isNotEmpty(fullList) && CollectionUtil.isNotEmpty(assignedAcls)) {
//            for (AccessRights aclObj : fullList) {
//                if (assignedAcls.contains(aclObj.getAclValue())) {
//                    aclObj.setChecked(Boolean.TRUE);
//                }
//                if (CollectionUtil.isNotEmpty(aclObj.getChildren())) {
//                    markAclListAsSelected(aclObj.getChildren(), assignedAcls);
//                }
//            }
//        }
//        return fullList;
//    }

//    @Override
//    public List<AccessRights> findByParentIdOrderByAclOrder(String acl) {
//
//        List<AccessRights> returnList = new ArrayList<>();
//        List<AccessRights> list = accessRightsRepository.findByParentId(acl);
//        for (AccessRights accessRights : list) {
//            returnList.add(accessRights);
//            for (AccessRights accessChildRights : accessRights.getChildren()) {
//                returnList.add(accessChildRights);
//                returnList.addAll(accessChildRights.getChildren());
//            }
//        }
//
//        return returnList;
//
//    }

}
