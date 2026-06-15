/*
 * vacuum_flask_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:35 by COMSOL 6.3.0.290. */
public class vacuum_flask_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Forced_and_Natural_Convection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("height", "380[mm]");
    model.param().descr("height", "\u4fdd\u6e29\u74f6\u9ad8\u5ea6");
    model.param().set("radius", "40[mm]");
    model.param().descr("radius", "\u74f6\u9888\u534a\u5f84");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"1.04*radius", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "0.96*height", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0*radius", "0*height"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 1.5*radius 1.5*radius 1.5*radius");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "0 0 0 0.68*height");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "1.5*radius", 0, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "1.5*radius", 0, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "1.04*radius", 0, 2);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "0.68*height", 1, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "0.751*height", 1, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "0.80*height", 1, 2);
    model.component("comp1").geom("geom1").run("qb1");
    model.component("comp1").geom("geom1").create("qb2", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "1.04*radius", 0, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "0.88*radius", 0, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "0.66*radius", 0, 2);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "0.80*height", 1, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "0.82*height", 1, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", "0.84*height", 1, 2);
    model.component("comp1").geom("geom1").run("qb2");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol2")
         .set("x", "0.66*radius 0.66*radius 0.66*radius 0.3*radius 0.3*radius 0.3*radius 0.3*radius 0.3*radius");
    model.component("comp1").geom("geom1").feature("pol2")
         .set("y", "0.84*height 0.96*height 0.96*height 0.96*height 0.96*height 0.83*height 0.83*height 0.79*height");
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("qb3", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", "0.3*radius", 0, 0);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", "0.56*radius", 0, 1);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", "0.73*radius", 0, 2);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", "0.79*height", 1, 0);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", "0.78*height", 1, 1);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", "0.75*height", 1, 2);
    model.component("comp1").geom("geom1").run("qb3");
    model.component("comp1").geom("geom1").create("qb4", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", "0.73*radius", 0, 0);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", "0.93*radius", 0, 1);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", "0.93*radius", 0, 2);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", "0.75*height", 1, 0);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", "0.72*height", 1, 1);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", "0.68*height", 1, 2);
    model.component("comp1").geom("geom1").run("qb4");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"0.93*radius", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"0.93*radius", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", "0.68*height", 1);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", "0.12*height", 1);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("qb5", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", "0.93*radius", 0, 0);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", "0.93*radius", 0, 1);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", "0*radius", 0, 2);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", "0.12*height", 1, 0);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", "0.036*height", 1, 1);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", "0.036*height", 1, 2);
    model.component("comp1").geom("geom1").run("qb5");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("pol1", 1);
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex2").set("qb5", 2);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input")
         .set("ls1", "ls2", "pol1", "pol2", "qb1", "qb2", "qb3", "qb4", "qb5");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("csol2", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol2").selection("input").set("csol1", "r1");
    model.component("comp1").geom("geom1").run("csol2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new String[]{"0*radius", "0.83*height"});
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"0.3*radius", "0.83*height"});
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("ls4", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls4").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls4").set("coord1", new String[]{"0*radius", "0.75*height"});
    model.component("comp1").geom("geom1").feature("ls4").selection("vertex2").set("csol2", 9);
    model.component("comp1").geom("geom1").run("ls4");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol3")
         .set("x", "0*radius 1.04*radius 1.04*radius 0*radius 0*radius");
    model.component("comp1").geom("geom1").feature("pol3")
         .set("y", "0.96*height 0.96*height 0.99*height 0.99*height 0.96*height");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 9, 17);
    model.component("comp1").geom("geom1").run("ige1");

    model.title(null);

    model.description("");

    model.label("vacuum_flask_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
