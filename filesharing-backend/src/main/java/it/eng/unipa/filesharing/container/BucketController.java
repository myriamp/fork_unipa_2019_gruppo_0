package it.eng.unipa.filesharing.container;

import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.eng.unipa.filesharing.dto.BucketDTO;
import it.eng.unipa.filesharing.dto.BucketTypeDTO;
import it.eng.unipa.filesharing.dto.MembershipDTO;
import it.eng.unipa.filesharing.service.TeamService;

@RestController
@RequestMapping("/bucket")
public class BucketController {
	
	private TeamService teamService;
	
	public BucketController(@Autowired TeamService teamService) {
		this.teamService = teamService;
	}
	
	@PutMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(@PathVariable("uuid") UUID uuid,@RequestBody BucketDTO bucket){
		teamService.addBucket(uuid, bucket);
	}

	@DeleteMapping("/{uuid}/{bucketName}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("uuid") UUID uuid,@PathVariable("bucketName") String bucketName){
		teamService.removeBucket(uuid, bucketName);
	}
	
	
	@GetMapping("/type")
	@ResponseStatus(value = HttpStatus.OK)
	public List<BucketTypeDTO> type() {
		return teamService.bucketTypeSupport();
	}
}
