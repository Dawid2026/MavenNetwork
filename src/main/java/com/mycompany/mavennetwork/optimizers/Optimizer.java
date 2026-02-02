/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavennetwork.optimizers;
import com.mycompany.mavennetwork.layers.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Dawid
 */
public interface Optimizer {
    void update(List<Layer> layers);
}
