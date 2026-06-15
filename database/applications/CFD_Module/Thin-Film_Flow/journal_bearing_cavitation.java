/*
 * journal_bearing_cavitation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class journal_bearing_cavitation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Thin-Film_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("tff", "ThinFilmFlowShell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tff", true);

    model.param().set("R", "0.03[m]");
    model.param().descr("R", "\u8f74\u627f\u9888\u534a\u5f84");
    model.param().set("H", "0.05[m]");
    model.param().descr("H", "\u8f74\u627f\u9888\u9ad8\u5ea6");
    model.param().set("c", "0.03[mm]");
    model.param().descr("c", "\u8f74\u627f\u4e0e\u8f74\u627f\u9888\u7684\u95f4\u9699");
    model.param().set("omega", "1500/60*2*pi[rad/s]");
    model.param().descr("omega", "\u8f74\u627f\u9888\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "H");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("angle", "atan2(y,x)[rad]");
    model.component("comp1").variable("var1").descr("angle", "\u6cbf\u5706\u5468\u7684\u89d2\u5ea6");
    model.component("comp1").variable("var1").set("th", "c*(1+0.6*cos(angle))");
    model.component("comp1").variable("var1").descr("th", "\u6da6\u6ed1\u6cb9\u819c\u539a\u5ea6");
    model.component("comp1").variable("var1").set("u_b", "-omega*R*sin(angle)");
    model.component("comp1").variable("var1").descr("u_b", "\u8f74\u627f\u9888\u901f\u5ea6 x \u5206\u91cf");
    model.component("comp1").variable("var1").set("v_b", "omega*R*cos(angle)");
    model.component("comp1").variable("var1").descr("v_b", "\u8f74\u627f\u9888\u901f\u5ea6 y \u5206\u91cf");

    model.component("comp1").physics("tff").prop("EquationType")
         .set("EquationType", "ReynoldsEquationWithCavitation");
    model.component("comp1").physics("tff").feature("ffp1").set("mure_mat", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("mure", "0.01[Pa*s]");
    model.component("comp1").physics("tff").feature("ffp1").set("hw1", "th");
    model.component("comp1").physics("tff").feature("ffp1").set("TangentialBaseVelocity", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("vb", new String[]{"u_b", "v_b", "0"});

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6d41\u4f53\u538b\u529b (tff)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"pressure", "\u538b\u529b", "Pa", "Pa"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "tff.p");
    model.result("pg1").feature("surf1").set("colortable", "Cividis");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "tff.p");
    model.result("pg1").feature("con1").set("colortable", "GrayScale");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u538b\u529b (MPa)");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u8d28\u91cf\u5206\u6570");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "tff.theta");
    model.result("pg2").feature("surf1").set("descr", "\u8d28\u91cf\u5206\u6570");
    model.result("pg2").run();

    model.title("\u8f74\u9888\u8f74\u627f\u7684\u7a7a\u7a74\u6548\u5e94");

    model
         .description("\u672c\u4f8b\u9884\u6d4b\u4e86\u4e00\u4e2a\u8f74\u9888\u8f74\u627f\u7684\u6da6\u6ed1\u5c42\u4e2d\u6c14\u4f53\u7a7a\u7a74\u7684\u5f62\u6210\u548c\u53d1\u5c55\u3002\u56e0\u4e3a\u7a7a\u7a74\u4f1a\u5bfc\u81f4\u8f74\u627f\u8fc7\u65e9\u635f\u574f\uff0c\u6240\u4ee5\u7a7a\u7a74\u7684\u5f62\u6210\u51b3\u5b9a\u4e86\u80fd\u591f\u65bd\u52a0\u5230\u8f74\u627f\u4e0a\u7684\u8f7d\u8377\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("journal_bearing_cavitation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
