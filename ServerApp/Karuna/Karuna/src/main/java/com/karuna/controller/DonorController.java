package com.karuna.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karuna.dto.ItemDto;
import com.karuna.dto.LoginDto;
import com.karuna.dto.LogoutDto;
import com.karuna.dto.RegisterDonorDto;
import com.karuna.entity.Donor;
import com.karuna.services.DonorService;

@RestController
@CrossOrigin
@RequestMapping("/donor")
public class DonorController {
    @Autowired
    private DonorService donorService;

	public DonorController() {
		super();
	}
    
    @PostMapping(value="/register")
    public ResponseEntity<?> addDonor(@RequestBody RegisterDonorDto donor) {
		return ResponseEntity.ok(donorService.registerDonor(donor));
    	
    }
    
    @GetMapping("/campaigns")
    public ResponseEntity<?> viewCampaign(@PathVariable Boolean status){
    	status=true;
    	return ResponseEntity.ok(donorService.viewCampaign(status));
    }
    
    @DeleteMapping("/{donorId}")
    public ResponseEntity<?> deleteDonor(@PathVariable Long donorId) {
    	return ResponseEntity.ok(donorService.deleteAccount(donorId));
    }
    
    @PutMapping("/profile")
    public ResponseEntity<?> updateDonor(@RequestBody RegisterDonorDto donor) {
    	return ResponseEntity.ok(donorService.updateDonor(donor));
    }
    
    @PostMapping(value="/donate")
    public ResponseEntity<?> donateItem(@RequestBody ItemDto newItem) {
    	return ResponseEntity.ok(donorService.donate(newItem));
    }
    
    @PostMapping("/login")
	public ResponseEntity<?> validateDonor(@RequestBody @Valid LoginDto dto){
    	return ResponseEntity.ok(donorService.login(dto));
    } 
    
    @GetMapping("/receivers")
    public ResponseEntity<?> viewReceivers(){
    	return ResponseEntity.ok(donorService.viewReceivers());
    }
    
    @GetMapping("/history/{donorId}")
    public ResponseEntity<?> viewHistory(@PathVariable Long donorId){
    	return ResponseEntity.ok(donorService.viewHistory(donorId));
    }
    
    @GetMapping("/logout")
    public ResponseEntity<?> logOut(@PathVariable LogoutDto logOutDto){
    	return ResponseEntity.ok(donorService.logout(logOutDto));
    }
}
