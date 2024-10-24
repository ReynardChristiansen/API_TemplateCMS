package com.api.templatecms.apitemplatecms.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.api.templatecms.apitemplatecms.model.MasterUser_Group;

@Repository
public interface MasterUsers_GroupRepository extends JpaRepository<MasterUser_Group, Long> {

        @Query("SELECT u.idGroup FROM MasterUser_Group u WHERE u.idUser = :index")
        List<Object[]> findUserGroupsByIdUser(@Param("index") Long index);
    // @Query("SELECT * FROM Master_UserGroup g")
    // List<Object[]> findAllGroups();
}

