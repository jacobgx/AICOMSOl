/*
 * wetting_and_drying_of_unsaturated_soil.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:17 by COMSOL 6.3.0.290. */
public class wetting_and_drying_of_unsaturated_soil {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("para", "0");
    model.param().descr("para", "\u53c2\u6570");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "InitSuction");
    model.func("int1").label("\u521d\u59cb\u5438\u529b\u8def\u5f84");
    model.func("int1").set("table", new String[][]{{"0", "0.2"}, {"9", "0.2"}, {"10", "1e-6"}, {"19", "1e-6"}});
    model.func("int1").setIndex("argunit", 1, 0);
    model.func("int1").setIndex("fununit", "MPa", 0);
    model.func().create("int2", "Interpolation");
    model.func("int2").set("funcname", "Suction");
    model.func("int2").label("\u5438\u529b\u8def\u5f84");
    model.func("int2")
         .set("table", new String[][]{{"0", "0.2"}, 
         {"1", "1e-6"}, 
         {"2", "1e-6"}, 
         {"3", "0.2"}, 
         {"4", "0.2"}, 
         {"5", "1e-6"}, 
         {"6", "1e-6"}, 
         {"7", "0.2"}, 
         {"8", "0.2"}, 
         {"9", "1e-6"}, 
         {"10", "1e-6"}, 
         {"11", "1e-6"}, 
         {"12", "0.2"}, 
         {"13", "1e-6"}, 
         {"14", "0.2"}, 
         {"15", "0.2"}, 
         {"16", "1e-6"}, 
         {"17", "0.1"}, 
         {"18", "0.1"}, 
         {"19", "0.2"}});
    model.func("int2").setIndex("argunit", 1, 0);
    model.func("int2").setIndex("fununit", "MPa", 0);
    model.func().create("int3", "Interpolation");
    model.func("int3").set("funcname", "Pressure");
    model.func("int3").label("\u5e73\u5747\u5e94\u529b\u8def\u5f84");
    model.func("int3")
         .set("table", new String[][]{{"0", "0.15"}, 
         {"1", "0.15"}, 
         {"2", "0.6"}, 
         {"3", "0.15"}, 
         {"4", "0.35"}, 
         {"5", "0.35"}, 
         {"6", "0.6"}, 
         {"7", "0.15"}, 
         {"8", "0.6"}, 
         {"9", "0.6"}, 
         {"10", "0.15"}, 
         {"11", "0.6"}, 
         {"12", "0.6"}, 
         {"13", "0.15"}, 
         {"14", "0.15"}, 
         {"15", "0.6"}, 
         {"16", "0.15"}, 
         {"17", "0.15"}, 
         {"18", "0.6"}, 
         {"19", "0.6"}});
    model.func("int3").setIndex("argunit", 1, 0);
    model.func("int3").setIndex("fununit", "MPa", 0);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"10[cm]", "10[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "10[cm]", 2);
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(8);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u571f\u58e4\u6750\u6599");

    model.component("comp1").physics("solid").create("epsm1", "ElastoplasticSoilMaterial", 3);
    model.component("comp1").physics("solid").feature("epsm1").selection().set(1);
    model.component("comp1").physics("solid").feature("epsm1").set("MaterialModel", "BarcelonaBasic");
    model.component("comp1").physics("solid").feature("epsm1").set("CamClayOption", "G");
    model.component("comp1").physics("solid").feature("epsm1").set("ss0", "InitSuction(para)");
    model.component("comp1").physics("solid").feature("epsm1").set("ss", "Suction(para)");
    model.component("comp1").physics("solid").feature("epsm1").set("pref", "0.1[MPa]");
    model.component("comp1").physics("solid").feature("epsm1").set("pc0", "0.2[MPa]");

    model.component("comp1").material("mat1").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("KG").set("G", new String[]{"10[MPa]"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("MohrCoulomb", "MohrCoulomb", "Mohr_Coulomb_criterion");
    model.component("comp1").material("mat1").propertyGroup("MohrCoulomb")
         .set("internalphi", new String[]{"25.4[deg]"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("BarcelonaBasicModel", "BarcelonaBasicModel", "Barcelona_Basic");
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel")
         .set("kappaSwelling", new String[]{"0.02"});
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel")
         .set("kappaSwellings", new String[]{"0.008"});
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel")
         .set("lambdaComp0", new String[]{"0.2"});
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel")
         .set("lambdaCompss", new String[]{"0.08"});
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel").set("wB", new String[]{"0.75"});
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel")
         .set("mB", new String[]{"0.5[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel").set("bB", new String[]{"100"});
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel").set("kB", new String[]{"0.6"});
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel")
         .set("evoidref0", new String[]{"0.9"});
    model.component("comp1").material("mat1").propertyGroup("BarcelonaBasicModel")
         .set("sy0", new String[]{"0.3[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2400[kg/m^3]"});

    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 2, 3);
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(4, 5, 6);
    model.component("comp1").physics("solid").feature("disp1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-disp", 2);
    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "disp", 0, 0);
    model.component("comp1").physics("solid").feature("ge1")
         .setIndex("equation", "(intop1(solid.pm)-Pressure(para))*1e5", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "displacement");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "pressure");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u6da6\u6e7f\u8def\u5f84 ABDF");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").label("\u7a33\u6001\uff1a\u8def\u5f84 AB");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.02,1)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").label("\u7a33\u6001\uff1a\u8def\u5f84 BDF");
    model.study("std1").feature("stat2").set("useinitsol", true);
    model.study("std1").feature("stat2").set("initmethod", "sol");
    model.study("std1").feature("stat2").set("initstudy", "std1");
    model.study("std1").feature("stat2").set("solnum", "last");
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "para", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "", 0);
    model.study("std1").feature("stat2").setIndex("pname", "para", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "range(1,0.005,2)", 0);
    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").copySolution("sol2");
    model.sol("sol1").runFromTo("st2", "s2");

    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 AB");
    model.result().dataset("cpt1").set("pointx", 0.05);
    model.result().dataset("cpt1").set("pointy", 0.05);
    model.result().dataset("cpt1").set("pointz", 0.05);
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 BDF");
    model.result().dataset("cpt2").set("data", "dset1");
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u52a0\u8f7d\u8def\u5f84");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u5438\u529b vs. \u5e73\u5747\u5e94\u529b");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0.1);
    model.result("pg1").set("xmax", 0.65);
    model.result("pg1").set("ymin", -0.01);
    model.result("pg1").set("ymax", 0.21);
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").label("\u8def\u5f84 AB");
    model.result("pg1").feature("ptgr1").set("data", "cpt1");
    model.result("pg1").feature("ptgr1").set("expr", "solid.ss");
    model.result("pg1").feature("ptgr1").set("descr", "\u5438\u529b");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "solid.pmGp");
    model.result("pg1").feature("ptgr1").set("xdatadescr", "\u538b\u529b");
    model.result("pg1").feature("ptgr1").set("linecolor", "blue");
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").label("\u8def\u5f84 BDF");
    model.result("pg1").feature("ptgr2").set("data", "cpt2");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6da6\u6e7f\u8def\u5f84");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6bd4\u5bb9 vs.\u5e73\u5747\u5e94\u529b");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").set("data", "cpt1");
    model.result("pg2").feature("ptgr1").label("\u8def\u5f84 AB");
    model.result("pg2").feature("ptgr1").set("expr", "solid.epsm1.v");
    model.result("pg2").feature("ptgr1").set("descr", "\u6bd4\u5bb9");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "solid.pmGp");
    model.result("pg2").feature("ptgr1").set("xdatadescr", "\u538b\u529b");
    model.result("pg2").feature("ptgr1").set("linecolor", "red");
    model.result("pg2").feature("ptgr1").set("linemarker", "asterisk");
    model.result("pg2").feature("ptgr1").set("markerpos", "interp");
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("data", "cpt2");
    model.result("pg2").feature("ptgr2").label("\u8def\u5f84 BDF");
    model.result("pg2").feature("ptgr2").set("legend", true);
    model.result("pg2").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr2").setIndex("legends", "ABDF", 0);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u6da6\u6e7f\u8def\u5f84 ACDF");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").label("\u7a33\u6001\uff1a\u8def\u5f84 AC");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(3,0.005,4)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study("std2").create("stat2", "Stationary");
    model.study("std2").feature("stat2").label("\u7a33\u6001\uff1a\u8def\u5f84 CD");
    model.study("std2").feature("stat2").set("useinitsol", true);
    model.study("std2").feature("stat2").set("initmethod", "sol");
    model.study("std2").feature("stat2").set("initstudy", "std2");
    model.study("std2").feature("stat2").set("solnum", "last");
    model.study("std2").feature("stat2").set("useparam", true);
    model.study("std2").feature("stat2").setIndex("pname", "para", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat2").setIndex("punit", "", 0);
    model.study("std2").feature("stat2").setIndex("pname", "para", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat2").setIndex("punit", "", 0);
    model.study("std2").feature("stat2").setIndex("plistarr", "range(4,0.01,5)", 0);
    model.study("std2").createAutoSequences("sol");

    model.sol("sol3").copySolution("sol4");
    model.sol("sol3").runFromTo("st2", "s2");

    model.study("std2").create("stat3", "Stationary");
    model.study("std2").feature("stat3").label("\u7a33\u6001\uff1a\u8def\u5f84 DF");
    model.study("std2").feature("stat3").set("useinitsol", true);
    model.study("std2").feature("stat3").set("initmethod", "sol");
    model.study("std2").feature("stat3").set("initstudy", "std2");
    model.study("std2").feature("stat3").set("solnum", "last");
    model.study("std2").feature("stat3").set("useparam", true);
    model.study("std2").feature("stat3").setIndex("pname", "para", 0);
    model.study("std2").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat3").setIndex("punit", "", 0);
    model.study("std2").feature("stat3").setIndex("pname", "para", 0);
    model.study("std2").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat3").setIndex("punit", "", 0);
    model.study("std2").feature("stat3").setIndex("plistarr", "range(5,0.005,6)", 0);
    model.study("std2").createAutoSequences("sol");

    model.sol("sol3").copySolution("sol5");
    model.sol("sol3").runFromTo("st3", "s3");

    model.result().dataset().duplicate("cpt3", "cpt1");
    model.result().dataset("cpt3").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 AC");
    model.result().dataset("cpt3").set("data", "dset4");
    model.result().dataset().duplicate("cpt4", "cpt2");
    model.result().dataset("cpt4").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 CD");
    model.result().dataset("cpt4").set("data", "dset5");
    model.result().dataset().duplicate("cpt5", "cpt4");
    model.result().dataset("cpt5").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 DF");
    model.result().dataset("cpt5").set("data", "dset3");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr3").label("\u8def\u5f84 AC");
    model.result("pg1").feature("ptgr3").set("data", "cpt3");
    model.result("pg1").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr4").label("\u8def\u5f84 CD");
    model.result("pg1").feature("ptgr4").set("data", "cpt4");
    model.result("pg1").feature().duplicate("ptgr5", "ptgr4");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr5").label("\u8def\u5f84 DF");
    model.result("pg1").feature("ptgr5").set("data", "cpt5");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").label("\u8def\u5f84 AC");
    model.result("pg2").feature("ptgr3").set("data", "cpt3");
    model.result("pg2").feature("ptgr3").set("linecolor", "green");
    model.result("pg2").feature("ptgr3").set("linemarker", "circle");
    model.result("pg2").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").label("\u8def\u5f84 CD");
    model.result("pg2").feature("ptgr4").set("data", "cpt4");
    model.result("pg2").feature().duplicate("ptgr5", "ptgr4");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr5").label("\u8def\u5f84 DF");
    model.result("pg2").feature("ptgr5").set("data", "cpt5");
    model.result("pg2").feature("ptgr5").set("legend", true);
    model.result("pg2").feature("ptgr5").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr5").setIndex("legends", "ACDF", 0);

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u7814\u7a76\uff1a\u6da6\u6e7f\u8def\u5f84 ACEF");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").label("\u7a33\u6001\uff1a\u8def\u5f84 ACE");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "para", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "", 0);
    model.study("std3").feature("stat").setIndex("pname", "para", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(7,0.005,8)", 0);
    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.study("std3").create("stat2", "Stationary");
    model.study("std3").feature("stat2").label("\u7a33\u6001\uff1a\u8def\u5f84 EF");
    model.study("std3").feature("stat2").set("useinitsol", true);
    model.study("std3").feature("stat2").set("initmethod", "sol");
    model.study("std3").feature("stat2").set("initstudy", "std3");
    model.study("std3").feature("stat2").set("solnum", "last");
    model.study("std3").feature("stat2").set("useparam", true);
    model.study("std3").feature("stat2").setIndex("pname", "para", 0);
    model.study("std3").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat2").setIndex("punit", "", 0);
    model.study("std3").feature("stat2").setIndex("pname", "para", 0);
    model.study("std3").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat2").setIndex("punit", "", 0);
    model.study("std3").feature("stat2").setIndex("plistarr", "range(8,0.01,9)", 0);
    model.study("std3").createAutoSequences("sol");

    model.sol("sol6").copySolution("sol7");
    model.sol("sol6").runFromTo("st2", "s2");

    model.result().dataset().duplicate("cpt6", "cpt3");
    model.result().dataset("cpt6").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 ACE");
    model.result().dataset("cpt6").set("data", "dset7");
    model.result().dataset().duplicate("cpt7", "cpt6");
    model.result().dataset("cpt7").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 EF");
    model.result().dataset("cpt7").set("data", "dset6");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr6", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr6").label("\u8def\u5f84 ACE");
    model.result("pg1").feature("ptgr6").set("data", "cpt6");
    model.result("pg1").feature().duplicate("ptgr7", "ptgr6");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr7").label("\u8def\u5f84 EF");
    model.result("pg1").feature("ptgr7").set("data", "cpt7");
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.15, 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.2, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "A", 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.15, 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "B", 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.35, 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.2, 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "C", 2, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.35, 3, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 3, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "D", 3, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.6, 4, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.2, 4, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "E", 4, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.6, 5, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 5, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "F", 5, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.15, 6, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.1, 6, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "G", 6, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.6, 7, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.1, 7, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "H", 7, 2);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr6", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr6").label("\u8def\u5f84 ACE");
    model.result("pg2").feature("ptgr6").set("data", "cpt6");
    model.result("pg2").feature("ptgr6").set("linecolor", "blue");
    model.result("pg2").feature("ptgr6").set("linemarker", "diamond");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr7", "ptgr2");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr7").label("\u8def\u5f84 EF");
    model.result("pg2").feature("ptgr7").set("data", "cpt7");
    model.result("pg2").feature("ptgr7").set("linecolor", "blue");
    model.result("pg2").feature("ptgr7").set("linemarker", "diamond");
    model.result("pg2").feature("ptgr7").setIndex("legends", "ACEF", 0);
    model.result("pg2").run();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").label("\u7814\u7a76\uff1a\u5e72\u71e5\u8def\u5f84 BFE");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("stat").label("\u7a33\u6001\uff1a\u8def\u5f84 BF");
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "para", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "", 0);
    model.study("std4").feature("stat").setIndex("pname", "para", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "range(10,0.005,11)", 0);
    model.study("std4").createAutoSequences("all");

    model.sol("sol8").runAll();

    model.study("std4").create("stat2", "Stationary");
    model.study("std4").feature("stat2").label("\u7a33\u6001\uff1a\u8def\u5f84 FE");
    model.study("std4").feature("stat2").set("useinitsol", true);
    model.study("std4").feature("stat2").set("initmethod", "sol");
    model.study("std4").feature("stat2").set("initstudy", "std4");
    model.study("std4").feature("stat2").set("solnum", "last");
    model.study("std4").feature("stat2").set("useparam", true);
    model.study("std4").feature("stat2").setIndex("pname", "para", 0);
    model.study("std4").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat2").setIndex("punit", "", 0);
    model.study("std4").feature("stat2").setIndex("pname", "para", 0);
    model.study("std4").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat2").setIndex("punit", "", 0);
    model.study("std4").feature("stat2").setIndex("plistarr", "range(11,0.01,12)", 0);
    model.study("std4").createAutoSequences("sol");

    model.sol("sol8").copySolution("sol9");
    model.sol("sol8").runFromTo("st2", "s2");

    model.result().dataset().duplicate("cpt8", "cpt1");
    model.result().dataset("cpt8").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 BF");
    model.result().dataset("cpt8").set("data", "dset9");
    model.result().dataset().duplicate("cpt9", "cpt2");
    model.result().dataset("cpt9").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 FE");
    model.result().dataset("cpt9").set("data", "dset8");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e72\u71e5\u8def\u5f84");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6bd4\u5bb9 vs.\u5e73\u5747\u5e94\u529b");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").label("\u8def\u5f84 BF");
    model.result("pg3").feature("ptgr1").set("data", "cpt8");
    model.result("pg3").feature("ptgr1").set("expr", "solid.epsm1.v");
    model.result("pg3").feature("ptgr1").set("descr", "\u6bd4\u5bb9");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "solid.pmGp");
    model.result("pg3").feature("ptgr1").set("xdatadescr", "\u538b\u529b");
    model.result("pg3").feature("ptgr1").set("linecolor", "red");
    model.result("pg3").feature("ptgr1").set("linemarker", "asterisk");
    model.result("pg3").feature("ptgr1").set("markerpos", "interp");
    model.result("pg3").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").label("\u8def\u5f84 FE");
    model.result("pg3").feature("ptgr2").set("data", "cpt9");
    model.result("pg3").feature("ptgr2").set("legend", true);
    model.result("pg3").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr2").setIndex("legends", "\u8def\u5f84 BFE", 0);

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std5").label("\u7814\u7a76\uff1a\u5e72\u71e5\u8def\u5f84 BAE");
    model.study("std5").setGenPlots(false);
    model.study("std5").feature("stat").label("\u7a33\u6001\uff1a\u8def\u5f84 BA");
    model.study("std5").feature("stat").set("useparam", true);
    model.study("std5").feature("stat").setIndex("pname", "para", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "", 0);
    model.study("std5").feature("stat").setIndex("punit", "", 0);
    model.study("std5").feature("stat").setIndex("pname", "para", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "", 0);
    model.study("std5").feature("stat").setIndex("punit", "", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "range(13,0.01,14)", 0);
    model.study("std5").createAutoSequences("all");

    model.sol("sol10").runAll();

    model.study("std5").create("stat2", "Stationary");
    model.study("std5").feature("stat2").label("\u7a33\u6001\uff1a\u8def\u5f84 AE");
    model.study("std5").feature("stat2").set("useinitsol", true);
    model.study("std5").feature("stat2").set("initmethod", "sol");
    model.study("std5").feature("stat2").set("initstudy", "std5");
    model.study("std5").feature("stat2").set("solnum", "last");
    model.study("std5").feature("stat2").set("useparam", true);
    model.study("std5").feature("stat2").setIndex("pname", "para", 0);
    model.study("std5").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std5").feature("stat2").setIndex("punit", "", 0);
    model.study("std5").feature("stat2").setIndex("pname", "para", 0);
    model.study("std5").feature("stat2").setIndex("plistarr", "", 0);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std5").feature("stat2").setIndex("punit", "", 0);
    model.study("std5").feature("stat2").setIndex("plistarr", "range(14,0.005,15)", 0);
    model.study("std5").createAutoSequences("sol");

    model.sol("sol10").copySolution("sol11");
    model.sol("sol10").runFromTo("st2", "s2");

    model.result().dataset().duplicate("cpt10", "cpt1");
    model.result().dataset("cpt10").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 BA");
    model.result().dataset("cpt10").set("data", "dset11");
    model.result().dataset().duplicate("cpt11", "cpt2");
    model.result().dataset("cpt11").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 AE");
    model.result().dataset("cpt11").set("data", "dset10");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr3", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").label("\u8def\u5f84 BA");
    model.result("pg3").feature("ptgr3").set("data", "cpt10");
    model.result("pg3").feature("ptgr3").set("linecolor", "green");
    model.result("pg3").feature("ptgr3").set("linemarker", "circle");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr4", "ptgr2");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr4").label("\u8def\u5f84 AE");
    model.result("pg3").feature("ptgr4").set("data", "cpt11");
    model.result("pg3").feature("ptgr4").set("linecolor", "green");
    model.result("pg3").feature("ptgr4").set("linemarker", "circle");
    model.result("pg3").feature("ptgr4").setIndex("legends", "\u8def\u5f84 BAE", 0);

    model.study().create("std6");
    model.study("std6").create("stat", "Stationary");
    model.study("std6").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std6").label("\u7814\u7a76\uff1a\u5e72\u71e5\u8def\u5f84 BGHE");
    model.study("std6").setGenPlots(false);
    model.study("std6").feature("stat").label("\u7a33\u6001\uff1a\u8def\u5f84 BG");
    model.study("std6").feature("stat").set("useparam", true);
    model.study("std6").feature("stat").setIndex("pname", "para", 0);
    model.study("std6").feature("stat").setIndex("plistarr", "", 0);
    model.study("std6").feature("stat").setIndex("punit", "", 0);
    model.study("std6").feature("stat").setIndex("pname", "para", 0);
    model.study("std6").feature("stat").setIndex("plistarr", "", 0);
    model.study("std6").feature("stat").setIndex("punit", "", 0);
    model.study("std6").feature("stat").setIndex("plistarr", "range(16,0.01,17)", 0);
    model.study("std6").createAutoSequences("all");

    model.sol("sol12").runAll();

    model.study("std6").create("stat2", "Stationary");
    model.study("std6").feature("stat2").label("\u7a33\u6001\uff1a\u8def\u5f84 GH");
    model.study("std6").feature("stat2").set("useinitsol", true);
    model.study("std6").feature("stat2").set("initmethod", "sol");
    model.study("std6").feature("stat2").set("initstudy", "std6");
    model.study("std6").feature("stat2").set("solnum", "last");
    model.study("std6").feature("stat2").set("useparam", true);
    model.study("std6").feature("stat2").setIndex("pname", "para", 0);
    model.study("std6").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std6").feature("stat2").setIndex("punit", "", 0);
    model.study("std6").feature("stat2").setIndex("pname", "para", 0);
    model.study("std6").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std6").feature("stat2").setIndex("punit", "", 0);
    model.study("std6").feature("stat2").setIndex("plistarr", "range(17,0.005,18)", 0);
    model.study("std6").createAutoSequences("sol");

    model.sol("sol12").copySolution("sol13");
    model.sol("sol12").runFromTo("st2", "s2");

    model.study("std6").create("stat3", "Stationary");
    model.study("std6").feature("stat3").label("\u7a33\u6001\uff1a\u8def\u5f84 HE");
    model.study("std6").feature("stat3").set("useinitsol", true);
    model.study("std6").feature("stat3").set("initmethod", "sol");
    model.study("std6").feature("stat3").set("initstudy", "std6");
    model.study("std6").feature("stat3").set("solnum", "last");
    model.study("std6").feature("stat3").set("useparam", true);
    model.study("std6").feature("stat3").setIndex("pname", "para", 0);
    model.study("std6").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std6").feature("stat3").setIndex("punit", "", 0);
    model.study("std6").feature("stat3").setIndex("pname", "para", 0);
    model.study("std6").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std6").feature("stat3").setIndex("punit", "", 0);
    model.study("std6").feature("stat3").setIndex("plistarr", "range(18,0.01,19)", 0);
    model.study("std6").createAutoSequences("sol");

    model.sol("sol12").copySolution("sol14");
    model.sol("sol12").runFromTo("st3", "s3");

    model.result().dataset().duplicate("cpt12", "cpt1");
    model.result().dataset("cpt12").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 BG");
    model.result().dataset("cpt12").set("data", "dset13");
    model.result().dataset().duplicate("cpt13", "cpt2");
    model.result().dataset("cpt13").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 GH");
    model.result().dataset("cpt13").set("data", "dset14");
    model.result().dataset().duplicate("cpt14", "cpt13");
    model.result().dataset("cpt14").label("\u4e09\u7ef4\u622a\u70b9\uff1a\u8def\u5f84 HE");
    model.result().dataset("cpt14").set("data", "dset12");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr5", "ptgr3");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr5").label("\u8def\u5f84 BG");
    model.result("pg3").feature("ptgr5").set("data", "cpt12");
    model.result("pg3").feature("ptgr5").set("linecolor", "blue");
    model.result("pg3").feature("ptgr5").set("linemarker", "diamond");
    model.result("pg3").feature().duplicate("ptgr6", "ptgr5");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr6").label("\u8def\u5f84 GH");
    model.result("pg3").feature("ptgr6").set("data", "cpt13");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr7", "ptgr4");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr7").label("\u8def\u5f84 HE");
    model.result("pg3").feature("ptgr7").set("data", "cpt14");
    model.result("pg3").feature("ptgr7").set("linecolor", "blue");
    model.result("pg3").feature("ptgr7").set("linemarker", "diamond");
    model.result("pg3").feature("ptgr7").setIndex("legends", "\u8def\u5f84 BGHE", 0);
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr8", "ptgr7");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr8").label("\u8def\u5f84 GH");
    model.result("pg1").feature("ptgr8").set("data", "cpt13");
    model.result("pg1").feature().move("ptgr8", 7);
    model.result("pg1").run();
    model.result("pg2").run();

    model
         .title("\u4f7f\u7528\u6269\u5c55\u5df4\u585e\u7f57\u90a3\u57fa\u672c\u6a21\u578b\u6a21\u62df\u90e8\u5206\u9971\u548c\u571f\u7684\u5e72\u6e7f\u8def\u5f84");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u5bf9\u5e72\u6e7f\u571f\u6837\u8fdb\u884c\u5b9e\u9a8c\uff0c\u4e86\u89e3\u571f\u58e4\u5728\u4e0d\u540c\u8f7d\u8377\u6761\u4ef6\u4e0b\u7684\u7279\u6027\u3002\u5176\u4e2d\u4f7f\u7528\u6269\u5c55\u5df4\u585e\u7f57\u90a3\u57fa\u672c (BBMx) \u571f\u58e4\u6a21\u578b\u6765\u6a21\u62df\u90e8\u5206\u9971\u548c\u571f\u6837\u5728\u5faa\u73af\u8f7d\u8377\u4f5c\u7528\u4e0b\u7684\u5e72\u6e7f\u8def\u5f84\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();

    model.label("wetting_and_drying_of_unsaturated_soil.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
