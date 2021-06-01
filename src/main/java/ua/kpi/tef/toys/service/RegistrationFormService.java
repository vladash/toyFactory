package ua.kpi.tef.toys.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.tef.toys.pojo.User;
import ua.kpi.tef.toys.repository.UserRepository;


@Slf4j
@Service
public class RegistrationFormService {
    private final UserRepository userRepository;

    @Autowired
    public RegistrationFormService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveNewUser (User user){

        try {
            userRepository.save(user);
        } catch (Exception ex){
            log.info("{Почтовый адрес уже существует}");
        }

    }
}
