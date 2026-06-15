/*
 * triaxial_test_hardening_soil_small_strain.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:16 by COMSOL 6.3.0.290. */
public class triaxial_test_hardening_soil_small_strain {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("solid2", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid2", true);

    model.param().set("disp", "0[cm]");
    model.param().descr("disp", "\u4f4d\u79fb\u53c2\u6570");
    model.param().set("para", "0");
    model.param().descr("para", "\u53c2\u6570");
    model.param().set("G_0", "190[MPa]");
    model.param().descr("G_0", "Hostun \u5bc6\u5b9e\u571f\u58e4\u7684\u521d\u59cb\u526a\u5207\u6a21\u91cf");
    model.param().set("a", "0.385");
    model.param().descr("a", "\u6750\u6599\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u571f\u58e4\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("Rho", "2400[kg/m^3]", "\u5bc6\u5ea6");
    model.param("par2").set("Nu", "0.25", "\u6cca\u677e\u6bd4");
    model.param("par2").set("Eiref", "65.488[MPa]", "\u4e3b\u8f7d\u8377\u7684\u53c2\u8003\u521d\u59cb\u521a\u5ea6");
    model.param("par2")
         .set("Eurref", "90[MPa]", "\u7528\u4e8e\u5378\u8f7d\u548c\u91cd\u65b0\u52a0\u8f7d\u7684\u53c2\u8003\u521a\u5ea6");
    model.param("par2").set("e0", "0.65", "\u521d\u59cb\u7a7a\u9699\u6bd4");
    model.param("par2").set("m", "0.55", "\u5e94\u529b\u6307\u6570");
    model.param("par2").set("rc", "1.84", "\u81a8\u80c0\u5230\u538b\u7f29\u6bd4");
    model.param("par2").set("c", "1[kPa]", "\u5185\u805a\u529b");
    model.param("par2").set("Psi", "16[deg]", "\u81a8\u80c0\u89d2");
    model.param("par2").set("Phi", "42[deg]", "\u5185\u6469\u64e6\u89d2");
    model.param("par2").set("Rc", "0.68027", "\u692d\u5706\u957f\u5bbd\u6bd4");
    model.param("par2").set("p0", "300[kPa]", "\u538b\u529b");
    model.param("par2").set("gamma_a", "2e-4", "\u9608\u503c\u526a\u5207\u5e94\u53d8");
    model.param("par2").set("gammaR", "gamma_a/a", "\u53c2\u8003\u526a\u5207\u5e94\u53d8");
    model.param("par2")
         .set("E0ref", "270[MPa]", "\u53c2\u8003\u538b\u529b\u7684\u521d\u59cb\u6768\u6c0f\u6a21\u91cf");
    model.param("par2")
         .set("G0ref", "E0ref/(2*(1+Nu))", "\u53c2\u8003\u538b\u529b\u7684\u521d\u59cb\u526a\u5207\u6a21\u91cf");
    model.param("par2").paramCase().create("case1");
    model.param("par2").paramCase("case1").label("Hostun \u5bc6\u5b9e\u571f\u58e4\u7684\u5c5e\u6027");
    model.param("par2").paramCase().create("case2");
    model.param("par2").paramCase("case2").label("Hostun \u677e\u6563\u571f\u58e4\u7684\u5c5e\u6027");
    model.param("par2").paramCase("case2").set("Rho", "2000[kg/m^3]");
    model.param("par2").paramCase("case2").set("Eiref", "23.8[MPa]");
    model.param("par2").paramCase("case2").set("Eurref", "60[MPa]");
    model.param("par2").paramCase("case2").set("e0", "0.85");
    model.param("par2").paramCase("case2").set("m", "0.75");
    model.param("par2").paramCase("case2").set("rc", "2.01");
    model.param("par2").paramCase("case2").set("Psi", "0[deg]");
    model.param("par2").paramCase("case2").set("Phi", "34[deg]");
    model.param("par2").paramCase("case2").set("Rc", "0.64103");
    model.param("par2").paramCase("case2").set("gamma_a", "1e-4");
    model.param("par2").paramCase("case2").set("E0ref", "168[MPa]");
    model.param("par2").paramCase().create("case3");
    model.param("par2").paramCase("case3").label("\u9ad8\u5cad\u571f\u7684\u5c5e\u6027");
    model.param("par2").paramCase("case3").set("Rho", "1700[kg/m^3]");
    model.param("par2").paramCase("case3").set("Nu", "0.2");
    model.param("par2").paramCase("case3").set("Eiref", "14.05[MPa]");
    model.param("par2").paramCase("case3").set("Eurref", "11.5[MPa]");
    model.param("par2").paramCase("case3").set("e0", "0.9");
    model.param("par2").paramCase("case3").set("m", "0.8");
    model.param("par2").paramCase("case3").set("rc", "4.76");
    model.param("par2").paramCase("case3").set("Psi", "0[deg]");
    model.param("par2").paramCase("case3").set("Phi", "20[deg]");
    model.param("par2").paramCase("case3").set("Rc", "1.2821");
    model.param("par2").paramCase("case3").set("E0ref", "80[MPa]");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "appliedDisp");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 0);
    model.component("comp1").func("int1").setIndex("table", "5e-3", 1, 1);
    model.component("comp1").func("int1").setIndex("table", 2, 2, 0);
    model.component("comp1").func("int1").setIndex("table", "-5e-3", 2, 1);
    model.component("comp1").func("int1").setIndex("table", 3, 3, 0);
    model.component("comp1").func("int1").setIndex("table", "5e-3", 3, 1);
    model.component("comp1").func("int1").setIndex("fununit", "cm", 0);
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"5[cm]", "10[cm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 2});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "20[cm]"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").label("\u56fa\u4f53\u529b\u5b66 [\u5355\u8c03]");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("epsm1", "ElastoplasticSoilMaterial", 2);
    model.component("comp1").physics("solid").feature("epsm1")
         .label("\u786c\u5316\u571f\u5c0f\u5e94\u53d8\uff1a\u83ab\u5c14-\u5e93\u4ed1");
    model.component("comp1").physics("solid").feature("epsm1").selection().set(1);
    model.component("comp1").physics("solid").feature("epsm1").set("MaterialModel", "HardeningSoilSmall");
    model.component("comp1").physics("solid").feature("epsm1").set("EiRef_mat", "from_mat");
    model.component("comp1").physics("solid").feature("epsm1").set("Kc_mat", "FromRatio");
    model.component("comp1").physics("solid").feature("epsm1").set("pref", "p0");
    model.component("comp1").physics("solid").feature("epsm1").set("pc0", "200[MPa]");
    model.component("comp1").physics("solid").feature("epsm1").create("exs1", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("epsm1").feature("exs1")
         .set("StressInputType", "InSituStress");
    model.component("comp1").physics("solid").feature("epsm1").feature("exs1")
         .set("sins", new String[]{"-p0", "0", "0", "0", "-p0", "0", "0", "0", "-p0"});
    model.component("comp1").physics("solid").feature().duplicate("epsm2", "epsm1");
    model.component("comp1").physics("solid").feature("epsm2")
         .label("\u786c\u5316\u571f\u5c0f\u5e94\u53d8\uff1aMatsuoka-Nakai");
    model.component("comp1").physics("solid").feature("epsm2").selection().set(2);
    model.component("comp1").physics("solid").feature("epsm2").set("HardeningSoilOption", "HSSmooth");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2, 5);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(3, 6);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-disp", 2);
    model.component("comp1").physics("solid2").label("\u56fa\u4f53\u529b\u5b66 [\u5faa\u73af]");
    model.component("comp1").physics("solid2").selection().set(1);
    model.component("comp1").physics("solid2").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid2").create("epsm1", "ElastoplasticSoilMaterial", 2);
    model.component("comp1").physics("solid2").feature("epsm1")
         .label("\u786c\u5316\u571f\u5c0f\u5e94\u53d8\uff1aMatsuoka-Nakai");
    model.component("comp1").physics("solid2").feature("epsm1").selection().set(1);
    model.component("comp1").physics("solid2").feature("epsm1").set("MaterialModel", "HardeningSoilSmall");
    model.component("comp1").physics("solid2").feature("epsm1").set("HardeningSoilOption", "HSSmooth");
    model.component("comp1").physics("solid2").feature("epsm1").set("EiRef_mat", "from_mat");
    model.component("comp1").physics("solid2").feature("epsm1").set("G0_mat", "from_mat");
    model.component("comp1").physics("solid2").feature("epsm1").set("Kc_mat", "FromRatio");
    model.component("comp1").physics("solid2").feature("epsm1").set("pref", "p0");
    model.component("comp1").physics("solid2").feature("epsm1").set("pc0", "200[MPa]");
    model.component("comp1").physics("solid2").feature("epsm1").create("exs1", "ExternalStress", 2);
    model.component("comp1").physics("solid2").feature("epsm1").feature("exs1")
         .set("StressInputType", "InSituStress");
    model.component("comp1").physics("solid2").feature("epsm1").feature("exs1")
         .set("sins", new String[]{"-p0", "0", "0", "0", "-p0", "0", "0", "0", "-p0"});
    model.component("comp1").physics("solid2").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid2").feature("roll1").selection().set(2);
    model.component("comp1").physics("solid2").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid2").feature("disp1").selection().set(3);
    model.component("comp1").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid2").feature("disp1").setIndex("U0", "-appliedDisp(para)", 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u571f\u58e4\u6750\u6599");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"Nu"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("HardeningSoilModel", "HardeningSoilModel", "Hardening_soil");
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel")
         .set("EiRef", new String[]{"Eiref"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel")
         .set("EurRef", new String[]{"Eurref"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel")
         .set("G0Ref", new String[]{"G0ref"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel")
         .set("gammaRef", new String[]{"gammaR"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel").set("mH", new String[]{"m"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("MohrCoulomb", "MohrCoulomb", "Mohr_Coulomb_criterion");
    model.component("comp1").material("mat1").propertyGroup("MohrCoulomb").set("cohesion", new String[]{"c"});
    model.component("comp1").material("mat1").propertyGroup("MohrCoulomb").set("psid", new String[]{"Psi"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel").set("rsc", new String[]{"rc"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel").set("Rcap", new String[]{"Rc"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel").set("evoid0", new String[]{"e0"});
    model.component("comp1").material("mat1").propertyGroup("MohrCoulomb").set("internalphi", new String[]{"Phi"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"Rho"});
    model.component("comp1").material("mat1").propertyGroup("HardeningSoilModel").set("G0", new String[]{"G_0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run("map1");

    model.study("std1").label("\u7814\u7a76\uff1a\u5355\u8c03\u4e09\u8f74\u52a0\u8f7d");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "switch");
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std1").feature("param").setIndex("switchname", "par2", 0);
    model.study("std1").feature("stat").setSolveFor("/physics/solid2", false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "a", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "a", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.02,1.2)", 0);
    model.study("std1").feature("stat").setIndex("punit", "cm", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("porder", "constant");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u5faa\u73af\u4e09\u8f74\u52a0\u8f7d");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "a", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "a", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.001,3)", 0);
    model.study("std2").feature("stat").setIndex("punit", 1, 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol6").feature("s1").feature("p1").set("porder", "constant");

    model.study("std2").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"youngsmodulus", "\u6768\u6c0f\u6a21\u91cf", "Pa", "Pa"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u8f74\u5411\u5e94\u529b vs. \u8f74\u5411\u5e94\u53d8\uff08\u5355\u8c03\uff09");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u8f74\u5411\u5e94\u529b vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u8f74\u5411\u5e94\u53d8 (1)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u65e0\u91cf\u7eb2\u8f74\u5411\u5e94\u529b (1)");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").set("legendposoutside", "bottom");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").set("data", "dset2");
    model.result("pg1").feature("ptgr1").setIndex("looplevelinput", "manual", 1);
    model.result("pg1").feature("ptgr1").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg1").feature("ptgr1").selection().set(6);
    model.result("pg1").feature("ptgr1").set("expr", "-solid.SZZ/p0");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "-solid.eZZ");
    model.result("pg1").feature("ptgr1").set("linemarker", "asterisk");
    model.result("pg1").feature("ptgr1").set("markerpos", "interp");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg1").feature("ptgr1").setIndex("legends", "Hostun \u5bc6\u5b9e\u571f\u58e4\uff0cMC", 0);
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").selection().set(8);
    model.result("pg1").feature("ptgr2").set("linemarker", "circle");
    model.result("pg1").feature("ptgr2").set("markers", 10);
    model.result("pg1").feature("ptgr2").setIndex("legends", "Hostun \u5bc6\u5b9e\u571f\u58e4\uff0cMN", 0);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr3").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg1").feature("ptgr3").setIndex("legends", "Hostun \u677e\u6563\u571f\u58e4\uff0cMC", 0);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr4", "ptgr2");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr4").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg1").feature("ptgr4").setIndex("legends", "Hostun \u677e\u6563\u571f\u58e4\uff0cMN", 0);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr5", "ptgr3");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr5").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg1").feature("ptgr5").setIndex("legends", "\u9ad8\u5cad\u571f\uff0cMC", 0);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr6", "ptgr4");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr6").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg1").feature("ptgr6").setIndex("legends", "\u9ad8\u5cad\u571f\uff0cMN", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u4f53\u79ef\u5e94\u53d8 vs. \u8f74\u5411\u5e94\u53d8\uff08\u5355\u8c03\uff09");
    model.result("pg2").set("title", "\u4f53\u79ef\u5e94\u53d8 vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg2").set("ylabel", "\u4f53\u79ef\u5e94\u53d8 (1)");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").set("expr", "solid.evol");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("expr", "solid.evol");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").set("expr", "solid.evol");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").set("expr", "solid.evol");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr5").set("expr", "solid.evol");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr6").set("expr", "solid.evol");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u8f74\u5411\u5e94\u529b vs. \u8f74\u5411\u5e94\u53d8\uff08\u5faa\u73af\uff09");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u8f74\u5411\u5e94\u529b vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u8f74\u5411\u5e94\u53d8 (1)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u8f74\u5411\u5e94\u529b (kPa)");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("data", "dset3");
    model.result("pg3").feature("ptgr1").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg3").feature("ptgr1").setIndex("looplevelindices", "range(1,1,1001)", 0);
    model.result("pg3").feature("ptgr1").selection().set(6);
    model.result("pg3").feature("ptgr1").set("expr", "-(solid2.Sl33+p0)");
    model.result("pg3").feature("ptgr1").set("unit", "kPa");
    model.result("pg3").feature("ptgr1").set("xdata", "expr");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "-solid2.el33");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u521d\u6b21\u52a0\u8f7d", 0);
    model.result("pg3").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").setIndex("looplevelindices", "range(1001,1,3001)", 0);
    model.result("pg3").feature("ptgr2").setIndex("legends", "\u5378\u8f7d\u548c\u91cd\u65b0\u52a0\u8f7d", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u6768\u6c0f\u6a21\u91cf vs. \u8f74\u5411\u5e94\u53d8\uff08\u5faa\u73af\uff09");
    model.result("pg4").set("title", "\u6768\u6c0f\u6a21\u91cf vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg4").set("ylabel", "\u6768\u6c0f\u6a21\u91cf (MPa)");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("expr", "solid2.E");
    model.result("pg4").feature("ptgr1").set("xdataexpr", "abs(solid2.el33)");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").setIndex("looplevelindices", "range(2002,1,3001)", 0);
    model.result("pg4").feature("ptgr2").set("expr", "solid2.E");
    model.result("pg4").feature("ptgr2")
         .set("xdataexpr", "abs(solid2.el33-withsol('sol6',solid2.el33,setval(para,2)))");
    model.result("pg4").feature("ptgr2").setIndex("legends", "\u91cd\u65b0\u52a0\u8f7d", 0);
    model.result("pg4").run();
    model.result("pg4").set("xlog", true);
    model.result("pg4").set("legendpos", "lowerleft");
    model.result("pg4").run();
    model.result("pg4").run();

    model
         .title("\u57fa\u4e8e\u786c\u5316\u571f\u5c0f\u5e94\u53d8\u6750\u6599\u6a21\u578b\u7684\u4e09\u8f74\u8bd5\u9a8c");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u786c\u5316\u571f\u5c0f\u5e94\u53d8\u201d\u6750\u6599\u6a21\u578b\u6765\u6a21\u62df\u5355\u8c03\u548c\u5faa\u73af\u4e09\u8f74\u8bd5\u9a8c\u3002\n\n\u6a21\u578b\u53ef\u4ee5\u6355\u6349\u5faa\u73af\u8f7d\u8377\u4e0b\u5c0f\u5e94\u53d8\u521a\u5ea6\u548c\u6ede\u540e\u7684\u5f71\u54cd\uff0c\u5f97\u5230\u7684\u5e94\u529b-\u5e94\u53d8\u5173\u7cfb\u4e0e\u53c2\u8003\u8d44\u6599\u4e2d\u62a5\u544a\u7684\u53cc\u66f2\u7ebf\u76f8\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("triaxial_test_hardening_soil_small_strain.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
