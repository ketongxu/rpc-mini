package service;

import api.UserInfoService;
import model.UserInfo;

public class UserInfoServiceImpl implements UserInfoService {

    public UserInfo queryUserInfo(UserInfo userInfo) {
        return new UserInfo("法外狂徒张三","18","11122",12);
    }
}
