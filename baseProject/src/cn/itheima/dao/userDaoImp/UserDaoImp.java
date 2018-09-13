package cn.itheima.dao.userDaoImp;

import cn.itheima.dao.UserDao;
import cn.itheima.domain.Manager;
import cn.itheima.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JdbcUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: web
 * @description: ${description}
 * @author: AloysMack
 * @create: 2018-09-06 18:59
 **/
public class UserDaoImp implements UserDao{
    private  JdbcTemplate jdbc=new JdbcTemplate(JdbcUtils.getDataSource());
    @Override
    public List<User> selectAll() {
        //查询数据库

        String sql="select * from user";
        List<User> list = jdbc.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return list;
    }

    @Override
    public Manager login(Manager manager) {
        String sql="select * from manager where name=? and password=?";
        Manager m=jdbc.queryForObject(sql,new BeanPropertyRowMapper<>(Manager.class),manager.getName(),manager.getPassword());
        return  m;
    }

    @Override
    public void delete(String name) {
        String sql="delete from user where name=?";
        jdbc.update(sql,name);
    }

    @Override
    public void add(User user) {
        String sql="INSERT INTO USER(name,gender,age,address,qq,email) VALUES(?,?,?,?,?,?)";
        jdbc.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }


//根据id查询用户信息

    @Override
    public User selectByid(int id) {

        String sql="select * from user where id=?";
        User user=jdbc.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),id);
        return user;
}
//查询用户信息
    @Override
    public void update(User user) {
        String sql="update user set gender=?,age=?,address=?,qq=?,email=? where id=?";
        jdbc.update(sql,user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }
//查询总的记录数
    @Override
    public int selectCount() {
        String sql="select count(*) from user";
        int count=jdbc.queryForObject(sql,Integer.class);
        return count;
    }

    @Override
    public List selectRecode(int i, int pageCount) {
        String sql="select * from user limit ?,?";
        List<User> query = jdbc.query(sql, new BeanPropertyRowMapper<>(User.class), (i - 1) * pageCount, pageCount);
        return query;
    }
//查询所有模糊查询的数据量
    @Override
    public int queryAll(User user) {
        //创建数据sql
        StringBuilder baseSql=new StringBuilder("select count(*) from user");
        StringBuilder whereSql=new StringBuilder(" where 1=1");
        //模糊查询条件
        String name=user.getName();
        ArrayList<String> list=new ArrayList<>();
        if (name!=null&&!name.trim().isEmpty()){
            whereSql.append(" and name like ?");
            list.add("%"+name+"%");
        }
         String address=user.getAddress();
        if (address!=null&&!address.trim().isEmpty()){
            whereSql.append(" and address like ?");
            list.add("%"+address+"%");
        }
        String gender=user.getGender();
        if (gender!=null&&!gender.trim().isEmpty()){
            whereSql.append(" and gender like ?");
            list.add("%"+gender+"%");
        }

        int count=jdbc.queryForObject(baseSql.append(whereSql).toString(),Integer.class,list.toArray());

        return count;
    }

    @Override
    public List<User> queryForList(User user, int page,int pageSize) {
        //创建数据sql
        StringBuilder baseSql=new StringBuilder("select * from user where 1=1");

        //模糊查询条件
        String name=user.getName();
        ArrayList<Object> list=new ArrayList<>();
        if (name!=null&&!name.trim().isEmpty()){
            baseSql.append(" and name like ?");
            list.add("%"+name+"%");
        }
        String address=user.getAddress();
        if (address!=null&&!address.trim().isEmpty()){
            baseSql.append(" and address like ?");
            list.add("%"+address+"%");
        }
        String gender=user.getGender();
        if (gender!=null&&!gender.trim().isEmpty()){
            baseSql.append(" and gender like ?");
            list.add("%"+gender+"%");
        }
        StringBuilder sb=new StringBuilder(" limit ?,?");
        baseSql.append(sb);
        list.add(((page-1)*pageSize));
        list.add(pageSize);
        String sql=baseSql.toString();

        return jdbc.query(sql, new BeanPropertyRowMapper<User>(User.class), list.toArray());
    }
}
