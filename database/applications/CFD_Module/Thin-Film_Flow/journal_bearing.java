/*
 * journal_bearing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class journal_bearing {

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
    model.param().descr("omega", "\u8f74\u627f\u9888\u7684\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "H");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("angle", "atan2(y,x)[rad]");
    model.component("comp1").variable("var1").descr("angle", "\u5706\u5468\u89d2");
    model.component("comp1").variable("var1").set("th", "c*(1+0.6*cos(angle))");
    model.component("comp1").variable("var1").descr("th", "\u6da6\u6ed1\u6cb9\u819c\u7684\u539a\u5ea6");
    model.component("comp1").variable("var1").set("u_b", "-omega*R*sin(angle)");
    model.component("comp1").variable("var1").descr("u_b", "\u8f74\u627f\u9888\u901f\u5ea6\u7684 x \u5206\u91cf");
    model.component("comp1").variable("var1").set("v_b", "omega*R*cos(angle)");
    model.component("comp1").variable("var1").descr("v_b", "\u8f74\u627f\u9888\u901f\u5ea6\u7684 y \u5206\u91cf");

    model.component("comp1").physics("tff").feature("ffp1").set("rho_mat", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("rho", "860[kg/m^3]");
    model.component("comp1").physics("tff").feature("ffp1").set("mure_mat", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("mure", "0.01[Pa*s]");
    model.component("comp1").physics("tff").feature("ffp1").set("hw1", "th");
    model.component("comp1").physics("tff").feature("ffp1").set("TangentialBaseVelocity", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("vb", new String[]{"u_b", "v_b", "0"});

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
    model.result("pg1").feature("surf1").set("colortable", "Cividis");
    model.result("pg1").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("colortable", "GrayScale");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u538b\u529b (MPa)");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6\u65b9\u5411");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"tff.vbx", "tff.vby", "tff.vbz"});
    model.result("pg2").feature("arws1").set("descr", "\u57fa\u901f\u5ea6");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").set("titletype", "none");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u6da6\u6ed1\u6cb9\u819c\u7684\u539a\u5ea6");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "tff.h");
    model.result("pg3").feature("surf1").set("descr", "\u95f4\u9699\u603b\u9ad8\u5ea6");
    model.result("pg3").feature("surf1").set("unit", "\u00b5m");
    model.result("pg3").feature("surf1").set("colortable", "Cividis");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg1").run();

    model.title("\u8f74\u9888\u8f74\u627f");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5b8c\u6574\u7684\u8f74\u9888\u8f74\u627f\u4e2d\u6da6\u6ed1\u819c\u4e0a\u538b\u529b\u5206\u5e03\u7684\u8ba1\u7b97\uff0c\u5176\u4e2d\u5305\u542b\u6574\u4e2a\u65cb\u8f6c\u8f74\u9888\u3002\u5728\u8f74\u9888\u5305\u88f9\u6da6\u6ed1\u819c\u7684\u8868\u9762\u6c42\u89e3\u96f7\u8bfa\u65b9\u7a0b\u65f6\uff0c\u4f7f\u7528\u6536\u655b\u548c\u53d1\u6563\u6da6\u6ed1\u539a\u5ea6\u5bf9\u8f74\u627f\u95f4\u9699\u5185\u8f74\u9888\u7684\u504f\u5fc3\u4f4d\u7f6e\u8fdb\u884c\u5efa\u6a21\u3002");

    model.label("journal_bearing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
