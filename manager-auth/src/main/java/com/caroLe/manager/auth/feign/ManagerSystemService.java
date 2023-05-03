package com.caroLe.manager.auth.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caroLe.manager.common.result.Result;
import com.caroLe.manager.repository.dto.system.SysMenuSecurityDTO;

/**
 * @author CaroLe
 * @Date 2023/4/21 15:05
 * @Description
 */
@FeignClient("manager-system")
public interface ManagerSystemService {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    @GetMapping("/system/sysUser/loadByUsername")
    Result<SysMenuSecurityDTO> loadByUsername(@RequestParam("name") String username);

}
