package inspur.crawl.sysManage.mapper;

import inspur.crawl.sysManage.pojo.Account;
import inspur.crawl.sysManage.pojo.AccountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    int countByExample(AccountCriteria example);

    int deleteByExample(AccountCriteria example);

    int insert(Account record);

    int insertSelective(Account record);
    
    Account selectByAccount(@Param("id") String id);
    
    Account selectByUsername(@Param("username") String username);
    List<Account> listPage(Account record);
    
    List<Account> selectUser(AccountCriteria example);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountCriteria example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountCriteria example);
}