/*
 * meshing_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:35 by COMSOL 6.3.0.290. */
public class meshing_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Meshing_Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").insertFile("meshing_sequence_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 7);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection()
         .set(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").active(false);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(7, 12);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 7);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("size").set("custom", false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").active(true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature().remove("size1");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.title("\u4f7f\u7528\u7f51\u683c\u5212\u5206\u5e8f\u5217");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u7f51\u683c\u5212\u5206\u5e8f\u5217\u6765\u521b\u5efa\u5305\u542b\u4e0d\u540c\u5355\u5143\u7c7b\u578b\u7684\u7f51\u683c\u3002\u60a8\u53ef\u4ee5\u5b66\u4e60\u5982\u4f55\u6dfb\u52a0\u3001\u79fb\u52a8\u3001\u7981\u7528\u548c\u5220\u9664\u7f51\u683c\u64cd\u4f5c\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u7f51\u683c\u5212\u5206\u5e8f\u5217\u4e2d\u7684\u5927\u5c0f\u7279\u5f81\u63a7\u5236\u7f51\u683c\u3002");

    model.mesh().clearMeshes();

    model.label("meshing_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
