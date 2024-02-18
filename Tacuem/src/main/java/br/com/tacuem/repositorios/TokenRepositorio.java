package br.com.tacuem.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tacuem.model.Token;
import java.util.Optional;

public interface TokenRepositorio extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    void deleteByUsuarioId(Long id);
    
}
