package com.api.templatecms.apitemplatecms.repository.cms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionOneTimeTokenRepository extends JpaRepository<TransactionOneTimeTokenEntity,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM Transaction_OneTimeToken WITH(NOLOCK) WHERE oneTimeToken=:oneTimeToken AND sts=1 and GETDATE()<expiredDate")
    Optional<TransactionOneTimeTokenEntity> findOneTimeToken(@Param("oneTimeToken") String oneTimeToken);
}
