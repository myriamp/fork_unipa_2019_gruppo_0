package it.eng.unipa.filesharing.repository;

import it.eng.unipa.filesharing.model.TokenUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenUtenteRepository extends JpaRepository<TokenUtente, String>{
    @Query("select tu from TokenUtente tu ")
    List<TokenUtente> utentiRichiestaNotifiche(@Param("email")String email);
}