# MyBlog API文档

## 管理员部分 @RequestMapping(“/admin”)

- 注意：Admin部分全部装配JWT拦截器，访问管理员部分务必使请求头headers包含token串，并且该token对应用户权限role=1 。否则将有如下错误返回信息

  * 无token

    ```json
    {
        "success": true,
        "errCode": null,
        "errMsg": null,
        "data": {
            "status": 500,
            "message": "java.lang.NullPointerException: Cannot invoke \"String.split(String)\" because \"token\" is null",
            "data": null
        }
    }
    ```

  * 无权限

    ```json
    {
        "success": true,
        "errCode": null,
        "errMsg": null,
        "data": {
            "status": 403,
            "message": "无权限",
            "data": null
        }
    }
    ```

1. 查找用户@PostMapping("/checkUser")

   * 请求体

   ```json
   #headers
   	token=""
   #param
   	username=""
   ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "username": "",
               "password": "",
               "email": "",
               "role":
           }
       }
       ```

     * 失败

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "status": 402,
               "message": "用户名为空或用户不存在",
               "data": null
           }
       }
       ```

2. 删除用户@PostMapping("/deleteUser")

   * 请求体

     ```json
     #headers
     	token=""
     #param
     	username=""
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": true
       }
       ```

     * 失败

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "status": 402,
               "message": "同步数据为空或用户不存在",
               "data": null
           }
       }
       ```

3. 查找博客@Postmapping("/checkBlog")

   * 请求体

     ```json
     #headers
     	token=""
     #param
     	blogName=""
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": true
       }
       ```

     * 失败

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "status": 402,
               "message": "博客不存在",
               "data": null
           }
       }
       ```

4. 删除博客@PostMapping("/deleteBlog")

   * 请求体

     ```json
     #headers
     	token=""
     #param
     	blogName=""
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": true
       }
       ```

     * 失败

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "status": 402,
               "message": "博客不存在",
               "data": null
           }
       }
       ```

5. 增加博客@PostMapping("/addBlog")

   * 请求体

     ```json
     #headers
     	token=""
     #param
     	blogName=""
     #body (form-data)
     	#文件名不要使用中文
     	file-value
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": true
       }
       ```

     * 失败

       * 上传异常——oss服务异常

         ```json
         {
             "success": true,
             "errCode": null,
             "errMsg": null,
             "data": {
                 "status": 503,
                 "message": "上传异常",
                 "data": null
             }
         }
         ```

       * 网络错误——同步数据库异常

         ```json
         {
             "success": true,
             "errCode": null,
             "errMsg": null,
             "data": {
                 "status": 405,
                 "message": "网络错误",
                 "data": null
             }
         }
         ```

6. 上传图片@PostMapping("/addPicture")

   * 请求体

     ```json
     #headers
     	token=""
     #param
     	pictureName=""
     #body (form-data)
     	#文件名不要使用中文
     	file-value
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": true
       }
       ```

     * 失败

       * 上传异常——oss服务异常

         ```json
         {
             "success": true,
             "errCode": null,
             "errMsg": null,
             "data": {
                 "status": 503,
                 "message": "上传异常",
                 "data": null
             }
         }
         ```

       * 网络错误——同步数据库异常

         ```json
         {
             "success": true,
             "errCode": null,
             "errMsg": null,
             "data": {
                 "status": 405,
                 "message": "网络错误",
                 "data": null
             }
         }
         ```

## 用户部分 @RequestMapping("/user")

1. 查找博客@PostMapping("/requestBlog")

   * 请求体

     ```json
     #param
     	blogName=""
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "id": 2,
               "blogName": "404yyds",
               "blogLocal": "https://jun-blog.oss-cn-hangzhou.aliyuncs.com/404.md"
           }
       }
       ```

     * 失败

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "status": 404,
               "message": "请求资源不存在",
               "data": null
           }
       }
       ```

2. 按时间顺序返回博客@PostMapping("/dateLine")

   * 请求体

     ```json
     #param
     	#count表示页数，首页为1，该方法每页返回5条记录
     	count=int
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data":{
               {
                   "id": 2,
                   "blogName": "404yyds",
                   "blogLocal": "https://jun-blog.oss-cn-hangzhou.aliyuncs.com/404.md"
               },
               {
                   "id": 4,
                   "blogName": "ggg",
                   "blogLocal": "ggg"
               }
           }
       }
       ```

     * 失败

       ```java
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "status": 403,
               "message": "没有更多记录",
               "data": null
           }
       }
       ```

3. 增加评论@PostMapping("/addComment")

   * 请求体

     ```json
     #headers
     	token=""
     #param
     	id=int
     	commentValue=""
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": true
       }
       ```

     * 失败

       ```json
       #评论不能为空
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "status": 500,
               "message": "org.springframework.dao.DataIntegrityViolationException: \r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Column 'commentValue' cannot be null\r\n### The error may exist in file [E:\\JavaProject\\myblog\\target\\classes\\mapper\\UserMapper.xml]\r\n### The error may involve com.example.myblog.mapper.UserMapper.addComment-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into myblog.comment(id, commenter, commentValue) VALUES (?,?,?)\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Column 'commentValue' cannot be null\n; Column 'commentValue' cannot be null; nested exception is java.sql.SQLIntegrityConstraintViolationException: Column 'commentValue' cannot be null",
               "data": null
           }
       }
       ```

## 注册与登录

1. 注册@PostMapping("/register")

   * 请求体

     ```json
     #param
     	username=""
     	password=""
     	email=""
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": true
       }
       ```

     * 失败——同步数据为空

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": {
               "status": 500,
               "message": "java.lang.NullPointerException: Cannot read the array length because \"input\" is null",
               "data": null
           }
       }
       ```

2. 登录@PostMapping("/login")

   * 请求体

     ```json
     #param
     	username=""
     	password=""
     ```

   * 返回体

     * 成功

       ```json
       {
       	"success":true,
       	"errCode":null,
       	"errMsg":null,
       	"data":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiMSIsImV4cCI6MTY0NTk1MzUwNSwiZW1haWwiOiJ6aG91cm9uZ2p1bjEyMzRAb3V0bG9vay5jb20iLCJ1c2VybmFtZSI6IlhpbkFuWGlhb0p1biJ9.rv1fJLNElDvTRu70CR2V5eD8abXsqjSR011tCam-xkM"
       }
       #注意：返回的data为token，应放入headers
       ```

     * 失败

       * 密码错误

         ```json
         {
             "success": true,
             "errCode": null,
             "errMsg": null,
             "data": {
                 "status": 403,
                 "message": "密码错误",
                 "data": null
             }
         }
         ```

       * 密码为空

         ```json
         {
             "success": true,
             "errCode": null,
             "errMsg": null,
             "data": {
                 "status": 500,
                 "message": "java.lang.NullPointerException: Cannot read the array length because \"input\" is null",
                 "data": null
             }
         }
         ```

       * 用户名为空或不存在

         ```json
         {
             "success": true,
             "errCode": null,
             "errMsg": null,
             "data": {
                 "status": 401,
                 "message": "用户名不存在",
                 "data": null
             }
         }
         ```

3. 修改密码@PostMapping("/fixPassword")

   * 请求体

     ```json
     #param
     	username=""
     	originPassword=""
     	newPassword=""
     ```

   * 返回体

     * 成功

       ```json
       {
           "success": true,
           "errCode": null,
           "errMsg": null,
           "data": true
       }
       ```

     * 失败

       * 用户不存在

         ```json
         {
             "success": true,
             "errCode": null,
             "errMsg": null,
             "data": {
                 "status": 403,
                 "message": "用户不存在",
                 "data": null
             }
         }
         ```

       * 原密码错误

         ```json
         {
             "success": true,
             "errCode": null,
             "errMsg": null,
             "data": {
                 "status": 500,
                 "message": "原密码错误",
                 "data": null
             }
         }
         ```

         