package inspur.crawl.ruleManage.pojo;

import java.util.List;

public class ParseRuleMidMapList {
	private List<ParseRuleMidMap> midMaps;

	public List<ParseRuleMidMap> getMidMaps() {
		return midMaps;
	}

	public void setMidMaps(List<ParseRuleMidMap> midMaps) {
		this.midMaps = midMaps;
	}

	public ParseRuleMidMapList(List<ParseRuleMidMap> midMaps) {
		super();
		this.midMaps = midMaps;
	}

	public ParseRuleMidMapList() {
		super();
	}

}
