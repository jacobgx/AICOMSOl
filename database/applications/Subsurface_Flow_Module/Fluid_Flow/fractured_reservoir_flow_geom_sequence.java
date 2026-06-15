/*
 * fractured_reservoir_flow_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:28 by COMSOL 6.3.0.290. */
public class fractured_reservoir_flow_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1")
         .set("filename", "fractured_reservoir_flow_geom_sequence_interpolation.txt");
    model.component("comp1").func("int1").importData();

    model.component("comp1").geom("geom1").create("ps1", "ParametricSurface");
    model.component("comp1").geom("geom1").feature("ps1").set("parmax1", 10);
    model.component("comp1").geom("geom1").feature("ps1").set("parmax2", 10);
    model.component("comp1").geom("geom1").feature("ps1").set("coord", new String[]{"100*s1", "100*s2", ""});
    model.component("comp1").geom("geom1").feature("ps1").setIndex("coord", "25*int(s1,s2)", 2);
    model.component("comp1").geom("geom1").feature("ps1").set("rtol", "1.0E-2");
    model.component("comp1").geom("geom1").feature("ps1").set("maxknots", 200);
    model.component("comp1").geom("geom1").feature("ps1").setIndex("coord", "25*int1(s1,s2)", 2);
    model.component("comp1").geom("geom1").run("ps1");
    model.component("comp1").geom("geom1").feature().duplicate("ps2", "ps1");
    model.component("comp1").geom("geom1").feature("ps2").setIndex("coord", "25*int1(s1+20,s2+20)", 2);
    model.component("comp1").geom("geom1").feature("ps2").set("pos", new int[]{0, 0, -250});
    model.component("comp1").geom("geom1").feature().duplicate("ps3", "ps2");
    model.component("comp1").geom("geom1").feature("ps3").setIndex("coord", "25*int1(s1+50,s2+30)", 2);
    model.component("comp1").geom("geom1").feature("ps3").set("pos", new int[]{0, 0, 40});
    model.component("comp1").geom("geom1").run("ps3");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{1000, 1000, 500});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{0, 0, -300});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("blk1", 1);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "faces");
    model.component("comp1").geom("geom1").feature("pard1").selection("face").set("ps1", 1);
    model.component("comp1").geom("geom1").feature("pard1").selection("face").set("ps2", 1);
    model.component("comp1").geom("geom1").feature("pard1").selection("face").set("ps3", 1);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("pard1", 4);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u9876\u90e8");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("del1", 3);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("del1", 2);
    model.component("comp1").geom("geom1").feature("sel2").label("\u4e2d\u95f4");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5e95\u90e8");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("del1", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("fractured_reservoir_flow_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
