import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.sjsu.cmpe282.model.Customer;

@Repository
public class AccountRepository{

    // Cassandra operations
    @Autowired
	private CassandraOperations operations;

    public Account createAccount(Account a) throws Exception{
        // validation
        if(authenticateAccount(a)){
            throws Exception("Account already exists");
        }
        Account createdAccount = operations.insert(a);
        createAccount.setPassword("");
        return createAccount;
    }


	
    
    public boolean authenticateAccount(Account a){
        boolean isAuthenticated = false;
        String email = customer.getEmail();
        String password = customer.getPassword();
        
        String cql = "select * from account where email = '" + email + "' allow filtering";

        Account dbAccount = operations.selectOne(cql,Account.class);
        if(dbAccount == null){
            // no record found
        }

        if(dbAccount != null && password.equals(dbAccount.getPassword())){
            isAuthenticated = true;
        } else {
            // false 
        }
        return isAuthenticated;
    }

}