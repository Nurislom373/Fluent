package org.khasanof.repository;

import org.khasanof.entity.FltUserHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.repository
 * @since 3/16/2024 7:58 PM
 */
@Repository
public interface FltUserRepository extends CrudRepository<FltUserHash, Long> {

    Optional<FltUserHash> findFirstByUserId(Long userId);
}
