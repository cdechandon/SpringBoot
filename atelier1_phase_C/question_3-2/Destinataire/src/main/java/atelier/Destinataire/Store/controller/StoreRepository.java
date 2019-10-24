package atelier.Destinataire.Store.controller;

import org.springframework.data.repository.CrudRepository;

import atelierASI2.CommonModel.Store.StoreModel;

public interface StoreRepository extends CrudRepository<StoreModel, Integer> {
	

}
