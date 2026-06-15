/*
 * ethanol_water_evaporation_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:28 by COMSOL 6.3.0.290. */
public class ethanol_water_evaporation_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Thermodynamics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("Hvdom", "0.5[m]");
    model.param().descr("Hvdom", "\u84b8\u6c7d\u57df\u7684\u9ad8\u5ea6");
    model.param().set("Wvdom", "0.5[m]");
    model.param().descr("Wvdom", "\u84b8\u6c7d\u57df\u7684\u5bbd\u5ea6");
    model.param().set("Ttable", "2[cm]");
    model.param().descr("Ttable", "\u684c\u5b50\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Wvdom", "Hvdom"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.008, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.08, 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.042, 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.145, 1);
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter("r1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pt1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("pt2");
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").create("pt3", "Point");
    model.component("comp1").geom("geom1").feature("pt3").setIndex("p", 0.034, 0);
    model.component("comp1").geom("geom1").feature("pt3").setIndex("p", 0.2, 1);
    model.component("comp1").geom("geom1").run("pt3");
    model.component("comp1").geom("geom1").create("pt4", "Point");
    model.component("comp1").geom("geom1").feature("pt4").setIndex("p", 0.036, 0);
    model.component("comp1").geom("geom1").feature("pt4").setIndex("p", 0.2, 1);
    model.component("comp1").geom("geom1").feature().duplicate("pt5", "pt4");
    model.component("comp1").geom("geom1").feature("pt5").setIndex("p", 0.04, 0);
    model.component("comp1").geom("geom1").feature("pt5").setIndex("p", 0.145, 1);
    model.component("comp1").geom("geom1").feature().duplicate("pt6", "pt5");
    model.component("comp1").geom("geom1").feature("pt6").setIndex("p", 0.01, 0);
    model.component("comp1").geom("geom1").feature("pt6").setIndex("p", 0.08, 1);
    model.component("comp1").geom("geom1").feature().duplicate("pt7", "pt6");
    model.component("comp1").geom("geom1").feature("pt7").setIndex("p", 0.003, 0);
    model.component("comp1").geom("geom1").feature("pt7").setIndex("p", 0.038, 1);
    model.component("comp1").geom("geom1").feature().duplicate("pt8", "pt7");
    model.component("comp1").geom("geom1").feature("pt8").setIndex("p", 0.042, 0);
    model.component("comp1").geom("geom1").feature("pt8").setIndex("p", 0, 1);
    model.component("comp1").geom("geom1").feature().duplicate("pt9", "pt8");
    model.component("comp1").geom("geom1").feature("pt9").setIndex("p", 0, 0);
    model.component("comp1").geom("geom1").feature("pt9").setIndex("p", 0.08, 1);
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u5206\u7ec4 1\uff1a\u70b9");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("pol1", false);
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.01, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.08, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.008, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.079, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.006, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.078, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.005, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.076, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.004, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.064, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.003, 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.038, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.003, 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.022, 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.0048404261469841, 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.016, 7, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.0062340328097343425, 8, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.012, 8, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.008117016404867172, 9, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.01, 9, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.01, 10, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.008, 10, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.01396806538105011, 11, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.006, 11, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.042, 12, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 12, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 13, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 13, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 14, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.08, 14, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.008, 15, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.08, 15, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("specify", "endsr");
    model.component("comp1").geom("geom1").feature("ca1").set("point1", new double[]{0.008, 0.08});
    model.component("comp1").geom("geom1").feature("ca1").set("point2", new double[]{0.034, 0.2});
    model.component("comp1").geom("geom1").feature("ca1").set("r", 0.11446);
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{0.034, 0.2});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{0.036, 0.2});
    model.component("comp1").geom("geom1").feature().duplicate("ca2", "ca1");
    model.component("comp1").geom("geom1").feature("ca2").set("point1", new double[]{0.01, 0.08});
    model.component("comp1").geom("geom1").feature("ca2").set("point2", new double[]{0.036, 0.2});
    model.component("comp1").geom("geom1").run("ca2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ca1", "ca2", "ls1", "pol1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("csol1", 16, 17);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "0.9[mm]");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.08, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.008, 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.08, 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.01859369918701851, 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.09, 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.032, 3, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.1133, 3, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 4, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.1133, 4, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 5, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.08, 5, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("pol2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("fil1");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0.1133, 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0.032, 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0.1133, 1, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0.0365, 2, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0.124, 2, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 3, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0.124, 3, 1);
    model.component("comp1").geom("geom1").feature().duplicate("dif2", "dif1");
    model.component("comp1").geom("geom1").runPre("dif2");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("pol3");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Wvdom", "Ttable"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-Ttable"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new double[]{0.017, 0.21});
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"0.017", "Hvdom*0.98"});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new double[]{0.05, 0.05});
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"Wvdom*0.97", "0.05"});
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(0);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("pt3", 1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("pt4", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mrv1", "MergeVertices");
    model.component("comp1").geom("geom1").feature("mrv1").selection("keepvtx").set("fin", 25);
    model.component("comp1").geom("geom1").feature("mrv1").selection("removevtx").set("fin", 26);
    model.component("comp1").geom("geom1").run("mrv1");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("mrv1", 10, 24, 26);
    model.component("comp1").geom("geom1").run("mce1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u9152");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("mce1", 3);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u9152\u676f");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("mce1", 2);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u84b8\u6c7d/\u7a7a\u6c14");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("mce1", 4);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u684c\u5b50");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("mce1", 1);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u6c7d-\u6db2\u8868\u9762");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("mce1", 8);
    model.component("comp1").geom("geom1").run("sel5");

    model.title(null);

    model.description("");

    model.label("ethanol_water_evaporation_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
