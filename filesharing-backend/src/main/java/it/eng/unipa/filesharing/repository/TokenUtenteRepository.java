package it.eng.unipa.filesharing.repository;

import it.eng.unipa.filesharing.model.TokenUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenUtenteRepository extends JpaRepository<TokenUtente, String>{

    @Query("select tu from TokenUtente tu where tu.token=:token")
    Optional<TokenUtente> utentiRichiestaNotifiche(@Param("token")String token);
}