package com.revature.daos;

import com.revature.models.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnomalyDAO extends JpaRepository<Anomaly, Integer> {

    List<Anomaly> findByAnomalyType(String anomalyType);
    List<Anomaly> findBySessionSessionId(Integer sessionId);
}
