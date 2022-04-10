# swagger2写文档

## 什么是 swagger2

一个开源软件框架，将代码和文档融为一体，满足 Restful API，方便直接维护文档。

简单的说就是俩点：

- 文档自动生成，不用改接口后再改文档
- 支持在线测试获得内容

## Swagger2配置

poe.xml

```xml
<dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-ui</artifactId>
            <version>3.0.3</version>
        </dependency>
```

application.properties添加（不知道为啥）

```
spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
```

## 使用样例

### 在项目中常用的注解说明

| **注解**             | **属性**     |         **值**          | **备注**                                                     |
| -------------------- | ------------ | :---------------------: | ------------------------------------------------------------ |
| `@Api`               | value        |         字符串          | 可用在`class`头上,`class`描述                                |
|                      | description  |         字符串          |                                                              |
|                      |              |                         | `@Api`(value = "xxx", description = "xxx")                   |
| `@ApiOperation`      | value        |         字符串          | 可用在方法头上.参数的描述容器                                |
|                      | notes        |         字符串          |                                                              |
|                      |              |                         | `@ApiOperation`(value = "xxx", notes = "xxx")                |
| `@ApiImplicitParams` | {}           | `@ApiImplicitParam`数组 | 可用在方法头上.参数的描述容器                                |
|                      |              |                         | `@ApiImplicitParams`({`@ApiImplicitParam1`,`@ApiImplicitParam2`,...}) |
| `@ApiImplicitParam`  | name         |  字符串 与参数命名对应  | 可用在`@ApiImplicitParams`里                                 |
|                      | value        |         字符串          | 参数中文描述                                                 |
|                      | required     |         布尔值          | true/false                                                   |
|                      | dataType     |         字符串          | 参数类型                                                     |
|                      | paramType    |         字符串          | 参数请求方式:query/path                                      |
|                      |              |                         | query:对应`@RequestParam`?传递                               |
|                      |              |                         | path: 对应`@PathVariable`{}path传递                          |
|                      | defaultValue |         字符串          | 在api测试中默认值                                            |
|                      |              |                         | 用例参见项目中的设置                                         |
| `@ApiResponses`      | {}           |   `@ApiResponse`数组    | 可用在方法头上.参数的描述容器                                |
|                      |              |                         | `@ApiResponses`({`@ApiResponse1`,`@ApiResponse2`,...})       |
| `@ApiResponse`       | code         |          整形           | 可用在`@ApiResponses`里                                      |
|                      | message      |         字符串          | 错误描述                                                     |
|                      |              |                         | `@ApiResponse`(code = 200, message = "Successful")           |



https://www.jianshu.com/p/f30e0c646c63

```SHELL
@ApiIgnore 注解表示不对某个接口生成文档

@Api：用在请求的类上，表示对类的说明
    tags="说明该类的作用，可以在UI界面上看到的注解"
    value="该参数没什么意义，在UI界面上也看到，所以不需要配置"

@ApiOperation：用在请求的方法上，说明方法的用途、作用
    value="说明方法的用途、作用"
    notes="方法的备注说明"

@ApiImplicitParams：用在请求的方法上，表示一组参数说明。如果有多个参数，可以将多个参数的 @ApiImplicitParam 注解放到 @ApiImplicitParams 中。

    @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
        name：参数名
        value：参数的汉字说明、解释
        required：参数是否必须传
        paramType：方法参数的类型，即参数放在哪个地方，有以下参数可以选择：
            · header --> 请求参数的获取方式：@RequestHeader
            · query --> 请求参数的获取方式：@RequestParam
            · path（用于restful接口）--> 请求参数的获取方式：@PathVariable
            · body（不常用）
            · form（不常用）    
        dataType：参数类型，默认String，其它值dataType="Integer"       
        defaultValue：参数的默认值

@ApiResponses：用在请求的方法上，表示一组响应。如果有多个 @ApiResponse，则放在一个 @ApiResponses 中。
    @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
        code：响应码数字，例如400，计网懂得都懂
        message：信息，例如"请求参数没填好"
        response：抛出异常的类

@ApiModel：用于响应类上，表示一个返回响应数据的信息
            （这种一般用在post创建的时候，使用@RequestBody这样的场景，
            请求参数无法使用@ApiImplicitParam注解进行描述的时候）
    @ApiModelProperty：用在属性上，描述响应类的属性
```

### 对于Controller的例子

```java
@RestController
@Api(tags = "用户数据接口")
public class UserController {
    @ApiOperation(value ="查询用户", notes = "根据 id 查询用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户 id", required = true)
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Integer id){
        return "查找的用户id是：" + id;
    }
 
    @ApiOperation(value = "新增用户", notes = "根据传入的用户名和密码添加一个新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username",
                    value = "用户名", required = true, defaultValue = "test"),
            @ApiImplicitParam(paramType = "query", name = "password",
                    value = "密码", required = true, defaultValue = "123")
    })
    @PostMapping("/user")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        return "新增用户：" + username + " " + password;
    }
 
    @ApiOperation(value = "删除用户", notes = "根据 id 删除用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功！"),
            @ApiResponse(code = 500, message = "删除失败！")
    })
    @DeleteMapping("/user/{id}")
    public Integer deleteUserById(@PathVariable Integer id) {
        return id;
    }
 
    @ApiOperation(value = "修改用户", notes = "传入用户信息进行更新修改")
    @PutMapping("/user")
    public String updateUser(@RequestBody User user){
        return user.toString();
    }
 
    @ApiIgnore
    @GetMapping("/user/test")
    public String test() {
        return "这是一个测试接口，不需要在api文档中显示。";
    }
}
```

```JAVA
package com.segroup.hospitalsite.controller;

import com.segroup.hospitalsite.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author zheng
 * @date 2022-03-30
 */
@RestController
@Api(tags = "打招呼接口")
public class HelloController {
    @GetMapping(value = "/hello")
    public User getUser() {
        return new User();
    }

    @ApiOperation(value = "打招呼", notes = "可以指定参数的API")
    @PostMapping("/param")
    public String hello(@ApiParam("用户名") String name) {
        return "hello" + name;
    }
}
```



### 对于Model的例子

```JAVA
@Getter
@Setter
@ToString
@ApiModel(value = "用户实体类", description = "用户信息描述类")
public class User {
    @ApiModelProperty(value = "用户id")
    private Integer id;
 
    @ApiModelProperty(value = "用户名")
    private String username;
 
    @ApiModelProperty(value = "用户密码")
    private String password;
}
```

## 如何查看接口文档

启动Springboot，在浏览器中输入 http://localhost:8080/doc.html 即可查看。

![image-20220330014631505](image/image-20220330014631505.png)

展开任意一个接口描述，单击 **Try it out** 按钮后，可以实现对该接口的测试。

![image-20220330014651180](image/image-20220330014651180.png)