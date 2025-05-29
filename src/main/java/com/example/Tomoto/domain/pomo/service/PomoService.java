package com.example.Tomoto.domain.pomo.service;

import com.example.Tomoto.domain.pomo.entiry.Pomo;
import com.example.Tomoto.domain.pomo.repository.PomoRepository;
import com.example.Tomoto.domain.user.entiry.User;
import com.example.Tomoto.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PomoService {
    private final PomoRepository pomoRepository;
    private final UserRepository userRepository;

    public void addTodayPomo(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();  // 오늘 00:00
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        Optional<Pomo> pomo = pomoRepository.findByUserAndCreatedAtBetween(user, startOfDay, endOfDay);
        if(pomo.isPresent()){
            Pomo todayPomo = pomo.get();
            todayPomo.setPomo_num(todayPomo.getPomo_num() + 1);
            user.setTotalPomo(user.getTotalPomo() + 1);
            userRepository.save(user);
            pomoRepository.save(todayPomo);
        }else{
            Pomo newPomo = Pomo.create(user);
            pomoRepository.save(newPomo);
        }
    }

    public int getTodayPomo(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();  // 오늘 00:00
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        Optional<Pomo> pomo = pomoRepository.findByUserAndCreatedAtBetween(user, startOfDay, endOfDay);
//        if(pomo.isPresent()){
//            return pomo.get().getPomo_num();
//        }else{
//            return 0;
//        }
        return pomo.map(Pomo::getPomo_num).orElse(0);

    }
}
