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

    public static class Role {
        public static final Role ADMIN = new Role("ADMIN");
        public static final Role USER = new Role("USER");

        private final String name;

        private Role(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}