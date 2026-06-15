/*
 * tesla_microvalve_parameter_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:40 by COMSOL 6.3.0.290. */
public class tesla_microvalve_parameter_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Design_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("L", "1[mm]");
    model.param().descr("L", "\u901a\u9053\u957f\u5ea6");
    model.param().set("D", "0.2[mm]");
    model.param().descr("D", "\u7279\u5f81\u5c3a\u5bf8");
    model.param().set("H", "1.75*D");
    model.param().descr("H", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("tol", "10[\u00b5m]");
    model.param().descr("tol", "\u957f\u5ea6\u5bb9\u5dee");
    model.param().set("error", "0[m]");
    model.param().descr("error", "+/- tol");
    model.param().set("aLT", "0.4");
    model.param().descr("aLT", "\u4e09\u89d2\u5f62\u5927\u5c0f\u53c2\u6570 (0<aLT<1)");
    model.param().set("LT", "(H-8*tol)*aLT");
    model.param().descr("LT", "\u4e09\u89d2\u5f62\u957f\u548c\u5bbd");
    model.param().set("aXT", "0.5");
    model.param().descr("aXT", "\u4e09\u89d2\u5f62\u4f4d\u7f6e\u53c2\u6570 (a<aXT<1)");
    model.param().set("XT", "R+5*tol+(L-LT-R-5*tol)*aXT");
    model.param().descr("XT", "\u4e09\u89d2\u5f62\u4f4d\u7f6e");
    model.param().set("YC", "0.5*(LT+H-R)");
    model.param().descr("YC", "\u4e2d\u95f4\u5706 Y \u4f4d\u7f6e");
    model.param().set("aRC", "1");
    model.param().descr("aRC", "\u4e2d\u95f4\u5706\u534a\u5f84\u53c2\u6570 (0<aRC<1)");
    model.param().set("RC", "(H-LT-R-2*tol)/2*aRC");
    model.param().descr("RC", "\u4e2d\u95f4\u5706\u534a\u5f84");
    model.param().set("aR", "0.4");
    model.param().descr("aR", "\u4e0a\u5706\u534a\u5f84\u53c2\u6570 (0<aR<1)");
    model.param().set("R", "(H-LT-3*tol)*aR");
    model.param().descr("R", "\u4e0a\u534a\u5706\u534a\u5f84");
    model.param().set("XC", "max(XT/2,RC+2*tol)");
    model.param().descr("XC", "\u4e2d\u95f4\u5706 X \u4f4d\u7f6e");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"1", "H"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"D/2", "D/2"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-D/2", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"L", "0"});
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"D/2", "D/2"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "XT", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "XT+LT", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "LT", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "XT+LT", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("sca1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("sca1").set("isotropic", "(LT/2+error)/LT*2");
    model.component("comp1").geom("geom1").feature("sca1").set("pos", new String[]{"XT+LT/2", "0"});
    model.component("comp1").geom("geom1").run("sca1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"XT+LT-R", "0"});
    model.component("comp1").geom("geom1").feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"XT+LT-R", "H"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("sca2", "Scale");
    model.component("comp1").geom("geom1").feature("sca2").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("sca2").set("isotropic", "(R+error)/R");
    model.component("comp1").geom("geom1").feature("sca2").set("pos", new String[]{"XT+LT-R", "H"});
    model.component("comp1").geom("geom1").run("sca2");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "RC");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"XC", "YC"});
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("sca3", "Scale");
    model.component("comp1").geom("geom1").feature("sca3").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("sca3").set("isotropic", "(RC+error)/RC");
    model.component("comp1").geom("geom1").feature("sca3").set("pos", new String[]{"XC", "YC"});
    model.component("comp1").geom("geom1").run("sca3");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "L", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "D/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "L", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "H", 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "L-H+D/2", 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "H", 2, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("pol2", "sca1", "sca2", "sca3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u53f3");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "L+D/2-1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u5de6");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "-D/2+1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "1e3*eps");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "1e3*eps");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("tesla_microvalve_parameter_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
