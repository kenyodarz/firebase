package com.bykenyodarz.firebase.services.providers;

import com.bykenyodarz.firebase.models.Persona;
import com.bykenyodarz.firebase.models.dto.PersonaDTO;
import com.bykenyodarz.firebase.services.apis.PersonaServiceAPI;
import com.bykenyodarz.firebase.shared.GenericServiceImpl;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, PersonaDTO> implements PersonaServiceAPI {

    private final Firestore firestore;

    public PersonaServiceImpl(Firestore firestore) {
        this.firestore = firestore;
    }


    @Override
    public CollectionReference getCollection() {
        return firestore.collection("personas");
    }
}
