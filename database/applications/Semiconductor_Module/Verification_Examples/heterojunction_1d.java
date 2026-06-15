/*
 * heterojunction_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:57 by COMSOL 6.3.0.290. */
public class heterojunction_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/semi", true);

    model.param().set("sign", "1");
    model.param().descr("sign", "\u5916\u52a0\u7535\u538b\u7684\u7b26\u53f7");
    model.param().set("Va", "0[V]");
    model.param().descr("Va", "\u7535\u538b");
    model.param().set("ramp", "1");
    model.param().descr("ramp", "\u8fde\u7eed\u53c2\u6570");
    model.param().set("L", "5[um]");
    model.param().descr("L", "\u957f\u5ea6");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u6e29\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "-L", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 0, 1);
    model.component("comp1").geom("geom1").feature().duplicate("i2", "i1");
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", 0, 0);
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("GaAs");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().set(1);

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("Eg", "1.42[V]");
    model.component("comp1").variable("var1").set("chi", "4.06[V]");
    model.component("comp1").variable("var1").set("er", "13.1");
    model.component("comp1").variable("var1").set("me_star0", "0.067*me_const");
    model.component("comp1").variable("var1").set("mh_star0", "0.48*me_const");
    model.component("comp1").variable("var1").set("Nc0", "2*(2*pi*me_star0*k_B_const*T0/h_const^2)^(3/2)");
    model.component("comp1").variable("var1").set("Nv0", "2*(2*pi*mh_star0*k_B_const*T0/h_const^2)^(3/2)");
    model.component("comp1").variable("var1").set("mun0", "7000[cm^2/(V*s)]");
    model.component("comp1").variable("var1").set("mup0", "300[cm^2/(V*s)]");
    model.component("comp1").variable("var1").set("taun0", "10[ns]");
    model.component("comp1").variable("var1").set("taup0", "10[ns]");
    model.component("comp1").variable("var1").set("Doping", "1e15[1/cm^3]");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("Al_0.25Ga_0.75As");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().set(2);

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("Eg", "1.74[V]");
    model.component("comp1").variable("var2").set("chi", "3.80[V]");
    model.component("comp1").variable("var2").set("er", "12.4");
    model.component("comp1").variable("var2").set("me_star0", "0.088*me_const");
    model.component("comp1").variable("var2").set("mh_star0", "0.56*me_const");
    model.component("comp1").variable("var2").set("Nc0", "2*(2*pi*me_star0*k_B_const*T0/h_const^2)^(3/2)");
    model.component("comp1").variable("var2").set("Nv0", "2*(2*pi*mh_star0*k_B_const*T0/h_const^2)^(3/2)");
    model.component("comp1").variable("var2").set("mun0", "2500[cm^2/(V*s)]");
    model.component("comp1").variable("var2").set("mup0", "150[cm^2/(V*s)]");
    model.component("comp1").variable("var2").set("taun0", "10[ns]");
    model.component("comp1").variable("var2").set("taup0", "10[ns]");
    model.component("comp1").variable("var2").set("Doping", "1e16[1/cm^3]");

    model.component("comp1").physics("semi").prop("LatticeProperties").set("T0", "T0");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "T0");
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 1);
    model.component("comp1").physics("semi").feature("tar1").selection().all();
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc1").selection().set(1);
    model.component("comp1").physics("semi").feature("mc1").set("V0", "sign*Va");
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc2").selection().set(3);
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm1").label("\u65bd\u4e3b\u63ba\u6742\uff08\u5de6\uff09");
    model.component("comp1").physics("semi").feature("adm1").selection().set(1);
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "Doping");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm2").label("\u65bd\u4e3b\u63ba\u6742\uff08\u53f3\uff09");
    model.component("comp1").physics("semi").feature("adm2").selection().set(2);
    model.component("comp1").physics("semi").feature("adm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm2").set("NDc", "Doping");
    model.component("comp1").physics("semi").create("adm3", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm3").label("\u53d7\u4e3b\u63ba\u6742\uff08\u5de6\uff09");
    model.component("comp1").physics("semi").feature("adm3").selection().set(1);
    model.component("comp1").physics("semi").feature("adm3").set("NAc", "Doping");
    model.component("comp1").physics("semi").create("adm4", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm4").label("\u53d7\u4e3b\u63ba\u6742\uff08\u53f3\uff09");
    model.component("comp1").physics("semi").feature("adm4").selection().set(2);
    model.component("comp1").physics("semi").feature("adm4").set("NAc", "Doping");
    model.component("comp1").physics("semi").create("cont2", "Continuity", 0);
    model.component("comp1").physics("semi").feature("cont2").selection().set(2);
    model.component("comp1").physics("semi").feature("cont2").set("HeteroModelSelection", 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor_material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mun", new String[]{"mun0"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mup", new String[]{"mup0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"er"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", new String[]{"Eg"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("chi0", new String[]{"chi"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nc", new String[]{"Nc0"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nv", new String[]{"Nv0"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("SRH", "SRH", "Shockley-Read-Hall_recombination");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taun", new String[]{"taun0"});
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taup", new String[]{"taup0"});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 500);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("n-n\uff1a\u8fde\u7eed\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"semi/adm3", "semi/adm4", "semi/cont2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").setIndex("pname", "sign", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "sign", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "-1 1", 0);
    model.study("std1").feature("stat").setIndex("pname", "Va", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "V", 1);
    model.study("std1").feature("stat").setIndex("pname", "Va", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "V", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0.05,0.05,1)", 1);
    model.study("std1").feature("stat").set("preusesol", "auto");
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1E-10");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("scalemethod", "none");
    model.sol("sol1").feature("v1").feature("comp1_Ne").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_Ne").set("scaleval", "1.0e22");
    model.sol("sol1").feature("v1").feature("comp1_Ph").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_Ph").set("scaleval", "1.0e4");
    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").label("n-n\uff1a\u70ed\u7535\u5b50\u53d1\u5c04");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"semi/adm3", "semi/adm4"});
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("solnum", 1);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").feature("stat").setIndex("pname", "sign", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "sign", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "-1 1", 0);
    model.study("std2").feature("stat").setIndex("pname", "Va", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "V", 1);
    model.study("std2").feature("stat").setIndex("pname", "Va", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "V", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0.05,0.05,1)", 1);
    model.study("std2").feature("stat").set("preusesol", "auto");
    model.study("std2").feature("stat").set("usestol", true);
    model.study("std2").feature("stat").set("stol", "1E-10");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("n-n\uff1aCont.");
    model.result().table("tbl1").importData("heterojunction_1d_case1_cont.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("n-n\uff1aTE");
    model.result().table("tbl2").importData("heterojunction_1d_case1_te.txt");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("n-n\uff1aJx \u6bd4\u8f83");
    model.result("pg1").set("data", "none");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "Va (V)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "abs(Jx) (A/cm^2)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linecolor", "black");
    model.result("pg1").feature("tblp1").set("linemarker", "asterisk");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u53c2\u8003 Cont.", 0);
    model.result("pg1").set("ylog", true);
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("table", "tbl2");
    model.result("pg1").feature("tblp2").set("linemarker", "circle");
    model.result("pg1").feature("tblp2").setIndex("legends", "\u53c2\u8003 TE", 0);
    model.result("pg1").run();
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").set("data", "dset1");
    model.result("pg1").feature("ptgr1").selection().set(2);
    model.result("pg1").feature("ptgr1").set("expr", "abs(semi.JX)");
    model.result("pg1").feature("ptgr1").set("unit", "A/cm^2");
    model.result("pg1").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result("pg1").feature("ptgr1").set("linecolor", "green");
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").set("data", "dset2");
    model.result("pg1").feature("ptgr2").set("linecolor", "blue");
    model.result("pg1").run();
    model.result("pg1").run();

    model.study().create("std3");
    model.study("std3").create("semie", "SemiconductorEquilibrium");
    model.study("std3").feature("semie").set("plotgroup", "Default");
    model.study("std3").feature("semie").set("ftplistmethod", "manual");
    model.study("std3").feature("semie").set("solnum", "auto");
    model.study("std3").feature("semie").set("notsolnum", "auto");
    model.study("std3").feature("semie").set("outputmap", new String[]{});
    model.study("std3").feature("semie").set("ngenAUX", "1");
    model.study("std3").feature("semie").set("goalngenAUX", "1");
    model.study("std3").feature("semie").set("ngenAUX", "1");
    model.study("std3").feature("semie").set("goalngenAUX", "1");
    model.study("std3").feature("semie").setSolveFor("/physics/semi", true);
    model.study("std3").feature("semie").set("useadvanceddisable", true);
    model.study("std3").feature("semie").set("disabledphysics", new String[]{"semi/adm1", "semi/adm4"});
    model.study("std3").label("p-n\uff1a\u70ed\u7535\u5b50\u53d1\u5c04");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"semi/adm1", "semi/adm4"});
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").set("sweeptype", "filled");
    model.study("std3").feature("stat").setIndex("pname", "sign", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "", 0);
    model.study("std3").feature("stat").setIndex("pname", "sign", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "-1 1", 0);
    model.study("std3").feature("stat").setIndex("pname", "Va", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "V", 1);
    model.study("std3").feature("stat").setIndex("pname", "Va", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "V", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0.05,0.05,1.7)", 1);
    model.study("std3").feature("stat").set("usestol", true);
    model.study("std3").feature("stat").set("stol", "1E-10");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("p-n\uff1aTE");
    model.result().table("tbl3").importData("heterojunction_1d_case2_te.txt");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("p-n\uff1aJx \u6bd4\u8f83");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Va (V)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "abs(Jx) (A/cm^2)");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("table", "tbl3");
    model.result("pg2").feature("tblp1").set("linestyle", "none");
    model.result("pg2").feature("tblp1").set("linecolor", "black");
    model.result("pg2").feature("tblp1").set("linemarker", "circle");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u53c2\u8003 TE", 0);
    model.result("pg2").set("ylog", true);
    model.result("pg2").run();
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").set("data", "dset3");
    model.result("pg2").feature("ptgr1").selection().set(2);
    model.result("pg2").feature("ptgr1").set("expr", "abs(semi.JX)");
    model.result("pg2").feature("ptgr1").set("unit", "A/cm^2");
    model.result("pg2").feature("ptgr1").set("linecolor", "blue");
    model.result("pg2").run();
    model.result("pg2").run();

    model.component("comp1").physics("semi").prop("Continuation").set("cp", "ramp");
    model.component("comp1").physics("semi").prop("Continuation").set("DopingTrapDensityContinuation", "Interface");
    model.component("comp1").physics("semi").feature("cont2").set("ContinuationType", "Interface");

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std4").label("n-p\uff1a\u70ed\u7535\u5b50\u53d1\u5c04");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"semi/adm2", "semi/adm3"});
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "sign", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "", 0);
    model.study("std4").feature("stat").setIndex("pname", "sign", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "", 0);
    model.study("std4").feature("stat").setIndex("pname", "ramp", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "1e-8 1e-6 1e-4 1e-2  0.1 0.25 0.5 0.75 1", 0);
    model.study("std4").create("stat2", "Stationary");
    model.study("std4").feature("stat2").set("useadvanceddisable", true);
    model.study("std4").feature("stat2").set("disabledphysics", new String[]{"semi/adm2", "semi/adm3"});
    model.study("std4").feature("stat2").set("useparam", true);
    model.study("std4").feature("stat2").set("sweeptype", "filled");
    model.study("std4").feature("stat2").setIndex("pname", "sign", 0);
    model.study("std4").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat2").setIndex("punit", "", 0);
    model.study("std4").feature("stat2").setIndex("pname", "sign", 0);
    model.study("std4").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat2").setIndex("punit", "", 0);
    model.study("std4").feature("stat2").setIndex("plistarr", "1 -1", 0);
    model.study("std4").feature("stat2").setIndex("pname", "Va", 1);
    model.study("std4").feature("stat2").setIndex("plistarr", "", 1);
    model.study("std4").feature("stat2").setIndex("punit", "V", 1);
    model.study("std4").feature("stat2").setIndex("pname", "Va", 1);
    model.study("std4").feature("stat2").setIndex("plistarr", "", 1);
    model.study("std4").feature("stat2").setIndex("punit", "V", 1);
    model.study("std4").feature("stat2").setIndex("plistarr", "range(0.05,0.05,1.7)", 1);
    model.study("std4").feature("stat2").set("preusesol", "auto");
    model.study("std4").feature("stat2").set("usestol", true);
    model.study("std4").feature("stat2").set("stol", "1E-10");
    model.study("std4").showAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("n-p\uff1aTE");
    model.result().table("tbl4").importData("heterojunction_1d_case3_te.txt");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("n-p\uff1aJx \u6bd4\u8f83");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "Va (V)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "abs(Jx) (A/cm^2)");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("table", "tbl4");
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("linecolor", "black");
    model.result("pg3").feature("tblp1").set("linemarker", "circle");
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "\u53c2\u8003 TE", 0);
    model.result("pg3").set("ylog", true);
    model.result("pg3").run();
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("data", "dset5");
    model.result("pg3").feature("ptgr1").selection().set(2);
    model.result("pg3").feature("ptgr1").set("expr", "abs(semi.JX)");
    model.result("pg3").feature("ptgr1").set("unit", "A/cm^2");
    model.result("pg3").feature("ptgr1").set("linecolor", "blue");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("n-n\uff1a\u80fd\u91cf\u56fe");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "last", 1);
    model.result("pg4").setIndex("looplevelinput", "first", 0);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg4").feature("lngr1").set("descr", "\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg4").feature("lngr1").set("unit", "eV");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("linecolor", "blue");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "Ec", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg4").feature("lngr2").set("descr", "\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg4").feature("lngr2").set("linestyle", "dashed");
    model.result("pg4").feature("lngr2").set("linecolor", "black");
    model.result("pg4").feature("lngr2").setIndex("legends", "Efn", 0);
    model.result("pg4").feature().duplicate("lngr3", "lngr2");
    model.result("pg4").run();
    model.result("pg4").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg4").feature("lngr3").set("descr", "\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg4").feature("lngr3").set("linestyle", "dotted");
    model.result("pg4").feature("lngr3").setIndex("legends", "Efp", 0);
    model.result("pg4").feature().duplicate("lngr4", "lngr3");
    model.result("pg4").run();
    model.result("pg4").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg4").feature("lngr4").set("descr", "\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg4").feature("lngr4").set("linestyle", "solid");
    model.result("pg4").feature("lngr4").set("linecolor", "green");
    model.result("pg4").feature("lngr4").setIndex("legends", "Ev", 0);
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u80fd\u91cf (eV)");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("p-n\uff1a\u80fd\u91cf\u56fe");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{21}, 0);
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("n-p\uff1a\u80fd\u91cf\u56fe");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").run();

    model.title("\u5f02\u8d28\u7ed3 - \u4e00\u7ef4");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u6a21\u62df\u4e09\u79cd\u4e0d\u540c\u6784\u578b\u7684\u5f02\u8d28\u7ed3\u5728\u6b63\u5411\u548c\u53cd\u5411\u504f\u538b\u4e0b\u7684\u7279\u6027\uff0c\u663e\u793a\u4e86\u4f7f\u7528\u8fde\u7eed\u51c6\u8d39\u7c73\u80fd\u7ea7\u516c\u5f0f\u4e0e\u70ed\u7535\u5b50\u53d1\u5c04\u516c\u5f0f\u8ba1\u7b97\u5f02\u8d28\u7ed3\u4e2d\u7684\u7535\u8377\u8f6c\u79fb\u7684\u5dee\u5f02\uff0c\u901a\u8fc7\u6a21\u62df\u6bcf\u79cd\u6784\u578b\u7684\u80fd\u7ea7\u5e76\u8fdb\u884c\u6bd4\u8f83\uff0c\u9610\u660e\u4e86\u7535\u8377\u8f6c\u79fb\u7684\u8d77\u6e90\uff0c\u5373\uff0c\u4e3b\u8981\u6e90\u81ea\u4ef7\u5e26\u4e2d\u7684\u7a7a\u7a74\u8fd8\u662f\u5bfc\u5e26\u4e2d\u7684\u7535\u5b50\u3002\u672c\u4f8b\u5c06\u9488\u5bf9\u6bcf\u79cd\u6784\u578b\u8ba1\u7b97\u7684 I-V \u66f2\u7ebf\u4e0e\u6587\u732e\u7ed3\u679c\u8fdb\u884c\u4e86\u6bd4\u8f83\uff1b\u5e76\u5728\u5404\u4e2a\u7814\u7a76\u6b65\u9aa4\u7684\u8bbe\u7f6e\u8fc7\u7a0b\u4e2d\u6f14\u793a\u4e86\u63d0\u9ad8\u6536\u655b\u6027\u7684\u591a\u79cd\u65b9\u6cd5\u3002");

    model.label("heterojunction_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
