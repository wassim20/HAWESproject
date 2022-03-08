/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pidevv.service;

import java.util.List;

/**
 *
 * @author DELL
 */
public interface IService<T> {

    public void add(T t);
    public List<T> getAll();
    public void update(T t);
    public void delete(T t);

}
