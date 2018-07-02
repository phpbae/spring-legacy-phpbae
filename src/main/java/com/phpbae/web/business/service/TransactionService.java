package com.phpbae.web.business.service;

import com.phpbae.web.business.domain.Owner;
import com.phpbae.web.dao.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner getTestData() {
        //init data
        Owner owner = new Owner();
        owner.setOwnerName("TestTest!!");
        owner.setPet(null);
        ownerRepository.save(owner); //트랜잭션이 Read-only 이기 때문에, insert 시, 롤백.
        return ownerRepository.findOne(1);
    }

    public Owner transactionTest(){
        Owner owner = new Owner();
        owner.setOwnerName("TestTest!!asdasd");
        owner.setPet(null);
        ownerRepository.save(owner);


        //exception
        int result = 100 / 0;

        return ownerRepository.findOne(1);
    }
}
