package br.com.camping.service;

import br.com.camping.request.CampingRequest;
import br.com.camping.enums.State;
import br.com.camping.model.Camping;
import br.com.camping.repository.CampingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CampingService {

    private final CampingRepository campingRepository;

    private final UserService userService;

    public List<Camping> findAll() {

        return campingRepository.findAll();

    }

    public List<Camping> findAllByState(State state) {

        return campingRepository.findAllByState(state);

    }

    public List<Camping> findAllByCity(String city) {

        return campingRepository.findAllByCity(city);

    }

    public List<Camping> findAllByStateAndCity(State state, String city) {

        return campingRepository.findAllByStateAndCity(state, city);

    }

    public Camping findById(Long id) {

        return campingRepository.findById(id).orElse(null);

    }

    public Camping save(Camping camping) {

        return campingRepository.save(camping);

    }

    public Camping saveCampRequest(CampingRequest campingRequest) {

        Camping camping = new Camping(campingRequest);
        camping.setUser(userService.findById(campingRequest.getUserId()));
        return campingRepository.save(camping);

    }

    public Camping editCamp(CampingRequest campingRequest, Long id) {

        Camping camping = new Camping(campingRequest);
        camping.setId(id);
        camping.setUser(userService.findById(campingRequest.getUserId()));

        return this.save(camping);

    }

    public void delete(Long id) {

        campingRepository.deleteById(id);

    }

}
