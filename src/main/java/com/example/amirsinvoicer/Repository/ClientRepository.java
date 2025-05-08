package com.example.amirsinvoicer.Repository;

import com.example.amirsinvoicer.Model.Client;
import com.example.amirsinvoicer.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findAllByUser(User user);

}

