package com.cashmanagement.vitalyevich.server.service;

import com.cashmanagement.vitalyevich.server.firebase.model.WorkTime;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FBService {

    public static final String COL_NAME="work_time";
    public WorkTime getWork(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        WorkTime workTime = null;

        if(document.exists()) {
            workTime = document.toObject(WorkTime.class);
            return workTime;
        }else {
            return null;
        }
    }

    public String saveWork(WorkTime workTime) throws InterruptedException, ExecutionException {
        workTime.setDateTime(Timestamp.now());
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(workTime.getLastName() + " " + workTime.getDateTime()).set(workTime);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateWork(WorkTime workTime) throws InterruptedException, ExecutionException {
        workTime.setDateTime(Timestamp.now());
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(workTime.getLastName() + " " + workTime.getDateTime()).set(workTime);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteWork(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with WorkTime ID "+name+" has been deleted";
    }

    public List<WorkTime> getWorks() throws InterruptedException, ExecutionException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(COL_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<WorkTime> workTimeList = new ArrayList<>();
        WorkTime workTime = null;

        while (iterator.hasNext()) {

            DocumentReference documentReference1=iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot documentSnapshot = future.get();

            workTime = documentSnapshot.toObject(WorkTime.class);
            workTimeList.add(workTime);
        }
            return workTimeList;
    }
}
