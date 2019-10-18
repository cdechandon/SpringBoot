package atelier.Destinataire.Card.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import atelierASI2.CommonModel.Card.CardModel;
import atelierASI2.CommonModel.User.UserModel;

@Repository

public interface CardModelRepository extends CrudRepository<CardModel, Integer> {
    List<CardModel> findByUser(UserModel u);
}
