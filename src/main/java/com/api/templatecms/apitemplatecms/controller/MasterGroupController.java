package com.api.templatecms.apitemplatecms.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.api.templatecms.apitemplatecms.model.MasterGroup;
import com.api.templatecms.apitemplatecms.repository.MasterGroupRepository;

@RestController
@RequestMapping("/groups")
public class MasterGroupController {
    @Autowired
    private MasterGroupRepository masterGroupRepository;

    @GetMapping("/getallgroup")
    public List<Map<String, Object>> getAllGroups() {
        List<Object[]> groups = masterGroupRepository.findAllGroups();
        return groups.stream()
                .map(group -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("nama", group[1]);
                    map.put("id", group[0]);
                    return map;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/getallGroupMenu")
    public List<Map<String, Object>> getAllGroupMenu() {
        List<Object[]> groups = masterGroupRepository.findGroupMenu();
        return groups.stream()
                .map(group -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("nama", group[1]);
                    map.put("id", group[0]);
                    return map;
                })
                .collect(Collectors.toList());
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
        String nama;
        String group;
        Integer sts;
        Integer tempId;
        try {
            nama = formData.get("nama");
            group = formData.get("group");
            sts = Integer.parseInt(formData.get("sts"));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Invalid Register Format", e));
        }
        LocalDateTime now = LocalDateTime.now();
        MasterGroup newUser = new MasterGroup();
        try{
            newUser.setNama(nama);
            newUser.setUpdateby(0);
            newUser.setCreatedby(0);
            newUser.setUpdatedate(now);
            newUser.setCreateddate(now);
            newUser.setSts(sts);
    
            MasterGroup savedUser = masterGroupRepository.save(newUser);
            tempId = savedUser.getId();
            
            
        }catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Failed to save", e));
        }
        try {
            String[] tempString = group.split(", ");

            String tempStringId = Long.toString(tempId);
            Integer tempInt = Integer.parseInt(tempStringId);
            for (int i = 0; i < tempString.length; i++) {
                masterGroupRepository.insertMasterGroup(tempInt, Integer.parseInt(tempString[i]));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Failed to save", e));

        }
        
        
        return ResponseEntity.ok(createSuccessMessage("User updated successfully"));


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
            List<Object[]> users = masterGroupRepository.findGroupbyId(index);
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
                    map.put("nama", user[1]);
                }
    
                if (user[2] != null) {
                    groupIds.add((Integer) user[2]);
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

    @Transactional
    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody Map<String, String> formData){
        String id;
        String nama;
        String group;
        Integer tempId;
        try {
            nama = formData.get("nama");
            id = formData.get("id");
            group = formData.get("group");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Invalid Register Format", e));
        }

        Optional<MasterGroup> optionalUser = masterGroupRepository.findById(Long.parseLong(id));
        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body(createErrorMessage("User not found", null));
        }

        MasterGroup existingUser = optionalUser.get();
        LocalDateTime now = LocalDateTime.now();

        try{
            existingUser.setNama(nama);
            existingUser.setUpdateby(0);
            existingUser.setCreatedby(0);
            existingUser.setUpdatedate(now);
            existingUser.setCreateddate(now);
            existingUser.setSts(0);
    
            MasterGroup savedUser = masterGroupRepository.save(existingUser);
            tempId = savedUser.getId();
            
            
            
        }catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Failed to save", e));
        }

        try {
            masterGroupRepository.deleteGroupMenuByUserId(Integer.parseInt(id));

            String[] tempString = group.split(", ");

            for (String groupId : tempString) {
                masterGroupRepository.insertMasterGroup(tempId.intValue(), Integer.parseInt(groupId.trim())); 
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Failed to update groups", e));
        }
        
        
        return ResponseEntity.ok(createSuccessMessage(id));


    }

    @Transactional
    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> delete(@RequestBody Map<String, String> formData) {
        String idStr = formData.get("id");
        Integer id;

        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Invalid Register Format", e));
        }

        try {
            masterGroupRepository.deleteGroupMenuByUserId(id);
            
            masterGroupRepository.deleteMasterGroup(id);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body(createErrorMessage("Failed to delete groups", e));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorMessage("An unexpected error occurred", e));
        }

        return ResponseEntity.ok(createSuccessMessage(idStr));
    }
}
