/*
 * damage_test_bar.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:29 by COMSOL 6.3.0.290. */
public class damage_test_bar {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Damage");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("L", "0.1[m]");
    model.param().descr("L", "\u68d2\u957f");
    model.param().set("H", "L/50");
    model.param().descr("H", "\u68d2\u539a\u5ea6");
    model.param().set("max_average_strain", "5e-4");
    model.param().descr("max_average_strain", "\u6700\u5927\u5e73\u5747\u5e94\u53d8");
    model.param().set("average_strain", "0");
    model.param().descr("average_strain", "\u5e73\u5747\u5e94\u53d8\uff08\u8f7d\u8377\u53c2\u6570\uff09");
    model.param().set("lscale", "0.001[m]");
    model.param().descr("lscale", "\u957f\u5ea6\u5c3a\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H/2"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(4);
    model.component("comp1").cpl("intop1").set("method", "summation");

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "H");
    model.component("comp1").physics("solid").feature("lemm1").create("dmg1", "Damage", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg1")
         .label("\u635f\u4f24\uff1a\u88c2\u7f1d\u5e26");
    model.component("comp1").physics("solid").feature("lemm1").create("dmg2", "Damage", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg2")
         .label("\u635f\u4f24\uff1a\u9690\u5f0f\u68af\u5ea6");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg2").set("regType", "impGradient");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg2").set("lint", "lscale");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg2").set("hdmg", "3*lscale");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"30[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2400"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ScalarDamage", "ScalarDamage", "Scalar_damage");
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").set("sigmap", new String[]{"2[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").set("Gf", new String[]{"60"});
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func().create("pw1", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1")
         .label("\u5206\u6bb5\uff1a\u5f31\u5316");
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1").set("funcname", "weak");
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1")
         .setIndex("pieces", 0.0495, 0, 1);
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1").setIndex("pieces", 1, 0, 2);
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1")
         .setIndex("pieces", 0.0495, 1, 0);
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1")
         .setIndex("pieces", 0.0505, 1, 1);
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1")
         .setIndex("pieces", 0.975, 1, 2);
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1")
         .setIndex("pieces", 0.0505, 2, 0);
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1")
         .setIndex("pieces", ".1", 2, 1);
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1").setIndex("pieces", 1, 2, 2);
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1").set("argunit", "m");
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").func("pw1").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage")
         .set("sigmap", new String[]{"2[MPa]*weak(X)"});

    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 2);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(4);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "L*average_strain", 0);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 101);

    model.study("std1").label("\u88c2\u7f1d\u5e26\uff0c\u4e8c\u6b21");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "average_strain", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.01,1)*max_average_strain", 0);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/dmg2"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pminstep", "1e-5*max_average_strain");
    model.sol("sol1").feature("s1").feature("p1").set("pinitstep", "0.01*max_average_strain");
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", "0.01*max_average_strain");
    model.sol("sol1").feature("v1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").set("scaleval", "1.0e-4");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 101, 0);
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
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "mm", "mm"}, 0);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 101, 0);
    model.result("pg2").label("\u635f\u4f24 (solid)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.dmgGp"});
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("bandcount", 11);
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u635f\u4f24");
    model.result("pg2").label("\u635f\u4f24 (solid)");
    model.result("pg2").run();
    model.result("pg2").label("\u635f\u4f24\uff1a\u88c2\u7f1d\u5e26");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("resolution", "custom");
    model.result("pg2").feature("surf1").set("refine", 2);
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def1").set("scale", 100);
    model.result("pg2").run();
    model.result("pg2").create("mesh1", "Mesh");
    model.result("pg2").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg2").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg2").feature("mesh1").set("elemcolor", "none");
    model.result("pg2").feature("mesh1").set("wireframecolor", "white");
    model.result("pg2").feature("mesh1").set("inheritplot", "surf1");
    model.result("pg2").feature("mesh1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "L", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "L", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "average_strain", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.01,1)*max_average_strain", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol2").feature("s1").feature("p1").set("pminstep", "1e-5*max_average_strain");
    model.sol("sol2").feature("s1").feature("p1").set("pinitstep", "0.01*max_average_strain");
    model.sol("sol2").feature("s1").feature("p1").set("pmaxstep", "0.01*max_average_strain");
    model.sol("sol2").feature("v1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").set("scaleval", "1.0e-4");

    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u9690\u5f0f\u68af\u5ea6\uff0c\u4e8c\u6b21");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.component("comp1").physics("solid").create("disc1", "Discretization", -1);
    model.component("comp1").physics("solid").feature("disc1").set("order_displacement", 1);
    model.component("comp1").physics("solid").feature("disc1").label("\u7ebf\u6027\u79bb\u6563\u5316");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "L", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "L", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "average_strain", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0,0.01,1)*max_average_strain", 0);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/dmg2"});
    model.study("std3").feature("stat").setEntry("discretization", "solid", "disc1");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol3").feature("s1").feature("p1").set("pminstep", "1e-5*max_average_strain");
    model.sol("sol3").feature("s1").feature("p1").set("pinitstep", "0.01*max_average_strain");
    model.sol("sol3").feature("s1").feature("p1").set("pmaxstep", "0.01*max_average_strain");
    model.sol("sol3").feature("v1").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").set("scaleval", "1.0e-4");

    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u88c2\u7f1d\u5e26\uff0c\u7ebf\u6027");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").setEntry("discretization", "solid", "disc1");
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "L", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "L", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "average_strain", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "range(0,0.01,1)*max_average_strain", 0);
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol4").feature("s1").feature("p1").set("pminstep", "1e-5*max_average_strain");
    model.sol("sol4").feature("s1").feature("p1").set("pinitstep", "0.01*max_average_strain");
    model.sol("sol4").feature("s1").feature("p1").set("pmaxstep", "0.01*max_average_strain");
    model.sol("sol4").feature("v1").set("scalemethod", "manual");
    model.sol("sol4").feature("v1").set("scaleval", "1.0e-4");

    model.study("std4").setGenPlots(false);
    model.study("std4").label("\u9690\u5f0f\u68af\u5ea6\uff0c\u7ebf\u6027");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result("pg2").run();
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("text", "\u4e8c\u9636\u5f62\u51fd\u6570");
    model.result("pg2").feature("ann1").set("posyexpr", "2*H");
    model.result("pg2").feature("ann1").set("showpoint", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").feature().duplicate("mesh2", "mesh1");
    model.result("pg2").feature().duplicate("ann2", "ann1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("data", "dset3");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("def1").set("expr", new String[]{"u", "v+4*H/100"});
    model.result("pg2").run();
    model.result("pg2").feature("mesh2").set("data", "dset3");
    model.result("pg2").run();
    model.result("pg2").feature("mesh2").feature("def1").set("expr", new String[]{"u", "v+4*H/100"});
    model.result("pg2").run();
    model.result("pg2").feature("ann2").set("text", "\u7ebf\u6027\u5f62\u51fd\u6570");
    model.result("pg2").feature("ann2").set("posyexpr", "6*H");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u635f\u4f24\uff1a\u9690\u5f0f\u68af\u5ea6");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("data", "dset4");
    model.result("pg3").run();
    model.result("pg3").feature("mesh2").set("data", "dset4");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f74\u5411\u529b");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "intop1(solid.RFx)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u529b", 0);
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u88c2\u7f1d\u5e26\uff0c\u4e8c\u6b21", 0);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("data", "dset2");
    model.result("pg4").feature("glob2").setIndex("legends", "\u9690\u5f0f\u68af\u5ea6\uff0c\u4e8c\u6b21", 0);
    model.result("pg4").feature().duplicate("glob3", "glob2");
    model.result("pg4").run();
    model.result("pg4").feature("glob3").set("data", "dset3");
    model.result("pg4").feature("glob3").setIndex("legends", "\u88c2\u7f1d\u5e26\uff0c\u7ebf\u6027", 0);
    model.result("pg4").feature().duplicate("glob4", "glob3");
    model.result("pg4").run();
    model.result("pg4").feature("glob4").set("data", "dset4");
    model.result("pg4").feature("glob4").setIndex("legends", "\u9690\u5f0f\u68af\u5ea6\uff0c\u7ebf\u6027", 0);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u5e73\u5747\u5e94\u53d8");
    model.result("pg4").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", "L/2");
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").label("\u622a\u70b9\uff1a\u7814\u7a76 1");
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").label("\u622a\u70b9\uff1a\u7814\u7a76 2");
    model.result().dataset("cpt2").set("data", "dset2");
    model.result().dataset().duplicate("cpt3", "cpt2");
    model.result().dataset("cpt3").label("\u622a\u70b9\uff1a\u7814\u7a76 3");
    model.result().dataset("cpt3").set("data", "dset3");
    model.result().dataset().duplicate("cpt4", "cpt3");
    model.result().dataset("cpt4").label("\u622a\u70b9\uff1a\u7814\u7a76 4");
    model.result().dataset("cpt4").set("data", "dset4");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u635f\u4f24\u7684\u5e94\u529b vs. \u5e94\u53d8");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").set("data", "cpt1");
    model.result("pg5").feature("ptgr1").set("expr", "solid.sdGpxx");
    model.result("pg5").feature("ptgr1")
         .set("descr", "\u5e94\u529b\u5f20\u91cf\uff0c\u635f\u4f24\uff0cxx \u5206\u91cf");
    model.result("pg5").feature("ptgr1").set("xdata", "expr");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "solid.eXX");
    model.result("pg5").feature("ptgr1").set("xdatadescr", "\u5e94\u53d8\u5f20\u91cf\uff0cXX \u5206\u91cf");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("linewidth", 2);
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u88c2\u7f1d\u5e26\uff0c\u4e8c\u6b21", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("data", "cpt2");
    model.result("pg5").feature("ptgr2").setIndex("legends", "\u9690\u5f0f\u68af\u5ea6\uff0c\u4e8c\u6b21", 0);
    model.result("pg5").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr3").set("data", "cpt3");
    model.result("pg5").feature("ptgr3").setIndex("legends", "\u88c2\u7f1d\u5e26\uff0c\u7ebf\u6027", 0);
    model.result("pg5").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr4").set("data", "cpt4");
    model.result("pg5").feature("ptgr4").setIndex("legends", "\u9690\u5f0f\u68af\u5ea6\uff0c\u7ebf\u6027", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u635f\u4f24\u6f14\u5316");
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("xdataexpr", "solid.dmg");
    model.result("pg6").feature("ptgr1").set("expr", "solid.dmg");
    model.result("pg6").feature("ptgr1").set("xdata", "solution");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("expr", "solid.dmg");
    model.result("pg6").feature("ptgr2").set("xdata", "solution");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr3").set("expr", "solid.dmg");
    model.result("pg6").feature("ptgr3").set("xdata", "solution");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr4").set("expr", "solid.dmg");
    model.result("pg6").feature("ptgr4").set("xdata", "solution");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5c40\u90e8\u5316\uff0c\u4e8c\u6b21");
    model.result("pg7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").set("titletype", "none");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().set(2);
    model.result("pg7").feature("lngr1").set("expr", "solid.kappadmgGp");
    model.result("pg7").feature("lngr1").set("descr", "\u7b49\u6548\u5e94\u53d8\u6700\u5927\u503c");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "X");
    model.result("pg7").feature("lngr1").set("linewidth", 2);
    model.result("pg7").feature("lngr1").set("linemarker", "cycle");
    model.result("pg7").feature("lngr1").set("markerpos", "interp");
    model.result("pg7").feature("lngr1").set("markers", 15);
    model.result("pg7").feature("lngr1").set("resolution", "norefine");
    model.result("pg7").feature("lngr1").set("smooth", "none");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "CB\uff1a\u6700\u5927 Eq. \u5e94\u53d8", 0);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("data", "dset2");
    model.result("pg7").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg7").feature("lngr2").setIndex("legends", "IG\uff1a\u6700\u5927 Eq. \u5e94\u53d8", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("lngr3", "lngr1");
    model.result("pg7").feature().duplicate("lngr4", "lngr2");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").set("expr", "solid.dmgGp");
    model.result("pg7").feature("lngr3").set("descr", "\u635f\u4f24");
    model.result("pg7").feature("lngr3").setIndex("legends", "CB\uff1a\u635f\u4f24", 0);
    model.result("pg7").run();
    model.result("pg7").feature("lngr4").set("expr", "solid.dmg");
    model.result("pg7").feature("lngr4").setIndex("legends", "IG\uff1a\u635f\u4f24", 0);
    model.result("pg7").run();
    model.result("pg7").set("twoyaxes", true);
    model.result("pg7").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg7").setIndex("plotonsecyaxis", true, 3, 1);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u5c40\u90e8\u5316\uff0c\u7ebf\u6027");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("data", "dset4");
    model.result("pg8").run();
    model.result("pg8").feature("lngr4").set("data", "dset4");
    model.result("pg8").run();
    model.result("pg7").run();
    model.result().duplicate("pg9", "pg7");
    model.result("pg9").run();
    model.result("pg9").label("\u6c34\u5e73\u4f4d\u79fb");
    model.result("pg9").set("twoyaxes", false);
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("expr", "u");
    model.result("pg9").feature("lngr1").setIndex("legends", "\u88c2\u7f1d\u5e26\uff0c\u4e8c\u6b21", 0);
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").set("expr", "u");
    model.result("pg9").feature("lngr2").setIndex("legends", "\u9690\u5f0f\u68af\u5ea6\uff0c\u4e8c\u6b21", 0);
    model.result("pg9").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg9").run();
    model.result("pg9").feature("lngr3").set("data", "dset3");
    model.result("pg9").feature("lngr3").setIndex("looplevelinput", "last", 0);
    model.result("pg9").feature("lngr3").set("expr", "u");
    model.result("pg9").feature("lngr3").setIndex("legends", "\u88c2\u7f1d\u5e26\uff0c\u7ebf\u6027", 0);
    model.result("pg9").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg9").run();
    model.result("pg9").feature("lngr4").set("data", "dset4");
    model.result("pg9").feature("lngr4").set("expr", "u");
    model.result("pg9").feature("lngr4").setIndex("legends", "\u9690\u5f0f\u68af\u5ea6\uff0c\u7ebf\u6027", 0);
    model.result("pg9").feature("lngr4").set("markerpos", "datapoints");
    model.result("pg9").run();
    model.result("pg9").set("axislimits", true);
    model.result("pg9").set("xmin", 40);
    model.result("pg9").set("xmax", 60);
    model.result("pg9").set("legendpos", "middleright");
    model.result("pg9").run();
    model.result("pg3").run();
    model.result("pg3").feature("mesh1").active(false);
    model.result("pg3").feature("ann1").active(false);
    model.result("pg3").feature("mesh2").active(false);
    model.result("pg3").feature("ann2").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("mesh1").active(true);
    model.result("pg3").feature("ann1").active(true);
    model.result("pg3").feature("mesh2").active(true);
    model.result("pg3").feature("ann2").active(true);
    model.result("pg4").run();

    model.title("\u5355\u8f74\u62c9\u4f38\u4e2d\u7684\u8106\u6027\u635f\u4f24");

    model
         .description("\u672c\u6559\u7a0b\u901a\u8fc7\u5355\u8f74\u62c9\u4f38\u6d4b\u8bd5\u663e\u793a\u4e24\u4e2a\u8106\u6027\u635f\u4f24\u6a21\u578b\u7684\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("damage_test_bar.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
