package com.phpbae.web.business.service;

import com.phpbae.web.business.domain.Owner;
import com.phpbae.web.business.domain.Pet;
import com.phpbae.web.dao.SampleDao;
import com.phpbae.web.dao.repository.OwnerRepository;
import com.phpbae.web.dao.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class SampleService {

    @Autowired
    private SampleDao sampleDao;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    public int getSampleData() {
        return sampleDao.exampleJDBC();
    }

    public Owner getJpaDataOwner() {
        //init data
        Owner owner = new Owner();
        owner.setOwnerName("전설의죽음");
        owner.setPet(null);
        ownerRepository.save(owner);
        return ownerRepository.findOne(1);
    }

    public Pet getJpaDataPet() {
        //init data
        Owner owner = ownerRepository.findOne(1);
        if (owner != null) {
            Random random = new Random();
            Pet pet = new Pet();
            pet.setOwner(owner);
            pet.setPetName("미친웰시코기" + random.nextInt(10));
            pet.setBirthDate(LocalDate.now());
            petRepository.save(pet);
        }

        return petRepository.findOne(1);
    }

}
