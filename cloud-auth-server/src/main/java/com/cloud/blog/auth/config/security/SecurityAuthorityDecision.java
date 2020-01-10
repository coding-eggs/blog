package com.cloud.blog.auth.config.security;

import com.cloud.blog.auth.mapper.sys.BlogPowerMapper;
import com.cloud.blog.common.config.exception.BlogException;
import com.cloud.blog.model.enums.EnumReturnCode;
import com.cloud.blog.model.po.sys.dto.BlogDecisionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * @program: blog
 * @description:
 * @author: Ailuoli
 * @create: 2019-05-22 01:43
 **/

@Component
public class SecurityAuthorityDecision {

    public static Map<String, Collection<ConfigAttribute>> operationPowerMap ;

    public static Map<String, Collection<ConfigAttribute>> pagePowerMap;

    public static Map<String, Collection<ConfigAttribute>> filePowerMap;

    public static Map<String, Collection<ConfigAttribute>> menuPowerMap;


    @Autowired
    private BlogPowerMapper blogPowerMapper;

    /**
     * @Author myk
     * @Description //判断请求者是否有访问该资源的权限
     * @Date 15:53 2019/12/30
     * @Param [request, authentication]
     * @return boolean
     **/
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Collection<ConfigAttribute> collection = getAttribute(request);
        if (authentication.getPrincipal().equals("anonymousUser") || CollectionUtils.isEmpty(collection)) {
            return false;
        }
        for (ConfigAttribute attribute : collection) {
            String needRole = attribute.getAttribute();
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (needRole.trim().equals(grantedAuthority.getAuthority())) {
                    return true;
                }
            }
        }
        throw new BlogException(EnumReturnCode.POWER_ACCESS_UNDEFINE);
    }

    public Collection<ConfigAttribute> getAttribute(HttpServletRequest request) {

        Collection<ConfigAttribute> roles = new ArrayList<>();
        if (CollectionUtils.isEmpty(operationPowerMap)) {
            loadOperationResourceDefine();
        }

        if (CollectionUtils.isEmpty(pagePowerMap)) {
            loadPageResourceDefine();
        }
        if (CollectionUtils.isEmpty(filePowerMap)) {
            loadFileResourceDefine();
        }
        if (CollectionUtils.isEmpty(menuPowerMap)) {
            loadMenuResourceDefine();
        }

        for (Map.Entry<String, Collection<ConfigAttribute>> entry : operationPowerMap.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                roles.addAll(operationPowerMap.get(url));
            }
        }
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : pagePowerMap.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                roles.addAll(pagePowerMap.get(url));
            }
        }
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : filePowerMap.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                roles.addAll(filePowerMap.get(url));
            }
        }
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : menuPowerMap.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                roles.addAll(menuPowerMap.get(url));
            }
        }

        return roles;
    }

    /**
     * 初始化所有资源对应的角色
     */

    private void loadPageResourceDefine(){
        pagePowerMap = new HashMap<>();
        List<BlogDecisionDTO> pagePowerList = blogPowerMapper.selectPagePowerList();
        //初始化页面元素
        pagePowerList.forEach(e->{
            String url = e.getPageElementName();
            String roleName = e.getRoleName();
            ConfigAttribute configAttribute = new SecurityConfig(roleName);
            //资源做key，角色做value
            if(pagePowerMap.containsKey(url)){
                pagePowerMap.get(url).add(configAttribute);
            }else {
                Collection<ConfigAttribute> list = new ArrayList<>();
                list.add(configAttribute);
                pagePowerMap.put(url,list);
            }
        });
    }
    private void loadFileResourceDefine(){
        filePowerMap = new HashMap<>();
        List<BlogDecisionDTO> filePowerList = blogPowerMapper.selectFilePowerList();

        //初始化文件权限
        filePowerList.forEach(e->{
            String url = e.getFileUrl();
            String roleName = e.getRoleName();
            ConfigAttribute configAttribute = new SecurityConfig(roleName);
            //资源做key，角色做value
            if(filePowerMap.containsKey(url)){
                filePowerMap.get(url).add(configAttribute);
            }else {
                Collection<ConfigAttribute> list = new ArrayList<>();
                list.add(configAttribute);
                filePowerMap.put(url,list);
            }
        });

    }
    private void loadMenuResourceDefine(){
        menuPowerMap = new HashMap<>();
        List<BlogDecisionDTO> menuPowerList = blogPowerMapper.selectMenuPowerList();
        //初始化菜单权限
        menuPowerList.forEach(e->{
            String url = e.getMenuUrl();
            String roleName = e.getRoleName();
            ConfigAttribute configAttribute = new SecurityConfig(roleName);
            //资源做key，角色做value
            if(menuPowerMap.containsKey(url)){
                menuPowerMap.get(url).add(configAttribute);
            }else {
                Collection<ConfigAttribute> list = new ArrayList<>();
                list.add(configAttribute);
                menuPowerMap.put(url,list);
            }
        });
    }

    private void loadOperationResourceDefine() {
        operationPowerMap = new HashMap<>();
        List<BlogDecisionDTO> operationPowerList = blogPowerMapper.selectOperationPowerList();
        //初始化操作权限
        operationPowerList.forEach(e->{
            String url = e.getPreResourceMapping();
            String roleName = e.getRoleName();
            ConfigAttribute configAttribute = new SecurityConfig(roleName);
            //资源做key，角色做value
            if(operationPowerMap.containsKey(url)){
                operationPowerMap.get(url).add(configAttribute);
            }else {
                Collection<ConfigAttribute> list = new ArrayList<>();
                list.add(configAttribute);
                operationPowerMap.put(url,list);
            }
        });
    }


}

