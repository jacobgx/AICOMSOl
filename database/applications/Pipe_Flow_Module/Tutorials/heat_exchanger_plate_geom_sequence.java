/*
 * heat_exchanger_plate_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:00 by COMSOL 6.3.0.290. */
public class heat_exchanger_plate_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("Lp", "190[mm]");
    model.param().descr("Lp", "\u7ba1\u957f");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 0.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("coord1", new double[]{0, 0.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"Lp", "0.5"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 5, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{0, 20, 0});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr1").set("selresultshow", "edg");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{2, 1, 1});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{-2, 0, 0});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"Lp", "0", "0"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("blk1", "blk2");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{1, 5, 1});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new int[]{0, 20, 0});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new int[]{2, 85, 1});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new int[]{-4, 0, 0});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").feature().duplicate("blk4", "blk3");
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new String[]{"Lp+2", "-4", "0"});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd1").selection("input")
         .set("fin", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.component("comp1").geom("geom1").run("cmd1");

    model.title(null);

    model.description("");

    model.label("heat_exchanger_plate_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
