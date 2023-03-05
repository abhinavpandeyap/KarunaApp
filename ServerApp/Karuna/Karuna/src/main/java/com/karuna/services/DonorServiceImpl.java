package com.karuna.services;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karuna.dto.ItemDto;
import com.karuna.dto.LoginDto;
import com.karuna.dto.LogoutDto;
import com.karuna.dto.RegisterDonorDto;
import com.karuna.entity.Campaign;
import com.karuna.entity.Donor;
import com.karuna.entity.Item;
import com.karuna.entity.Receiver;
import com.karuna.exception.ResourceNotFoundException;
import com.karuna.repos.CampaignRepo;
import com.karuna.repos.DonorRepo;
import com.karuna.repos.ItemRepo;
import com.karuna.repos.ReceiverRepo;

@Service
@Transactional
public class DonorServiceImpl implements DonorService {
     
	@Autowired
	private DonorRepo donorRepo;
	
	@Autowired
	private CampaignRepo campaignRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private ReceiverRepo receiverRepo;
	
	
   	
	public DonorServiceImpl() {
		super();
	}

	@Override
	public Donor registerDonor(RegisterDonorDto newDonor) {
          Donor donor=mapper.map(newDonor, Donor.class);
		return donorRepo.save(donor);
	}

	@Override
	public List<Campaign> viewCampaign(Boolean status) {
		return campaignRepo.findAllByStatus(status);
		
		
	}

	@Override
	public Donor login(LoginDto loginDto) {
		Donor donor = donorRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!"));
		donor.setStatus(true);
		return mapper.map(donor, Donor.class);
	}

	@Override
	public Item donate(ItemDto newItem) {
		Item item=mapper.map(newItem, Item.class);
		return itemRepo.save(item);
	}

	@Override
	public String logout(LogoutDto logoutDto) {
		return null;
	}

	@Override
	public String deleteAccount(Long donorId) {
		donorRepo.deleteById(donorId);
		return "Donor Deleted!!!!";
	}

	@Override
	public Donor updateDonor(RegisterDonorDto updatedDonor) {
        Donor donor=mapper.map(updatedDonor,Donor.class);     
		if(donorRepo.existsById(updatedDonor.getId())) {
    	   return donorRepo.save(donor);
       }
		return null;
	}

	@Override
	public List<Item> viewHistory(Donor donor) {
		if(donorRepo.existsById(donor.getId())) {
			return itemRepo.findAllByDonorId(donor);
		}
		return null;
	}

	@Override
	public List<Receiver> viewReceivers() {
		return receiverRepo.findAll();
	}

	
	
	

}
