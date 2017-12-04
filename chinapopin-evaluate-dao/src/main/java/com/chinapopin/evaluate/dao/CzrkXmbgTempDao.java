package com.chinapopin.evaluate.dao;

import com.chinapopin.evaluate.bean.CzrkXmbgTemp;
import com.chinapopin.framework.datasource.utils.MyMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CzrkXmbgTempDao extends MyMapper<CzrkXmbgTemp> {
}