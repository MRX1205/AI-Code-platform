package com.lyhm.lyhmaicodeplatform.service;

import com.lyhm.lyhmaicodeplatform.model.dto.app.AppQueryRequest;
import com.lyhm.lyhmaicodeplatform.model.entity.User;
import com.lyhm.lyhmaicodeplatform.model.vo.AppVO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.lyhm.lyhmaicodeplatform.model.entity.App;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.util.List;

/**
 *  服务层。
 *
 * @author <a href="https://github.com/MRX1205">掠影航猫</a>
 */
public interface AppService extends IService<App> {
    /**
     * 获取APP包装类
     * @param app
     * @return
     */
    AppVO getAppVO(App app);

    /**
     * 添加构造对象
     * @param appQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    /**
     * 获取用户信息
     * @param appList
     * @return
     */
    List<AppVO> getAppVOList(List<App> appList);
    Flux<String> chatToGenCode(Long appId, String message, User loginUser);
    /**
     *
     * 应用部署服务
     */
    String deployApp(Long appId,User loginUser);


}
