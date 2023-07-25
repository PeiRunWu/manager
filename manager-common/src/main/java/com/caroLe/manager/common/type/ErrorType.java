package com.caroLe.manager.common.type;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:38
 * @Description
 */
public class ErrorType extends StatusType {

    public static final ErrorType SERVICE_ERROR = new ErrorType(202, "服务异常");
    public static final ErrorType USER_NO_ACCESS = new ErrorType(203, "用户登入失败");
    public static final ErrorType DATA_ERROR = new ErrorType(204, "数据异常");
    public static final ErrorType ILLEGAL_REQUEST = new ErrorType(205, "该用户无权限访问此请求");
    public static final ErrorType LOGIN_IS_OVERDUE = new ErrorType(206, "您的操作已超时，请重新登录");
    public static final ErrorType LOGIN_AUTH = new ErrorType(207, "用户未登陆");
    public static final ErrorType PERMISSION = new ErrorType(208, "该用户无权限访问");
    public static final ErrorType ARGUMENT_VALID_ERROR = new ErrorType(209, "参数校验异常");
    public static final ErrorType USER_NAME_EXIT = new ErrorType(210, "用户名已存在");
    public static final ErrorType AUTHENTICATION_FAILED = new ErrorType(211, "认证失败");
    public static final ErrorType ACCOUNT_STOP = new ErrorType(212, "账号已停用");
    public static final ErrorType NODE_ERROR = new ErrorType(213, "该节点下有子节点，不可以删除");

    public static final StatusType USER_OFFLINE = new StatusType(214, "用户下线");

    public static final StatusType DELETE_DATE = new StatusType(215, "后台删除数据异常");

    public static final StatusType UPDATE_DATE = new StatusType(216, "后台更新数据异常");

    public static final ErrorType MENU_USER_BIND = new ErrorType(217, "该节点已绑定用户权限，无法删除");

    public static final ErrorType ROLE_CODE_EXIST = new ErrorType(218, "角色编码已存在");

    public static final ErrorType ROLE_EXIST = new ErrorType(218, "角色必须存在一个");

    public static final ErrorType USER_NAME_NOT_EXIT = new ErrorType(219, "用户名不存在");

    public static final ErrorType PARENT_TAG_NOT_SELECT = new ErrorType(220, "父标签必须选择父标签类型");

    public static final ErrorType CHILDREN_TAG_NOT_SELECT = new ErrorType(221, "子标签必须选择子标签类型");

    public static final ErrorType BINDING_PERMISSIONS = new ErrorType(222, "该资源路径已绑定权限,不可以修改");

    public static final ErrorType WECHAT_NOT_REGISTERED = new ErrorType(223, "该微信无法注册");

    public static final ErrorType REMOTE_CALL_FAILED = new ErrorType(224, "远程调用失败");

    public static final ErrorType GET_USERID_ERROR = new ErrorType(225, "获取UserId错误");

    public static final ErrorType FILE_UPLOAD_FAILED = new ErrorType(226, "文件上传失败");

    public static final ErrorType FILE_DELETE_FAILED = new ErrorType(227, "文件删除失败");

    public static final ErrorType IMAGE_CONTENT_ILLEGAL = new ErrorType(228, "图片内容不合规");

    public ErrorType(Integer code, String message) {
        super(code, message);
    }
}
