package com.api.templatecms.apitemplatecms.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterUsersRepository extends JpaRepository<MasterUsersEntity,Long> {
}
