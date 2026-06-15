/*
 * acoustics_pipe_system_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:58 by COMSOL 6.3.0.290. */
public class acoustics_pipe_system_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Pipe_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Di", "35[cm]", "\u5185\u7ba1\u5f84");
    model.param().set("Lj", "4[m]", "\u63a5\u5934\u957f\u5ea6");
    model.param().set("L1", "70[m]", "\u7ba1 1 \u7684\u957f\u5ea6");
    model.param().set("L2", "15[m]", "\u7ba1 2 \u7684\u957f\u5ea6");
    model.param().set("L3", "30[m]", "\u7ba1 3 \u7684\u957f\u5ea6");
    model.param().set("L4", "25[m]", "\u7ba1 4 \u7684\u957f\u5ea6");
    model.param().set("rbend", "5 [cm]", "\u5f2f\u5934\u5185\u534a\u5f84");

    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-Lj", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"-L1+Lj/2", "0", "0"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"-L1+Lj/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"-L1", "0", "0"});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Di/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Lj");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-Lj", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "Di/3");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "Lj/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"-Lj/2", "-Lj/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new String[]{"-Lj/2", "-Lj/2", "0"});
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"-Lj/2", "-L2", "0"});
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("ls4", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls4").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls4").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls4").set("coord2", new String[]{"L3-Lj/2", "0", "0"});
    model.component("comp1").geom("geom1").run("ls4");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "Di/2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "Lj/2-rbend-Di/2");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"L3-Lj/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").selection("inputface").set("cyl3", 4);
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("rev1").set("pos", new String[]{"0", "-rbend-Di/2"});
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "Di/2");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "Lj/2-rbend-Di/2");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new String[]{"L3", "-Lj/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl4").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("ls5", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls5").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls5").set("coord1", new String[]{"L3", "-Lj/2", "0"});
    model.component("comp1").geom("geom1").feature("ls5").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls5").set("coord2", new String[]{"L3", "-L4", "0"});
    model.component("comp1").geom("geom1").run("ls5");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("acoustics_pipe_system_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
