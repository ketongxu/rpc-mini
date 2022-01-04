import api.UserInfoService;
import model.UserInfo;
import proxy.ProxyService;

public class CustomerMain {

    public static void main(String[] args) {
        ProxyService proxyService = new ProxyService( 8888,"localhost");
        UserInfoService userService = (UserInfoService) proxyService.proxy(UserInfoService.class);
        UserInfo userInfo = new UserInfo();
        UserInfo user = userService.queryUserInfo(userInfo);
        System.out.println(user);
    }
}
