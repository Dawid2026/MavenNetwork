
package com.mycompany.mavennetwork.optimizers;
import com.mycompany.mavennetwork.layers.*;
import java.util.ArrayList;
import java.util.List;
/**
 *  Interface used for updating gradiends in layers.
 * 
 * @author Dawid
 */
public interface Optimizer {
    void update(List<Layer> layers);
}
