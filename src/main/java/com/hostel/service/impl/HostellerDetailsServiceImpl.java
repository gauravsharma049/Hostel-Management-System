package com.hostel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.dto.HostellerDetailsDto;
import com.hostel.dto.UserDto;
import com.hostel.model.HostellerDetails;
import com.hostel.model.User;
import com.hostel.repository.HostellerDetailsRepository;
import com.hostel.service.HostellerDetailsService;

@Service
public class HostellerDetailsServiceImpl implements HostellerDetailsService{
    @Autowired UserServiceImpl userService;
    @Autowired HostellerDetailsRepository hostellerDetailsRepository;


    @Override
    public HostellerDetails save(HostellerDetailsDto hostellerDetailsDto) {
        UserDto userDto = new UserDto();
        userDto.setName(hostellerDetailsDto.getName());
        userDto.setMobile(hostellerDetailsDto.getMobile());
        userDto.setEmail(hostellerDetailsDto.getEmail());
        userDto.setPassword(hostellerDetailsDto.getPassword());
        userDto.setRoles(hostellerDetailsDto.getRoles());

        User user = userService.save(userDto);

        HostellerDetails hostellerDetails = new HostellerDetails();
        hostellerDetails.setHostellerId(hostellerDetailsDto.getHostellerId());
        hostellerDetails.setHostelNumber(hostellerDetailsDto.getHostelNumber());
        hostellerDetails.setRoomNumber(hostellerDetailsDto.getRoomNumber());
        hostellerDetails.setEntryDate(hostellerDetailsDto.getEntryDate());
        hostellerDetails.setExitDate(hostellerDetailsDto.getExitDate());
        hostellerDetails.setAddress(hostellerDetailsDto.getAddress());
        hostellerDetails.setIdentityType(hostellerDetailsDto.getIdentityType());
        hostellerDetails.setIdentityNumber(hostellerDetailsDto.getIdentityNumber());
        hostellerDetails.setHostellerStatus(hostellerDetailsDto.isHostellerStatus());
        hostellerDetails.setTotalFeesAmount(hostellerDetailsDto.getTotalFeesAmount());
        hostellerDetails.setPaidFeesAmount(hostellerDetailsDto.getPaidFeesAmount());
        hostellerDetails.setDueFeesAmount(hostellerDetailsDto.getDueFeesAmount());
        hostellerDetails.setFeesStatus(hostellerDetailsDto.isFeesStatus());
        hostellerDetails.setUser(user);

        return hostellerDetailsRepository.save(hostellerDetails);


    }
    @Override
    public HostellerDetails update(HostellerDetailsDto hostellerDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public HostellerDetails findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    @Override
    public List<HostellerDetailsDto> findAllHostellerDetails() {
        List<HostellerDetailsDto> hostellerDetailsDtos = new ArrayList<>();
        List<HostellerDetails> hostellerDetails = hostellerDetailsRepository.findAll();
        for(HostellerDetails hostellerDetail : hostellerDetails) {
            HostellerDetailsDto hostellerDetailsDto = new HostellerDetailsDto();
            hostellerDetailsDto.setHostellerId(hostellerDetail.getHostellerId());
            hostellerDetailsDto.setHostelNumber(hostellerDetail.getHostelNumber());
            hostellerDetailsDto.setRoomNumber(hostellerDetail.getRoomNumber());
            hostellerDetailsDto.setEntryDate(hostellerDetail.getEntryDate());
            hostellerDetailsDto.setExitDate(hostellerDetail.getExitDate());
            hostellerDetailsDto.setAddress(hostellerDetail.getAddress());
            hostellerDetailsDto.setIdentityType(hostellerDetail.getIdentityType());
            hostellerDetailsDto.setIdentityNumber(hostellerDetail.getIdentityNumber());
            hostellerDetailsDto.setHostellerStatus(hostellerDetail.isHostellerStatus());
            hostellerDetailsDto.setTotalFeesAmount(hostellerDetail.getTotalFeesAmount());
            hostellerDetailsDto.setPaidFeesAmount(hostellerDetail.getPaidFeesAmount());
            hostellerDetailsDto.setDueFeesAmount(hostellerDetail.getDueFeesAmount());
            hostellerDetailsDto.setFeesStatus(hostellerDetail.isFeesStatus());

            hostellerDetailsDto.setName(hostellerDetail.getUser().getName());
            hostellerDetailsDto.setMobile(hostellerDetail.getUser().getMobile());
            hostellerDetailsDto.setEmail(hostellerDetail.getUser().getEmail());
            hostellerDetailsDtos.add(hostellerDetailsDto);
        }
        return hostellerDetailsDtos;
    }
    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    //save hosteller details
    
}
