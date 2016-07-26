package inspur.crawl.requirementCode.mapper;

import inspur.crawl.requirementCode.pojo.RCode;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RCodeMapper {

	public List<RCode> listPage(RCode query);

	public int saveInfo(RCode query);

	public int updateInfo(RCode query);

	public int deleteByPrimaryKey(@Param("id") Integer id);

	// ----
	public List<RCode> listPageType(RCode query);

	public int saveInfoType(RCode query);

	public int updateInfoType(RCode query);

	public int deleteByPrimaryKeyType(@Param("id") Integer id);

	// ----
	public List<RCode> listPageStatus(RCode query);

	public int saveInfoStatus(RCode query);

	public int updateInfoStatus(RCode query);

	public int deleteByPrimaryKeyStatus(@Param("id") Integer id);

	// ----
	public List<RCode> listPageFrequency(RCode query);

	public int saveInfoFrequency(RCode query);

	public int updateInfoFrequency(RCode query);

	public int deleteByPrimaryKeyFrequency(@Param("id") Integer id);

	// ----
	public List<RCode> listPageGroup(RCode query);

	public int saveInfoGroup(RCode query);

	public int updateInfoGroup(RCode query);

	public int deleteByPrimaryKeyGroup(@Param("id") Integer id);

}