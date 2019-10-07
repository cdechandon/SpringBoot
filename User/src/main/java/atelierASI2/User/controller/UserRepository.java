package atelierASI2.User.controller;

import atelierASI2.CommonModel.User.UserModel;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Integer> {

	List<UserModel> findByLoginAndPwd(String login,String pwd);
}
