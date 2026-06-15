/*
 * uniaxial_loading_of_shape_memory_alloy.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:37 by COMSOL 6.3.0.290. */
public class uniaxial_loading_of_shape_memory_alloy {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Shape_Memory_Alloys");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("T", "298[K]");
    model.param().descr("T", "\u5e94\u7528\u6e29\u5ea6");
    model.param().set("para", "0");
    model.param().descr("para", "\u8fde\u7eed\u53c2\u6570");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{6, 20});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("sma1", "ShapeMemoryAlloy", 2);
    model.component("comp1").physics("solid").feature("sma1").selection().all();
    model.component("comp1").physics("solid").feature("sma1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("sma1").set("minput_temperature", "T");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2);

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 1);
    model.component("comp1").func("int1").setIndex("table", 2, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 2, 1);

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "850[MPa]*int1(para)"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("LagoudasModel", "LagoudasModel", "Lagoudas_model");
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("T0", new String[]{"318"});
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", new String[]{"0.33"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ShapeMemoryAlloyAustenite", "ShapeMemoryAlloyAustenite", "Austenite_phase");
    model.component("comp1").material("mat1").propertyGroup("ShapeMemoryAlloyAustenite")
         .set("E_A", new String[]{"55[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("ShapeMemoryAlloyAustenite")
         .set("Cp_A", new String[]{"400"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ShapeMemoryAlloyMartensite", "ShapeMemoryAlloyMartensite", "Martensite_phase");
    model.component("comp1").material("mat1").propertyGroup("ShapeMemoryAlloyMartensite")
         .set("E_M", new String[]{"46[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("ShapeMemoryAlloyMartensite")
         .set("Cp_M", new String[]{"400"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("TMs", new String[]{"245"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("TMf", new String[]{"230"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("CM", new String[]{"7.4e6"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("TAs", new String[]{"270"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("TAf", new String[]{"280"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel").set("CA", new String[]{"7.4e6"});
    model.component("comp1").material("mat1").propertyGroup("LagoudasModel")
         .set("etrmaxLagoudas", new String[]{"0.056"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"6500"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u4f2a\u5f39\u6027\uff0c\u5355\u6b21\u52a0\u8f7d\u5faa\u73af");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "T", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "T", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("plistarr", "328 308 276 260", 0);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.01,2)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1")
         .label("\u5e94\u529b vs. \u5e94\u53d8\uff08\u4f2a\u5f39\u6027\uff0c\u5355\u6b21\u52a0\u8f7d\u5faa\u73af\uff09");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(4);
    model.result("pg1").feature("ptgr1").set("expr", "solid.sGpzz");
    model.result("pg1").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0czz \u5206\u91cf");
    model.result("pg1").feature("ptgr1").set("unit", "MPa");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "solid.eZZ");
    model.result("pg1").feature("ptgr1").set("xdatadescr", "\u5e94\u53d8\u5f20\u91cf\uff0cZZ \u5206\u91cf");
    model.result("pg1").feature("ptgr1").set("autopoint", false);
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("xlabel", "\u8f74\u5411\u5e94\u53d8");
    model.result("pg1").set("ylabel", "\u8f74\u5411\u5e94\u529b (MPa)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2")
         .label("\u9a6c\u6c0f\u4f53\u7684\u4f53\u79ef\u5206\u6570\uff08\u4f2a\u5f39\u6027\uff0c\u5355\u6b21\u52a0\u8f7d\u5faa\u73af\uff09");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(4);
    model.result("pg2").feature("ptgr1").set("expr", "solid.xiGp_M");
    model.result("pg2").feature("ptgr1").set("descr", "\u9a6c\u6c0f\u4f53\u4f53\u79ef\u5206\u6570");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("autopoint", false);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();

    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int2").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int2").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int2").setIndex("table", 1, 1, 1);
    model.component("comp1").func("int2").setIndex("table", 2, 2, 0);
    model.component("comp1").func("int2").setIndex("table", 0.4, 2, 1);
    model.component("comp1").func("int2").setIndex("table", 3, 3, 0);
    model.component("comp1").func("int2").setIndex("table", 0.8, 3, 1);
    model.component("comp1").func("int2").setIndex("table", 4, 4, 0);
    model.component("comp1").func("int2").setIndex("table", 0, 4, 1);

    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(3);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "20[cm]*0.07*int2(para)", 2);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u4f2a\u5f39\u6027\uff0c\u591a\u6b21\u52a0\u8f7d\u5faa\u73af");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "T", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "T", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "K", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.02,4)", 0);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/bndl1"});
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3")
         .label("\u5e94\u529b vs. \u5e94\u53d8\uff08\u4f2a\u5f39\u6027\uff0c\u591a\u6b21\u52a0\u8f7d\u5faa\u73af\uff09");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").run();
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4")
         .label("\u9a6c\u6c0f\u4f53\u7684\u4f53\u79ef\u5206\u6570\uff08\u4f2a\u5f39\u6027\uff0c\u591a\u6b21\u52a0\u8f7d\u5faa\u73af\uff09");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").run();

    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").set("funcname", "temperature");
    model.component("comp1").func("int3").setIndex("table", 2, 0, 0);
    model.component("comp1").func("int3").setIndex("table", 260, 0, 1);
    model.component("comp1").func("int3").setIndex("table", 3, 1, 0);
    model.component("comp1").func("int3").setIndex("table", 300, 1, 1);
    model.component("comp1").func("int3").setIndex("fununit", "K", 0);
    model.component("comp1").func("int3").setIndex("argunit", 1, 0);

    model.component("comp1").physics("solid").feature().duplicate("sma2", "sma1");
    model.component("comp1").physics("solid").feature("sma2").set("minput_temperature", "temperature(para)");
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl2").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"0", "0", "300[MPa]*int1(para)"});

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u7814\u7a76\uff1a\u5f62\u72b6\u8bb0\u5fc6\u6548\u5e94");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "T", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "T", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "K", 0);
    model.study("std3").feature("stat").setIndex("pname", "para", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0,0.02,2) range(2.05,0.05,3)", 0);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1"});
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol8").runAll();

    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u5e94\u529b vs. \u5e94\u53d8\uff08\u5f62\u72b6\u8bb0\u5fc6\u6548\u5e94\uff09");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"solid/disp1", "solid/sma2", "solid/bndl2"});
    model.study("std2").feature("stat")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/sma2", "solid/bndl2"});

    model.result("pg5").run();

    model.title("\u5f62\u72b6\u8bb0\u5fc6\u5408\u91d1\u7684\u5355\u8f74\u8f7d\u8377");

    model
         .description("\u672c\u4f8b\u6267\u884c\u4e09\u4e2a\u7814\u7a76\uff0c\u4ee5\u6f14\u793a\u954d\u949b\u5408\u91d1\u5757\u5728\u5355\u8f74\u62c9\u4f38-\u538b\u7f29\u8f7d\u8377\u4f5c\u7528\u4e0b\u7684\u5c5e\u6027\u3002\n\n\u7b2c\u4e00\u4e2a\u53c2\u6570\u5316\u7814\u7a76\u663e\u793a\u4e0d\u540c\u6e29\u5ea6\u4e0b\u7684\u4f2a\u5f39\u6027\u6548\u5e94\u3002\u7b2c\u4e8c\u4e2a\u7814\u7a76\u4e2d\u6dfb\u52a0\u4e86\u90e8\u5206\u52a0\u8f7d-\u5378\u8f7d\u5faa\u73af\u3002\u6700\u540e\uff0c\u7b2c\u4e09\u4e2a\u7814\u7a76\u663e\u793a\u5728\u4f4e\u6e29\u52a0\u8f7d\u5faa\u73af\u540e\u5347\u9ad8\u6e29\u5ea6\u7684\u60c5\u51b5\u4e0b\uff0c\u5f62\u72b6\u8bb0\u5fc6\u6548\u5e94\u7684\u8868\u73b0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("uniaxial_loading_of_shape_memory_alloy.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
