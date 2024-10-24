package com.api.templatecms.apitemplatecms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.api.templatecms.apitemplatecms.model.MasterGroup;

import jakarta.transaction.Transactional;

@Repository
public interface MasterGroupRepository extends JpaRepository<MasterGroup, Long> {

    @Query("SELECT g.id, g.nama FROM MasterGroup g")
    List<Object[]> findAllGroups();

    @Modifying
    @Query(value = "SELECT u.id, u.nama FROM Master_Menu u", nativeQuery = true)
    List<Object[]> findGroupMenu();

    @Query(value = "SELECT u.id, u.nama, " +
        "g.idMenu " +  
        "FROM Master_Group u " +
        "LEFT JOIN Master_GroupMenu g ON u.id = g.idGroup " +
        "WHERE u.id = :index", 
    nativeQuery = true)
    List<Object[]> findGroupbyId(@Param("index") int index);

    @Modifying
    @Query(value = "DELETE FROM Master_GroupMenu WHERE idGroup = :groupId", nativeQuery = true)
    public void deleteGroupMenuByUserId(@Param("groupId") Integer groupId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Master_GroupMenu (idGroup, idMenu) VALUES (:igroup, :imenu)", nativeQuery = true)
    int insertMasterGroup(@Param("igroup") Integer igroup, @Param("imenu") Integer imenu);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Master_Group WHERE id = :igroup", nativeQuery = true)
    int deleteMasterGroup(@Param("igroup") Integer igroup);
    

}
