package com.karuna.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karuna.dto.LoginDto;
import com.karuna.dto.LogoutDto;
import com.karuna.dto.RegisterReceiverDto;
import com.karuna.dto.RequestDto;
import com.karuna.entity.Donor;
import com.karuna.entity.Item;
import com.karuna.entity.Receiver;
import com.karuna.entity.Request;
import com.karuna.entity.Staff;
import com.karuna.exception.ResourceNotFoundException;
import com.karuna.repos.DonorRepo;
import com.karuna.repos.ItemRepo;
import com.karuna.repos.ReceiverRepo;
import com.karuna.repos.RequestRepo;

@Service
@Transactional
public class ReceiverServiceImpl implements ReceiverService {

	@Autowired
	private ReceiverRepo receiverRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private RequestRepo reqRepo;
	
	@Autowired
	private DonorRepo donorRepo;
	
	
	
	public ReceiverServiceImpl() {
		super();
	}

	@Override
	public Receiver registerReceiver(RegisterReceiverDto newReceiver) {
		Receiver receiver=mapper.map(newReceiver, Receiver.class);
		return receiverRepo.save(receiver);
	}

	@Override
	public Receiver login(LoginDto loginDto) {
		Receiver receiver=receiverRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
		receiver.setStatus(true);
		return mapper.map(receiver, Receiver.class);
	}

	@Override
	public Item receive(Long itemId) {
		Item item=itemRepo.findById(itemId).orElseThrow();
			item.setStatus(true);
			item.setAcceptedDateTime(LocalDateTime.now());
			return item;
		}
	
		
	

	@Override
	public String logout(LogoutDto logoutDto) {
		return null;
	}

	@Override
	public String deleteAccount(Long receiverId) {
		receiverRepo.deleteById(receiverId);
		return "Receiver Deleted!!!";
	}

	@Override
	public Receiver updateReceiver(RegisterReceiverDto updatedReceiver) {
		Receiver receiver=mapper.map(updatedReceiver, Receiver.class);
		if(receiverRepo.existsById(updatedReceiver.getId())) {
			return receiverRepo.save(receiver);
		}
		return null;
	}

	@Override
	public List<Item> viewItems() {
		return itemRepo.findAll();
	}

	@Override
	public List<Item> viewHistory(Long receiverId) {
		if(donorRepo.existsById(receiverId)) {
			return itemRepo.findAllByReceiverId(receiverId);
		}
		return null;
	}

	@Override
	public List<Donor> viewDonors() {
		return donorRepo.findAll();
	}

	@Override
	public Request createRequest(RequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

}
