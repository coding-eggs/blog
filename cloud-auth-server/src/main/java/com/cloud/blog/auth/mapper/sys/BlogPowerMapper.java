package com.cloud.blog.auth.mapper.sys;

import com.cloud.blog.model.po.sys.BlogPower;
import com.cloud.blog.model.po.sys.dto.BlogDecisionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogPowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogPower record);

    int insertSelective(BlogPower record);

    BlogPower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogPower record);

    int updateByPrimaryKey(BlogPower record);

    List<BlogDecisionDTO> selectOperationPowerList();

    List<BlogDecisionDTO> selectPagePowerList();

    List<BlogDecisionDTO> selectFilePowerList();

    List<BlogDecisionDTO> selectMenuPowerList();
}
