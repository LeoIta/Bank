package com.finalproject.BankApplication.reposiitory;
/*
import com.finalproject.BankApplication.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Integer> {
    public Assessment findFirstByOrderByIdDesc();
    public Assessment findAssessmentByTrackingNumber(String TrackingNumber);

    @Modifying
    @Query("UPDATE Assessment a SET a.status = :status WHERE a.id = :id")
    int updateStatus(@Param("id") int id, @Param("status") String status);

    @Modifying
    @Query("UPDATE Assessment a SET a.type = :type WHERE a.id = :id")
    int updateType(@Param("id") int id, @Param("type") String type);

    @Modifying
    @Query("UPDATE Assessment a SET a.tracking = :trackingNumber WHERE a.id = :id")
    int updateTrackingNumber(@Param("id") int id, @Param("tracking") String trackingNumber);

}
*/