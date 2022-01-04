package model;

import java.io.Serializable;

public class UserInfo  implements Serializable {

    private String name;

    private String age;

    private Integer id;

    private  String accountId;

    public  UserInfo(String name,String age,String accountId,Integer id){
        super();
        this.accountId=accountId;
        this.age=age;
        this.id=id;
        this.name=name;
    }

    public  UserInfo(){
    }

}
