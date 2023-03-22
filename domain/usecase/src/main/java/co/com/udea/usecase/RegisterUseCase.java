package co.com.udea.usecase;

import co.com.udea.model.register.gateways.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RegisterUseCase {

    private final RegisterRepository registerRepository;
}
