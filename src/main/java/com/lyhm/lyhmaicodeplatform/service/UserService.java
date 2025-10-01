package com.lyhm.lyhmaicodeplatform.service;

import com.lyhm.lyhmaicodeplatform.model.dto.UserQueryRequest;
import com.lyhm.lyhmaicodeplatform.model.vo.LoginUserVO;
import com.lyhm.lyhmaicodeplatform.model.vo.UserVO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.lyhm.lyhmaicodeplatform.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 *  服务层。
 *
 * @author <a href="https://github.com/MRX1205">掠影航猫</a>
 */
public interface UserService extends IService<User> {

    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     * 加密
     *
     * @param userPassword 用户密码
     * @return 加密后的用户密码
     */
    String getEncryptPassword(String userPassword);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     *
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏后的用户信息
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息 （分页）
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 获取查询对象
     */
    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);




}
