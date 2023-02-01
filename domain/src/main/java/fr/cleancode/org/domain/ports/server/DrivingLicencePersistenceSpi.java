package fr.cleancode.org.domain.ports.server;

import fr.cleancode.org.domain.functional.model.DrivingLicence;

import java.util.UUID;

public interface DrivingLicencePersistenceSpi extends PersistenceSpi<DrivingLicence, UUID> {}
