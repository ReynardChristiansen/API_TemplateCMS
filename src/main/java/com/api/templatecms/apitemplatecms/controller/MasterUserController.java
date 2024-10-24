package com.api.templatecms.apitemplatecms.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.templatecms.apitemplatecms.model.MasterUser;
import com.api.templatecms.apitemplatecms.repository.MasterUserRepository;
import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/users")
public class MasterUserController {
    @Autowired
    private MasterUserRepository masterUserRepository;

    @Autowired
    @GetMapping("/getusers")
    public List<Map<String, Object>> getAllUsers() {
        List<Object[]> users = masterUserRepository.findAllProjectedBy();
        return users.stream()
                .map(user -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", user[0]);
                    map.put("username", user[1]);
                    map.put("nama", user[2]);
                    map.put("ldap", user[3]);
                    map.put("status", user[4]);
                    return map;
                })
                .collect(Collectors.toList());
    };


    @PostMapping("/stsupdt")
    public ResponseEntity<Map<String, String>> updateStatusByIndex(@RequestBody Map<String, String> updateData) {
        Integer index;
        try {
            index = Integer.parseInt(updateData.get("index"));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Invalid index format", e));
        }

        Optional<MasterUser> userToUpdate = masterUserRepository.findById(index.longValue());

        if (!userToUpdate.isPresent()) {
            return ((BodyBuilder) ResponseEntity.notFound()).body(createErrorMessage("User not found", null));
        }

        MasterUser user = userToUpdate.get();
        Integer newStatus = (user.getSts() == 0) ? 1 : 0;
        user.setSts(newStatus);

        try {
            masterUserRepository.save(user);
            return ResponseEntity.ok(createSuccessMessage("User updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(createErrorMessage("Error updating user", e));
        }
    }

    private List<Map<String, Object>> createErrorMessage2(String message, Exception e) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> errors = new ArrayList<>(); // Initialize the list
        response.put("status", "error");
        response.put("message", message);
        errors.add(response);
        if (e != null) {
            System.err.println("Error updating user: " + e.getMessage());
        }
        return errors;
    }

    private Map<String, String> createErrorMessage(String message, Exception e) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        if (e != null) {
            System.err.println("Error updating user: " + e.getMessage());
        }
        return response;
    }

    private Map<String, String> createSuccessMessage(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", message);
        return response;
    }

    @PostMapping("/addnew")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, String> formData){
        String id;
        Integer isLDAP;
        Integer sts;
        String nama;
        String userLogin;
        String userPass;
        String group;
        Long tempId;
        try {
            nama = formData.get("nama");
            userLogin = formData.get("userLogin");
            userPass = formData.get("userPass");
            id = formData.get("id");
            group = formData.get("group");
            isLDAP = Integer.parseInt(formData.get("isLDAP"));
            sts = Integer.parseInt(formData.get("sts"));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Invalid Register Format", e));
        }
        LocalDateTime now = LocalDateTime.now();
        MasterUser newUser = new MasterUser();
        try{
            newUser.setNama(nama);
            newUser.setCreateddate(now);
            newUser.setCreatedby(id);
            newUser.setUserLogin(userLogin);
            newUser.setUserPass(userPass);
            newUser.setLDAP(isLDAP); 
            newUser.setSts(sts); 
            newUser.setCountFailedLogin(0); 
    
            MasterUser savedUser = masterUserRepository.save(newUser);
            tempId = savedUser.getId();
            
            
        }catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Failed to save", e));
        }
        try {
            String[] tempString = group.split(", ");

            String tempStringId = Long.toString(tempId);
            Integer tempInt = Integer.parseInt(tempStringId);
            for (int i = 0; i < tempString.length; i++) {
                masterUserRepository.insertMasterUserGroup(tempInt, Integer.parseInt(tempString[i]));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Failed to save", e));

        }
        
        
        return ResponseEntity.ok(createSuccessMessage("User updated successfully"));


    }

    @Transactional
    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody Map<String, String> formData){
        String id;
        Integer isLDAP;
        Integer sts;
        String nama;
        String userLogin;
        String userPass;
        String group;
        Long tempId;
        Integer updatedby;
        try {
            nama = formData.get("nama");
            userLogin = formData.get("userLogin");
            userPass = formData.get("userPass");
            id = formData.get("id");
            group = formData.get("group");
            isLDAP = Integer.parseInt(formData.get("isLDAP"));
            sts = Integer.parseInt(formData.get("sts"));
            updatedby = Integer.parseInt(formData.get("updatedby"));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Invalid Register Format", e));
        }

        Optional<MasterUser> optionalUser = masterUserRepository.findById(Long.parseLong(id));
        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body(createErrorMessage("User not found", null));
        }

        MasterUser existingUser = optionalUser.get();
        LocalDateTime now = LocalDateTime.now();

        try{
            existingUser.setNama(nama);
            existingUser.setUpdateddate(now);
            existingUser.setUpdatedby(updatedby);
            existingUser.setUserLogin(userLogin);
            existingUser.setUserPass(userPass);
            existingUser.setLDAP(isLDAP); 
            existingUser.setSts(sts); 
            existingUser.setCountFailedLogin(0); 
    
            MasterUser savedUser = masterUserRepository.save(existingUser);
            tempId = savedUser.getId();
            
            
            
        }catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Failed to save", e));
        }

        try {
            masterUserRepository.deleteUserGroupsByUserId(Integer.parseInt(id));

            String[] tempString = group.split(", ");

            for (String groupId : tempString) {
                masterUserRepository.insertMasterUserGroup(tempId.intValue(), Integer.parseInt(groupId.trim())); 
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Failed to update groups", e));
        }
        
        
        return ResponseEntity.ok(createSuccessMessage(id));


    }

    @PostMapping("/getdatabyid")
    public ResponseEntity<List<Map<String, Object>>> getDataByID(@RequestBody Map<String, String> updateData) {
        Integer index;
        try {
            index = Integer.parseInt(updateData.get("index"));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage2("Invalid index format", e));
        }
    
        try {
            // Ambil data dari repository
            List<Object[]> users = masterUserRepository.findUserbyId(index);
            if (users.isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorMessage2("Id not Found", null));
            }
    
            // Map hasil query ke dalam response
            Map<String, Object> map = new HashMap<>();
            List<Integer> groupIds = new ArrayList<>();
    
            for (Object[] user : users) {
                // Ambil data user yang hanya diambil sekali
                if (map.isEmpty()) {
                    map.put("id", user[0]);
                    map.put("username", user[1]);
                    map.put("nama", user[2]);
                    map.put("ldap", user[3]);
                    map.put("status", user[4]);
                    map.put("password", user[5]);
                }
    
                if (user[5] != null) {
                    groupIds.add((Integer) user[6]);
                }
            }
    
            String idGroups = groupIds.stream()
                                      .map(String::valueOf)
                                      .collect(Collectors.joining(", "));
            map.put("group", idGroups); 
    
            return ResponseEntity.ok(Collections.singletonList(map));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorMessage2("Error retrieving data", e));
        }
    }
}
