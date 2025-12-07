package org.springframework.samples.petclinic.pet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/pets")
public class PetListController {

	private final PetRepository repository;

	public PetListController(PetRepository repo){
		this.repository = repo;
	}

	@GetMapping("/find")
	public String find(@RequestParam(required = false) String petName,
					   @RequestParam(defaultValue = "0") int page,
					   @RequestParam(defaultValue = "10") int size,
					   Model model) {
		Pageable pageable = PageRequest.of(page,size);
		Page<Pet> petPage = this.repository.searchPets(petName,pageable);

		model.addAttribute("petPage", petPage);
		model.addAttribute("pets", petPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", petPage.getTotalPages());

		return "pets/findPets";
	}
}
