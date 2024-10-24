package com.api.templatecms.apitemplatecms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.templatecms.apitemplatecms.model.MasterUser;

import jakarta.transaction.Transactional;

@Repository
public interface MasterUserRepository extends JpaRepository<MasterUser, Long> {
    @Query("SELECT u.id, u.userLogin, u.nama, u.isLDAP, u.sts as status FROM MasterUser u")
    List<Object[]> findAllProjectedBy();

    @Query(value = "SELECT u.id, u.userLogin, u.nama, u.isLDAP, u.sts as status, u.userPass, " +
               "g.idGroup " +  
               "FROM Master_Users u " +
               "LEFT JOIN Master_UserGroup g ON u.id = g.idUser " +
               "WHERE u.id = :index", 
       nativeQuery = true)
    List<Object[]> findUserbyId(@Param("index") int index);

    @Modifying 
    @Query("UPDATE MasterUser u SET u.sts = :newStatus WHERE u.id IN (SELECT id FROM MasterUser ORDER BY id LIMIT 1 OFFSET :index)")
    int updateStatusByIndex(@Param("newStatus") String newStatus, @Param("index") int index);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Master_UserGroup (idUser, idGroup) VALUES (:iuser, :igroup)", nativeQuery = true)
    int insertMasterUserGroup(@Param("iuser") Integer iuser, @Param("igroup") Integer igroup);

    @Modifying
    @Query(value = "DELETE FROM Master_UserGroup WHERE idUser = :userId", nativeQuery = true)
    public void deleteUserGroupsByUserId(@Param("userId") Integer userId);

    @Query("SELECT u.userPass, u.sts FROM MasterUser u WHERE u.userLogin = :username")
    Optional<List<Object[]>> findUserNameLogin(@Param("username") String username);

}
