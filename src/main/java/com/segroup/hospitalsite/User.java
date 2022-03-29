package com.segroup.hospitalsite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: User类
 * @author zheng
 * @date 2022-03-29
 */

@ApiModel(value = "用户实体类", description = "用户信息描述类")
public class User {
    @ApiModelProperty(value = "用户id")
    private long id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;

    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }


}
