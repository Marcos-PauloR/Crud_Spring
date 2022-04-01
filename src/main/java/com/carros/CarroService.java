package com.carros;

import com.carros.entity.Carro;
import com.carros.repository.CarrroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarrroRepository carrroRepository;

    public Carro find(Integer id){
        Optional<Carro> obj = carrroRepository.findById(id);
        return obj.orElse(null);
    }

    public Carro update(Carro obj){
        Carro newCarro = find(obj.getId());
        updateDatas(newCarro, obj);
        return carrroRepository.save(newCarro);
    }

    private void updateDatas(Carro novo, Carro obj){
        novo.setAno(obj.getAno());
        novo.setMarca(obj.getMarca());
        novo.setModelo(obj.getModelo());
    }

}
