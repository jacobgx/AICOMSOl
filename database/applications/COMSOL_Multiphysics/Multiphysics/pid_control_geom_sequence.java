/*
 * pid_control_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:40 by COMSOL 6.3.0.290. */
public class pid_control_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Multiphysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca1").set("point1", new double[]{-0.01, -0.004});
    model.component("comp1").geom("geom1").feature("ca1").set("point2", new double[]{-0.012, -0.002});
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "-0.012 -0.014 -0.014 -0.014 -0.014 -0.012");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "-0.002 -0.002 -0.002 0 0 0");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca2").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca2").set("point1", new double[]{-0.012, 0});
    model.component("comp1").geom("geom1").feature("ca2").set("point2", new double[]{-0.01, 0.002});
    model.component("comp1").geom("geom1").feature("ca2").set("angle1", 270);
    model.component("comp1").geom("geom1").run("ca2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{-0.01, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{-0.004, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{-0.01, 0.002});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{-0.004, 0.002});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ca3", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca3").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca3").set("point1", new double[]{-0.004, 0.002});
    model.component("comp1").geom("geom1").feature("ca3").set("point2", new double[]{-0.002, 0.004});
    model.component("comp1").geom("geom1").feature("ca3").set("angle1", 270);
    model.component("comp1").geom("geom1").run("ca3");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol2").set("x", "-0.002 -0.002 -0.002 0 0 0");
    model.component("comp1").geom("geom1").feature("pol2").set("y", "0.004 0.008 0.008 0.008 0.008 0.004");
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("ca4", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca4").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca4").set("point1", new double[]{0, 0.004});
    model.component("comp1").geom("geom1").feature("ca4").set("point2", new double[]{0.002, 0.002});
    model.component("comp1").geom("geom1").feature("ca4").set("angle1", 180);
    model.component("comp1").geom("geom1").run("ca4");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol3").set("x", "0.002 0.008 0.008");
    model.component("comp1").geom("geom1").feature("pol3").set("y", "0.002 0.002 0");
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("ca5", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca5").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca5").set("point1", new double[]{0.008, 0});
    model.component("comp1").geom("geom1").feature("ca5").set("point2", new double[]{0.01, -0.002});
    model.component("comp1").geom("geom1").feature("ca5").set("angle1", 180);
    model.component("comp1").geom("geom1").run("ca5");
    model.component("comp1").geom("geom1").create("pol4", "Polygon");
    model.component("comp1").geom("geom1").feature("pol4").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol4").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol4").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol4").set("x", "0.01 0.012 0.012 0.012 0.012 0.008 -0.01");
    model.component("comp1").geom("geom1").feature("pol4")
         .set("y", "-0.002 -0.002 -0.002 -0.004 -0.004 -0.004 -0.004");
    model.component("comp1").geom("geom1").run("pol4");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input")
         .set("ca1", "ca2", "ca3", "ca4", "ca5", "ls1", "pol1", "pol2", "pol3", "pol4");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("pid_control_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
