/*
 * lumped_receiver_04cc_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class lumped_receiver_04cc_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "0.5[mm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "49[mm]");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "4.72[mm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "5.7[mm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "49[mm]"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "3.175[mm]");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "0.5[mm]");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "0", "54.2[mm]"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u6cd5\u5411\u52a0\u901f\u5ea6");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 10);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u963b\u6297");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 7);
    model.component("comp1").geom("geom1").run("sel2");

    model.title(null);

    model.description("");

    model.label("lumped_receiver_04cc_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
