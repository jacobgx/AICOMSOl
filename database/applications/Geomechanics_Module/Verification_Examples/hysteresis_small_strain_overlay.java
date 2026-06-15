/*
 * hysteresis_small_strain_overlay.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:15 by COMSOL 6.3.0.290. */
public class hysteresis_small_strain_overlay {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("para", "0");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "appliedDisp");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int1").setIndex("table", "5e-3", 1, 1);
    model.component("comp1").func("int1").setIndex("table", 2, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 2, 1);
    model.component("comp1").func("int1").setIndex("table", 3, 3, 0);
    model.component("comp1").func("int1").setIndex("table", "-5e-3", 3, 1);
    model.component("comp1").func("int1").setIndex("table", 4, 4, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 4, 1);
    model.component("comp1").func("int1").setIndex("table", 5, 5, 0);
    model.component("comp1").func("int1").setIndex("table", "5e-3", 5, 1);
    model.component("comp1").func("int1").setIndex("fununit", "cm", 0);
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"5[cm]", "10[cm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("nlemm1", "NonlinearElasticMaterial", 2);
    model.component("comp1").physics("solid").feature("nlemm1").selection().set(1);
    model.component("comp1").physics("solid").feature("nlemm1").set("NonlinearElasticModel", "SmallStrainOverlay");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 2);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(3);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-appliedDisp(para)", 1);
    model.component("comp1").physics("solid").create("disp2", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp2").selection().set(4);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp2").setIndex("U0", "appliedDisp(para)", 1);
    model.component("comp1").physics("solid").feature().duplicate("disp3", "disp2");
    model.component("comp1").physics("solid").feature("disp3").selection().set(1);
    model.component("comp1").physics("solid").feature("disp3").setIndex("U0", 0, 1);
    model.component("comp1").physics("solid").create("disp4", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp4").selection().set(3);
    model.component("comp1").physics("solid").feature("disp4").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp4").setIndex("U0", "appliedDisp(para)", 0);
    model.component("comp1").physics("solid").feature().duplicate("disp5", "disp4");
    model.component("comp1").physics("solid").feature("disp5").selection().set(2);
    model.component("comp1").physics("solid").feature("disp5").setIndex("U0", 0, 0);

    model.nodeGroup().create("grp1", "Physics", "solid");
    model.nodeGroup("grp1").placeAfter("nlemm1");
    model.nodeGroup("grp1").add("roll1");
    model.nodeGroup("grp1").add("disp1");
    model.nodeGroup("grp1").label("\u5faa\u73af\u4e09\u8f74\u52a0\u8f7d");
    model.nodeGroup().create("grp2", "Physics", "solid");
    model.nodeGroup("grp2").placeAfter("nlemm1");
    model.nodeGroup("grp2").add("disp2");
    model.nodeGroup("grp2").add("disp3");
    model.nodeGroup("grp2").add("disp4");
    model.nodeGroup("grp2").add("disp5");
    model.nodeGroup("grp2").label("\u5faa\u73af\u526a\u5207\u52a0\u8f7d");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u571f\u58e4\u6750\u6599");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearElasticMaterial", "NonlinearElasticMaterial", "Nonlinear_elastic_material");
    model.component("comp1").material("mat1").propertyGroup("NonlinearElasticMaterial")
         .set("gammaRef", new String[]{"5.1948E-4"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.2"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearElasticMaterial")
         .set("G0", new String[]{"185[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2400[kg/m^3]"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run("map1");

    model.study("std1").label("\u7814\u7a76\uff1a\u5faa\u73af\u4e09\u8f74\u52a0\u8f7d");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"solid/disp2", "solid/disp3", "solid/disp4", "solid/disp5"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.001,5)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u5faa\u73af\u526a\u5207\u52a0\u8f7d");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.001,5)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u8f74\u5411\u5e94\u529b vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u8f74\u5411\u5e94\u529b vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u8f74\u5411\u5e94\u53d8 (1)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u8f74\u5411\u5e94\u529b (kPa)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").set("data", "dset1");
    model.result("pg1").feature("ptgr1").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg1").feature("ptgr1").setIndex("looplevelindices", "range(1,1,1001)", 0);
    model.result("pg1").feature("ptgr1").selection().set(4);
    model.result("pg1").feature("ptgr1").set("expr", "-solid.SYY");
    model.result("pg1").feature("ptgr1").set("unit", "kPa");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "-solid.eYY");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg1").feature("ptgr1").setIndex("legends", "\u521d\u6b21\u52a0\u8f7d", 0);
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").setIndex("looplevelindices", "range(1001,1,5001)", 0);
    model.result("pg1").feature("ptgr2").setIndex("legends", "\u5378\u8f7d\u548c\u91cd\u65b0\u52a0\u8f7d", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u6768\u6c0f\u6a21\u91cf vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg2").set("title", "\u6768\u6c0f\u6a21\u91cf vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg2").set("ylabel", "\u6768\u6c0f\u6a21\u91cf (MPa)");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").set("expr", "solid.E");
    model.result("pg2").feature("ptgr1").set("unit", "MPa");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "abs(solid.eYY)");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").setIndex("looplevelindices", "range(3002,1,4001)", 0);
    model.result("pg2").feature("ptgr2").set("expr", "solid.E");
    model.result("pg2").feature("ptgr2").set("unit", "MPa");
    model.result("pg2").feature("ptgr2").set("xdataexpr", "abs(solid.eYY-withsol('sol1',solid.eYY,setval(para,3)))");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u91cd\u65b0\u52a0\u8f7d", 0);
    model.result("pg2").run();
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("legendpos", "lowerleft");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u526a\u5207\u5e94\u529b vs. \u526a\u5207\u5e94\u53d8");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("title", "\u526a\u5207\u5e94\u529b vs. \u526a\u5207\u5e94\u53d8");
    model.result("pg3").set("xlabel", "\u526a\u5207\u5e94\u53d8 (1)");
    model.result("pg3").set("ylabel", "\u526a\u5207\u5e94\u529b (kPa)");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("data", "dset2");
    model.result("pg3").feature("ptgr1").set("expr", "solid.SXY");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "solid.eXY");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").set("data", "dset2");
    model.result("pg3").feature("ptgr2").set("expr", "solid.SXY");
    model.result("pg3").feature("ptgr2").set("xdataexpr", "solid.eXY");
    model.result("pg3").run();
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u526a\u5207\u6a21\u91cf vs. \u526a\u5207\u5e94\u53d8");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("title", "\u526a\u5207\u6a21\u91cf vs. \u526a\u5207\u5e94\u53d8");
    model.result("pg4").set("xlabel", "\u526a\u5207\u5e94\u53d8 (1)");
    model.result("pg4").set("ylabel", "\u526a\u5207\u6a21\u91cf (MPa)");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("data", "dset2");
    model.result("pg4").feature("ptgr1").set("expr", "solid.G");
    model.result("pg4").feature("ptgr1").set("xdataexpr", "abs(solid.eXY)");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").set("data", "dset2");
    model.result("pg4").feature("ptgr2").set("expr", "solid.G");
    model.result("pg4").feature("ptgr2").set("xdataexpr", "abs(solid.eXY-withsol('sol2',solid.eXY,setval(para,3)))");
    model.result("pg4").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup("grp3").add("plotgroup", "pg1");
    model.nodeGroup("grp3").add("plotgroup", "pg2");
    model.nodeGroup("grp3").label("\u4e09\u8f74\u52a0\u8f7d");

    model.result("pg3").run();

    model.nodeGroup().create("grp4", "Results");
    model.nodeGroup("grp4").set("type", "plotgroup");
    model.nodeGroup().move("grp4", 3);
    model.nodeGroup("grp4").add("plotgroup", "pg3");
    model.nodeGroup("grp4").add("plotgroup", "pg4");
    model.nodeGroup("grp4").label("\u526a\u5207\u52a0\u8f7d");

    model.result("pg1").run();

    model
         .title("\u4f7f\u7528\u5c0f\u5e94\u53d8\u53e0\u52a0\u6a21\u578b\u8ba1\u7b97\u571f\u58e4\u6ede\u540e\u73b0\u8c61");

    model
         .description("\u5c0f\u5e94\u53d8\u53e0\u52a0\u6750\u6599\u6a21\u578b\u53ef\u4ee5\u6355\u6349\u4f4e\u5e94\u53d8\u6761\u4ef6\u4e0b\u7684\u9ad8\u521a\u5ea6\u6548\u5e94\u4ee5\u53ca\u5faa\u73af\u8f7d\u8377\u4e0b\u7684\u6ede\u540e\u6548\u5e94\uff0c\u8fd9\u662f\u5927\u591a\u6570\u571f\u58e4\u8868\u73b0\u51fa\u6765\u7684\u5178\u578b\u73b0\u8c61\u3002\u8be5\u6a21\u578b\u5141\u8bb8\u521a\u5ea6\u968f\u526a\u5207\u5e94\u53d8\u7684\u589e\u52a0\u800c\u9010\u6e10\u9000\u5316\uff0c\u5e76\u5728\u8f7d\u8377\u53cd\u8f6c\u65f6\u5b8c\u5168\u6062\u590d\u3002\n\n\u5728\u672c\u4f8b\u4e2d\uff0c\u6211\u4eec\u901a\u8fc7\u5faa\u73af\u62c9\u4f38\u548c\u526a\u5207\u8bd5\u9a8c\u5c55\u793a\u4e86\u5c0f\u5e94\u53d8\u53e0\u52a0\u6a21\u578b\u7684\u521a\u5ea6\u9000\u5316\u548c\u6ede\u540e\u6548\u5e94\u3002");

    model.label("hysteresis_small_strain_overlay.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
