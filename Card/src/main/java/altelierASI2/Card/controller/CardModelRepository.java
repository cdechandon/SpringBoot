package altelierASI2.Card.controller;

import atelierASI2.CommonModel.User.UserModel;
import org.springframework.data.repository.CrudRepository;

import atelierASI2.CommonModel.Card.CardModel;

import java.util.List;

public interface CardModelRepository extends CrudRepository<CardModel, Integer> {
    List<CardModel> findByUser(UserModel u);
}
