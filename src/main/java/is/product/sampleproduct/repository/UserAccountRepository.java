package is.product.sampleproduct.repository;

import is.product.sampleproduct.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author "Noverry Ambo"
 * @start 10/24/2023
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {


    Optional<UserAccount> findByEmail(String email);
}
