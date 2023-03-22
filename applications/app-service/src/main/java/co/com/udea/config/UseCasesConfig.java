package co.com.udea.config;

import co.com.udea.mongo.contacts.ContactsDataAdapter;
import co.com.udea.mongo.organizations.OrganizationsDataAdapter;
import co.com.udea.mongo.register.RegisterDataAdapter;
import co.com.udea.mongo.users.UsersDataAdapter;
import co.com.udea.usecase.ContactsUseCase;
import co.com.udea.usecase.OrganizationsUseCase;
import co.com.udea.usecase.RegisterUseCase;
import co.com.udea.usecase.UsersUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public ContactsUseCase createContactsUseCase(ContactsDataAdapter dataAdapter) {
        return new ContactsUseCase(dataAdapter);
    }

    @Bean
    public UsersUseCase createUsersUseCase(UsersDataAdapter dataAdapter) {
        return new UsersUseCase(dataAdapter);
    }
    @Bean
    public RegisterUseCase createRegisterUseCase(RegisterDataAdapter dataAdapter) {
        return new RegisterUseCase(dataAdapter);
    }
    @Bean
    public OrganizationsUseCase createOrganizationsUseCase(OrganizationsDataAdapter dataAdapter) {
        return new OrganizationsUseCase(dataAdapter);
    }

}
