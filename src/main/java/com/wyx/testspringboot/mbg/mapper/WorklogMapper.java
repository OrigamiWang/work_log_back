package com.wyx.testspringboot.mbg.mapper;

import com.wyx.testspringboot.mbg.model.Worklog;
import com.wyx.testspringboot.mbg.model.WorklogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorklogMapper {
    long countByExample(WorklogExample example);

    int deleteByExample(WorklogExample example);

    int deleteByPrimaryKey(Integer no);

    int insert(Worklog record);

    int insertSelective(Worklog record);

    List<Worklog> selectByExample(WorklogExample example);

    Worklog selectByPrimaryKey(Integer no);

    int updateByExampleSelective(@Param("record") Worklog record, @Param("example") WorklogExample example);

    int updateByExample(@Param("record") Worklog record, @Param("example") WorklogExample example);

    int updateByPrimaryKeySelective(Worklog record);

    int updateByPrimaryKey(Worklog record);
}