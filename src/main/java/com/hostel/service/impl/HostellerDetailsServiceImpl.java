package com.hostel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hostel.dto.HostellerDetailsDto;
import com.hostel.dto.UserDto;
import com.hostel.model.HostellerDetails;
import com.hostel.model.Role;
import com.hostel.model.User;
import com.hostel.repository.HostellerDetailsRepository;
import com.hostel.repository.RoleRepository;
import com.hostel.service.HostellerDetailsService;

@Service
public class HostellerDetailsServiceImpl implements HostellerDetailsService{
    @Autowired UserServiceImpl userService;
    @Autowired HostellerDetailsRepository hostellerDetailsRepository;
    @Autowired HostelFeesServiceImpl hostelFeesService;
    @Autowired RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public boolean feesStatus (int hostellerId){
        double dueFeesAmount = hostellerDetailsRepository.findById(hostellerId).get().getDueFeesAmount();
        if(dueFeesAmount == 0){
            return true;
        }
        return false;
    }
    public HostellerDetails dtoToEntity(HostellerDetailsDto hostellerDetailsDto) {
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
        hostellerDetails.setUser(hostellerDetailsDto.getUser());
        return hostellerDetails;
    }

    @Override
    public HostellerDetails save(HostellerDetailsDto hostellerDetailsDto) {
        List<Role> roles = new ArrayList<>();
        Role role = new Role(4, "ROLE_hosteller");
        roleRepository.save(role);
        roles.add(role);
        hostellerDetailsDto.getUser().setRoles(roles);
        hostellerDetailsDto.getUser().setPassword(passwordEncoder.encode(hostellerDetailsDto.getUser().getPassword()));

//        UserDto userDto = new UserDto();
//        userDto.setName(hostellerDetailsDto.getName());
//        userDto.setMobile(hostellerDetailsDto.getMobile());
//        userDto.setEmail(hostellerDetailsDto.getEmail());
//        userDto.setPassword(hostellerDetailsDto.getPassword());
//        userDto.setRoles(roles);
//
//        User user = userService.save(userDto);

        HostellerDetails hostellerDetails = dtoToEntity(hostellerDetailsDto);
        hostellerDetails.setTotalFeesAmount(hostelFeesService.getHostelFeesDetails().getTotalFeesAmount());
        hostellerDetails.setDueFeesAmount(hostellerDetails.getTotalFeesAmount());
        hostellerDetails.setFeesStatus(hostellerDetailsDto.isFeesStatus());
//        hostellerDetails.setUser(user);
        System.out.println(hostellerDetails);
        return hostellerDetailsRepository.save(hostellerDetails);
//          return null;
        
    }
    @Override
    public HostellerDetails update(int id, HostellerDetailsDto hostellerDetailsdto) {
        HostellerDetails hostellerDetails = dtoToEntity(hostellerDetailsdto);
        HostellerDetails h2 = hostellerDetailsRepository.findById(id).get();
        if(h2 != null) {
            if(hostellerDetails.getAddress()!= null){
                h2.setAddress(hostellerDetails.getAddress());
            }
            if(hostellerDetails.getEntryDate()!= null){
                h2.setEntryDate(hostellerDetails.getEntryDate());
            }
            if(hostellerDetails.getDueFeesAmount() != 0){
                h2.setDueFeesAmount(hostellerDetails.getDueFeesAmount());
            }
            if(hostellerDetails.getExitDate()!= null){
                h2.setExitDate(hostellerDetails.getExitDate());
            }
            if(hostellerDetails.getHostelNumber()!= null){
                h2.setHostelNumber(hostellerDetails.getHostelNumber());
            }
            if(hostellerDetails.isHostellerStatus()!= false){
                h2.setHostellerStatus(hostellerDetails.isHostellerStatus());
            }
            if(hostellerDetails.getIdentityNumber()!= null){
                h2.setIdentityNumber(hostellerDetails.getIdentityNumber());
            }
            if(hostellerDetails.getIdentityType()!= null){
                h2.setIdentityType(hostellerDetails.getIdentityType());
            }
            if(hostellerDetails.getPaidFeesAmount() != 0){
                h2.setPaidFeesAmount(hostellerDetails.getPaidFeesAmount());
            }
            if(hostellerDetails.getRoomNumber()!= null){
                h2.setRoomNumber(hostellerDetails.getRoomNumber());
            }
            if(hostellerDetails.getTotalFeesAmount() != 0){
                h2.setTotalFeesAmount(hostellerDetails.getTotalFeesAmount());
            }
            if(hostellerDetails.getUser()!= null){
                User user = h2.getUser();
                User u = hostellerDetails.getUser();
                if(u.getName()!=null && !u.getName().equals(user.getName())){
                    user.setName(u.getName());
                }
                if(u.getEmail() != null && !u.getEmail().equals(user.getEmail())){
                    user.setEmail(u.getEmail());
                }
                if(u.getMobile() != null && !u.getMobile().equals(user.getMobile())){
                    user.setMobile(u.getMobile());
                }
                h2.setUser(user);
            }
            h2.setFeesStatus(feesStatus(h2.getHostellerId()));
        }
        return hostellerDetailsRepository.save(h2);
    }
    @Override
    public HostellerDetails findById(int id) {
        return hostellerDetailsRepository.findById(id).get();
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
            hostellerDetailsDto.setUser(hostellerDetail.getUser());
            hostellerDetailsDtos.add(hostellerDetailsDto);
        }
        return hostellerDetailsDtos;
    }
    @Override
    public void delete(int id) {
        hostellerDetailsRepository.deleteById(id);
    }

    public HostellerDetails findByUserId(int id){
        return hostellerDetailsRepository.findByUserUserId(id);
    }
    //save hosteller details
    
}
