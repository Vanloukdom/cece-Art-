package com.example.brenda.Cece.s.Art.service.implementations;
import com.example.brenda.Cece.s.Art.mapper.Mapper;
import com.example.brenda.Cece.s.Art.model.dto.UserDto;
import com.example.brenda.Cece.s.Art.model.entity.User;
import com.example.brenda.Cece.s.Art.model.enums.Role;
import com.example.brenda.Cece.s.Art.model.response.MetaDto;
import com.example.brenda.Cece.s.Art.model.response.PaginationDto;
import com.example.brenda.Cece.s.Art.model.response.ResponseDto;
import com.example.brenda.Cece.s.Art.repository.UserRepository;

import com.example.brenda.Cece.s.Art.service.interfaces.UserService;
import com.example.brenda.Cece.s.Art.utils.DateTimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Mapper mapper;

    private final MongoTemplate mongoTemplate;

    private DateTimeUtil dateTimeUtil;

//add get all users
    public ResponseDto createAccount(UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())){
            MetaDto meta = new MetaDto(400,"Bad Request","User Already Exist");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        userRepository.save(mapper.convertToEntity(userDto));
        MetaDto meta = new MetaDto(200,"Successfull","User Created Successively");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;

    }

    public ResponseDto getUserDetails(String userId){
        if(userRepository.existsById(userId)){
            MetaDto meta = new MetaDto(200,"Successfull","Request Processed Successively");
            PaginationDto pagination = new PaginationDto(1, 1);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, userRepository.findById(userId),error,pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404,"Not Found","User Not Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;

    }

    public ResponseDto updateUserDetails(UserDto userDto,String id){
        if(userRepository.existsById(id)){
            Query query = new Query(Criteria.where("_id").is(id));
            Update update = new Update()
                    .set("email", userDto.getEmail())
                    .set("name",userDto.getName())
                    .set("phoneNumber",userDto.getPhoneNumber())
                    .set("address",userDto.getAddress())
                    .set("updated_At",dateTimeUtil.getDateTime());
            mongoTemplate.findAndModify(query, update, User.class);
            MetaDto meta = new MetaDto(200,"Successfull","User Updated Successively");
            PaginationDto pagination = new PaginationDto(1, 1);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404,"Not Found","User Not Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error=null;
        ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
        return responseDto;

    }
    
    public ResponseDto getCustomers(int page) {
        int size = 15;
        if (page < 0) {
            MetaDto meta = new MetaDto(400, "Bad Request", "size and page should be greater than 0");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error = null;
            ResponseDto responseDto = new ResponseDto(meta, null, error, pagination);
            return responseDto;
        }
        List<User> users = userRepository.findAllByRole(Role.USER);
        if (!users.isEmpty()) {
            Pageable paging = PageRequest.of(page, size);
            Page<User> pageLoans = userRepository.findAllByRoleOrderByCreatedAtDesc(Role.USER, paging);
            MetaDto meta = new MetaDto(200, "Successful", "Request processed successfully");
            PaginationDto pagination = new PaginationDto(pageLoans.getTotalElements(), pageLoans.getSize());
            String error = null;
            ResponseDto responseDto = new ResponseDto(meta, pageLoans.getContent(), error, pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404, "Not Found", "No Customer Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error = null;
        ResponseDto responseDto = new ResponseDto(meta, null, error, pagination);
        return responseDto;
    }
    public ResponseDto getAdmins(int page){
        int size=10;
        if(page<0 ){
            MetaDto meta = new MetaDto(400,"Bad Request","size and page should be greater than 0");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        List<User> users=userRepository.findAllByRole(Role.ADMIN);
        if(!users.isEmpty()){
            Pageable paging = PageRequest.of(page, size);
            Page<User> pageLoans = userRepository.findAllByRoleOrderByCreatedAtDesc(Role.ADMIN,paging);
            MetaDto meta = new MetaDto(200,"Successful","Request processed successfully");
            PaginationDto pagination = new PaginationDto(pageLoans.getTotalElements(), pageLoans.getSize());
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, pageLoans.getContent(),error,pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404, "Not Found", "No Admin Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error = null;
        ResponseDto responseDto = new ResponseDto(meta, null, error, pagination);
        return responseDto;
    }
    public ResponseDto deleteUser(String userId){
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            MetaDto meta = new MetaDto(200,"Successful","User Deleted Successively");
            PaginationDto pagination = new PaginationDto(0, 0);
            String error=null;
            ResponseDto responseDto=new ResponseDto(meta, null,error,pagination);
            return responseDto;
        }
        MetaDto meta = new MetaDto(404, "Not Found", "User Not Found");
        PaginationDto pagination = new PaginationDto(0, 0);
        String error = null;
        ResponseDto responseDto = new ResponseDto(meta, null, error, pagination);
        return responseDto;

    }




}
