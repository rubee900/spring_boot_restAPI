package com.techtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtech.dto.DogDTO;
import com.techtech.enitity.DogEntity;
import com.techtech.repo.DogRepository;

@Service
public class DogService {
	
	@Autowired
	private DogRepository dogRepository;
	
	public DogDTO save (DogDTO dogDTO) {
		DogEntity dogEntity = new DogEntity();
		BeanUtils.copyProperties(dogDTO, dogEntity);
		DogEntity entity= dogRepository.save(dogEntity);
		dogDTO.setDid(entity.getDid());
		return dogDTO;
	}
	
	public List<DogDTO> findDogs(){
		List<DogDTO> dogDTOs= new ArrayList<>();
		List<DogEntity> dogEntities= dogRepository.findAll();
		for(DogEntity dogEntity: dogEntities) {
			DogDTO dogDTO= new DogDTO();
			BeanUtils.copyProperties(dogEntity, dogDTO);
			dogDTOs.add(dogDTO);
			
		}
		return dogDTOs;
		
	}
	
	public void deleteDog(int did) {
		dogRepository.deleteById(did);
	}

}
