package com.caroLe.manager.repository.dao.hxds.dr;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caroLe.manager.repository.po.hxds.dr.TbDriver;

/**
 * @author CaroLe
 * @Date 2023/7/9 19:01
 * @Description
 */
@Mapper
public interface TbDriverDao extends BaseMapper<TbDriver> {
    /**
     * 是否存在司机账户
     * 
     * @param param 参数
     * @return 账户数
     */
    Long hasDriver(@Param("param") Map<Object, Object> param);

    /**
     * 注册新司机用户
     * 
     * @param openId openId
     * @param nickname 昵称
     * @param photo 照片路径
     */
    void registerNewDriver(@Param("openId") String openId, @Param("nickname") String nickname,
        @Param("photo") String photo);

    /**
     * 更新archive
     * 
     * @param driverId 司机Id
     * @return 条数
     */
    Integer updateDriverArchive(Long driverId);
}
