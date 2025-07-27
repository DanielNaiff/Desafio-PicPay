package com.danielnaiff.backend.service;

import com.danielnaiff.backend.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;

    public void validateTransfer(){

    }
}
