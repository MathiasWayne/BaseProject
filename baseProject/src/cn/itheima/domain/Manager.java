package cn.itheima.domain;

/**
 * @program: web
 * @description: ${description}
 * @author: AloysMack
 * @create: 2018-09-06 20:51
 **/
public class Manager {
    private String name;
    private String password;

    public Manager() {
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
