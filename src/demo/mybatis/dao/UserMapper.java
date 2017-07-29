package demo.mybatis.dao;

import demo.mybatis.pojo.User;

public interface UserMapper {
	User findByID(int id);
}
