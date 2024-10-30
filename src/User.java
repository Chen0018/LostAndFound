class User {
    private String username;
    private Role role;

    public User(String username, Role role) {//构造函数
        this.username = username;
        this.role = role;
    }

    public String getUsername() {//获取用户名
        return username;
    }

    public Role getRole() {//获取角色
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        ADMIN, USER
    }
}