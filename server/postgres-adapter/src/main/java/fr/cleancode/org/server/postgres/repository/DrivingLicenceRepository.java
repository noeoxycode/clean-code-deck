package fr.cleancode.org.server.postgres.repository;

import fr.cleancode.org.server.postgres.entity.DrivingLicenceEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface DrivingLicenceRepository extends JpaRepository<DrivingLicenceEntity, UUID> {

  @EntityGraph(attributePaths = "offences")
  Option<DrivingLicenceEntity> findDrivingLicenceEntityById(UUID id);
}
