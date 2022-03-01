package com.lecture.carrental.service;

import com.lecture.carrental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ReservationService {

    //    @Autowired
    private final ReservationRepository reservationRepository;
//    public ReservationService(ReservationRepository reservationRepository) {
//        this.reservationRepository = reservationRepository;
//    }


}