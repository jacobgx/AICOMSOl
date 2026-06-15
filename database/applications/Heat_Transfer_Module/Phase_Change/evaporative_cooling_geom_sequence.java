/*
 * evaporative_cooling_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:24 by COMSOL 6.3.0.290. */
public class evaporative_cooling_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Phase_Change");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone1").set("r", 3.3);
    model.component("comp1").geom("geom1").feature("cone1").set("h", 8);
    model.component("comp1").geom("geom1").feature("cone1").set("rtop", 4);
    model.component("comp1").geom("geom1").feature("cone1").set("pos", new int[]{68, 0, 0});
    model.component("comp1").geom("geom1").feature("cone1").setIndex("layer", 0.4, 0);
    model.component("comp1").geom("geom1").feature("cone1").set("layerbottom", true);
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("cone1", 8);
    model.component("comp1").geom("geom1").feature("wp1").set("offset", 0.5);
    model.component("comp1").geom("geom1").feature("wp1").set("reverse", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("cone1", 3);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("pard1", 3);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{85, 9.5, 18});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("del1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.title(null);

    model.description("");

    model.label("evaporative_cooling_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
