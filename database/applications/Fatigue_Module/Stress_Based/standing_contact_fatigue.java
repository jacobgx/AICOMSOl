/*
 * standing_contact_fatigue.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:03 by COMSOL 6.3.0.290. */
public class standing_contact_fatigue {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Stress_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("H", "5[mm]");
    model.param().descr("H", "\u6a21\u578b\u9ad8\u5ea6");
    model.param().set("dH", "0.5[mm]");
    model.param().descr("dH", "\u5916\u58f3\u6df1\u5ea6");
    model.param().set("dT", "0.1[mm]");
    model.param().descr("dT", "\u8fc7\u6e21\u6df1\u5ea6");
    model.param().set("W", "5[mm]");
    model.param().descr("W", "\u6a21\u578b\u5bbd\u5ea6");
    model.param().set("dW", "0.7[mm]");
    model.param().descr("dW", "\u7ec6\u5316\u533a\u5bbd\u5ea6");
    model.param().set("P", "384[N]");
    model.param().descr("P", "\u6700\u5927\u8f7d\u8377");
    model.param().set("E", "200[GPa]");
    model.param().descr("E", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nu", "0.30");
    model.param().descr("nu", "\u6cca\u677e\u6bd4");
    model.param().set("rho", "7800[kg/m^3]");
    model.param().descr("rho", "\u5bc6\u5ea6");
    model.param().set("Es", "E/(2*(1-nu^2))");
    model.param().descr("Es", "\u8d6b\u5179\u63a5\u89e6\u521a\u5ea6");
    model.param().set("ri", "7[mm]");
    model.param().descr("ri", "\u538b\u5934\u534a\u5f84");
    model.param().set("sL", "0");
    model.param().descr("sL", "\u8f7d\u8377\u653e\u5927\u955c");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H-dH-dT"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"dW", "dT"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "H-dH-dT"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"W-dW", "dT"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"dW", "H-dH-dT"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"dW", "dH"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "H-dH"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"W-dW", "dH"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"dW", "H-dH"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl("intop1").set("frame", "material");

    model.component("comp1").physics("solid").feature("lemm1").set("E_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("E", "E");
    model.component("comp1").physics("solid").feature("lemm1").set("nu_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("nu", "nu");
    model.component("comp1").physics("solid").feature("lemm1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("rho", "rho");
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").selection().set(3, 5);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").set("sigmags_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").set("sigmags", "570[MPa]");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").set("Et_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").set("Et", "69[GPa]");
    model.component("comp1").physics("solid").feature("lemm1").create("plsty2", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").label("\u5851\u6027\u5916\u58f3");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").label("\u5851\u6027\u82af");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").selection().set(1);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").set("sigmags_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").set("sigmags", "515[MPa]");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2")
         .set("IsotropicHardeningModel", "Ludwik");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").set("k_lud_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").set("k_lud", "2.47[GPa]");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").set("n_lud_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").set("n_lud", 0.55);

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u8fc7\u6e21\u51fd\u6570");
    model.func("int1").setIndex("table", "H-dH-dT", 0, 0);
    model.func("int1").setIndex("table", 1, 0, 1);
    model.func("int1").setIndex("table", "H-dH", 1, 0);
    model.func("int1").setIndex("table", 0, 1, 1);
    model.func("int1").setIndex("argunit", "m", 0);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("s", "int1(z)");
    model.component("comp1").variable("var1").descr("s", "\u8fc7\u6e21\u5c42\u4f4d\u7f6e");
    model.component("comp1").variable("var1").set("nT", "1-0.45*s");
    model.component("comp1").variable("var1").descr("nT", "\u786c\u5316\u6307\u6570\u8fc7\u6e21\u5c42");
    model.component("comp1").variable("var1").set("kT", "10^(-1.62*s+11) [Pa]");
    model.component("comp1").variable("var1").descr("kT", "\u5f3a\u5ea6\u7cfb\u6570\u8fc7\u6e21\u5c42");
    model.component("comp1").variable("var1").set("s0T", "(570e6*(1-s)+515e6*s) [Pa]");
    model.component("comp1").variable("var1").descr("s0T", "\u521d\u59cb\u5c48\u670d\u5e94\u529b\u8fc7\u6e21\u5c42");

    model.component("comp1").physics("solid").feature("lemm1").create("plsty3", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty3").label("\u5851\u6027\u8fc7\u6e21");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty3").selection().set(2, 4);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty3").set("sigmags_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty3").set("sigmags", "s0T");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty3")
         .set("IsotropicHardeningModel", "Ludwik");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty3").set("k_lud_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty3").set("k_lud", "kT");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty3").set("n_lud_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty3").set("n_lud", "nT");

    model.func().create("int2", "Interpolation");
    model.func("int2").label("\u6b8b\u4f59\u5e94\u529b");
    model.func("int2").setIndex("table", 0, 0, 0);
    model.func("int2").setIndex("table", -500, 0, 1);
    model.func("int2").setIndex("table", 1, 1, 0);
    model.func("int2").setIndex("table", 100, 1, 1);
    model.func("int2").setIndex("fununit", "MPa", 0);

    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("Sil", new String[]{"int2(s)", "0", "0", "0", "int2(s)", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2, 13, 14, 15);

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u5468\u671f\u6027\u8f7d\u8377");
    model.func("an1").set("expr", "0.5*(1-cos(x*2*pi))");

    model.component("comp1").variable("var1").set("ai", "(3/4*P*an1(sL)*ri/Es)^(1/3)");
    model.component("comp1").variable("var1").descr("ai", "\u538b\u75d5\u534a\u5f84");
    model.component("comp1").variable("var1").set("p0", "3*P*an1(sL)/(2*pi*ai*ai)");
    model.component("comp1").variable("var1").descr("p0", "\u6700\u5927\u538b\u529b");

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(7);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "if(r<ai,p0*sqrt(1-(r/ai)^2),0)");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 6, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 70);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(5, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(3, 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "H", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "H", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "sL", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0.0,0.025,0.5) range(0.55,0.05,2)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 51, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 51, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 10);
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u5851\u6027\u53d8\u5f62\u4f53\u79ef");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "solid.epeGp");
    model.result("pg3").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").feature("surf1").set("expr", "solid.epeGp>0");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature().remove("def");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u5851\u6027\u5e94\u53d8");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "solid.epeGp");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5851\u6027\u5e94\u53d8\u5386\u53f2");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "intop1(solid.epeGp)", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "mm^3", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u79ef\u5206\u7684\u5851\u6027\u5e94\u53d8", 0);
    model.result("pg5").run();
    model.result("pg1").run();
    model.result().duplicate("pg6", "pg1");
    model.result("pg6").run();
    model.result("pg6").label("\u5cf0\u503c\u8f7d\u8377\u65f6\u7684\u7b49\u6548\u5e94\u529b");
    model.result("pg6").setIndex("looplevel", 41, 0);
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u5cf0\u503c\u8f7d\u8377\u65f6\u7684\u526a\u5207\u5e94\u529b");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "solid.sGprz");
    model.result("pg7").feature("surf1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0crz \u5206\u91cf");
    model.result("pg7").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").create("stre1", "StressBasedModel", 2);
    model.component("comp1").physics("ftg").feature("stre1").selection().all();
    model.component("comp1").physics("ftg").feature("stre1").set("fatigueHCFMultiaxModel", "DangVan");
    model.component("comp1").physics("ftg").feature("stre1").set("fatigueInputPhysics", "solid");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(3, 5);
    model.component("comp1").material("mat1").propertyGroup()
         .create("fatigueStressDangVan", "fatigueStressDangVan", "Dang_Van[Fatigue]");
    model.component("comp1").material("mat1").propertyGroup("fatigueStressDangVan")
         .set("a_DangVan", new String[]{"0.19"});
    model.component("comp1").material("mat1").propertyGroup("fatigueStressDangVan")
         .set("b_DangVan", new String[]{"282[MPa]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(1, 2, 4);
    model.component("comp1").material("mat2").propertyGroup()
         .create("fatigueStressDangVan", "fatigueStressDangVan", "Dang_Van[Fatigue]");
    model.component("comp1").material("mat2").propertyGroup("fatigueStressDangVan")
         .set("a_DangVan", new String[]{"0.23"});
    model.component("comp1").material("mat2").propertyGroup("fatigueStressDangVan")
         .set("b_DangVan", new String[]{"248[MPa]"});

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").feature("ftge").set("notsolnum", "from_list");
    model.study("std2").feature("ftge")
         .set("notlistsolnum", new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"ftg.fus"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colortabletrans", "none");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").set("colortable", "Traffic");
    model.result("pg8").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg)");
    model.result("pg8").feature("surf1").create("mrkr1", "Marker");
    model.result("pg8").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg8").feature("surf1").feature("mrkr1").set("display", "max");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset2");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "rev1");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"ftg.fus"});
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").feature("surf1").set("colortabletrans", "none");
    model.result("pg9").feature("surf1").set("colorscalemode", "linear");
    model.result("pg9").feature("surf1").set("colortable", "Traffic");
    model.result("pg9").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50, 3D (ftg)");
    model.result("pg8").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg8").run();

    model.title("\u957f\u671f\u63a5\u89e6\u75b2\u52b3");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u8868\u9762\u786c\u5316\u6750\u6599\u7684\u957f\u671f\u63a5\u89e6\u75b2\u52b3\uff0c\u901a\u8fc7\u611f\u5e94\u6dec\u706b\u8fc7\u7a0b\u5728\u6750\u6599\u4e2d\u5f15\u5165\u6b8b\u4f59\u5e94\u529b\u3002\u4e09\u4e2a\u4e0d\u540c\u7684\u5c42\u5448\u73b0\u4e86\u4e0d\u540c\u7684\u6750\u6599\u5c5e\u6027\uff0c\u5176\u4e2d\u4f7f\u7528 Dang Van \u6a21\u578b\u6765\u9884\u6d4b\u75b2\u52b3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("standing_contact_fatigue.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
