package com.techtech.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtech.dto.DogDTO;
import com.techtech.service.DogService;

@RestController
@RequestMapping("/v1")
public class DogRestController {

	final private DogService dogService;

	public DogRestController(final DogService dogService) {
		this.dogService = dogService;
	}

	@GetMapping("/dogs")
	public List<DogDTO> showDogs() {
		return dogService.findDogs();

		/*
		 * List<DogDTO> dtos = new ArrayList<DogDTO>(); dtos.add(new DogDTO(1, "Cherry",
		 * "red")); dtos.add(new DogDTO(2, "Merry", "white")); dtos.add(new DogDTO(3,
		 * "tommy", "blue"));
		 * 
		 * return dtos;
		 */

	}

	@GetMapping("/dogs/{did}")
	public DogDTO showDog(@PathVariable int did) {
		return new DogDTO(did, "Cherry", "red");
	}

	@PostMapping("/dogs")
	public ResponseEntity<DogDTO> createDog(@RequestBody DogDTO dogDTO) {
		dogDTO = dogService.save(dogDTO);
		return new ResponseEntity<DogDTO>(dogDTO, HttpStatus.CREATED);

	}

	@DeleteMapping("/dogs/{did}")
	public ResponseEntity<Void> deleteDog(@PathVariable int did) {
		dogService.deleteDog(did);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	/*
	 * @PostMapping("/dogs") public DogDTO createDog(@RequestBody DogDTO dogDTO) {
	 * System.out.println("request payload = " + dogDTO); dogDTO.setDid(new
	 * Random().nextInt()); return dogDTO; }
	 */
}
