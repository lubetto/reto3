package com.r3.reto3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return  categoryRepository.getCategory(id);
    }

    public Category save(Category p){
        if(p.getId() == null){
            return categoryRepository.save(p);
        }else{
            Optional<Category> categoryOptional = categoryRepository.getCategory(p.getId());
            if(categoryOptional.isEmpty()){
                return categoryRepository.save(p);
            }else{
                return p;
            }
        }
    }

    public Category update(Category p){
        if(p.getId()!=null){
            Optional<Category>g=categoryRepository.getCategory(p.getId());
            if(!g.isEmpty()){
                if(p.getDescription()!=null){
                    g.get().setDescription(p.getDescription());
                }
                if(p.getName()!=null){
                    g.get().setName(p.getName());
                }
                return categoryRepository.save(g.get());
            }
        }
        return p;
    }
    public boolean deleteCategory(int Id){
        Boolean d=getCategory(Id).map(p -> {
            categoryRepository.delete(p);
            return true;
        }).orElse(false);
        return d;
    }
}
