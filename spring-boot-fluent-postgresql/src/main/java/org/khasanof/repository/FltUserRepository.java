package org.khasanof.repository;

import org.khasanof.entity.FltUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.repository
 * @since 3/2/2024 12:57 AM
 */
@Repository
public interface FltUserRepository extends JpaRepository<FltUserEntity, Long> {

    boolean existsByUserId(Long userId);

    Optional<FltUserEntity> findFirstByUserId(Long userId);
}
