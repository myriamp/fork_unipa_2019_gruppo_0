package it.eng.unipa.filesharing.container;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.eng.unipa.filesharing.dto.TeamDTO;
import it.eng.unipa.filesharing.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {
	
	private TeamService teamService;
	
	public TeamController(@Autowired TeamService teamService) {
		this.teamService = teamService;
	}

	
	@GetMapping("")
	public ResponseEntity<List<TeamDTO>> myTeams(){
		return new ResponseEntity<List<TeamDTO>>(this.teamService.myTeams(),HttpStatus.OK);
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<TeamDTO> get(@PathVariable("uuid")UUID uuid){
		TeamDTO teamDTO = teamService.get(uuid);
		return teamDTO!=null ? new ResponseEntity<TeamDTO>(teamDTO,HttpStatus.OK) : new ResponseEntity<TeamDTO>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(@RequestBody TeamDTO team){
		teamService.save(team);
	}
	
	
	@DeleteMapping("/{uuid}/{recursive}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("uuid")UUID uuid,@PathVariable("recursive")Boolean recursive){
		teamService.delete(uuid,recursive);
	}
}