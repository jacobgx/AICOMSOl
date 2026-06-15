/*
 * sedimentation_ptmm_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class sedimentation_ptmm_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Multiphase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics().create("phtr", "PhaseTransportFree", "geom1");

    model.component("comp1").multiphysics().create("mfmm1", "MultiphaseFlowMixtureModel", 2);
    model.component("comp1").multiphysics("mfmm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfmm1").set("fluidflow_physics", "spf");
    model.component("comp1").multiphysics("mfmm1").selection().all();

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 12, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 12, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -3.3, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -4, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.5, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -7, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.5, 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -7.4, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -7.4, 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 7, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol2").set("x", "0 0.4 0.4 0.4");
    model.component("comp1").geom("geom1").feature("pol2").set("y", "-5.4 -5.4 -5.4 -3.4");
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca1").set("point1", new double[]{1.6, -2.2});
    model.component("comp1").geom("geom1").feature("ca1").set("point2", new double[]{0.4, -3.4});
    model.component("comp1").geom("geom1").feature("ca1").set("angle1", 90);
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{1.6, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{1.6, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{1.6, -2.2});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{1.6, -2});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca2").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca2").set("point1", new double[]{1.6, -2});
    model.component("comp1").geom("geom1").feature("ca2").set("point2", new double[]{0.2, -3.4});
    model.component("comp1").geom("geom1").feature("ca2").set("angle1", 90);
    model.component("comp1").geom("geom1").run("ca2");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol3").set("x", "0.2 0.2 0.2 0 0 0");
    model.component("comp1").geom("geom1").feature("pol3").set("y", "-3.4 -5.2 -5.2 -5.2 -5.2 -5.4");
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input")
         .set("ca1", "ca2", "ls1", "pol2", "pol3");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.05);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{0, -3.4});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.4, 0.4});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{11.2, -0.4});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1", "csol1", "r1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{1, 0.5});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{11.6, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("r2", 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 0.5);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 0.1);
    model.component("comp1").geom("geom1").feature("c2").set("pos", new double[]{12.1, 0});
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1", "fil1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("c2");
    model.component("comp1").geom("geom1").feature("dif2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("pol4", "Polygon");
    model.component("comp1").geom("geom1").feature("pol4").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 0.19, 0, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", -3.22, 0, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 0.2, 1, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", -2.9, 1, 1);
    model.component("comp1").geom("geom1").run("pol4");
    model.component("comp1").geom("geom1").create("pol5", "Polygon");
    model.component("comp1").geom("geom1").feature("pol5").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 0.2, 0, 0);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", -2.9, 0, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 0.35, 1, 0);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", -0.25, 1, 1);
    model.component("comp1").geom("geom1").run("pol5");
    model.component("comp1").geom("geom1").create("pol6", "Polygon");
    model.component("comp1").geom("geom1").feature("pol6").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 0.2, 0, 0);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", -0.1, 0, 1);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 7.6, 1, 0);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", -0.6, 1, 1);
    model.component("comp1").geom("geom1").run("pol6");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("sedimentation_ptmm_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
