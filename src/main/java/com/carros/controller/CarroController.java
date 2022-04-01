package com.carros.controller;


import com.carros.CarroService;
import com.carros.entity.Carro;
import com.carros.repository.CarrroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarrroRepository repository;

    @Autowired
    private CarroService service;

    @RequestMapping(value = "/inserir/", method = RequestMethod.POST)
    public ResponseEntity<Void> insert( @RequestBody Carro carro){
        Carro obj = repository.save(carro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update( @RequestBody Carro carro, @PathVariable Integer id){
        Carro carrin = carro;
        carrin.setId(id);
        carrin = service.update(carrin);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Carro>> find(@PathVariable Integer id){
        Optional<Carro> carro = repository.findById(id);
        return ResponseEntity.ok().body(carro);
    }

    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/listarCarros", method = RequestMethod.GET)
    public ResponseEntity<List<Carro>> findAll(){
        List<Carro> carros = repository.findAll();
        return ResponseEntity.ok().body(carros);
    }

    @RequestMapping(value = "/buscaPorNome/{nome}", method = RequestMethod.GET)
    public ResponseEntity<Carro> findByMarca(@PathVariable String nome){
        Carro carro = repository.findByMarca(nome);
        return ResponseEntity.ok().body(carro);
    }




}
