package inspur.crawl.requirementCode.service;

import inspur.crawl.requirementCode.mapper.RCodeMapper;
import inspur.crawl.requirementCode.pojo.RCode;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class RCodeService {

	@Resource
	RCodeMapper rCodeMapper;

	public List<RCode> getResourceAll(RCode query) {
		List<RCode> list = rCodeMapper.listPage(query);
		return list;
	}

	public int saveInfo(RCode query) {
		return rCodeMapper.saveInfo(query);
	}

	public int updateInfo(RCode query) {
		return rCodeMapper.updateInfo(query);
	}

	public int deleteByPrimaryKey(Integer id) {
		return rCodeMapper.deleteByPrimaryKey(id);
	};

	// ======
	public List<RCode> getResourceAllType(RCode query) {
		List<RCode> list = rCodeMapper.listPageType(query);
		return list;
	}

	public int saveInfoType(RCode query) {
		return rCodeMapper.saveInfoType(query);
	}

	public int updateInfoType(RCode query) {
		return rCodeMapper.updateInfoType(query);
	}

	public int deleteByPrimaryKeyType(Integer id) {
		return rCodeMapper.deleteByPrimaryKeyType(id);
	};

	// ======
	public List<RCode> getResourceAllStatus(RCode query) {
		List<RCode> list = rCodeMapper.listPageStatus(query);
		return list;
	}

	public int saveInfoStatus(RCode query) {
		return rCodeMapper.saveInfoStatus(query);
	}

	public int updateInfoStatus(RCode query) {
		return rCodeMapper.updateInfoStatus(query);
	}

	public int deleteByPrimaryKeyStatus(Integer id) {
		return rCodeMapper.deleteByPrimaryKeyStatus(id);
	};

	// ======
	public List<RCode> getResourceAllFrequency(RCode query) {
		List<RCode> list = rCodeMapper.listPageFrequency(query);
		return list;
	}

	public int saveInfoFrequency(RCode query) {
		return rCodeMapper.saveInfoFrequency(query);
	}

	public int updateInfoFrequency(RCode query) {
		return rCodeMapper.updateInfoFrequency(query);
	}

	public int deleteByPrimaryKeyFrequency(Integer id) {
		return rCodeMapper.deleteByPrimaryKeyFrequency(id);
	};

	// ======
	public List<RCode> getResourceAllGroup(RCode query) {
		List<RCode> list = rCodeMapper.listPageGroup(query);
		return list;
	}

	public int saveInfoGroup(RCode query) {
		return rCodeMapper.saveInfoGroup(query);
	}

	public int updateInfoGroup(RCode query) {
		return rCodeMapper.updateInfoGroup(query);
	}

	public int deleteByPrimaryKeyGroup(Integer id) {
		return rCodeMapper.deleteByPrimaryKeyGroup(id);
	};
}
