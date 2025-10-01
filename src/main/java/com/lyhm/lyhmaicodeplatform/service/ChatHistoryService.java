package com.lyhm.lyhmaicodeplatform.service;

import com.lyhm.lyhmaicodeplatform.model.dto.chathistory.ChatHistoryQueryRequest;
import com.lyhm.lyhmaicodeplatform.model.entity.User;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.lyhm.lyhmaicodeplatform.model.entity.ChatHistory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;

import java.time.LocalDateTime;

/**
 *  服务层。
 *
 * @author <a href="https://github.com/MRX1205">掠影航猫</a>
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    boolean addChatMessage(Long appId,String message,String messageType,Long userId);

    boolean deleteByAppId(Long appId);

    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);

    //游标查询服务方法
    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                                User loginUser);


    int loadChatHistoryToMemory(Long appId, MessageWindowChatMemory chatMemory,int maxCount);
}
