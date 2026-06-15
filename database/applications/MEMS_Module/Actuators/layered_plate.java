/*
 * layered_plate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:54 by COMSOL 6.3.0.290. */
public class layered_plate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("Tdeposition", "800[degC]");
    model.param().descr("Tdeposition", "\u6d82\u5c42\u6c89\u79ef\u6e29\u5ea6");
    model.param().set("Tepoxying", "150[degC]");
    model.param()
         .descr("Tepoxying", "\u7528\u73af\u6c27\u6811\u8102\u5c06\u6d82\u5c42/\u57fa\u677f\u80f6\u5408\u5230\u627f\u8f7d\u5c42\u65f6\u7684\u6e29\u5ea6");
    model.param().set("Troom", "20[degC]");
    model.param().descr("Troom", "\u5ba4\u6e29");
    model.param().set("Temp", "1[K]");
    model.param().descr("Temp", "\u8f7d\u8377\u53c2\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.02, 0.014});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.002, 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.01, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_strainreferencetemperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_strainreferencetemperature", "Tdeposition");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1").set("minput_temperature", "Temp");
    model.component("comp1").physics("solid").feature("lemm1").create("act1", "Activation", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("act1").selection().set(1);
    model.component("comp1").physics("solid").feature("lemm1").feature("act1")
         .set("activation_expression", "Temp<Tepoxying");
    model.component("comp1").physics("solid").create("rms1", "RigidMotionSuppression", 2);
    model.component("comp1").physics("solid").feature("rms1").selection().set(2);
    model.component("comp1").physics("solid").create("wrp1", "Warpage", 1);
    model.component("comp1").physics("solid").feature("wrp1").selection().set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u627f\u8f7d\u5c42");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"2.15e11"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6e-6"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u57fa\u677f");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"1.3e11"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.28"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"3e-6"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u6d82\u5c42");
    model.component("comp1").material("mat3").selection().set(3);
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"7e10"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.17"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"5e-7"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Tdeposition", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "Tdeposition", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "Temp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "Tepoxying Troom", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").feature("surf1").create("filt1", "Filter");
    model.result("pg1").feature("surf1").feature("filt1").set("expr", "solid.isactive");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.sGpx");
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7fd8\u66f2 (wrp1)");
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").label("\u7fd8\u66f2\u4f4d\u79fb");
    model.result("pg2").feature("line1").set("expr", "solid.wrp1.dispn_warp");
    model.result("pg2").feature("line1").set("colortable", "WaveLight");
    model.result("pg2").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("showsolutionparams", "on");
    model.result("pg2").feature("line1").set("data", "parent");
    model.result("pg2").feature("line1").feature().create("def1", "Deform");
    model.result("pg2").feature("line1").feature("def1").set("expr", new String[]{"solid.wrp1.u", "solid.wrp1.v"});
    model.result("pg2").feature("line1").feature().create("mrkr1", "Marker");
    model.result("pg2").feature().create("line2", "Line");
    model.result("pg2").feature("line2").label("\u5e73\u5747\u4f4d\u79fb");
    model.result("pg2").feature("line2").set("expr", "solid.wrp1.disp_avg");
    model.result("pg2").feature("line2").set("coloring", "uniform");
    model.result("pg2").feature("line2").set("color", "gray");
    model.result("pg2").feature("line2").set("smooth", "internal");
    model.result("pg2").feature("line2").set("inheritcolor", false);
    model.result("pg2").feature("line2").set("inheritrange", false);
    model.result("pg2").feature("line2").set("showsolutionparams", "on");
    model.result("pg2").feature("line2").set("data", "parent");
    model.result("pg2").feature("line2").set("inheritplot", "line1");
    model.result("pg2").feature("line2").feature().create("def1", "Deform");
    model.result("pg2").feature("line2").feature("def1")
         .set("expr", new String[]{"solid.wrp1.u_avg", "solid.wrp1.v_avg"});
    model.result("pg2").label("\u7fd8\u66f2 (wrp1)");
    model.result("pg2").run();
    model.result("pg1").run();

    model.title("\u591a\u5c42\u677f\u7684\u70ed\u5e94\u529b");

    model
         .description("\u4e00\u4e2a\u4e24\u5c42\uff08\u6d82\u5c42\u548c\u5e95\u5c42\uff09\u7ed3\u6784\u7684\u677f\u5728 800\u00b0C \u7684\u6e29\u5ea6\u4e0b\u6ca1\u6709\u5e94\u529b\u548c\u5e94\u53d8\uff0c\u5f53\u677f\u7684\u6e29\u5ea6\u964d\u4f4e\u5230 150\u00b0C \u65f6\u4f1a\u4ea7\u751f\u70ed\u5e94\u529b\u3002\u7136\u540e\uff0c\u6211\u4eec\u5728\u65e0\u5e94\u529b\u72b6\u6001\u4e0b\u6fc0\u6d3b\u7b2c\u4e09\u5c42\uff0c\u5373\u8f7d\u4f53\u5c42\u3002\u677f\u7684\u6e29\u5ea6\u6700\u7ec8\u964d\u81f3 20\u00b0C\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("layered_plate.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
